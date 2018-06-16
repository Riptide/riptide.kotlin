package org.codetome.riptide.messagebus.adapter

import com.rabbitmq.client.BuiltinExchangeType.TOPIC
import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory
import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitConfig
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitMessagingAdapter
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitNamingStrategy
import org.codetome.riptide.messagebus.api.exception.MissingNameException
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.PayloadKind
import org.codetome.riptide.protobuf.ProcessState
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@Suppress("UNCHECKED_CAST")
class RabbitMessagingAdapterTest {

    internal lateinit var target: RabbitMessagingAdapter

    val messageReceiverStub = object : MessageReceiver {
        override fun receive(message: Message<Any>, operation: OperationDescriptor) {
            consumeTestMessage(message as Message<String>)
        }
    }

    lateinit var channel: Channel

    private val consumedMessages = mutableListOf<Message<String>>()
    private val rabbitNamingStrategy = RabbitNamingStrategy()
    private val latch = CountDownLatch(1)

    @Mock
    internal lateinit var processEventServiceMock: ProcessEventService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        channel = createRabbitChannel()
        target = RabbitMessagingAdapter(
                rabbitConfig = RABBIT_CONFIG,
                processEventService = processEventServiceMock)
        target.subscribe(OperationDescriptor(
                name = OPERATION_NAME,
                groupName = GROUP,
                versionName = VERSION,
                sourceClass = String::class.java,
                consumerFn = this@RabbitMessagingAdapterTest::consumeTestMessage as (Message<out Any>) -> Unit,
                dispatchType = OperationDispatchType.CONSUMER))
        target.setMessageReceiver(messageReceiverStub)
    }

    @After
    fun tearDown() {
        channel.exchangeDelete(rabbitNamingStrategy.generateExchangeName(OPERATION_NAME))
        channel.exchangeDelete(rabbitNamingStrategy.generateExchangeName(UNKNOWN_OPERATION))
    }

    @Test
    fun shouldDeclareRabbitExchangeProperly() {
        val exchangeName = rabbitNamingStrategy.generateExchangeName(OPERATION_NAME)
        channel.exchangeDeclare(exchangeName, TOPIC, DURABLE, AUTO_DELETE, mapOf())
    }

    @Test(expected = MissingNameException::class)
    fun shouldThrowExceptionWhenTryingToPublishOperationlessMessage() {
        target.publish(STUB_MESSAGE.copy(operation = ""))
    }

    @Test
    fun shouldSucceedWhenTryingToPublishToUnknownExchange() {
        channel.exchangeDelete(UNKNOWN_OPERATION)

        target.publish(STUB_MESSAGE.copy(operation = UNKNOWN_OPERATION))

        // this shouldReceiveStateMessageWhenListenerIsAddedAndMessageIsSent fail if there was no exchange declared automatically
        target.publish(STUB_MESSAGE.copy(operation = UNKNOWN_OPERATION))
    }

    @Test
    fun shouldReceiveMessageProperlyWhenSubscribedAndPublishIsCalled() {
        target.publish(STUB_MESSAGE)
        latch.await(2, SECONDS)

        assertThat(consumedMessages).hasSize(1)
        assertThat(consumedMessages.first()).isEqualTo(STUB_MESSAGE)
    }

    private fun createRabbitChannel(): Channel {
        val factory = ConnectionFactory()
        factory.host = RABBIT_CONFIG.host
        factory.port = RABBIT_CONFIG.port
        val channel = factory.newConnection().createChannel()
        return channel
    }

    private fun consumeTestMessage(msg: Message<String>) {
        consumedMessages.add(msg)
        latch.countDown()
    }

    companion object {
        val TEST_MESSAGE_TO_CONSUME = "Foo"
        val OPERATION_NAME = "OPERATION_NAME"
        val UNKNOWN_OPERATION = "fubar"
        val GROUP = "GROUP"
        val VERSION = "VERSION"
        val PAYLOAD_TYPE: String = String::class.java.name
        val PID: UUID = UUID.randomUUID()
        val SENDER_ID: UUID = UUID.randomUUID()

        val STUB_MESSAGE = Message(
                payload = TEST_MESSAGE_TO_CONSUME,
                payloadType = PAYLOAD_TYPE,
                group = GROUP,
                version = VERSION,
                operation = OPERATION_NAME,
                pid = PID,
                senderId = SENDER_ID,
                payloadKind = PayloadKind.JSON,
                processState = ProcessState.IN_PROGRESS
        )

        val RABBIT_CONFIG = RabbitConfig()

        private val DURABLE = false
        private val AUTO_DELETE = true
        private val EXCLUSIVE = false
        private val AUTO_ACK = true

        private val WRONG_DURABLE = DURABLE.not()
        private val WRONG_AUTO_DELETE = AUTO_DELETE.not()
        private val WRONG_EXCLUSIVE = EXCLUSIVE.not()
        private val WRONG_AUTO_ACK = AUTO_ACK.not()
    }
}

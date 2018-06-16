package org.codetome.riptide.messagebus.service.impl

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.service.messaging.MessagingAdapter
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@Suppress("UNCHECKED_CAST")
class MessagePublisherImplTest {

    lateinit var target: MessagePublisherImpl

    val publishedMessages = mutableListOf<Message<String>>()
    val latch = CountDownLatch(1)
    val instanceId: UUID = UUID.randomUUID()

    @Mock
    internal lateinit var processEventServiceMock: ProcessEventService

    internal val messagingAdapterStub = object : MessagingAdapter {

        override fun publish(message: Message<*>) {
            publishedMessages.add(message as Message<String>)
            latch.countDown()
        }

        override fun subscribe(operation: OperationDescriptor) {
            throw UnsupportedOperationException()
        }

        override fun fetchOperationFor(message: Message<*>): OperationDescriptor {
            throw UnsupportedOperationException()
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = MessagePublisherImpl(
                messagingAdapter = messagingAdapterStub,
                serviceInstanceId = instanceId)
    }

    @Test
    fun shouldDeliverMessageProperlyWhenNoConcreteOperationIsSupplied() {
        target = MessagePublisherImpl(
                messagingAdapter = messagingAdapterStub,
                serviceInstanceId = instanceId)

        target.publish(NO_OP_MESSAGE)

        val expectedMessage = NO_OP_MESSAGE.copy(
                senderId = instanceId,
                operation = "java_lang_String")

        assertThat(publishedMessages).containsExactly(expectedMessage)
    }

    @Test
    fun shouldConsumeWhenProduce() {
        target.publish(START_MESSAGE)
        latch.await(2, SECONDS)

        val expectedMessage = START_MESSAGE.copy(senderId = instanceId)

        assertThat(publishedMessages).containsExactly(expectedMessage)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowExceptionWhenPublishIsCalledWithoutPid() {
        target.publish(WRONG_PROC_START_MESSAGE)
    }

    private fun consumeTestMessage(msg: Message<String>) {
        publishedMessages.add(msg)
        latch.countDown()
    }

    companion object {
        val GROUP = "GROUP"
        val VERSION = "VERSION"
        val PAYLOAD = "Foobar"
        val OPERATION = "OPERATION"

        val NO_OP_MESSAGE = Message.create(PAYLOAD).asProcessStart()

        val START_MESSAGE = Message(
                payload = PAYLOAD,
                operation = OPERATION,
                group = GROUP,
                version = VERSION,
                payloadType = String::class.java.name).asProcessStart()

        val WRONG_PROC_START_MESSAGE = Message(
                payload = PAYLOAD,
                operation = OPERATION,
                group = GROUP,
                version = VERSION,
                payloadType = String::class.java.name)
    }

}

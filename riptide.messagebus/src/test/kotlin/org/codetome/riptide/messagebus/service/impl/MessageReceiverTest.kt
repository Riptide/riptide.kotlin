package org.codetome.riptide.messagebus.service.impl

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.service.messaging.MessagePublisher
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType
import org.codetome.riptide.protobuf.ProcessState
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")
class MessageReceiverTest {

    val targets = mutableMapOf<String, MessageReceiver>()

    var singleLatch = CountDownLatch(1)
    val publishedMessages = mutableListOf<Message<Int>>()
    val consumedStrings = mutableListOf<String>()
    val processEvents = mutableListOf<ProcessEvent>()

    internal val processEventService = ProcessEventServiceImpl()

    val consumerOp = OperationDescriptor(
            name = STRING_CONSUMER_OPERATION,
            sourceClass = String::class.java,
            consumerFn = this@MessageReceiverTest::consumingFunction as (Message<out Any>) -> Unit,
            operationType = OperationType.CONSUME,
            dispatchType = OperationDispatchType.CONSUMER)

    val processorOp = OperationDescriptor(
            name = INT_PROCESSOR_OPERATION,
            sourceClass = String::class.java,
            resultClass = Int::class.java,
            processorFn = this@MessageReceiverTest::processingFunction as (Message<out Any>) -> Message<out Any>,
            operationType = OperationType.PROCESS,
            dispatchType = OperationDispatchType.CONSUMER)

    internal val messagePublisherStub = object : MessagePublisher {
        override fun <T : Any> publish(message: Message<T>) {
            publishedMessages.add(message as Message<Int>)
            singleLatch.countDown()
        }

        override fun addOperationOverrideFor(klass: Class<out Any>, operationName: String) {
            TODO("not implemented")
        }
    }

    @Before
    fun setUp() {
        targets.put("MessageReceiverImpl", MessageReceiverImpl(
                messagePublisher = messagePublisherStub,
                processEventService = processEventService))
        targets.put("SingleThreadDispatchMessageReceiver", SingleThreadDispatchMessageReceiver(
                messagePublisher = messagePublisherStub,
                processEventService = processEventService))
    }

    @Test
    fun shouldReceiveConsumerMessagesProperlyWhenReceiveIsCalledWithConsumableMessage() {
        targets.forEach { name, target ->
            singleLatch = CountDownLatch(1)

            target.receive(TEST_MESSAGE as Message<Any>, consumerOp)

            singleLatch.await(2, SECONDS)

            assertThat(consumedStrings)
                    .`as`("MessageReceiver implementation '$name' should consumeMessages string message properly!")
                    .containsExactly(TEST_STRING)

            consumedStrings.clear()
        }
    }

    @Test
    fun shouldReceiveAndPublishProcessingMessagesProperlyWhenReceiveIsCalledWithProcessableMessage() {
        targets.forEach { name, target ->
            singleLatch = CountDownLatch(1)

            target.receive(TEST_INT_MESSAGE as Message<Any>, processorOp)

            singleLatch.await(2, SECONDS)

            assertThat(publishedMessages)
                    .`as`("MessageReceiver implementation '$name' should processMessages string message properly!")
                    .containsExactly(TEST_INT_MESSAGE_RESPONSE)

            publishedMessages.clear()

        }
    }

    @Test
    fun shouldThrowMissingNameExceptionWhenUnknownMessageIsReceived() {
        val latch = CountDownLatch(2)
        processEventService.addProcessListener(object : ProcessEventListener {
            override fun onProcessEvent(processEvent: ProcessEvent) {
                processEvents.add(processEvent)
                latch.countDown()
            }

        }, ProcessState.ERRORED)
        targets.forEach { name, target ->
            target.receive(Message("", ""), OperationDescriptor())
        }
        latch.await(2, SECONDS)
        assertThat(processEvents).hasSize(2)
    }

    fun consumingFunction(message: Message<String>) {
        consumedStrings.add(message.payload)
    }

    fun processingFunction(message: Message<String>): Message<Int> {
        return Message.correlateTo(message, message.payload.toInt())
    }

    companion object {
        val STRING_CONSUMER_OPERATION = "STRING_CONSUMER_OPERATION"
        val INT_PROCESSOR_OPERATION = "INT_PROCESSOR_OPERATION"
        val TEST_PID: UUID = UUID.fromString("488586f6-b61b-402e-96f8-994609941e9a")
        val SENDER_ID: UUID = UUID.randomUUID()
        val TEST_STRING = "wombat"

        val ONE = "1"

        val TEST_MESSAGE = Message(
                payload = TEST_STRING,
                payloadType = String::class.java.name,
                pid = TEST_PID,
                operation = STRING_CONSUMER_OPERATION,
                senderId = SENDER_ID)

        val TEST_INT_MESSAGE = Message(
                payload = ONE,
                payloadType = String::class.java.name,
                pid = TEST_PID,
                operation = INT_PROCESSOR_OPERATION,
                senderId = SENDER_ID)

        val TEST_INT_MESSAGE_RESPONSE = Message.correlateTo(TEST_INT_MESSAGE, TEST_INT_MESSAGE.payload.toInt())
    }
}

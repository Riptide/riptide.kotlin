package org.codetome.riptide.messagebus.service.impl

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.service.messaging.MessagePublisher
import org.codetome.riptide.protobuf.OperationType
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS
import java.util.concurrent.atomic.AtomicInteger

@Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")
class SingleThreadDispatchMessageReceiverTest {

    internal lateinit var target: SingleThreadDispatchMessageReceiver

    val consumerDescriptor = OperationDescriptor(
            operationType = OperationType.CONSUME,
            consumerFn = this@SingleThreadDispatchMessageReceiverTest::consumingFunction as (Message<out Any>) -> Unit)
    val consumerCallerThreadNames = ConcurrentHashMap<String, AtomicInteger>()
    val messageCount = AtomicInteger(0)

    val latch = CountDownLatch(MESSAGE_COUNT)
    val singleLatch = CountDownLatch(1)
    val publishedMessages = mutableListOf<Message<Int>>()

    internal val messagePublisherStub = object: MessagePublisher {
        override fun <T: Any> publish(message: Message<T>) {
            publishedMessages.add(message as Message<Int>)
            singleLatch.countDown()
        }

        override fun addOperationOverrideFor(klass: Class<out Any>, operationName: String) {
            TODO("not implemented")
        }
    }
    internal val processEventService = ProcessEventServiceImpl()

    @Before
    fun setUp() {
        target = SingleThreadDispatchMessageReceiver(
                messagePublisher = messagePublisherStub,
                processEventService = processEventService)
        MockitoAnnotations.initMocks(this)
        publishedMessages.clear()
    }

    @Test
    fun shouldConsumeMessagesHavingTheSameProcessFromASingleThreadWhenReceiveIsCalledFromMultipleThreads() {
        val msg = Message(
                payload = "foo",
                payloadType = String::class.java.name,
                pid = TEST_PID,
                operation = TEST_OPERATION,
                senderId = SENDER_ID)

        1.rangeTo(MESSAGE_COUNT).forEach {
            Thread({
                try {
                    target.receive(msg as Message<Any>, consumerDescriptor)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }).start()
        }
        latch.await(2, SECONDS)

        assertThat(consumerCallerThreadNames.keys).hasSize(1)
        assertThat(messageCount.get()).isEqualTo(MESSAGE_COUNT)
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenClosedAndReceiveIsCalled() {
        target.close()
        target.receive(MessageReceiverTest.TEST_MESSAGE as Message<Any>, consumerDescriptor)
    }

    @Test
    fun shouldDispatchToTwoThreadsWhenMessagesFromDifferentProcessesAreReceived() {
        val msg = Message(
                payload = "shouldCreateMultipleWhenCreateMultipleIsCalledWithMultipleElements",
                payloadType = String::class.java.name,
                pid = TEST_PID,
                operation = TEST_OPERATION,
                senderId = SENDER_ID)

        val diffDispatchMsg = Message(
                payload = "shouldCreateMultipleWhenCreateMultipleIsCalledWithMultipleElements",
                payloadType = String::class.java.name,
                pid = DIFF_PID,
                operation = TEST_OPERATION,
                senderId = SENDER_ID)


        1.rangeTo(MESSAGE_COUNT).forEach {
            Thread({
                try {
                    target.receive((if(it % 2 == 0) msg else diffDispatchMsg) as Message<Any>, consumerDescriptor)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }).start()
        }
        latch.await(2, SECONDS)

        assertThat(consumerCallerThreadNames.keys).hasSize(2)
        consumerCallerThreadNames.keys.forEach { key ->
            assertThat(consumerCallerThreadNames[key]?.get()).isEqualTo(MESSAGE_COUNT / 2)
        }
        assertThat(messageCount.get()).isEqualTo(MESSAGE_COUNT)

    }

    fun consumingFunction(message: Message<String>) {
        val threadName = Thread.currentThread().name
        consumerCallerThreadNames.putIfAbsent(threadName, AtomicInteger(0))
        consumerCallerThreadNames[threadName]?.incrementAndGet()
        messageCount.incrementAndGet()
        latch.countDown()
    }

    companion object {
        val TEST_OPERATION = "STRING_CONSUMER_OPERATION"
        val TEST_PID: UUID = UUID.fromString("488586f6-b61b-402e-96f8-994609941e9a")
        val DIFF_PID: UUID = UUID.fromString("673ac4f5-4de9-4b3c-8047-6307a73d0f98")
        val SENDER_ID: UUID = UUID.randomUUID()
        val MESSAGE_COUNT = 10
    }
}

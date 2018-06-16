package org.codetome.riptide.messagebus.service.impl

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitConfig
import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.messagebus.api.RiptideService
import org.codetome.riptide.messagebus.api.RiptideServiceBuilder
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.protobuf.ProcessEventPayload
import org.codetome.riptide.protobuf.ProcessState
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@Suppress("UNUSED_PARAMETER")
class RiptideServiceImplIntTest {

    val consumedStrings = mutableListOf<String>()
    val processedInts = mutableListOf<Int>()
    val consumedBooleans = mutableListOf<Boolean>()
    val consumedProcessEventPayloads = mutableListOf<ProcessEventPayload>()
    val processEvents = mutableListOf<ProcessEvent>()
    val failException = RuntimeException("Fail")

    lateinit var stringConsumerLatch: CountDownLatch
    lateinit var booleanConsumerLatch: CountDownLatch
    lateinit var protoLatch: CountDownLatch
    lateinit var failLatch: CountDownLatch

    @Before
    fun setUp() {
        stringConsumerLatch = CountDownLatch(1)
        booleanConsumerLatch = CountDownLatch(1)
        protoLatch = CountDownLatch(1)
        failLatch = CountDownLatch(1)
    }

    @After
    fun tearDown() {
        processEvents.clear()
    }

    @Test
    fun shouldReceiveProcessEventWhenConsumerFnFails() {
        val publisher = createDefaultRiptideService()
        val consumer = createDefaultRiptideService()

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::failingConsumerFn)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }
        consumer.addProcessListener(object: ProcessEventListener {
            override fun onProcessEvent(processEvent: ProcessEvent) {
                processEvents.add(processEvent)
            }
        }, ProcessState.ERRORED)

        val msg = Message.create("Foo")
                .forGroup(group)
                .forName(name)
                .forVersion(version)
        publisher.publish(msg)
        failLatch.await(TIMEOUT, SECONDS)
        Thread.sleep(500)


        assertThat(processEvents).containsExactly(ProcessEvent(
                message = "Failed to receive message of type 'java.lang.String'.",
                processState = ProcessState.ERRORED,
                pid = msg.pid,
                exceptions = listOf(failException)))
    }

    @Test
    fun shouldConsumeStringMessageWhenStringMessageIsSent() {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }
        val expected = StringPayload("Foo")

        publisher.publish(Message.create(expected)
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart())
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings).containsExactly(expected.str)
    }

    @Test
    fun shouldConsumeStringMessageWhenStringMessageIsSentWithCustomRoutingParameters() {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()
        val customParam0 = "customParam0"
        val customParam1 = "customParam1"

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            withCustomRoutingParameters(customParam0, customParam1)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }
        val expected = StringPayload("Foo")

        publisher.publish(Message.create(expected)
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart()
                .withCustomRoutingParameters(customParam0, customParam1))
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings).containsExactly(expected.str)
    }

    @Test
    fun shouldNotConsumeStringMessageWhenStringMessageIsConsumedWithWrongCustomRoutingParameters() {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()
        val customParam0 = "customParam0"
        val customParam1 = "customParam1"

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            withCustomRoutingParameters(customParam0) // wrong routing params
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }
        val expected = StringPayload("Foo")

        publisher.publish(Message.create(expected)
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart()
                .withCustomRoutingParameters(customParam0, customParam1))
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings).isEmpty()
    }

    @Test
    fun shouldNotConsumeStringMessageWhenStringMessageIsSentWithWrongCustomRoutingParameters() {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()
        val customParam0 = "customParam0"
        val customParam1 = "customParam1"

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            withCustomRoutingParameters(customParam0, customParam1) // wrong routing params
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }
        val expected = StringPayload("Foo")

        publisher.publish(Message.create(expected)
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart()
                .withCustomRoutingParameters(customParam0))
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings).isEmpty()
    }

    @Test
    fun shouldReturnBooleanMessageWhenIntMessageIsSentToIntProcessor() {
        val expectedConsumedBoolean = true
        val expectedProcessedInt = 1

        val processor = createDefaultRiptideService()
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()

        val (group, name, version) = generateGroupNameVersionTriple()
        val resultName = randomizeEnum(Name.TEST_RESULT_NAME)

        processor.processMessages {
            withFunction(this@RiptideServiceImplIntTest::processIntToBooleanPayload)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
            usingResultName(resultName)
        }

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeBooleanPayload)
            havingGroup(group)
            havingName(resultName)
            havingVersion(version)
        }

        publisher.publish(Message.create(IntPayload(expectedProcessedInt))
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart())
        booleanConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedBooleans).containsExactly(expectedConsumedBoolean)
        assertThat(processedInts).containsExactly(expectedProcessedInt)
    }

    @Test
    fun shouldProperlyConsumeProtobufMessagesWhenProtobufMessageIsSent() {
        val expectedMessage = ProcessEventPayload.newBuilder()
                .setMessage("Foobar")
                .build()

        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeProcessEventPayload)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }

        publisher.publish(Message.create(expectedMessage)
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart())
        protoLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedProcessEventPayloads).containsExactly(expectedMessage)
    }

    @Test
    fun shouldConsumeProcessMessageWhenProcessMessageIsSent() {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()
        val expectedString = "Wombat"

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }

        publisher.publish(Message.create(StringPayload(expectedString))
                .forGroup(group)
                .forName(name)
                .forVersion(version)
                .asProcessStart())
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings).containsExactly(expectedString)
    }

    @Test
    fun shouldNotConsumeProcessMessageWhenProcessMessageIsSentWithWrongVersionNameOrGroup() {
        Wrong.values().forEach {
            testWrong(it)
        }
    }

    private fun testWrong(wrong: Wrong) {
        val consumer = createDefaultRiptideService()
        val publisher = createDefaultRiptideService()
        val expectedString = "Wombat"

        val (group, name, version) = generateGroupNameVersionTriple()

        consumer.consumeMessages {
            withFunction(this@RiptideServiceImplIntTest::consumeStringPayload)
            havingGroup(group)
            havingName(name)
            havingVersion(version)
        }

        publisher.publish(Message.create(StringPayload(expectedString))
                .forGroup(if(Wrong.GROUP == wrong) "WRONG_GROUP" else group)
                .forName(if(Wrong.NAME == wrong) "WRONG_NAME" else name)
                .forVersion(if(Wrong.VERSION == wrong) "WRONG_VERSION" else version)
                .asProcessStart())
        stringConsumerLatch.await(TIMEOUT, SECONDS)

        assertThat(consumedStrings)
                .`as`("Should not have consumed message with wrong ${wrong.name}!")
                .isEmpty()
    }

    private fun generateGroupNameVersionTriple() = Triple(
            randomizeEnum(Group.TEST_GROUP),
            randomizeEnum(Name.TEST_NAME),
            randomizeEnum(Version.TEST_VERSION))

    private fun randomizeEnum(enum: Enum<*>) = "${enum.name}_${UUID.randomUUID().toString().substring(0, 4)}"

    private fun createDefaultRiptideService(): RiptideService {
        return RiptideServiceBuilder()
                .useRabbit(RabbitConfig())
                .build()
    }

    private fun failingConsumerFn(msg: String) {
        failLatch.countDown()
        throw failException
    }

    private fun consumeProcessEventPayload(payload: ProcessEventPayload) {
        consumedProcessEventPayloads.add(payload)
        protoLatch.countDown()
    }

    private fun consumeStringPayload(payload: StringPayload) {
        consumedStrings.add(payload.str)
        stringConsumerLatch.countDown()
    }

    private fun processIntToBooleanPayload(payload: IntPayload): BooleanPayload {
        val int = payload.int
        processedInts.add(int)
        return BooleanPayload(int != 0)
    }

    private fun consumeBooleanPayload(payload: BooleanPayload) {
        consumedBooleans.add(payload.bool)
        booleanConsumerLatch.countDown()
    }

    private enum class Wrong {
        GROUP,
        VERSION,
        NAME
    }

    private enum class Group {
        TEST_GROUP
    }

    private enum class Version {
        TEST_VERSION
    }

    private enum class Name {
        TEST_NAME,
        TEST_RESULT_NAME
    }

    private class StringPayload(val str: String)

    private class IntPayload(val int: Int)

    private class BooleanPayload(val bool: Boolean)

    companion object {
        val TIMEOUT = 2L
    }

}

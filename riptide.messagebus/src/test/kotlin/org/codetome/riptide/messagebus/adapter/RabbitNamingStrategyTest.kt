package org.codetome.riptide.messagebus.adapter

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitNamingStrategy
import org.codetome.riptide.messagebus.domain.Defaults
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType.*
import org.codetome.riptide.protobuf.OperationType
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class RabbitNamingStrategyTest {

    internal lateinit var target: RabbitNamingStrategy

    @Mock
    lateinit var uuidGeneratorMock: () -> UUID

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = RabbitNamingStrategy(uuidGeneratorMock)
    }

    @Test
    fun shouldGenerateProperExchangeNameWhenGenerateExchangeNameIsCalled() {
        val expected = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_NAME".toLowerCase()
        val actual = target.generateExchangeName(OPERATION_NAME)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldGenerateProperQueueNameWhenGenerateQueueNameIsCalledForCreatingConsumerQueue() {
        val uuid = UUID.randomUUID()
        Mockito.`when`(uuidGeneratorMock.invoke()).thenReturn(uuid)

        val expected = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_NAME.consumer.$uuid".toLowerCase()

        val actual = target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = CONSUMER))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldGenerateProperQueueNameWhenGenerateQueueNameIsCalledForConsumerAndTwoTimes() {
        val uuid0 = UUID.randomUUID()
        val uuid1 = UUID.randomUUID()
        Mockito.`when`(uuidGeneratorMock.invoke()).thenReturn(uuid0).thenReturn(uuid1)

        val expectedFirst = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_NAME.consumer.$uuid0".toLowerCase()
        val expectedSecond = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_NAME.consumer.$uuid1".toLowerCase()

        val actual0 = target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = CONSUMER))
        val actual1 = target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = CONSUMER))

        assertThat(actual0).isEqualTo(expectedFirst)
        assertThat(actual1).isEqualTo(expectedSecond)
    }

    @Test
    fun shouldGenerateProperQueueNameWhenGenerateQueueIsCalledForWorkerAndTwoTimes() {
        val uuid0 = UUID.randomUUID()
        val uuid1 = UUID.randomUUID()
        Mockito.`when`(uuidGeneratorMock.invoke()).thenReturn(uuid0).thenReturn(uuid1)

        val expected = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_NAME.worker".toLowerCase()

        val actual0 = target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = WORKER))
        val actual1 = target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = WORKER))

        assertThat(actual0).isEqualTo(expected)
        assertThat(actual1).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowExceptionWhenGenerateQueueNameCalledWithUnknownDispatchType() {
        target.generateQueueName(STUB_OPERATION_DESCRIPTOR.copy(dispatchType = UNRECOGNIZED))
    }

    @Test
    fun shouldUseCustomRoutingParamsWhenPresentAndGenerateRoutingKeyIsCalled() {
        val expected = "$GROUP.$OPERATION_NAME.$VERSION.$CUSTOM_0.$CUSTOM_1".toLowerCase()

        val actual = target.generateRoutingKey(
                groupName = GROUP,
                versionName = VERSION,
                operationName = OPERATION_NAME,
                customRoutingParams = listOf(CUSTOM_0, CUSTOM_1))

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldGenerateProperRoutingKeyWhenGenerateRoutingKeyIsCalled() {
        val expected = "$GROUP.$OPERATION_NAME.$VERSION".toLowerCase()

        val actual = target.generateRoutingKey(
                groupName = GROUP,
                versionName = VERSION,
                operationName = OPERATION_NAME,
                customRoutingParams = listOf())

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        val OPERATION_NAME = "OPERATION_NAME"
        val GROUP = "GROUP"
        val VERSION = "VERSION"

        val CUSTOM_0 = "CUSTOM_0"
        val CUSTOM_1 = "CUSTOM_1"

        val STUB_OPERATION_DESCRIPTOR = OperationDescriptor(
                groupName = GROUP,
                versionName = VERSION,
                name = OPERATION_NAME,
                sourceClass = Any::class.java,
                resultClass = Any::class.java,
                operationType = OperationType.PRODUCE)
    }
}

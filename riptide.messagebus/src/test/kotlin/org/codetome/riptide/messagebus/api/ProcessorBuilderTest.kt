package org.codetome.riptide.messagebus.api

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType
import org.codetome.riptide.protobuf.ReservedGroups
import org.codetome.riptide.protobuf.ReservedVersions
import org.junit.Test

@Suppress("UNUSED_PARAMETER")
class ProcessorBuilderTest {


    @Test
    fun shouldBuildDescriptorWithMinimalConfigurationWhenBuildIsCalled() {
        val source = "1"
        val result = 1
        val msg = Message.create(source)
        val resultMsg = Message.correlateTo(msg, result)

        val processorFn: (String) -> Int = {
            it.toInt()
        }

        val od =  ProcessorBuilder()
                .withFunction(processorFn)
                .build()


        assertThat(od.groupName).isEqualTo(ReservedGroups.RT_DEFAULT_GROUP.name)
        assertThat(od.versionName).isEqualTo(ReservedVersions.RT_DEFAULT_VERSION.name)
        assertThat(od.name).isEqualTo(OperationBuilder.generateOpNameFromSourceClass(String::class.java))
        assertThat(od.operationType).isEqualTo(OperationType.PROCESS)
        assertThat(od.sourceClass).isEqualTo(String::class.java)
        assertThat(od.resultClass).isEqualTo(Integer::class.java)
        assertThat(od.consumerFn).isEqualTo(OperationDescriptor.NO_CONSUMER_FN)
        assertThat(od.processorFn(msg)).isEqualTo(resultMsg)
        assertThat(od.dispatchType).isEqualTo(OperationDispatchType.CONSUMER)
    }

    @Test
    fun shouldSetWorkerProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .asWorker()
                .build()

        assertThat(od.dispatchType).isEqualTo(OperationDispatchType.WORKER)
    }

    @Test
    fun shouldSetConsumerProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .asConsumer()
                .build()

        assertThat(od.dispatchType).isEqualTo(OperationDispatchType.CONSUMER)
    }

    @Test
    fun shouldSetGroupProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingGroup(Group.ENUM_GROUP.name)
                .build()

        assertThat(od.groupName).isEqualTo(Group.ENUM_GROUP.name)
    }

    @Test
    fun shouldSetGroupWithEnumProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingGroup(Group.ENUM_GROUP)
                .build()

        assertThat(od.groupName).isEqualTo(Group.ENUM_GROUP.name)
    }

    @Test
    fun shouldSetVersionProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingVersion(Version.ENUM_VERSION.name)
                .build()

        assertThat(od.versionName).isEqualTo(Version.ENUM_VERSION.name)
    }

    @Test
    fun shouldSetVersionWithEnumProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingVersion(Version.ENUM_VERSION)
                .build()

        assertThat(od.versionName).isEqualTo(Version.ENUM_VERSION.name)
    }

    @Test
    fun shouldSetNameProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingName(Name.ENUM_NAME.name)
                .build()

        assertThat(od.name).isEqualTo(Name.ENUM_NAME.name)
    }

    @Test
    fun shouldSetNameWithEnumProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .havingName(Name.ENUM_NAME)
                .build()

        assertThat(od.name).isEqualTo(Name.ENUM_NAME.name)
    }

    @Test
    fun shouldSetCustomRoutingParamsProperly() {
        val od =  ProcessorBuilder()
                .withFunction(this@ProcessorBuilderTest::processorFn)
                .withCustomRoutingParameters(CUSTOM_PARAM_0, CUSTOM_PARAM_1)
                .build()

        assertThat(od.customRoutingParameters).containsExactly(CUSTOM_PARAM_0, CUSTOM_PARAM_1)
    }

    private fun processorFn(msg: String): Int = throw UnsupportedOperationException()

    private enum class Group {
        ENUM_GROUP
    }

    private enum class Version {
        ENUM_VERSION
    }

    private enum class Name {
        ENUM_NAME
    }

    companion object {
        val CUSTOM_PARAM_0 = "CUSTOM_PARAM_0"
        val CUSTOM_PARAM_1 = "CUSTOM_PARAM_1"
    }
}

package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType

interface OperationBuilder {

    var od: OperationDescriptor

    fun havingGroup(group: Enum<*>): OperationBuilder {
        return havingGroup(group.name)
    }

    fun havingGroup(groupName: String): OperationBuilder {
        od.groupName = groupName
        return this
    }

    fun havingVersion(version: Enum<*>): OperationBuilder {
        return havingVersion(version.name)
    }

    fun havingVersion(versionName: String): OperationBuilder {
        od.versionName = versionName
        return this
    }

    fun havingName(name: Enum<*>): OperationBuilder {
        return havingName(name.name)
    }

    fun havingName(name: String): OperationBuilder {
        od.name = name
        return this
    }

    fun asWorker(): OperationBuilder {
        od.dispatchType = OperationDispatchType.WORKER
        return this
    }

    fun asConsumer(): OperationBuilder {
        od.dispatchType = OperationDispatchType.CONSUMER
        return this
    }

    fun withCustomRoutingParameters(vararg params: String): OperationBuilder {
        od.customRoutingParameters = params.toList()
        return this
    }

    fun build(): OperationDescriptor {
        od.check()
        return od
    }

    companion object {
        fun generateOpNameFromSourceClass(klass: Class<out Any>) = klass.name.replace(".", "_").replace("$", "_")
    }
}

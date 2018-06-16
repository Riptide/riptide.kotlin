package org.codetome.riptide.messagebus.domain

import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType
import org.codetome.riptide.protobuf.ReservedGroups
import org.codetome.riptide.protobuf.ReservedVersions
import java.util.*

data class OperationDescriptor(
        var groupName: String = ReservedGroups.RT_DEFAULT_GROUP.name,
        var versionName: String = ReservedVersions.RT_DEFAULT_VERSION.name,
        var name: String = NO_NAME,
        var resultName: String = NO_NAME,
        var operationType: OperationType = OperationType.UNRECOGNIZED,
        var sourceClass: Class<out Any> = NoClass::class.java,
        var resultClass: Class<out Any> = NoClass::class.java,
        var consumerFn: (Message<out Any>) -> Unit = NO_CONSUMER_FN,
        var processorFn: (Message<out Any>) -> Message<*> = NO_PROCESSOR_FN,
        var dispatchType: OperationDispatchType = OperationDispatchType.UNRECOGNIZED,
        var customRoutingParameters: List<String> = listOf()) {

    class NoClass

    fun hasResultName() = resultName != NO_NAME

    fun check() {
        if(groupName.isBlank()) {
            throw IllegalStateException("Can't build an Operation with an empty group name! Try using the default if you need no group")
        }
        if(versionName.isBlank()) {
            throw IllegalStateException("Can't build an Operation with an empty version name! Try using the default if you need no version")
        }
        if(name == NO_NAME || name.isBlank()) {
            throw IllegalStateException("There is no name for this operation!")
        }
        if(operationType == OperationType.UNRECOGNIZED) {
            throw IllegalStateException("Unrecognized operation type! You either have to choose 'CONSUME' or 'PROCESS'.")
        }
        if(consumerFn == NO_CONSUMER_FN && processorFn == NO_PROCESSOR_FN) {
            throw IllegalStateException("Can't build an Operation without a consumer or a processor function!")
        }
        if(sourceClass == NO_CLASS) {
            throw IllegalStateException("Can't build an Operation without a source class!")
        }
        if(resultClass != NO_CLASS && processorFn == NO_PROCESSOR_FN) {
            throw IllegalStateException("No processor function is present for result class: '${resultClass.simpleName}'!")
        }
        if(resultClass == NO_CLASS && processorFn != NO_PROCESSOR_FN) {
            throw IllegalStateException("No result class present for processor function!")
        }
        if(dispatchType == OperationDispatchType.UNRECOGNIZED) {
            throw IllegalStateException("Unrecognized dispatch type! You either have to choose 'WORKER' or 'CONSUMER'.")
        }
    }

    @Suppress("UNUSED_PARAMETER")
    companion object {
        val NO_CONSUMER_FN = { msg: Message<out Any> -> }
        val NO_PROCESSOR_FN = {msg: Message<out Any> -> throw UnsupportedOperationException()}
        val NO_NAME = UUID.randomUUID().toString()
        val NO_CLASS = NoClass::class.java
    }
}


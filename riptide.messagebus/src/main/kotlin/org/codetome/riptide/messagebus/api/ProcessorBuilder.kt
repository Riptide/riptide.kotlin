package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType

@Suppress("UNCHECKED_CAST")
class ProcessorBuilder : OperationBuilder {

    override var od = OperationDescriptor(
            operationType = OperationType.PROCESS,
            dispatchType = OperationDispatchType.CONSUMER)

    fun usingResultName(resultEnum: Enum<*>) {
        usingResultName(resultEnum.name)
    }

    fun usingResultName(resultName: String) {
        od.resultName = resultName
    }

    inline fun <reified T : Any, reified R : Any> withFunction(noinline fn: (T) -> R): ProcessorBuilder {
        od.sourceClass = T::class.java
        od.resultClass = R::class.java
        if (od.name == OperationDescriptor.NO_NAME) {
            od.name = OperationBuilder.generateOpNameFromSourceClass(od.sourceClass)
        }
        od.processorFn = { msg ->
            Message.correlateTo(msg, fn(msg.payload as T))
        }
        return this
    }
}

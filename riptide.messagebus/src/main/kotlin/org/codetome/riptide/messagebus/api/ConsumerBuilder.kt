package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType


@Suppress("UNCHECKED_CAST")
class ConsumerBuilder : OperationBuilder {

    override var od = OperationDescriptor(
            operationType = OperationType.CONSUME,
            dispatchType = OperationDispatchType.CONSUMER)

    inline fun <reified T : Any> withFunction(noinline fn: (msg: T) -> Unit): ConsumerBuilder {
        od.sourceClass = T::class.java
        if (od.name == OperationDescriptor.NO_NAME) {
            od.name = OperationBuilder.generateOpNameFromSourceClass(od.sourceClass)
        }
        od.consumerFn = { (payload) ->
            fn(payload as T)
        }
        return this
    }
}

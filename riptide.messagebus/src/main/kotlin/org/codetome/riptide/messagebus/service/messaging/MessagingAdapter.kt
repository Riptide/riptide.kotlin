package org.codetome.riptide.messagebus.service.messaging

import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor

internal interface MessagingAdapter {

    fun publish(message: Message<*>)

    fun subscribe(operation: OperationDescriptor)

    fun fetchOperationFor(message: Message<*>): OperationDescriptor
}

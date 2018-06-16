package org.codetome.riptide.messagebus.service.messaging

import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor

/**
 * Receives messages (possibly from a MessagingAdapter).
 */
interface MessageReceiver {

    /**
     * Receives a message and publishes it to the corresponding
     * consumer / processor.
     */
    fun receive(message: Message<Any>, operation: OperationDescriptor)

}

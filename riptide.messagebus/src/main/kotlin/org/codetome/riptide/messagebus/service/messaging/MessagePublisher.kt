package org.codetome.riptide.messagebus.service.messaging

import org.codetome.riptide.messagebus.domain.Message

/**
 * Used to publish messages.
 */
internal interface MessagePublisher {

    /**
     * Publishes a message.
     */
    fun <T: Any> publish(message: Message<T>)

    fun addOperationOverrideFor(klass: Class<out Any>, operationName: String)
}

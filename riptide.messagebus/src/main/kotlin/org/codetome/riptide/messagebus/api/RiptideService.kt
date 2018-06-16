package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.protobuf.ProcessState

/**
 * Facade which encapsulates the core functionality of Riptide.
 */
interface RiptideService : MessageReceiver {

    fun <T: Any> publish(message: Message<T>)

    fun consumeMessages(init: ConsumerBuilder.() -> Unit)

    fun processMessages(init: ProcessorBuilder.() -> Unit)

    fun addProcessListener(listener: ProcessEventListener, vararg processStates: ProcessState)

}

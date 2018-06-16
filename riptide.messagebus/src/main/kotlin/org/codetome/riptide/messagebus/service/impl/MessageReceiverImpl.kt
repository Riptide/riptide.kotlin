package org.codetome.riptide.messagebus.service.impl

import org.codetome.riptide.messagebus.api.exception.MissingNameException
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.service.messaging.MessagePublisher
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.codetome.riptide.protobuf.OperationType.CONSUME
import org.codetome.riptide.protobuf.OperationType.PROCESS
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal class MessageReceiverImpl(
        private val messagePublisher: MessagePublisher,
        private val processEventService: ProcessEventService)
    : MessageReceiver {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun receive(message: Message<Any>, operation: OperationDescriptor) {
        when (operation.operationType) {
            CONSUME -> {
                operation.consumerFn(message)
            }
            PROCESS -> {
                messagePublisher.publish(operation.processorFn(message))
            }
            else -> {
                val msg = "No operation found for received message: $message!"
                logger.error(msg)
                processEventService.sendProcessEvent(ProcessEvent.createFromException(MissingNameException(), msg))
            }
        }
    }
}

package org.codetome.riptide.messagebus.service.impl

import org.codetome.riptide.messagebus.api.ConsumerBuilder
import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.messagebus.api.ProcessorBuilder
import org.codetome.riptide.messagebus.api.RiptideService
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.service.messaging.MessagePublisher
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.messagebus.service.messaging.MessagingAdapter
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.codetome.riptide.protobuf.ProcessState
import org.codetome.riptide.protobuf.ReservedOperations
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class RiptideServiceImpl internal constructor(
        private val messagePublisher: MessagePublisher,
        private val messageReceivers: Map<String, MessageReceiver>,
        private val messagingAdapter: MessagingAdapter,
        private val processEventService: ProcessEventService,
        private val serviceInstanceId: UUID)
    : RiptideService {


    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun <T : Any> publish(message: Message<T>) {
        try {
            logger.info("Forwarding message of type: '{}' to MessagePublisher.", message.payloadType)
            messagePublisher.publish(message)
        } catch (e: Exception) {
            val msg = "Failed to publish message of type '${message.payloadType}' to MessagePublisher. " +
                    if(e.message != null) " Reason: ${e.message}" else ""
            logger.error(msg, e)
            handleProcessError(e, message, msg)
        }
    }

    override fun receive(message: Message<Any>, operation: OperationDescriptor) {
        try {
            logger.info("Delegating message of type: '${message.payloadType}' to receiver.")
            val defaultReceiver = messageReceivers[ReservedOperations.RT_DEFAULT_OP.name] ?: throw IllegalStateException("No default receiver is present!")
            messageReceivers.getOrDefault(message.operation, defaultReceiver).receive(message, operation)
        } catch (e: Exception) {
            val msg = "Failed to receive message of type '${message.payloadType}'."
            logger.error(msg)
            handleProcessError(e, message, msg)
        }
    }

    override fun consumeMessages(init: ConsumerBuilder.() -> Unit) {
        val builder = ConsumerBuilder()
        init(builder)
        messagingAdapter.subscribe(builder.build())
    }

    override fun processMessages(init: ProcessorBuilder.() -> Unit) {
        val builder = ProcessorBuilder()
        init(builder)
        val od = builder.build()
        if (od.hasResultName()) {
            messagePublisher.addOperationOverrideFor(od.resultClass, od.resultName)
        }
        messagingAdapter.subscribe(od)
    }

    override fun addProcessListener(listener: ProcessEventListener, vararg processStates: ProcessState) {
        processEventService.addProcessListener(listener, *processStates)
    }

    private fun <T : Any> handleProcessError(e: Exception, message: Message<T>, msg: String) {
        processEventService.sendProcessEvent(ProcessEvent(
                pid = if (message.hasPid()) message.pid else ProcessEvent.UNKNOWN_PID,
                message = msg,
                processState = ProcessState.ERRORED,
                exceptions = listOf(e)))
    }
}

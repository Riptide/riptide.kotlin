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
import java.io.Closeable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

internal class SingleThreadDispatchMessageReceiver(
        private val processEventService: ProcessEventService,
        private var messagePublisher: MessagePublisher,
        private val numThreads: Int = Runtime.getRuntime().availableProcessors().times(2))
    : MessageReceiver, Closeable {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val executors = mutableListOf<ExecutorService>()
    private val closed = AtomicBoolean(false)

    init {
        1.rangeTo(numThreads).forEach {
            executors.add(Executors.newSingleThreadExecutor())
        }
    }

    override fun receive(message: Message<Any>, operation: OperationDescriptor) {
        checkClosed()
        executors[fetchSubjectIndexForMessage(message)].submit { doReceive(message, operation) }
    }

    private fun doReceive(message: Message<Any>, operation: OperationDescriptor) {
        try {
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
        } catch (e: Exception) {
            val msg = "Failed to receive message!"
            logger.error(msg, e)
            processEventService.sendProcessEvent(ProcessEvent.createFromException(e, msg))
        }
    }

    override fun close() {
        closed.set(true)
        executors.forEach { it.shutdown() }
    }

    private fun checkClosed() {
        if (closed.get()) {
            throw IllegalStateException("This MessageReceiver is closed!")
        }
    }

    private fun fetchSubjectIndexForMessage(message: Message<Any>): Int {
        return Math.abs(message.pid.mostSignificantBits.rem(numThreads)).toInt()
    }
}

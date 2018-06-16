package org.codetome.riptide.messagebus.service.impl

import com.google.protobuf.MessageLite
import org.codetome.riptide.messagebus.api.OperationBuilder
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.service.messaging.MessagePublisher
import org.codetome.riptide.messagebus.service.messaging.MessagingAdapter
import org.codetome.riptide.protobuf.PayloadKind.JSON
import org.codetome.riptide.protobuf.PayloadKind.PROTOBUF
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class MessagePublisherImpl internal constructor(private val messagingAdapter: MessagingAdapter,
                                                private val serviceInstanceId: UUID)
    : MessagePublisher {

    private val operationOverrides = ConcurrentHashMap<Class<out Any>, String>()
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun <T : Any> publish(message: Message<T>) {
        checkPid(message)
        logger.info("Forwarding publishable message of type '${message.payloadType}' to MessagingAdapter.")
        messagingAdapter.publish(message.copy(
                senderId = serviceInstanceId,
                operation = fetchOperationNameForMessage(message),
                payloadKind = if (message.payload is MessageLite) PROTOBUF else JSON))
    }

    override fun addOperationOverrideFor(klass: Class<out Any>, operationName: String) {
        operationOverrides.put(klass, operationName)
    }

    private fun <T : Any> checkPid(message: Message<T>) {
        if (message.hasPid().not()) {
            throw IllegalArgumentException("Can't publish message with processMessages state '${message.processState}' which has no pid (try using 'asProcessStart' if you want to start a processMessages)!")
        }
    }

    private fun <T : Any> fetchOperationNameForMessage(message: Message<T>): String {
        return if (message.hasNoOperationNameDefined()) {
            val klass = message.payload::class.java
            if (operationOverrides.containsKey(klass)) {
                operationOverrides[klass]!!
            } else {
                OperationBuilder.generateOpNameFromSourceClass(klass)
            }
        } else {
            message.operation
        }
    }
}

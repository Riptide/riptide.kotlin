package org.codetome.riptide.messagebus.adapter.rabbit

import org.codetome.riptide.messagebus.domain.Defaults
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.protobuf.OperationDispatchType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

internal class RabbitNamingStrategy(private val uuidGeneratorFn: () -> java.util.UUID = UUID::randomUUID) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun generateQueueName(operation: OperationDescriptor): String {
        val queueName = when (operation.dispatchType) {
            OperationDispatchType.CONSUMER -> BROADCAST_QUEUE_NAME_TEMPLATE
                    .replace(OPERATION_PLACEHOLDER, operation.name)
                    .replace(UUID_PLACEHOLDER, uuidGeneratorFn().toString())
            OperationDispatchType.WORKER -> WORKER_QUEUE_NAME_TEMPLATE
                    .replace(OPERATION_PLACEHOLDER, operation.name)
            else -> {
                throw IllegalArgumentException("Encountered unsupported dispatch type!")
            }
        }.toLowerCase()
        logger.info("Generated queue name '$queueName' for operation {name=${operation.name}, dispatchType=${operation.dispatchType}}.")
        return queueName
    }

    fun generateRoutingKey(groupName: String, versionName: String, operationName: String, customRoutingParams: List<String>): String {
        var routingKey = ROUTING_KEY_TEMPLATE
                .replace(GROUP_PLACEHOLDER, groupName)
                .replace(VERSION_PLACEHOLDER, versionName)
                .replace(OPERATION_PLACEHOLDER, operationName).toLowerCase()
        if(customRoutingParams.isNotEmpty()) {
            routingKey = routingKey.plus(".").plus(customRoutingParams.joinToString(".").toLowerCase())
        }
        logger.info("Generated routing key '$routingKey' for group '$groupName', version '$versionName' and operation '$operationName'.")
        return routingKey
    }

    fun generateExchangeName(operationName: String): String {
        val exchangeName = EXCHANGE_NAME_TEMPLATE.replace(OPERATION_PLACEHOLDER, operationName).toLowerCase()
        logger.info("Generated exchange name '$exchangeName' for operation name '$operationName'.")
        return exchangeName
    }

    companion object {
        private val VERSION_PLACEHOLDER = "{group}"
        private val GROUP_PLACEHOLDER = "{version}"
        private val OPERATION_PLACEHOLDER = "{operation}"
        private val UUID_PLACEHOLDER = "uuid"

        private val EXCHANGE_NAME_TEMPLATE = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_PLACEHOLDER"
        private val WORKER_QUEUE_NAME_TEMPLATE = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_PLACEHOLDER.worker"
        private val BROADCAST_QUEUE_NAME_TEMPLATE = "${Defaults.RABBIT_ADAPTER_VERSION}.$OPERATION_PLACEHOLDER.consumer.$UUID_PLACEHOLDER"
        private val ROUTING_KEY_TEMPLATE = "$GROUP_PLACEHOLDER.$OPERATION_PLACEHOLDER.$VERSION_PLACEHOLDER"
    }
}

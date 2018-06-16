package org.codetome.riptide.messagebus.adapter.rabbit

import com.rabbitmq.client.*
import com.rabbitmq.client.BuiltinExchangeType.TOPIC
import org.codetome.riptide.messagebus.api.exception.MissingMessageReceiverException
import org.codetome.riptide.messagebus.api.exception.MissingNameException
import org.codetome.riptide.messagebus.api.exception.MissingOperationException
import org.codetome.riptide.messagebus.domain.Message
import org.codetome.riptide.messagebus.domain.OperationDescriptor
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.service.messaging.MessageReceiver
import org.codetome.riptide.messagebus.service.messaging.MessagingAdapter
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.codetome.riptide.protobuf.ProtoMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*


internal class RabbitMessagingAdapter internal constructor(
        rabbitConfig: RabbitConfig = RabbitConfig(),
        private val processEventService: ProcessEventService,
        private val rabbitNamingStrategy: RabbitNamingStrategy = RabbitNamingStrategy()) : MessagingAdapter {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val connection: Connection
    private val inboundChannel: Channel
    private val outboundChannel: Channel

    private val knownExchanges = mutableSetOf<String>()
    private val operations: MutableList<OperationDescriptor> = mutableListOf()

    private var messageReceiver: Optional<MessageReceiver> = Optional.empty()

    init {
        val factory = createConnectionFactory(rabbitConfig)
        connection = factory.newConnection()
        inboundChannel = connection.createChannel()
        outboundChannel = connection.createChannel()
    }

    override fun publish(message: Message<*>) {
        val exchangeName = rabbitNamingStrategy.generateExchangeName(message.operation)
        if (message.operation.isBlank()) {
            throw MissingNameException()
        }
        if (knownExchanges.contains(exchangeName).not()) {
            declareExchangeFor(message.operation)
            knownExchanges.add(exchangeName)
        }
        val routingKey = rabbitNamingStrategy.generateRoutingKey(
                groupName = message.group,
                versionName = message.version,
                operationName = message.operation,
                customRoutingParams = message.customRoutingParameters)
        logger.info("Publishing message of type '{}' to exchange: '{}' with routing key: '{}'",
                message.payloadType, exchangeName, routingKey)
        outboundChannel.basicPublish(exchangeName, routingKey, null, message.toProtobuf().toByteArray())
    }

    override fun subscribe(operation: OperationDescriptor) {
        operations.add(operation)
        val exchangeName = declareExchangeFor(operation.name)
        knownExchanges.add(exchangeName)
        val queueName = rabbitNamingStrategy.generateQueueName(operation)
        val routingKey = rabbitNamingStrategy.generateRoutingKey(
                groupName = operation.groupName,
                versionName = operation.versionName,
                operationName = operation.name,
                customRoutingParams = (operation.customRoutingParameters).map(String::toLowerCase)
        )

        logger.info("Binding queue: '$queueName' to exchange: '$exchangeName' with routing key: '$routingKey'")

        declareAndBindQueueFor(exchangeName, queueName, routingKey)

        logger.info("Subscribing operation: '${operation.name}' on queue: '$queueName'")
        val consumer = object : DefaultConsumer(inboundChannel) {
            override fun handleDelivery(consumerTag: String, envelope: Envelope,
                                        properties: AMQP.BasicProperties, body: ByteArray) {
                try {
                    if (messageReceiver.isPresent.not()) {
                        throw MissingMessageReceiverException()
                    }
                    val message = Message.fromProtobuf(ProtoMessage.parseFrom(body))
                    logger.info("Message of type '${message.payloadType}' is received from Rabbit. ($message)")
                    messageReceiver.get().receive(message, fetchOperationFor(message))
                } catch (e: Exception) {
                    val msg = "Failed to handle delivery of inbound message!" +
                            if (e.message != null) " Reason: '${e.message}'" else ""
                    logger.error(msg, e)
                    processEventService.sendProcessEvent(ProcessEvent.createFromException(e, msg))
                }
            }
        }
        inboundChannel.basicConsume(queueName, AUTO_ACK, consumer)
    }

    override fun fetchOperationFor(message: Message<*>): OperationDescriptor {
        val group = message.group
        val version = message.version
        val payloadClass = message.payload::class.java
        return operations.filter { op ->
            (op.groupName == group)
                    .and(op.versionName == version)
                    .and(op.sourceClass == payloadClass)
        }.getOrElse(0, { throw MissingOperationException("Can't find consumer/processor for Message with group: " +
                "$group, version: $version and class: $payloadClass") })
    }

    fun setMessageReceiver(messageReceiver: MessageReceiver) {
        this.messageReceiver = Optional.of(messageReceiver)
    }

    private fun createConnectionFactory(rabbitConfig: RabbitConfig): ConnectionFactory {
        val factory = ConnectionFactory()
        factory.host = rabbitConfig.host
        factory.port = rabbitConfig.port
        factory.isAutomaticRecoveryEnabled = true
        return factory
    }

    private fun declareExchangeFor(operationName: String): String {
        val exchangeName = rabbitNamingStrategy.generateExchangeName(operationName)
        logger.info("Generating exchange '{}' for operation '{}'.",
                exchangeName, operationName)
        outboundChannel.exchangeDeclare(exchangeName, TOPIC, DURABLE, AUTO_DELETE, mapOf())
        return exchangeName
    }

    private fun declareAndBindQueueFor(exchangeName: String, queueName: String, routingKey: String): String {
        inboundChannel.queueDeclare(queueName, DURABLE, EXCLUSIVE, AUTO_DELETE, mapOf())
        inboundChannel.queueBind(queueName, exchangeName, routingKey)
        return queueName
    }

    companion object {
        private val DURABLE = false
        private val AUTO_DELETE = true
        private val EXCLUSIVE = false
        private val AUTO_ACK = true
    }
}

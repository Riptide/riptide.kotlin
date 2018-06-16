package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.adapter.rabbit.RabbitConfig
import org.codetome.riptide.messagebus.adapter.rabbit.RabbitMessagingAdapter
import org.codetome.riptide.messagebus.service.impl.*
import org.codetome.riptide.protobuf.ReservedOperations
import java.util.*

/**
 * Use this builder to create a fine-grained RiptideService to use.
 */
class RiptideServiceBuilder {

    private lateinit var rabbitConfig: RabbitConfig
    private var dispatchProcessesOnSingleThread = false

    fun dispatchProcessesOnSingleThread(): RiptideServiceBuilder {
        dispatchProcessesOnSingleThread = true
        return this
    }

    fun useRabbit(rabbitConfig: RabbitConfig): RiptideServiceBuilder {
        this.rabbitConfig = rabbitConfig
        return this
    }


    fun build(): RiptideService {
        val instanceId = UUID.randomUUID()
        val processService = ProcessEventServiceImpl()

        val messagingAdapter = RabbitMessagingAdapter(
                rabbitConfig = rabbitConfig,
                processEventService = processService)

        val messagePublisher = MessagePublisherImpl(
                messagingAdapter = messagingAdapter,
                serviceInstanceId = instanceId)

        val messageReceiver = if (dispatchProcessesOnSingleThread) {
            SingleThreadDispatchMessageReceiver(
                    messagePublisher = messagePublisher,
                    processEventService = processService)
        } else {
            MessageReceiverImpl(
                    messagePublisher = messagePublisher,
                    processEventService = processService)
        }

        val service = RiptideServiceImpl(
                messagePublisher = messagePublisher,
                messageReceivers = mapOf(Pair(ReservedOperations.RT_DEFAULT_OP.name, messageReceiver)),
                messagingAdapter = messagingAdapter,
                processEventService = processService,
                serviceInstanceId = instanceId)
        messagingAdapter.setMessageReceiver(service)
        return service
    }
}

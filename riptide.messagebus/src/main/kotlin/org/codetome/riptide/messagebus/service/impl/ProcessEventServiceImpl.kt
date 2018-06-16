package org.codetome.riptide.messagebus.service.impl

import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.service.process.ProcessEventService
import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.protobuf.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap

internal class ProcessEventServiceImpl : ProcessEventService {

    private val listeners = ConcurrentHashMap<ProcessState, MutableList<ProcessEventListener>>()
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    init {
        ProcessState.values().forEach { state ->
            listeners.put(state, mutableListOf())
        }
    }

    override fun sendProcessEvent(processEvent: ProcessEvent) {
        listeners[processEvent.processState]!!.forEach { it.onProcessEvent(processEvent) }
    }

    override fun addProcessListener(listener: ProcessEventListener, vararg processStates: ProcessState) {
        logger.info("Adding listener for processMessages states: '${processStates.joinToString(", ")}'.")
        processStates.forEach { state ->
            listeners[state]!!.add(listener)
        }
    }
}

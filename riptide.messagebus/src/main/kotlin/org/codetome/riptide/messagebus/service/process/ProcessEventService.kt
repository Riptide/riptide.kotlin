package org.codetome.riptide.messagebus.service.process

import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.protobuf.ProcessState

internal interface ProcessEventService {

    fun sendProcessEvent(processEvent: ProcessEvent)

    fun addProcessListener(listener: ProcessEventListener, vararg processStates: ProcessState)

}

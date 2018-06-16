package org.codetome.riptide.messagebus.api

import org.codetome.riptide.messagebus.domain.ProcessEvent

interface ProcessEventListener {

    fun onProcessEvent(processEvent: ProcessEvent)
}

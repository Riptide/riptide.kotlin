package org.codetome.riptide.messagebus.domain

import org.codetome.riptide.protobuf.ProcessState
import java.util.*

data class ProcessEvent(val message: String,
                        val processState: ProcessState,
                        val pid: UUID = UNKNOWN_PID,
                        val exceptions: List<Exception> = listOf()) {

    fun hasNoPid() = pid == UNKNOWN_PID

    companion object {
        val UNKNOWN_PID: UUID = UUID.randomUUID()

        internal fun createFromException(e: Exception, message: String) = ProcessEvent(
                message = message,
                processState = ProcessState.ERRORED,
                pid = UNKNOWN_PID,
                exceptions = listOf(e))

        internal fun createFromExceptionWithPid(e: Exception, message: String, pid: UUID) = ProcessEvent(
                message = message,
                processState = ProcessState.ERRORED,
                pid = pid,
                exceptions = listOf(e))
    }
}

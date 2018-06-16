package org.codetome.riptide.messagebus.service.impl

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.domain.ProcessEvent
import org.codetome.riptide.messagebus.api.ProcessEventListener
import org.codetome.riptide.protobuf.ProcessState
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.atomic.AtomicReference

class ProcessEventServiceImplTest {

    lateinit internal var target: ProcessEventServiceImpl

    @Before
    fun setUp() {
        target = ProcessEventServiceImpl()
    }

    @Test
    fun shouldReceiveStateMessageWhenListenerIsAddedAndMessageIsSent() {
        val actual = AtomicReference<ProcessEvent>()
        val processState = ProcessState.STARTED
        val expected = ProcessEvent(
                pid = UUID.randomUUID(),
                message = "msg",
                processState = processState)

        val listener = object : ProcessEventListener {
            override fun onProcessEvent(processEvent: ProcessEvent) {
                actual.set(processEvent)
            }
        }
        target.addProcessListener(listener, processState)
        target.sendProcessEvent(expected)

        assertThat(actual.get()).isEqualTo(expected)
    }

    @Test
    fun shouldReceiveAllStateMessagesWhenAListenerForMultipleEventIsAddedAndMessageIsSent() {
        val receivedMessages = mutableListOf<ProcessEvent>()
        val processState0 = ProcessState.STARTED
        val processState1 = ProcessState.FAILED
        val expected0 = ProcessEvent(
                pid = UUID.randomUUID(),
                message = "msg",
                processState = processState0)
        val expected1 = ProcessEvent(
                pid = UUID.randomUUID(),
                message = "msg",
                processState = processState1)

        val listener = object : ProcessEventListener {
            override fun onProcessEvent(processEvent: ProcessEvent) {
                receivedMessages.add(processEvent)
            }
        }
        target.addProcessListener(listener, processState0, processState1)
        target.sendProcessEvent(expected0)
        target.sendProcessEvent(expected1)

        assertThat(receivedMessages).containsExactlyInAnyOrder(expected0, expected1)
    }

    @Test
    fun shouldReceiveAllStateMessagesWhenListenersAreAddedAndMessageIsSent() {
        val receivedMessages = mutableListOf<ProcessEvent>()
        val processState0 = ProcessState.STARTED
        val processState1 = ProcessState.FAILED
        val expected0 = ProcessEvent(
                pid = UUID.randomUUID(),
                message = "msg",
                processState = processState0)
        val expected1 = ProcessEvent(
                pid = UUID.randomUUID(),
                message = "msg",
                processState = processState1)

        val listener = object : ProcessEventListener {
            override fun onProcessEvent(processEvent: ProcessEvent) {
                receivedMessages.add(processEvent)
            }
        }
        target.addProcessListener(listener, processState0)
        target.addProcessListener(listener, processState1)
        target.sendProcessEvent(expected0)
        target.sendProcessEvent(expected1)

        assertThat(receivedMessages).containsExactlyInAnyOrder(expected0, expected1)
    }

}

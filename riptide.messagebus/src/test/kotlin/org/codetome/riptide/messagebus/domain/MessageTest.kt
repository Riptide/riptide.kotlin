package org.codetome.riptide.messagebus.domain

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.protobuf.PayloadKind
import org.codetome.riptide.protobuf.ProcessState
import org.junit.Test
import java.util.*

class MessageTest {


    @Test
    fun shouldProperlyMapFromAndToProtobuf() {
        val actual = Message.fromProtobuf(STUB_MESSAGE.toProtobuf())

        assertThat(actual).isEqualTo(STUB_MESSAGE)
    }

    @Test
    fun shouldCreateMultipleWhenCreateMultipleIsCalledWithMultipleElements() {
        val expected = listOf("foo", "bar", "baz")
        val messageList = Message.createMultiple(expected)

        val first = messageList.payload
        val second = messageList.next.get().payload
        val third = messageList.next.get().next.get().payload
        val fourthPresent = messageList.next.get().next.get().next.isPresent
        assertThat(fourthPresent).isFalse()
        assertThat(listOf(first, second, third)).isEqualTo(expected)
    }

    @Test
    fun shouldCreateSingleWhenCreateMultipleIsCalledWithOneElement() {
        val expected = listOf("foo")
        val messageList = Message.createMultiple(expected)

        val first = messageList.payload
        val secondPresent = messageList.next.isPresent
        assertThat(secondPresent).isFalse()
        assertThat(listOf(first)).isEqualTo(expected)
    }

    @Test
    fun shouldFetchSiblingsWhenFetchSiblingsIsCalledWithMultipleSiblings() {
        val expected = listOf("foo", "bar", "baz")
        val messages = Message.createMultiple(expected)

        assertThat(messages.fetchAsListWithSiblings().map { it.payload }).isEqualTo(expected)
    }

    companion object {
        val TEST_MESSAGE_TO_CONSUME = "Foo"
        val OPERATION_NAME = "OPERATION_NAME"
        val GROUP = "GROUP"
        val VERSION = "VERSION"
        val PAYLOAD_TYPE: String = String::class.java.name
        val PID: UUID = UUID.randomUUID()
        val SENDER_ID: UUID = UUID.randomUUID()
        
        val STUB_MESSAGE = Message(
                payload = TEST_MESSAGE_TO_CONSUME,
                payloadType = PAYLOAD_TYPE,
                group = GROUP,
                version = VERSION,
                operation = OPERATION_NAME,
                pid = PID,
                senderId = SENDER_ID,
                payloadKind = PayloadKind.JSON,
                processState = ProcessState.IN_PROGRESS
        )
    }
}

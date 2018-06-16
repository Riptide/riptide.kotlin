package org.codetome.riptide.messagebus.domain

import com.google.gson.Gson
import com.google.protobuf.ByteString
import com.google.protobuf.MessageLite
import org.codetome.riptide.protobuf.*
import org.codetome.riptide.protobuf.PayloadKind.JSON
import org.codetome.riptide.protobuf.PayloadKind.PROTOBUF
import org.codetome.riptide.protobuf.ProcessState.*
import java.util.*

data class Message<T: Any> internal constructor(val payload: T,
                                           internal val payloadType: String,
                                           val group: String = ReservedGroups.RT_DEFAULT_GROUP.name,
                                           val version: String = ReservedVersions.RT_DEFAULT_VERSION.name,
                                           val operation: String = NO_OPERATION,
                                           val pid: UUID = NO_UUID,
                                           val customRoutingParameters: List<String> = listOf(),
                                           internal val senderId: UUID = NO_UUID,
                                           internal val payloadKind: PayloadKind = JSON,
                                           internal val processState: ProcessState = IN_PROGRESS) {

    internal var next: Optional<Message<T>> = Optional.empty()

    fun forName(name: Enum<*>) = forName(name.name)

    fun forName(name: String) = this.copy(operation = name)

    fun forGroup(group: Enum<*>) = forGroup(group.name)

    fun forGroup(group: String) = this.copy(group = group)

    fun forVersion(version: Enum<*>) = forVersion(version.name)

    fun forVersion(version: String) = this.copy(version = version)

    fun withCustomRoutingParameters(vararg params: String) = this.copy(customRoutingParameters = params.toList().map(String::toLowerCase))

    fun asProcessStart(): Message<T> {
        return this.copy(
                processState = STARTED,
                pid = UUID.randomUUID())
    }

    fun asProcessFinish(): Message<T> {
        return this.copy(processState = FINISHED)
    }

    internal fun fetchAsListWithSiblings(): List<Message<T>> {
        val first = this
        var currentMsg = first
        val result = mutableListOf(currentMsg)
        while (currentMsg.next.isPresent) {
            val next = currentMsg.next.get()
            currentMsg = next
            result.add(next)
        }
        return result
    }

    internal fun hasNoOperationNameDefined() = operation == NO_OPERATION

    internal fun hasPid() = pid != NO_UUID

    internal fun hasNoSender() = senderId == NO_UUID

    internal fun toProtobuf() = ProtoMessage.newBuilder()
            .setPayload(serializePayload(payloadKind, payload))
            .setPayloadType(payloadType)
            .setGroup(group)
            .setVersion(version)
            .setOperation(operation)
            .setPid(pid.toString())
            .setSenderId(senderId.toString())
            .setPayloadKind(payloadKind)
            .build()

    private fun serializePayload(payloadKind: PayloadKind, payload: T): ByteString? {
        return ByteString.copyFrom(when (payloadKind) {
            PROTOBUF -> {
                if (payload is MessageLite) {
                    payload.toByteArray()
                } else {
                    throw IllegalArgumentException()
                }
            }
            JSON -> {
                Gson().toJson(payload).toByteArray()
            }
            else -> {
                throw IllegalArgumentException()
            }
        })
    }

    companion object {

        fun <T : Any> create(payload: T) = Message(
                payload = payload,
                payloadType = payload::class.java.name)
                .asProcessStart()

        fun <T : Any> correlateTo(sourceMessage: Message<*>,
                                  responsePayload: T) = Message(
                payload = responsePayload,
                group = sourceMessage.group,
                version = sourceMessage.version,
                pid = sourceMessage.pid,
                payloadType = responsePayload::class.java.name,
                processState = IN_PROGRESS)

        fun <T : Any> createMultiple(payloads: List<T>): Message<T> {
            val pl = payloads.first()
            val head = Message(
                    payload = pl,
                    payloadType = pl::class.java.name)
            var currentMsg = head
            payloads.subList(1, payloads.size).forEach { payload ->
                currentMsg.next = Optional.of(Message(
                        payload = payload,
                        payloadType = pl::class.java.name
                ))
                currentMsg = currentMsg.next.get()
            }
            return head
        }

        internal fun fromProtobuf(protobufMessage: ProtoMessage): Message<Any> {
            val dataClass = Thread.currentThread().contextClassLoader.loadClass(protobufMessage.payloadType)
            val payload = deserializePayload(protobufMessage.payloadKind, protobufMessage.payload, dataClass)
            return Message(
                    payload = payload,
                    payloadType = dataClass.name,
                    group = protobufMessage.group,
                    version = protobufMessage.version,
                    operation = protobufMessage.operation,
                    pid = UUID.fromString(protobufMessage.pid),
                    senderId = UUID.fromString(protobufMessage.senderId),
                    payloadKind = protobufMessage.payloadKind
            )
        }

        private fun deserializePayload(payloadKind: PayloadKind, payload: ByteString, dataClass: Class<*>): Any {
            return when (payloadKind) {
                PROTOBUF -> {
                    try {
                        val method = dataClass.getDeclaredMethod("parseFrom", ByteString::class.java)
                        method.invoke(null, payload)
                    } catch (e: Exception) {
                        throw e
                    }
                }
                JSON -> {
                    Gson().fromJson(payload.toString("UTF-8"), dataClass)
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        private val NO_UUID = UUID.randomUUID()
        private val NO_OPERATION = UUID.randomUUID().toString()
    }
}

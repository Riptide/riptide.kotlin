package org.codetome.riptide.messagebus.domain

import org.codetome.riptide.protobuf.OperationDispatchType
import org.codetome.riptide.protobuf.OperationType
import org.junit.Test

class OperationDescriptorTest {

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndGroupNameIsBlank() {
        FULL_OP_DESCRIPTOR.copy(groupName = "  ").check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndVersionNameIsBlank() {
        FULL_OP_DESCRIPTOR.copy(versionName = "  ").check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndOperationNameIsBlank() {
        FULL_OP_DESCRIPTOR.copy(name = "  ").check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndOperationNameIsDefault() {
        FULL_OP_DESCRIPTOR.copy(name = OperationDescriptor.NO_NAME).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndOperationTypeIsUnrecognized() {
        FULL_OP_DESCRIPTOR.copy(operationType = OperationType.UNRECOGNIZED).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndOperationDispatchTypeIsUnrecognized() {
        FULL_OP_DESCRIPTOR.copy(dispatchType = OperationDispatchType.UNRECOGNIZED).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndNoConsumerOrProcessorIsPresent() {
        FULL_OP_DESCRIPTOR.copy(
                consumerFn = OperationDescriptor.NO_CONSUMER_FN,
                processorFn = OperationDescriptor.NO_PROCESSOR_FN).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndSourceClassIsNoClass() {
        FULL_OP_DESCRIPTOR.copy(sourceClass = OperationDescriptor.NoClass::class.java).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndThereIsResultClassWithoutProcessorFn() {
        FULL_OP_DESCRIPTOR.copy(processorFn = OperationDescriptor.NO_PROCESSOR_FN).check()
    }

    @Test(expected = IllegalStateException::class)
    fun shouldThrowExceptionWhenCheckIsCalledAndThereIsProcessorFnWithoutResultClass() {
        FULL_OP_DESCRIPTOR.copy(resultClass = OperationDescriptor.NoClass::class.java).check()
    }

    companion object {
        val FULL_OP_DESCRIPTOR = OperationDescriptor(
                groupName = "GROUP",
                versionName = "VERSION",
                name = "NAME",
                operationType = OperationType.CONSUME,
                sourceClass = String::class.java,
                resultClass = Int::class.java,
                consumerFn = {},
                processorFn = { throw RuntimeException() },
                dispatchType = OperationDispatchType.CONSUMER)
    }
}

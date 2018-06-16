// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/operation_dispatch_type.proto

package org.codetome.riptide.protobuf;

/**
 * Protobuf enum {@code data.OperationDispatchType}
 */
public enum OperationDispatchType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>CONSUMER = 0;</code>
   */
  CONSUMER(0),
  /**
   * <code>WORKER = 1;</code>
   */
  WORKER(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>CONSUMER = 0;</code>
   */
  public static final int CONSUMER_VALUE = 0;
  /**
   * <code>WORKER = 1;</code>
   */
  public static final int WORKER_VALUE = 1;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static OperationDispatchType valueOf(int value) {
    return forNumber(value);
  }

  public static OperationDispatchType forNumber(int value) {
    switch (value) {
      case 0: return CONSUMER;
      case 1: return WORKER;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<OperationDispatchType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      OperationDispatchType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<OperationDispatchType>() {
          public OperationDispatchType findValueByNumber(int number) {
            return OperationDispatchType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return org.codetome.riptide.protobuf.OperationDispatchTypeOuterClass.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final OperationDispatchType[] VALUES = values();

  public static OperationDispatchType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private OperationDispatchType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:data.OperationDispatchType)
}


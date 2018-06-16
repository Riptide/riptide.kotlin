// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/operation_type.proto

package org.codetome.riptide.protobuf;

/**
 * Protobuf enum {@code data.OperationType}
 */
public enum OperationType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>PRODUCE = 0;</code>
   */
  PRODUCE(0),
  /**
   * <code>CONSUME = 1;</code>
   */
  CONSUME(1),
  /**
   * <code>PROCESS = 2;</code>
   */
  PROCESS(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>PRODUCE = 0;</code>
   */
  public static final int PRODUCE_VALUE = 0;
  /**
   * <code>CONSUME = 1;</code>
   */
  public static final int CONSUME_VALUE = 1;
  /**
   * <code>PROCESS = 2;</code>
   */
  public static final int PROCESS_VALUE = 2;


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
  public static OperationType valueOf(int value) {
    return forNumber(value);
  }

  public static OperationType forNumber(int value) {
    switch (value) {
      case 0: return PRODUCE;
      case 1: return CONSUME;
      case 2: return PROCESS;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<OperationType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      OperationType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<OperationType>() {
          public OperationType findValueByNumber(int number) {
            return OperationType.forNumber(number);
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
    return org.codetome.riptide.protobuf.OperationTypeOuterClass.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final OperationType[] VALUES = values();

  public static OperationType valueOf(
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

  private OperationType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:data.OperationType)
}

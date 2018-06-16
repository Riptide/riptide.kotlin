// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/reserved_operations.proto

package org.codetome.riptide.protobuf;

/**
 * Protobuf enum {@code data.ReservedOperations}
 */
public enum ReservedOperations
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>RT_DEFAULT_OP = 0;</code>
   */
  RT_DEFAULT_OP(0),
  /**
   * <code>RT_PROCESS_STATE_CHANGE = 1;</code>
   */
  RT_PROCESS_STATE_CHANGE(1),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>RT_DEFAULT_OP = 0;</code>
   */
  public static final int RT_DEFAULT_OP_VALUE = 0;
  /**
   * <code>RT_PROCESS_STATE_CHANGE = 1;</code>
   */
  public static final int RT_PROCESS_STATE_CHANGE_VALUE = 1;


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
  public static ReservedOperations valueOf(int value) {
    return forNumber(value);
  }

  public static ReservedOperations forNumber(int value) {
    switch (value) {
      case 0: return RT_DEFAULT_OP;
      case 1: return RT_PROCESS_STATE_CHANGE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ReservedOperations>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ReservedOperations> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ReservedOperations>() {
          public ReservedOperations findValueByNumber(int number) {
            return ReservedOperations.forNumber(number);
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
    return org.codetome.riptide.protobuf.ReservedOperationsOuterClass.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final ReservedOperations[] VALUES = values();

  public static ReservedOperations valueOf(
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

  private ReservedOperations(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:data.ReservedOperations)
}


// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/message.proto

package org.codetome.riptide.protobuf;

/**
 * Protobuf type {@code data.ProtoMessage}
 */
public  final class ProtoMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:data.ProtoMessage)
    ProtoMessageOrBuilder {
  // Use ProtoMessage.newBuilder() to construct.
  private ProtoMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProtoMessage() {
    group_ = "";
    version_ = "";
    operation_ = "";
    pid_ = "";
    senderId_ = "";
    payloadType_ = "";
    payloadKind_ = 0;
    payload_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ProtoMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            group_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            version_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            operation_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            pid_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            senderId_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            payloadType_ = s;
            break;
          }
          case 56: {
            int rawValue = input.readEnum();

            payloadKind_ = rawValue;
            break;
          }
          case 66: {

            payload_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.codetome.riptide.protobuf.Message.internal_static_data_ProtoMessage_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.codetome.riptide.protobuf.Message.internal_static_data_ProtoMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.codetome.riptide.protobuf.ProtoMessage.class, org.codetome.riptide.protobuf.ProtoMessage.Builder.class);
  }

  public static final int GROUP_FIELD_NUMBER = 1;
  private volatile java.lang.Object group_;
  /**
   * <code>optional string group = 1;</code>
   */
  public java.lang.String getGroup() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      group_ = s;
      return s;
    }
  }
  /**
   * <code>optional string group = 1;</code>
   */
  public com.google.protobuf.ByteString
      getGroupBytes() {
    java.lang.Object ref = group_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      group_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VERSION_FIELD_NUMBER = 2;
  private volatile java.lang.Object version_;
  /**
   * <code>optional string version = 2;</code>
   */
  public java.lang.String getVersion() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      version_ = s;
      return s;
    }
  }
  /**
   * <code>optional string version = 2;</code>
   */
  public com.google.protobuf.ByteString
      getVersionBytes() {
    java.lang.Object ref = version_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      version_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int OPERATION_FIELD_NUMBER = 3;
  private volatile java.lang.Object operation_;
  /**
   * <code>optional string operation = 3;</code>
   */
  public java.lang.String getOperation() {
    java.lang.Object ref = operation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      operation_ = s;
      return s;
    }
  }
  /**
   * <code>optional string operation = 3;</code>
   */
  public com.google.protobuf.ByteString
      getOperationBytes() {
    java.lang.Object ref = operation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      operation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PID_FIELD_NUMBER = 4;
  private volatile java.lang.Object pid_;
  /**
   * <code>optional string pid = 4;</code>
   */
  public java.lang.String getPid() {
    java.lang.Object ref = pid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      pid_ = s;
      return s;
    }
  }
  /**
   * <code>optional string pid = 4;</code>
   */
  public com.google.protobuf.ByteString
      getPidBytes() {
    java.lang.Object ref = pid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      pid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SENDER_ID_FIELD_NUMBER = 5;
  private volatile java.lang.Object senderId_;
  /**
   * <code>optional string sender_id = 5;</code>
   */
  public java.lang.String getSenderId() {
    java.lang.Object ref = senderId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      senderId_ = s;
      return s;
    }
  }
  /**
   * <code>optional string sender_id = 5;</code>
   */
  public com.google.protobuf.ByteString
      getSenderIdBytes() {
    java.lang.Object ref = senderId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      senderId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAYLOAD_TYPE_FIELD_NUMBER = 6;
  private volatile java.lang.Object payloadType_;
  /**
   * <code>optional string payload_type = 6;</code>
   */
  public java.lang.String getPayloadType() {
    java.lang.Object ref = payloadType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      payloadType_ = s;
      return s;
    }
  }
  /**
   * <code>optional string payload_type = 6;</code>
   */
  public com.google.protobuf.ByteString
      getPayloadTypeBytes() {
    java.lang.Object ref = payloadType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      payloadType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAYLOAD_KIND_FIELD_NUMBER = 7;
  private int payloadKind_;
  /**
   * <code>optional .data.PayloadKind payload_kind = 7;</code>
   */
  public int getPayloadKindValue() {
    return payloadKind_;
  }
  /**
   * <code>optional .data.PayloadKind payload_kind = 7;</code>
   */
  public org.codetome.riptide.protobuf.PayloadKind getPayloadKind() {
    org.codetome.riptide.protobuf.PayloadKind result = org.codetome.riptide.protobuf.PayloadKind.valueOf(payloadKind_);
    return result == null ? org.codetome.riptide.protobuf.PayloadKind.UNRECOGNIZED : result;
  }

  public static final int PAYLOAD_FIELD_NUMBER = 8;
  private com.google.protobuf.ByteString payload_;
  /**
   * <code>optional bytes payload = 8;</code>
   */
  public com.google.protobuf.ByteString getPayload() {
    return payload_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getGroupBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, group_);
    }
    if (!getVersionBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, version_);
    }
    if (!getOperationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, operation_);
    }
    if (!getPidBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, pid_);
    }
    if (!getSenderIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, senderId_);
    }
    if (!getPayloadTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, payloadType_);
    }
    if (payloadKind_ != org.codetome.riptide.protobuf.PayloadKind.JSON.getNumber()) {
      output.writeEnum(7, payloadKind_);
    }
    if (!payload_.isEmpty()) {
      output.writeBytes(8, payload_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getGroupBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, group_);
    }
    if (!getVersionBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, version_);
    }
    if (!getOperationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, operation_);
    }
    if (!getPidBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, pid_);
    }
    if (!getSenderIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, senderId_);
    }
    if (!getPayloadTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, payloadType_);
    }
    if (payloadKind_ != org.codetome.riptide.protobuf.PayloadKind.JSON.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(7, payloadKind_);
    }
    if (!payload_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(8, payload_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.codetome.riptide.protobuf.ProtoMessage)) {
      return super.equals(obj);
    }
    org.codetome.riptide.protobuf.ProtoMessage other = (org.codetome.riptide.protobuf.ProtoMessage) obj;

    boolean result = true;
    result = result && getGroup()
        .equals(other.getGroup());
    result = result && getVersion()
        .equals(other.getVersion());
    result = result && getOperation()
        .equals(other.getOperation());
    result = result && getPid()
        .equals(other.getPid());
    result = result && getSenderId()
        .equals(other.getSenderId());
    result = result && getPayloadType()
        .equals(other.getPayloadType());
    result = result && payloadKind_ == other.payloadKind_;
    result = result && getPayload()
        .equals(other.getPayload());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + GROUP_FIELD_NUMBER;
    hash = (53 * hash) + getGroup().hashCode();
    hash = (37 * hash) + VERSION_FIELD_NUMBER;
    hash = (53 * hash) + getVersion().hashCode();
    hash = (37 * hash) + OPERATION_FIELD_NUMBER;
    hash = (53 * hash) + getOperation().hashCode();
    hash = (37 * hash) + PID_FIELD_NUMBER;
    hash = (53 * hash) + getPid().hashCode();
    hash = (37 * hash) + SENDER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getSenderId().hashCode();
    hash = (37 * hash) + PAYLOAD_TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getPayloadType().hashCode();
    hash = (37 * hash) + PAYLOAD_KIND_FIELD_NUMBER;
    hash = (53 * hash) + payloadKind_;
    hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getPayload().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProtoMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.codetome.riptide.protobuf.ProtoMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code data.ProtoMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:data.ProtoMessage)
      org.codetome.riptide.protobuf.ProtoMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.codetome.riptide.protobuf.Message.internal_static_data_ProtoMessage_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.codetome.riptide.protobuf.Message.internal_static_data_ProtoMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.codetome.riptide.protobuf.ProtoMessage.class, org.codetome.riptide.protobuf.ProtoMessage.Builder.class);
    }

    // Construct using org.codetome.riptide.protobuf.ProtoMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      group_ = "";

      version_ = "";

      operation_ = "";

      pid_ = "";

      senderId_ = "";

      payloadType_ = "";

      payloadKind_ = 0;

      payload_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.codetome.riptide.protobuf.Message.internal_static_data_ProtoMessage_descriptor;
    }

    public org.codetome.riptide.protobuf.ProtoMessage getDefaultInstanceForType() {
      return org.codetome.riptide.protobuf.ProtoMessage.getDefaultInstance();
    }

    public org.codetome.riptide.protobuf.ProtoMessage build() {
      org.codetome.riptide.protobuf.ProtoMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.codetome.riptide.protobuf.ProtoMessage buildPartial() {
      org.codetome.riptide.protobuf.ProtoMessage result = new org.codetome.riptide.protobuf.ProtoMessage(this);
      result.group_ = group_;
      result.version_ = version_;
      result.operation_ = operation_;
      result.pid_ = pid_;
      result.senderId_ = senderId_;
      result.payloadType_ = payloadType_;
      result.payloadKind_ = payloadKind_;
      result.payload_ = payload_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.codetome.riptide.protobuf.ProtoMessage) {
        return mergeFrom((org.codetome.riptide.protobuf.ProtoMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.codetome.riptide.protobuf.ProtoMessage other) {
      if (other == org.codetome.riptide.protobuf.ProtoMessage.getDefaultInstance()) return this;
      if (!other.getGroup().isEmpty()) {
        group_ = other.group_;
        onChanged();
      }
      if (!other.getVersion().isEmpty()) {
        version_ = other.version_;
        onChanged();
      }
      if (!other.getOperation().isEmpty()) {
        operation_ = other.operation_;
        onChanged();
      }
      if (!other.getPid().isEmpty()) {
        pid_ = other.pid_;
        onChanged();
      }
      if (!other.getSenderId().isEmpty()) {
        senderId_ = other.senderId_;
        onChanged();
      }
      if (!other.getPayloadType().isEmpty()) {
        payloadType_ = other.payloadType_;
        onChanged();
      }
      if (other.payloadKind_ != 0) {
        setPayloadKindValue(other.getPayloadKindValue());
      }
      if (other.getPayload() != com.google.protobuf.ByteString.EMPTY) {
        setPayload(other.getPayload());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.codetome.riptide.protobuf.ProtoMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.codetome.riptide.protobuf.ProtoMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object group_ = "";
    /**
     * <code>optional string group = 1;</code>
     */
    public java.lang.String getGroup() {
      java.lang.Object ref = group_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        group_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string group = 1;</code>
     */
    public com.google.protobuf.ByteString
        getGroupBytes() {
      java.lang.Object ref = group_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        group_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string group = 1;</code>
     */
    public Builder setGroup(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      group_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string group = 1;</code>
     */
    public Builder clearGroup() {
      
      group_ = getDefaultInstance().getGroup();
      onChanged();
      return this;
    }
    /**
     * <code>optional string group = 1;</code>
     */
    public Builder setGroupBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      group_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object version_ = "";
    /**
     * <code>optional string version = 2;</code>
     */
    public java.lang.String getVersion() {
      java.lang.Object ref = version_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        version_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string version = 2;</code>
     */
    public com.google.protobuf.ByteString
        getVersionBytes() {
      java.lang.Object ref = version_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        version_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string version = 2;</code>
     */
    public Builder setVersion(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      version_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string version = 2;</code>
     */
    public Builder clearVersion() {
      
      version_ = getDefaultInstance().getVersion();
      onChanged();
      return this;
    }
    /**
     * <code>optional string version = 2;</code>
     */
    public Builder setVersionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      version_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object operation_ = "";
    /**
     * <code>optional string operation = 3;</code>
     */
    public java.lang.String getOperation() {
      java.lang.Object ref = operation_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        operation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string operation = 3;</code>
     */
    public com.google.protobuf.ByteString
        getOperationBytes() {
      java.lang.Object ref = operation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        operation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string operation = 3;</code>
     */
    public Builder setOperation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      operation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string operation = 3;</code>
     */
    public Builder clearOperation() {
      
      operation_ = getDefaultInstance().getOperation();
      onChanged();
      return this;
    }
    /**
     * <code>optional string operation = 3;</code>
     */
    public Builder setOperationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      operation_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object pid_ = "";
    /**
     * <code>optional string pid = 4;</code>
     */
    public java.lang.String getPid() {
      java.lang.Object ref = pid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        pid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string pid = 4;</code>
     */
    public com.google.protobuf.ByteString
        getPidBytes() {
      java.lang.Object ref = pid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        pid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string pid = 4;</code>
     */
    public Builder setPid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      pid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string pid = 4;</code>
     */
    public Builder clearPid() {
      
      pid_ = getDefaultInstance().getPid();
      onChanged();
      return this;
    }
    /**
     * <code>optional string pid = 4;</code>
     */
    public Builder setPidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      pid_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object senderId_ = "";
    /**
     * <code>optional string sender_id = 5;</code>
     */
    public java.lang.String getSenderId() {
      java.lang.Object ref = senderId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        senderId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string sender_id = 5;</code>
     */
    public com.google.protobuf.ByteString
        getSenderIdBytes() {
      java.lang.Object ref = senderId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        senderId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string sender_id = 5;</code>
     */
    public Builder setSenderId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      senderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string sender_id = 5;</code>
     */
    public Builder clearSenderId() {
      
      senderId_ = getDefaultInstance().getSenderId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string sender_id = 5;</code>
     */
    public Builder setSenderIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      senderId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object payloadType_ = "";
    /**
     * <code>optional string payload_type = 6;</code>
     */
    public java.lang.String getPayloadType() {
      java.lang.Object ref = payloadType_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        payloadType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string payload_type = 6;</code>
     */
    public com.google.protobuf.ByteString
        getPayloadTypeBytes() {
      java.lang.Object ref = payloadType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        payloadType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string payload_type = 6;</code>
     */
    public Builder setPayloadType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      payloadType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string payload_type = 6;</code>
     */
    public Builder clearPayloadType() {
      
      payloadType_ = getDefaultInstance().getPayloadType();
      onChanged();
      return this;
    }
    /**
     * <code>optional string payload_type = 6;</code>
     */
    public Builder setPayloadTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      payloadType_ = value;
      onChanged();
      return this;
    }

    private int payloadKind_ = 0;
    /**
     * <code>optional .data.PayloadKind payload_kind = 7;</code>
     */
    public int getPayloadKindValue() {
      return payloadKind_;
    }
    /**
     * <code>optional .data.PayloadKind payload_kind = 7;</code>
     */
    public Builder setPayloadKindValue(int value) {
      payloadKind_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional .data.PayloadKind payload_kind = 7;</code>
     */
    public org.codetome.riptide.protobuf.PayloadKind getPayloadKind() {
      org.codetome.riptide.protobuf.PayloadKind result = org.codetome.riptide.protobuf.PayloadKind.valueOf(payloadKind_);
      return result == null ? org.codetome.riptide.protobuf.PayloadKind.UNRECOGNIZED : result;
    }
    /**
     * <code>optional .data.PayloadKind payload_kind = 7;</code>
     */
    public Builder setPayloadKind(org.codetome.riptide.protobuf.PayloadKind value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      payloadKind_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>optional .data.PayloadKind payload_kind = 7;</code>
     */
    public Builder clearPayloadKind() {
      
      payloadKind_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>optional bytes payload = 8;</code>
     */
    public com.google.protobuf.ByteString getPayload() {
      return payload_;
    }
    /**
     * <code>optional bytes payload = 8;</code>
     */
    public Builder setPayload(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      payload_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional bytes payload = 8;</code>
     */
    public Builder clearPayload() {
      
      payload_ = getDefaultInstance().getPayload();
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:data.ProtoMessage)
  }

  // @@protoc_insertion_point(class_scope:data.ProtoMessage)
  private static final org.codetome.riptide.protobuf.ProtoMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.codetome.riptide.protobuf.ProtoMessage();
  }

  public static org.codetome.riptide.protobuf.ProtoMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProtoMessage>
      PARSER = new com.google.protobuf.AbstractParser<ProtoMessage>() {
    public ProtoMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProtoMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProtoMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ProtoMessage> getParserForType() {
    return PARSER;
  }

  public org.codetome.riptide.protobuf.ProtoMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}


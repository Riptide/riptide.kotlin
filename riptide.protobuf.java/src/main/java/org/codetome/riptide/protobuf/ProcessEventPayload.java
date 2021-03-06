// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/process_event_payload.proto

package org.codetome.riptide.protobuf;

/**
 * Protobuf type {@code data.ProcessEventPayload}
 */
public  final class ProcessEventPayload extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:data.ProcessEventPayload)
    ProcessEventPayloadOrBuilder {
  // Use ProcessEventPayload.newBuilder() to construct.
  private ProcessEventPayload(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ProcessEventPayload() {
    message_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ProcessEventPayload(
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

            message_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              extraData_ = com.google.protobuf.MapField.newMapField(
                  ExtraDataDefaultEntryHolder.defaultEntry);
              mutable_bitField0_ |= 0x00000002;
            }
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
            extraData = input.readMessage(
                ExtraDataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
            extraData_.getMutableMap().put(extraData.getKey(), extraData.getValue());
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
    return org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 3:
        return internalGetExtraData();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.codetome.riptide.protobuf.ProcessEventPayload.class, org.codetome.riptide.protobuf.ProcessEventPayload.Builder.class);
  }

  private int bitField0_;
  public static final int MESSAGE_FIELD_NUMBER = 1;
  private volatile java.lang.Object message_;
  /**
   * <code>optional string message = 1;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>optional string message = 1;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXTRA_DATA_FIELD_NUMBER = 3;
  private static final class ExtraDataDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, java.lang.String> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, java.lang.String>newDefaultInstance(
                org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_ExtraDataEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  private com.google.protobuf.MapField<
      java.lang.String, java.lang.String> extraData_;
  private com.google.protobuf.MapField<java.lang.String, java.lang.String>
  internalGetExtraData() {
    if (extraData_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          ExtraDataDefaultEntryHolder.defaultEntry);
    }
    return extraData_;
  }

  public int getExtraDataCount() {
    return internalGetExtraData().getMap().size();
  }
  /**
   * <code>map&lt;string, string&gt; extra_data = 3;</code>
   */

  public boolean containsExtraData(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    return internalGetExtraData().getMap().containsKey(key);
  }
  /**
   * Use {@link #getExtraDataMap()} instead.
   */
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, java.lang.String> getExtraData() {
    return getExtraDataMap();
  }
  /**
   * <code>map&lt;string, string&gt; extra_data = 3;</code>
   */

  public java.util.Map<java.lang.String, java.lang.String> getExtraDataMap() {
    return internalGetExtraData().getMap();
  }
  /**
   * <code>map&lt;string, string&gt; extra_data = 3;</code>
   */

  public java.lang.String getExtraDataOrDefault(
      java.lang.String key,
      java.lang.String defaultValue) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetExtraData().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, string&gt; extra_data = 3;</code>
   */

  public java.lang.String getExtraDataOrThrow(
      java.lang.String key) {
    if (key == null) { throw new java.lang.NullPointerException(); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetExtraData().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
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
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, message_);
    }
    for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
         : internalGetExtraData().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
      extraData = ExtraDataDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      output.writeMessage(3, extraData);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, message_);
    }
    for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
         : internalGetExtraData().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
      extraData = ExtraDataDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, extraData);
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
    if (!(obj instanceof org.codetome.riptide.protobuf.ProcessEventPayload)) {
      return super.equals(obj);
    }
    org.codetome.riptide.protobuf.ProcessEventPayload other = (org.codetome.riptide.protobuf.ProcessEventPayload) obj;

    boolean result = true;
    result = result && getMessage()
        .equals(other.getMessage());
    result = result && internalGetExtraData().equals(
        other.internalGetExtraData());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (!internalGetExtraData().getMap().isEmpty()) {
      hash = (37 * hash) + EXTRA_DATA_FIELD_NUMBER;
      hash = (53 * hash) + internalGetExtraData().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.codetome.riptide.protobuf.ProcessEventPayload parseFrom(
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
  public static Builder newBuilder(org.codetome.riptide.protobuf.ProcessEventPayload prototype) {
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
   * Protobuf type {@code data.ProcessEventPayload}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:data.ProcessEventPayload)
      org.codetome.riptide.protobuf.ProcessEventPayloadOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetExtraData();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetMutableExtraData();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.codetome.riptide.protobuf.ProcessEventPayload.class, org.codetome.riptide.protobuf.ProcessEventPayload.Builder.class);
    }

    // Construct using org.codetome.riptide.protobuf.ProcessEventPayload.newBuilder()
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
      message_ = "";

      internalGetMutableExtraData().clear();
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.codetome.riptide.protobuf.ProcessEventPayloadOuterClass.internal_static_data_ProcessEventPayload_descriptor;
    }

    public org.codetome.riptide.protobuf.ProcessEventPayload getDefaultInstanceForType() {
      return org.codetome.riptide.protobuf.ProcessEventPayload.getDefaultInstance();
    }

    public org.codetome.riptide.protobuf.ProcessEventPayload build() {
      org.codetome.riptide.protobuf.ProcessEventPayload result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.codetome.riptide.protobuf.ProcessEventPayload buildPartial() {
      org.codetome.riptide.protobuf.ProcessEventPayload result = new org.codetome.riptide.protobuf.ProcessEventPayload(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.message_ = message_;
      result.extraData_ = internalGetExtraData();
      result.extraData_.makeImmutable();
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof org.codetome.riptide.protobuf.ProcessEventPayload) {
        return mergeFrom((org.codetome.riptide.protobuf.ProcessEventPayload)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.codetome.riptide.protobuf.ProcessEventPayload other) {
      if (other == org.codetome.riptide.protobuf.ProcessEventPayload.getDefaultInstance()) return this;
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      internalGetMutableExtraData().mergeFrom(
          other.internalGetExtraData());
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
      org.codetome.riptide.protobuf.ProcessEventPayload parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.codetome.riptide.protobuf.ProcessEventPayload) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object message_ = "";
    /**
     * <code>optional string message = 1;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string message = 1;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string message = 1;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string message = 1;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>optional string message = 1;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> extraData_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetExtraData() {
      if (extraData_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            ExtraDataDefaultEntryHolder.defaultEntry);
      }
      return extraData_;
    }
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetMutableExtraData() {
      onChanged();;
      if (extraData_ == null) {
        extraData_ = com.google.protobuf.MapField.newMapField(
            ExtraDataDefaultEntryHolder.defaultEntry);
      }
      if (!extraData_.isMutable()) {
        extraData_ = extraData_.copy();
      }
      return extraData_;
    }

    public int getExtraDataCount() {
      return internalGetExtraData().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public boolean containsExtraData(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetExtraData().getMap().containsKey(key);
    }
    /**
     * Use {@link #getExtraDataMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getExtraData() {
      return getExtraDataMap();
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public java.util.Map<java.lang.String, java.lang.String> getExtraDataMap() {
      return internalGetExtraData().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public java.lang.String getExtraDataOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetExtraData().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public java.lang.String getExtraDataOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetExtraData().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearExtraData() {
      getMutableExtraData().clear();
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public Builder removeExtraData(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      getMutableExtraData().remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String>
    getMutableExtraData() {
      return internalGetMutableExtraData().getMutableMap();
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */
    public Builder putExtraData(
        java.lang.String key,
        java.lang.String value) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      if (value == null) { throw new java.lang.NullPointerException(); }
      getMutableExtraData().put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; extra_data = 3;</code>
     */

    public Builder putAllExtraData(
        java.util.Map<java.lang.String, java.lang.String> values) {
      getMutableExtraData().putAll(values);
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


    // @@protoc_insertion_point(builder_scope:data.ProcessEventPayload)
  }

  // @@protoc_insertion_point(class_scope:data.ProcessEventPayload)
  private static final org.codetome.riptide.protobuf.ProcessEventPayload DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.codetome.riptide.protobuf.ProcessEventPayload();
  }

  public static org.codetome.riptide.protobuf.ProcessEventPayload getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ProcessEventPayload>
      PARSER = new com.google.protobuf.AbstractParser<ProcessEventPayload>() {
    public ProcessEventPayload parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProcessEventPayload(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ProcessEventPayload> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ProcessEventPayload> getParserForType() {
    return PARSER;
  }

  public org.codetome.riptide.protobuf.ProcessEventPayload getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}


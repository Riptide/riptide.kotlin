// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/message.proto

package org.codetome.riptide.protobuf;

public final class Message {
  private Message() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_data_ProtoMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_data_ProtoMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022data/message.proto\022\004data\032\027data/payload" +
      "_kind.proto\"\261\001\n\014ProtoMessage\022\r\n\005group\030\001 " +
      "\001(\t\022\017\n\007version\030\002 \001(\t\022\021\n\toperation\030\003 \001(\t\022" +
      "\013\n\003pid\030\004 \001(\t\022\021\n\tsender_id\030\005 \001(\t\022\024\n\014paylo" +
      "ad_type\030\006 \001(\t\022\'\n\014payload_kind\030\007 \001(\0162\021.da" +
      "ta.PayloadKind\022\017\n\007payload\030\010 \001(\014B#\n\035org.c" +
      "odetome.riptide.protobufH\001P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.codetome.riptide.protobuf.PayloadKindOuterClass.getDescriptor(),
        }, assigner);
    internal_static_data_ProtoMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_data_ProtoMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_data_ProtoMessage_descriptor,
        new java.lang.String[] { "Group", "Version", "Operation", "Pid", "SenderId", "PayloadType", "PayloadKind", "Payload", });
    org.codetome.riptide.protobuf.PayloadKindOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
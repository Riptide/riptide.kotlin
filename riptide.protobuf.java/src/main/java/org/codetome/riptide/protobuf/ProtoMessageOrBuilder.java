// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data/message.proto

package org.codetome.riptide.protobuf;

public interface ProtoMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:data.ProtoMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string group = 1;</code>
   */
  java.lang.String getGroup();
  /**
   * <code>optional string group = 1;</code>
   */
  com.google.protobuf.ByteString
      getGroupBytes();

  /**
   * <code>optional string version = 2;</code>
   */
  java.lang.String getVersion();
  /**
   * <code>optional string version = 2;</code>
   */
  com.google.protobuf.ByteString
      getVersionBytes();

  /**
   * <code>optional string operation = 3;</code>
   */
  java.lang.String getOperation();
  /**
   * <code>optional string operation = 3;</code>
   */
  com.google.protobuf.ByteString
      getOperationBytes();

  /**
   * <code>optional string pid = 4;</code>
   */
  java.lang.String getPid();
  /**
   * <code>optional string pid = 4;</code>
   */
  com.google.protobuf.ByteString
      getPidBytes();

  /**
   * <code>optional string sender_id = 5;</code>
   */
  java.lang.String getSenderId();
  /**
   * <code>optional string sender_id = 5;</code>
   */
  com.google.protobuf.ByteString
      getSenderIdBytes();

  /**
   * <code>optional string payload_type = 6;</code>
   */
  java.lang.String getPayloadType();
  /**
   * <code>optional string payload_type = 6;</code>
   */
  com.google.protobuf.ByteString
      getPayloadTypeBytes();

  /**
   * <code>optional .data.PayloadKind payload_kind = 7;</code>
   */
  int getPayloadKindValue();
  /**
   * <code>optional .data.PayloadKind payload_kind = 7;</code>
   */
  org.codetome.riptide.protobuf.PayloadKind getPayloadKind();

  /**
   * <code>optional bytes payload = 8;</code>
   */
  com.google.protobuf.ByteString getPayload();
}
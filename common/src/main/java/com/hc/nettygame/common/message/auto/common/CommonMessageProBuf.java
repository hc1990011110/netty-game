// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common.proto

package com.hc.nettygame.common.message.auto.common;

public final class CommonMessageProBuf {
  private CommonMessageProBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CommonResponseServerProBufOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommonResponseServerProBuf)
      com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code CommonResponseServerProBuf}
   */
  public static final class CommonResponseServerProBuf extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:CommonResponseServerProBuf)
      CommonResponseServerProBufOrBuilder {
    // Use CommonResponseServerProBuf.newBuilder() to construct.
    private CommonResponseServerProBuf(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CommonResponseServerProBuf(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CommonResponseServerProBuf defaultInstance;
    public static CommonResponseServerProBuf getDefaultInstance() {
      return defaultInstance;
    }

    public CommonResponseServerProBuf getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CommonResponseServerProBuf(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonResponseServerProBuf_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonResponseServerProBuf_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.class, com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.Builder.class);
    }

    public static com.google.protobuf.Parser<CommonResponseServerProBuf> PARSER =
        new com.google.protobuf.AbstractParser<CommonResponseServerProBuf>() {
      public CommonResponseServerProBuf parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommonResponseServerProBuf(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CommonResponseServerProBuf> getParserForType() {
      return PARSER;
    }

    private void initFields() {
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
      getSerializedSize();
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code CommonResponseServerProBuf}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommonResponseServerProBuf)
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBufOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonResponseServerProBuf_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonResponseServerProBuf_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.class, com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.Builder.class);
      }

      // Construct using com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonResponseServerProBuf_descriptor;
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf getDefaultInstanceForType() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.getDefaultInstance();
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf build() {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf buildPartial() {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf result = new com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf(this);
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf) {
          return mergeFrom((com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf other) {
        if (other == com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf.getDefaultInstance()) return this;
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonResponseServerProBuf) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CommonResponseServerProBuf)
    }

    static {
      defaultInstance = new CommonResponseServerProBuf(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CommonResponseServerProBuf)
  }

  public interface CommonErrorResponseServerProBufOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommonErrorResponseServerProBuf)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 state = 1;</code>
     */
    boolean hasState();
    /**
     * <code>required int32 state = 1;</code>
     */
    int getState();

    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    boolean hasArg();
    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    java.lang.String getArg();
    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    com.google.protobuf.ByteString
        getArgBytes();
  }
  /**
   * Protobuf type {@code CommonErrorResponseServerProBuf}
   */
  public static final class CommonErrorResponseServerProBuf extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:CommonErrorResponseServerProBuf)
      CommonErrorResponseServerProBufOrBuilder {
    // Use CommonErrorResponseServerProBuf.newBuilder() to construct.
    private CommonErrorResponseServerProBuf(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CommonErrorResponseServerProBuf(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CommonErrorResponseServerProBuf defaultInstance;
    public static CommonErrorResponseServerProBuf getDefaultInstance() {
      return defaultInstance;
    }

    public CommonErrorResponseServerProBuf getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CommonErrorResponseServerProBuf(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              state_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              arg_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonErrorResponseServerProBuf_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonErrorResponseServerProBuf_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.class, com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.Builder.class);
    }

    public static com.google.protobuf.Parser<CommonErrorResponseServerProBuf> PARSER =
        new com.google.protobuf.AbstractParser<CommonErrorResponseServerProBuf>() {
      public CommonErrorResponseServerProBuf parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommonErrorResponseServerProBuf(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CommonErrorResponseServerProBuf> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int STATE_FIELD_NUMBER = 1;
    private int state_;
    /**
     * <code>required int32 state = 1;</code>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 state = 1;</code>
     */
    public int getState() {
      return state_;
    }

    public static final int ARG_FIELD_NUMBER = 2;
    private java.lang.Object arg_;
    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    public boolean hasArg() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    public java.lang.String getArg() {
      java.lang.Object ref = arg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          arg_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string arg = 2;</code>
     *
     * <pre>
     *错误参数，格式：xx,xxx,xxxx
     * </pre>
     */
    public com.google.protobuf.ByteString
        getArgBytes() {
      java.lang.Object ref = arg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        arg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      state_ = 0;
      arg_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasState()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, state_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getArgBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, state_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getArgBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code CommonErrorResponseServerProBuf}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommonErrorResponseServerProBuf)
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBufOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonErrorResponseServerProBuf_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonErrorResponseServerProBuf_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.class, com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.Builder.class);
      }

      // Construct using com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        state_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        arg_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.internal_static_CommonErrorResponseServerProBuf_descriptor;
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf getDefaultInstanceForType() {
        return com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.getDefaultInstance();
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf build() {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf buildPartial() {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf result = new com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.state_ = state_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.arg_ = arg_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf) {
          return mergeFrom((com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf other) {
        if (other == com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf.getDefaultInstance()) return this;
        if (other.hasState()) {
          setState(other.getState());
        }
        if (other.hasArg()) {
          bitField0_ |= 0x00000002;
          arg_ = other.arg_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasState()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.hc.nettygame.common.message.auto.common.CommonMessageProBuf.CommonErrorResponseServerProBuf) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int state_ ;
      /**
       * <code>required int32 state = 1;</code>
       */
      public boolean hasState() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 state = 1;</code>
       */
      public int getState() {
        return state_;
      }
      /**
       * <code>required int32 state = 1;</code>
       */
      public Builder setState(int value) {
        bitField0_ |= 0x00000001;
        state_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 state = 1;</code>
       */
      public Builder clearState() {
        bitField0_ = (bitField0_ & ~0x00000001);
        state_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object arg_ = "";
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public boolean hasArg() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public java.lang.String getArg() {
        java.lang.Object ref = arg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            arg_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public com.google.protobuf.ByteString
          getArgBytes() {
        java.lang.Object ref = arg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          arg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public Builder setArg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        arg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public Builder clearArg() {
        bitField0_ = (bitField0_ & ~0x00000002);
        arg_ = getDefaultInstance().getArg();
        onChanged();
        return this;
      }
      /**
       * <code>optional string arg = 2;</code>
       *
       * <pre>
       *错误参数，格式：xx,xxx,xxxx
       * </pre>
       */
      public Builder setArgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        arg_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CommonErrorResponseServerProBuf)
    }

    static {
      defaultInstance = new CommonErrorResponseServerProBuf(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CommonErrorResponseServerProBuf)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommonResponseServerProBuf_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CommonResponseServerProBuf_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommonErrorResponseServerProBuf_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CommonErrorResponseServerProBuf_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014common.proto\"\034\n\032CommonResponseServerPr" +
      "oBuf\"=\n\037CommonErrorResponseServerProBuf\022" +
      "\r\n\005state\030\001 \002(\005\022\013\n\003arg\030\002 \001(\tBB\n+com.hc.ne" +
      "ttygame.common.message.auto.commonB\023Comm" +
      "onMessageProBuf"
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
        }, assigner);
    internal_static_CommonResponseServerProBuf_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommonResponseServerProBuf_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_CommonResponseServerProBuf_descriptor,
        new java.lang.String[] { });
    internal_static_CommonErrorResponseServerProBuf_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CommonErrorResponseServerProBuf_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_CommonErrorResponseServerProBuf_descriptor,
        new java.lang.String[] { "State", "Arg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

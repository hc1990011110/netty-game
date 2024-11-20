// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tcponline-server.proto

package com.hc.nettygame.generatecode.message.auto.tcp.online.server;

public final class OnlineTCPServerProBuf {
  private OnlineTCPServerProBuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface OnlineHeartTCPServerProBufOrBuilder extends
      // @@protoc_insertion_point(interface_extends:OnlineHeartTCPServerProBuf)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 playerId = 1;</code>
     */
    boolean hasPlayerId();
    /**
     * <code>required int64 playerId = 1;</code>
     */
    long getPlayerId();

    /**
     * <code>required int32 tocken = 2;</code>
     */
    boolean hasTocken();
    /**
     * <code>required int32 tocken = 2;</code>
     */
    int getTocken();
  }
  /**
   * Protobuf type {@code OnlineHeartTCPServerProBuf}
   */
  public static final class OnlineHeartTCPServerProBuf extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:OnlineHeartTCPServerProBuf)
      OnlineHeartTCPServerProBufOrBuilder {
    // Use OnlineHeartTCPServerProBuf.newBuilder() to construct.
    private OnlineHeartTCPServerProBuf(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private OnlineHeartTCPServerProBuf(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final OnlineHeartTCPServerProBuf defaultInstance;
    public static OnlineHeartTCPServerProBuf getDefaultInstance() {
      return defaultInstance;
    }

    public OnlineHeartTCPServerProBuf getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private OnlineHeartTCPServerProBuf(
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
              playerId_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              tocken_ = input.readInt32();
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
      return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.internal_static_OnlineHeartTCPServerProBuf_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.internal_static_OnlineHeartTCPServerProBuf_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.class, com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.Builder.class);
    }

    public static com.google.protobuf.Parser<OnlineHeartTCPServerProBuf> PARSER =
        new com.google.protobuf.AbstractParser<OnlineHeartTCPServerProBuf>() {
      public OnlineHeartTCPServerProBuf parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new OnlineHeartTCPServerProBuf(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<OnlineHeartTCPServerProBuf> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PLAYERID_FIELD_NUMBER = 1;
    private long playerId_;
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }

    public static final int TOCKEN_FIELD_NUMBER = 2;
    private int tocken_;
    /**
     * <code>required int32 tocken = 2;</code>
     */
    public boolean hasTocken() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 tocken = 2;</code>
     */
    public int getTocken() {
      return tocken_;
    }

    private void initFields() {
      playerId_ = 0L;
      tocken_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasPlayerId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTocken()) {
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
        output.writeInt64(1, playerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, tocken_);
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
          .computeInt64Size(1, playerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, tocken_);
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

    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf prototype) {
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
     * Protobuf type {@code OnlineHeartTCPServerProBuf}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:OnlineHeartTCPServerProBuf)
        com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBufOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.internal_static_OnlineHeartTCPServerProBuf_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.internal_static_OnlineHeartTCPServerProBuf_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.class, com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.Builder.class);
      }

      // Construct using com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.newBuilder()
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
        playerId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        tocken_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.internal_static_OnlineHeartTCPServerProBuf_descriptor;
      }

      public com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf getDefaultInstanceForType() {
        return com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.getDefaultInstance();
      }

      public com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf build() {
        com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf buildPartial() {
        com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf result = new com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.playerId_ = playerId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.tocken_ = tocken_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf) {
          return mergeFrom((com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf other) {
        if (other == com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.getDefaultInstance()) return this;
        if (other.hasPlayerId()) {
          setPlayerId(other.getPlayerId());
        }
        if (other.hasTocken()) {
          setTocken(other.getTocken());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasPlayerId()) {
          
          return false;
        }
        if (!hasTocken()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long playerId_ ;
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public boolean hasPlayerId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public long getPlayerId() {
        return playerId_;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public Builder setPlayerId(long value) {
        bitField0_ |= 0x00000001;
        playerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public Builder clearPlayerId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        playerId_ = 0L;
        onChanged();
        return this;
      }

      private int tocken_ ;
      /**
       * <code>required int32 tocken = 2;</code>
       */
      public boolean hasTocken() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 tocken = 2;</code>
       */
      public int getTocken() {
        return tocken_;
      }
      /**
       * <code>required int32 tocken = 2;</code>
       */
      public Builder setTocken(int value) {
        bitField0_ |= 0x00000002;
        tocken_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 tocken = 2;</code>
       */
      public Builder clearTocken() {
        bitField0_ = (bitField0_ & ~0x00000002);
        tocken_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:OnlineHeartTCPServerProBuf)
    }

    static {
      defaultInstance = new OnlineHeartTCPServerProBuf(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:OnlineHeartTCPServerProBuf)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OnlineHeartTCPServerProBuf_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_OnlineHeartTCPServerProBuf_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026tcponline-server.proto\">\n\032OnlineHeartT" +
      "CPServerProBuf\022\020\n\010playerId\030\001 \002(\003\022\016\n\006tock" +
      "en\030\002 \002(\005BU\n<com.hc.nettygame.generatecod" +
      "e.message.auto.tcp.online.serverB\025Online" +
      "TCPServerProBuf"
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
    internal_static_OnlineHeartTCPServerProBuf_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_OnlineHeartTCPServerProBuf_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_OnlineHeartTCPServerProBuf_descriptor,
        new java.lang.String[] { "PlayerId", "Tocken", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

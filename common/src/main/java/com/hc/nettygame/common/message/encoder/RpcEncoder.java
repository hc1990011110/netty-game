package com.hc.nettygame.common.message.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by hc on 2017/3/7.
 */
public class RpcEncoder extends MessageToByteEncoder {

    private final Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
//            IRpcSerialize IRpcSerialize = LocalMananger.getInstance().getLocalSpringBeanManager().getProtostuffSerialize();
//            byte[] data = IRpcSerialize.serialize(in);
//            //byte[] data = JsonUtil.serialize(in); // Not use this, have some bugs
//            out.writeInt(data.length);
//            out.writeBytes(data);
        }
    }
}
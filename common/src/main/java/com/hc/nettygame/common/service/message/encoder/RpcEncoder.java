package com.hc.nettygame.common.service.message.encoder;

import com.hc.nettygame.common.service.rpc.serialize.protostuff.ProtostuffSerializeI;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/7.
 */
@Service
@Scope("prototype")
public class RpcEncoder extends MessageToByteEncoder {
    private final Class<?> genericClass;
    @Autowired
    private ProtostuffSerializeI protostuffSerialize;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = protostuffSerialize.serialize(in);
            //byte[] data = JsonUtil.serialize(in); // Not use this, have some bugs
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
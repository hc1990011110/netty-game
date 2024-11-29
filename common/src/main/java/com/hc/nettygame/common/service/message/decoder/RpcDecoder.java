package com.hc.nettygame.common.service.message.decoder;


import com.hc.nettygame.common.service.rpc.serialize.protostuff.ProtostuffSerializeI;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hc on 2017/3/7.
 */
@Service
@Scope("prototype")
public class RpcDecoder extends ByteToMessageDecoder {
    private final Class<?> genericClass;
    @Autowired
    private ProtostuffSerializeI protostuffSerialize;

    public RpcDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        /*if (dataLength <= 0) {
            ctx.close();
        }*/
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        Object obj = protostuffSerialize.deserialize(data, genericClass);
        //Object obj = JsonUtil.deserialize(data,genericClass); // Not use this, have some bugs
        out.add(obj);
    }

}
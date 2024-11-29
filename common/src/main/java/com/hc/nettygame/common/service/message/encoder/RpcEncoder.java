package com.hc.nettygame.common.service.message.encoder;

import com.hc.nettygame.common.service.rpc.serialize.IRpcSerialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/7.
 */
@Service
public class RpcEncoder extends MessageToByteEncoder {
    @Autowired
    private IRpcSerialize IRpcSerialize;
    private final Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if (genericClass.isInstance(in)) {
            byte[] data = IRpcSerialize.serialize(in);
            //byte[] data = JsonUtil.serialize(in); // Not use this, have some bugs
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
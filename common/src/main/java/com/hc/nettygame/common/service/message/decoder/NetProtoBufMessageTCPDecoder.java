package com.hc.nettygame.common.service.message.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by hc on 17/2/3.
 */
@Component
@Scope("prototype")
public class NetProtoBufMessageTCPDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Charset charset;

    @Autowired
    private NetProtoBufTcpMessageDecoderFactory iNetMessageDecoderFactory;

    public NetProtoBufMessageTCPDecoder() {
        this(CharsetUtil.UTF_8);
    }

    public NetProtoBufMessageTCPDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        } else {
            this.charset = charset;
        }
    }


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(iNetMessageDecoderFactory.parseMessage(msg));
    }
}


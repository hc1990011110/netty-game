package com.hc.nettygame.common.service.message.encoder;


import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by hc on 17/2/8.
 */
@Component
@Scope("prototype")
public class NetProtoBufMessageTCPEncoder extends MessageToMessageEncoder<AbstractNetProtoBufMessage> {
    private final Logger LOGGER = LoggerFactory.getLogger(NetProtoBufMessageTCPEncoder.class);
    private final Charset charset;

    @Autowired
    private NetProtoBufTcpMessageEncoderFactory iNetMessageEncoderFactory;

    public NetProtoBufMessageTCPEncoder() {
        this(CharsetUtil.UTF_8);

    }

    public NetProtoBufMessageTCPEncoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        } else {
            this.charset = charset;
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractNetProtoBufMessage msg, List<Object> out) throws Exception {
        ByteBuf netMessageBuf = iNetMessageEncoderFactory.createByteBuf(msg);
        out.add(netMessageBuf);
    }
}

package com.hc.nettygame.common.service.message.encoder;


import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by hc on 2017/9/28.
 */
public interface INetProtoBufMessageEncoderFactory {
    public ByteBuf createByteBuf(AbstractNetProtoBufMessage netMessage) throws Exception;
}

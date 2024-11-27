package com.hc.nettygame.common.service.message.decoder;


import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by hc on 2017/9/28.
 */
public interface INetProtoBufMessageDecoderFactory {
    public AbstractNetProtoBufMessage parseMessage(ByteBuf byteBuf) throws CodecException;
}

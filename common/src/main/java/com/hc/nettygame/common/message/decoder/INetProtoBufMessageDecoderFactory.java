package com.hc.nettygame.common.message.decoder;


import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.AbstractNetProtoBufMessage;
import io.netty.buffer.ByteBuf;

/**
 * Created by jiangwenping on 2017/9/28.
 */
public interface INetProtoBufMessageDecoderFactory {
    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf) throws CodecException;
}

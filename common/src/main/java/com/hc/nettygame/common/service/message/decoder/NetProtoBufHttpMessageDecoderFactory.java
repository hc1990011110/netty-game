package com.hc.nettygame.common.service.message.decoder;


import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.message.NetHttpMessageHead;
import com.hc.nettygame.common.service.message.NetProtoBufMessageBody;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/9/28.
 */
@Service
public class NetProtoBufHttpMessageDecoderFactory implements INetProtoBufHttpMessageDecoderFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(NetProtoBufHttpMessageDecoderFactory.class);
    @Autowired
    private MessageRegistry messageRegistry;

    @Override
    public AbstractNetProtoBufMessage parseMessage(ByteBuf byteBuf) throws CodecException {
        //读取head
        NetHttpMessageHead netMessageHead = new NetHttpMessageHead();
        //head为两个字节，跳过
        byteBuf.skipBytes(2);
        netMessageHead.setLength(byteBuf.readInt());
        netMessageHead.setVersion(byteBuf.readByte());

        //读取内容
        short cmd = byteBuf.readShort();
        netMessageHead.setCmd(cmd);
        netMessageHead.setSerial(byteBuf.readInt());

        netMessageHead.setPlayerId(byteBuf.readLong());
        //读取token
        short tokenLength = byteBuf.readShort();
        byte[] tokenBytes = new byte[tokenLength];
        ByteBuf tokenBuff = byteBuf.readBytes(tokenBytes);
        netMessageHead.setToken(tokenBuff.toString());

        AbstractNetProtoBufMessage netMessage = messageRegistry.getMessage(cmd);
        if (netMessage == null) {
            return null;
        }
        //读取body
        NetProtoBufMessageBody netMessageBody = new NetProtoBufMessageBody();
        int byteLength = byteBuf.readableBytes();
        byte[] bytes = new byte[byteLength];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes);
        netMessageBody.setBytes(bytes);
        netMessage.setNetMessageHead(netMessageHead);
        netMessage.setNetMessageBody(netMessageBody);
        try {
            netMessage.decoderNetProtoBufMessageBody();
            netMessage.releaseMessageBody();
        } catch (Exception e) {
            throw new CodecException("message cmd " + cmd + "decoder error", e);
        }

        //增加协议解析打印
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("receive net message" + netMessage.toAllInfoString());
        }

        return netMessage;
    }
}

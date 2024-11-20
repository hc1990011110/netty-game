package com.hc.nettygame.common.message.decoder;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.message.NetMessageHead;
import com.hc.nettygame.common.message.NetProtoBufMessageBody;
import com.hc.nettygame.common.message.registry.MessageRegistry;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Service;

/**
 * Created by jiangwenping on 17/2/3.
 */
@Service
public class NetProtoBufTcpMessageDecoderFactory implements INetProtoBufTcpMessageDecoderFactory {

    public AbstractNetProtoBufMessage praseMessage(ByteBuf byteBuf) throws CodecException {
        //读取head
        NetMessageHead netMessageHead = new NetMessageHead();
        //head为两个字节，跳过
        byteBuf.skipBytes(2);
        netMessageHead.setLength(byteBuf.readInt());
        netMessageHead.setVersion(byteBuf.readByte());

        //读取内容
        short cmd = byteBuf.readShort();
        netMessageHead.setCmd(cmd);
        netMessageHead.setSerial(byteBuf.readInt());

        MessageRegistry messageRegistry = new MessageRegistry();//LocalMananger.getInstance().getLocalSpringServiceManager().getMessageRegistry();
        AbstractNetProtoBufMessage netMessage = messageRegistry.getMessage(cmd);
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
        if (Loggers.sessionLogger.isDebugEnabled()) {
            Loggers.sessionLogger.debug("revice net message" + netMessage.toAllInfoString());
        }

        return netMessage;
    }
}

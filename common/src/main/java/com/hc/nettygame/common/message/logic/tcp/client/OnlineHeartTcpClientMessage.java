package com.hc.nettygame.common.message.logic.tcp.client;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.auto.tcp.client.TcpClientMessageProBuf;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import lombok.Getter;
import lombok.Setter;


/**
 * 心跳
 *
 * @author CodeGenerator, don't modify this file please.
 */
@Setter
@Getter
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_CLIENT_TCP_MESSAGE)
public class OnlineHeartTcpClientMessage extends AbstractNetProtoBufTcpMessage {

    private int id;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        TcpClientMessageProBuf.OnlineHeartTcpClientProBuf req = TcpClientMessageProBuf.OnlineHeartTcpClientProBuf.parseFrom(bytes);
        setId(req.getId());
    }

    @Override
    public void release() {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        TcpClientMessageProBuf.OnlineHeartTcpClientProBuf.Builder builder = TcpClientMessageProBuf.OnlineHeartTcpClientProBuf.newBuilder();
        builder.setId(getId());
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}

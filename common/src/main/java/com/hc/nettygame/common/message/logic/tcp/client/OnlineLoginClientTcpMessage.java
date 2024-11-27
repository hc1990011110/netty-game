package com.hc.nettygame.common.message.logic.tcp.client;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.auto.tcp.client.TcpClientMessageProBuf;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 17/2/21.
 */
@Setter
@Getter
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
public class OnlineLoginClientTcpMessage extends AbstractNetProtoBufTcpMessage {

    private int id;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        TcpClientMessageProBuf.OnlineLoginTcpClientProBuf req = TcpClientMessageProBuf.OnlineLoginTcpClientProBuf.parseFrom(bytes);
        setId(req.getId());
    }

    @Override
    public void release() {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        TcpClientMessageProBuf.OnlineLoginTcpClientProBuf.Builder builder = TcpClientMessageProBuf.OnlineLoginTcpClientProBuf.newBuilder();
        builder.setId(getId());
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}


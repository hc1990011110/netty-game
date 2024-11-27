package com.hc.nettygame.generatecode.message.logic.tcp.server;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.tcp.server.TcpServerMessageProBuf;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 17/2/21.
 */

@Setter
@Getter
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_SERVER_MESSAGE)
public class OnlineLoginServerTcpMessage extends AbstractNetProtoBufTcpMessage {

    private long playerId;
    private int token;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        TcpServerMessageProBuf.OnlineLoginTcpServerProBuf req = TcpServerMessageProBuf.OnlineLoginTcpServerProBuf.parseFrom(bytes);
        setPlayerId(req.getPlayerId());
        setToken(req.getToken());
    }

    @Override
    public void release() throws CodecException {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        TcpServerMessageProBuf.OnlineLoginTcpServerProBuf.Builder builder = TcpServerMessageProBuf.OnlineLoginTcpServerProBuf.newBuilder();
        builder.setPlayerId(playerId);
        builder.setToken(token);
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}

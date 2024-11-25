package com.hc.nettygame.generatecode.message.logic.tcp.online.server;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.tcp.online.server.OnlineTCPServerProBuf;

/**
 * Created by jiangwenping on 17/2/21.
 */

@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_SERVER_MESSAGE)
public class OnlineLoginServerTcpMessage extends AbstractNetProtoBufTcpMessage {

    private long playerId;
    private int token;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf req = OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.parseFrom(bytes);
        setPlayerId(req.getPlayerId());
        setToken(req.getToken());
    }

    @Override
    public void release() throws CodecException {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.Builder builder = OnlineTCPServerProBuf.OnlineHeartTCPServerProBuf.newBuilder();
        builder.setPlayerId(playerId);
        builder.setToken(token);
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}

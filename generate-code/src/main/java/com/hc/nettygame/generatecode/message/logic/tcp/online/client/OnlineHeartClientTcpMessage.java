package com.hc.nettygame.generatecode.message.logic.tcp.online.client;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.tcp.online.client.OnlineTCPClientProBuf;

/**
 * Created by jiangwenping on 17/2/8.
 */
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_CLIENT_TCP_MESSAGE)
public class OnlineHeartClientTcpMessage extends AbstractNetProtoBufTcpMessage {

    private int id;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        OnlineTCPClientProBuf.OnlineHeartTCPClientProBuf req = OnlineTCPClientProBuf.OnlineHeartTCPClientProBuf.parseFrom(bytes);
        setId(req.getId());
    }

    @Override
    public void release() {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        OnlineTCPClientProBuf.OnlineHeartTCPClientProBuf.Builder builder = OnlineTCPClientProBuf.OnlineHeartTCPClientProBuf.newBuilder();
        builder.setId(getId());
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

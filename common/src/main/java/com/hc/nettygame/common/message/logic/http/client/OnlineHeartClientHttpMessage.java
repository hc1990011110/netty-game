package com.hc.nettygame.common.message.logic.http.client;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.auto.http.client.HttpClientMessageProBuf;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufHttpMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/9/30.
 */

@Setter
@Getter
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_HTTP_CLIENT_MESSAGE)
public class OnlineHeartClientHttpMessage extends AbstractNetProtoBufHttpMessage {

    private int id;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        HttpClientMessageProBuf.OnlineHeartHttpClientProBuf req = HttpClientMessageProBuf.OnlineHeartHttpClientProBuf.parseFrom(bytes);
        setId(req.getId());
    }

    @Override
    public void release() {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        HttpClientMessageProBuf.OnlineHeartHttpClientProBuf.Builder builder = HttpClientMessageProBuf.OnlineHeartHttpClientProBuf.newBuilder();
        builder.setId(getId());
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}


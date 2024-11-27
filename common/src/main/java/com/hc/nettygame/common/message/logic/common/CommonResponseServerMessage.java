package com.hc.nettygame.common.message.logic.common;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.auto.common.CommonMessageProBuf;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;

/**
 * Created by hc on 17/2/20.
 */
@MessageCommandAnnotation(command = MessageCommandIndex.COMMON_RESPONSE_MESSAGE)
public class CommonResponseServerMessage extends AbstractNetProtoBufTcpMessage {

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        CommonMessageProBuf.CommonResponseServerProBuf req = CommonMessageProBuf.CommonResponseServerProBuf.parseFrom(bytes);
    }

    @Override
    public void release() throws CodecException {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        CommonMessageProBuf.CommonResponseServerProBuf.Builder builder = CommonMessageProBuf.CommonResponseServerProBuf.newBuilder();
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}

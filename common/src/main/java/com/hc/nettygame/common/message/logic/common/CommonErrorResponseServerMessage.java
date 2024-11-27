package com.hc.nettygame.common.message.logic.common;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.exception.CodecException;
import com.hc.nettygame.common.message.auto.common.CommonMessageProBuf;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import com.hc.nettygame.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 17/2/20.
 */
@Setter
@Getter
@MessageCommandAnnotation(command = MessageCommandIndex.COMMON_ERROR_RESPONSE_MESSAGE)
public class CommonErrorResponseServerMessage extends AbstractNetProtoBufTcpMessage {

    /**
     * 状态码
     */
    private int state;
    /**
     * 特殊提示
     */
    private String arg;

    @Override
    public void decoderNetProtoBufMessageBody() throws Exception {
        byte[] bytes = getNetMessageBody().getBytes();
        CommonMessageProBuf.CommonErrorResponseServerProBuf req = CommonMessageProBuf.CommonErrorResponseServerProBuf.parseFrom(bytes);
        this.state = req.getState();
        this.arg = req.getArg();
    }

    @Override
    public void release() throws CodecException {

    }

    @Override
    public void encodeNetProtoBufMessageBody() throws Exception {
        CommonMessageProBuf.CommonErrorResponseServerProBuf.Builder builder = CommonMessageProBuf.CommonErrorResponseServerProBuf.newBuilder();
        if (!StringUtils.isEmpty(arg)) {
            builder.setArg(arg);
        }
        builder.setState(state);
        byte[] bytes = builder.build().toByteArray();
        getNetMessageBody().setBytes(bytes);
    }

}

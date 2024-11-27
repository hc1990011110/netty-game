package com.hc.nettygame.common.service.message.factory;


import com.hc.nettygame.common.message.logic.common.CommonErrorResponseServerMessage;
import com.hc.nettygame.common.message.logic.common.CommonResponseServerMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import org.springframework.stereotype.Service;

/**
 * Created by jwp on 2017/2/10.
 */
@Service
public class TcpMessageFactory implements ITcpMessageFactory {

    @Override
    public AbstractNetMessage createCommonErrorResponseMessage(int serial, int state, String tip) {
        CommonErrorResponseServerMessage abstractNetMessage = (CommonErrorResponseServerMessage) createCommonErrorResponseMessage(serial, state);
        abstractNetMessage.setArg(tip);
        return abstractNetMessage;
    }

    @Override
    public AbstractNetMessage createCommonErrorResponseMessage(int serial, int state) {
        CommonErrorResponseServerMessage commonErrorResponseServerMessage = new CommonErrorResponseServerMessage();
        commonErrorResponseServerMessage.setSerial(serial);
        commonErrorResponseServerMessage.setState(state);
        return commonErrorResponseServerMessage;
    }

    @Override
    public AbstractNetMessage createCommonResponseMessage(int serial) {
        CommonResponseServerMessage commonResponseServerMessage = new CommonResponseServerMessage();
        commonResponseServerMessage.setSerial(serial);
        return commonResponseServerMessage;
    }


}

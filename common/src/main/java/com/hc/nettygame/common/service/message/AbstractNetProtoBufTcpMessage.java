package com.hc.nettygame.common.service.message;

/**
 * Created by hc on 2017/2/17.
 */
public abstract class AbstractNetProtoBufTcpMessage extends AbstractNetProtoBufMessage {

    public AbstractNetProtoBufTcpMessage() {
        super();
        setNetMessageBody(new NetProtoBufMessageBody());
        initHeadCmd();

    }

}

package com.hc.nettygame.common.message;

/**
 * Created by jwp on 2017/2/17.
 */
public abstract class AbstractNetProtoBufTcpMessage extends AbstractNetProtoBufMessage {

    public AbstractNetProtoBufTcpMessage() {
        super();
        setNetMessageBody(new NetProtoBufMessageBody());
        initHeadCmd();

    }

}

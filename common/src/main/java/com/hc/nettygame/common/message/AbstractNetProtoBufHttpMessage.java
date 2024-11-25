package com.hc.nettygame.common.message;

/**
 * Created by hc on 2017/9/28.
 */
public abstract class AbstractNetProtoBufHttpMessage extends AbstractNetProtoBufMessage {

    public AbstractNetProtoBufHttpMessage() {
        super();
        setNetMessageHead(new NetHttpMessageHead());
        setNetMessageBody(new NetProtoBufMessageBody());
        initHeadCmd();
    }

}

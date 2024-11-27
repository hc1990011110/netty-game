package com.hc.nettygame.common.service.message.factory;


import com.hc.nettygame.common.service.message.AbstractNetMessage;

/**
 * Created by jwp on 2017/2/10.
 * 协议工厂
 */
public interface ITcpMessageFactory {
    public AbstractNetMessage createCommonErrorResponseMessage(int serial, int state, String tip);

    public AbstractNetMessage createCommonErrorResponseMessage(int serial, int state);

    public AbstractNetMessage createCommonResponseMessage(int serial);
}

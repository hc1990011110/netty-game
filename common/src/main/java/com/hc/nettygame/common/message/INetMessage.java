package com.hc.nettygame.common.message;

/**
 * Created by jwp on 2017/2/17.
 * 基础协议
 */
public interface INetMessage {
    public NetMessageHead getNetMessageHead();

    public NetMessageBody getNetMessageBody();
}
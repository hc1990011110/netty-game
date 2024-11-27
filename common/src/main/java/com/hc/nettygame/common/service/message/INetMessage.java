package com.hc.nettygame.common.service.message;

/**
 * Created by hc on 2017/2/17.
 * 基础协议
 */
public interface INetMessage {
    public NetMessageHead getNetMessageHead();

    public NetMessageBody getNetMessageBody();
}

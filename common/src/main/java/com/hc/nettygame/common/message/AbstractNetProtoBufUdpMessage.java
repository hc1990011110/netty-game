package com.hc.nettygame.common.message;

import java.net.InetSocketAddress;

/**
 * Created by jwp on 2017/2/16.
 * 抽象的udp消息
 */
public abstract class AbstractNetProtoBufUdpMessage extends AbstractNetProtoBufMessage {
    /**
     * 发送方
     */
    private InetSocketAddress send;
    /**
     * 接收方
     */
    private InetSocketAddress receive;


    public AbstractNetProtoBufUdpMessage() {
        super();
        setNetMessageHead(new NetUdpMessageHead());
        setNetMessageBody(new NetProtoBufMessageBody());
        initHeadCmd();
    }

    public InetSocketAddress getSend() {
        return send;
    }

    public void setSend(InetSocketAddress send) {
        this.send = send;
    }

    public InetSocketAddress getReceive() {
        return receive;
    }

    public void setReceive(InetSocketAddress receive) {
        this.receive = receive;
    }

    public long getPlayerId() {
        NetUdpMessageHead netUdpMessageHead = (NetUdpMessageHead) getNetMessageHead();
        return netUdpMessageHead.getPlayerId();
    }

    public void setPlayerId(long playerId) {
        NetUdpMessageHead netUdpMessageHead = (NetUdpMessageHead) getNetMessageHead();
        netUdpMessageHead.setPlayerId(playerId);
    }

    public int getTocken() {
        NetUdpMessageHead netUdpMessageHead = (NetUdpMessageHead) getNetMessageHead();
        return netUdpMessageHead.getTocken();
    }

    public void setTocken(int tocken) {
        NetUdpMessageHead netUdpMessageHead = (NetUdpMessageHead) getNetMessageHead();
        netUdpMessageHead.setTocken(tocken);
    }
}

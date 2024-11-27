package com.hc.nettygame.common.logic.player;


import com.hc.nettygame.common.service.net.session.NettyTcpNetMessageSender;

/**
 * Created by HC on 17/2/20.
 */
public interface IPlayer {
    public long getPlayerId();

    public int getPlayerUdpToken();

    public NettyTcpNetMessageSender getNettyTcpNetMessageSender();
}

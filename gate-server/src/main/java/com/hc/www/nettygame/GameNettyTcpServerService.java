package com.hc.www.nettygame;

import io.netty.channel.ChannelInitializer;


public class GameNettyTcpServerService extends AbstractNettyTcpServerService {

    public GameNettyTcpServerService(String serviceId, int serverPort, String bossThreadName, String workThreadName, ChannelInitializer channelInitializer) {
        super(serviceId, serverPort, bossThreadName, workThreadName, channelInitializer);
    }
}

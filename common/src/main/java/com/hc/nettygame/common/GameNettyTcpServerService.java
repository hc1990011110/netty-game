package com.hc.nettygame.common;

import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class GameNettyTcpServerService extends AbstractNettyTcpServerService {
    public GameNettyTcpServerService(@Qualifier("GameTcpServerChannelInitializer") ChannelInitializer channelInitializer) {
        super(channelInitializer);
    }
}

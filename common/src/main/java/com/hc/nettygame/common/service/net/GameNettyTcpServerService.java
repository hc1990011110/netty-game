package com.hc.nettygame.common.service.net;

import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class GameNettyTcpServerService extends AbstractNettyTcpServerService {

    @Autowired
    public GameNettyTcpServerService(String serviceId, int serverPort, ChannelInitializer channelInitializer) {
        super(serviceId, serverPort, channelInitializer);
    }
}

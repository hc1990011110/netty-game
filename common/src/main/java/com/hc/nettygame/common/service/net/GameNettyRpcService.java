package com.hc.nettygame.common.service.net;

import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/7.
 * 增加rpc服务
 */
@Service
@Scope("prototype")
public class GameNettyRpcService extends AbstractNettyTcpServerService {
    @Autowired
    public GameNettyRpcService(String serviceId, int serverPort, ChannelInitializer channelInitializer) {
        super(serviceId, serverPort, channelInitializer);
    }
}

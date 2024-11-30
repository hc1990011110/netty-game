package com.hc.nettygame.common.bootstrap;

import com.hc.nettygame.common.exception.StartUpException;
import com.hc.nettygame.common.service.net.GameNetRpcChannelInitializer;
import com.hc.nettygame.common.service.net.GameNettyRpcService;
import com.hc.nettygame.common.service.net.GameNettyTcpServerService;
import com.hc.nettygame.common.service.net.GameTcpServerChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class NetManager {
    private final Logger LOGGER = LoggerFactory.getLogger(NetManager.class);
    @Value("${netty.serverId}")
    private String serverId;
    @Value("${netty.tcp-port}")
    private Integer tcpPort;
    @Value("${netty.rpc-port}")
    private Integer rpcPort;
    @Value("${netty.rpcOpen}")
    private boolean rpcOpen;

    @Autowired
    private ApplicationContext context;
    @Autowired
    private GameTcpServerChannelInitializer gameTcpServerChannelInitializer;
    @Autowired
    private GameNetRpcChannelInitializer gameNetRpcChannelInitializer;

    private GameNettyTcpServerService tcpService;
    private GameNettyRpcService rpcService;

    public void startup() throws Exception {
        initNetService();
    }

    public void initNetService() throws Exception {
        tcpService = context.getBean(GameNettyTcpServerService.class, serverId, tcpPort, gameTcpServerChannelInitializer);
        if (!tcpService.startService()) {
            throw new StartUpException(serverId + " tcpService startup error");
        }
        LOGGER.info("{} tcpService start success on port: {}", serverId, tcpPort);
        if (rpcOpen) {
            rpcService = context.getBean(GameNettyRpcService.class, serverId, rpcPort, gameNetRpcChannelInitializer);
            if (!rpcService.startService()) {
                throw new StartUpException(serverId + " rpcService startup error");
            }
            LOGGER.info("{} rpcService start success on port: {}", serverId, rpcPort);
        }
    }

    //    @Override
    public void shutdown() throws Exception {
        if (tcpService != null) {
            tcpService.stopService();
        }
        if (rpcService != null) {
            rpcService.stopService();
        }
    }
}

package com.hc.nettygame.common;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.StartUpException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NetManager {
    private final Logger LOGGER = Loggers.serverLogger;

    @Autowired
    private GameNettyTcpServerService tcpService;


    public void startup() throws Exception {
        initNetService();
    }

    public void initNetService() throws Exception {
        if (!tcpService.startService()) {
            throw new StartUpException(tcpService.getServerId() + " startup error");
        }
        LOGGER.info("{} start success", tcpService.getServerId());
    }

    //    @Override
    public void shutdown() throws Exception {
        if (tcpService != null) {
            tcpService.stopService();
        }
    }
}

package com.hc.nettygame.common.bootstrap;

import com.hc.nettygame.common.service.lookup.NetTcpSessionLoopUpService;
import com.hc.nettygame.common.service.message.facade.GameFacade;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalMananger {

    @Autowired
    private MessageRegistry messageRegistry;
    @Autowired
    private NetTcpSessionLoopUpService netTcpSessionLoopUpService;
    @Autowired
    private GameFacade gameFacade;

    public void startup() throws Exception {
        messageRegistry.startup();
        netTcpSessionLoopUpService.startup();
        gameFacade.startup();
    }

}

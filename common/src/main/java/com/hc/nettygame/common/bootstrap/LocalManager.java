package com.hc.nettygame.common.bootstrap;

import com.hc.nettygame.common.service.lookup.NetTcpSessionLoopUpService;
import com.hc.nettygame.common.service.message.facade.GameFacade;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import com.hc.nettygame.common.service.rpc.server.RemoteRpcHandlerService;
import com.hc.nettygame.common.service.rpc.server.RpcMethodRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalManager {
    @Autowired
    private MessageRegistry messageRegistry;
    @Autowired
    private NetTcpSessionLoopUpService netTcpSessionLoopUpService;
    @Autowired
    private GameFacade gameFacade;
    @Autowired
    private RpcMethodRegistry rpcMethodRegistry;
    @Autowired
    private RemoteRpcHandlerService remoteRpcHandlerService;

    public void startup() throws Exception {
        messageRegistry.startup();
        netTcpSessionLoopUpService.startup();
        gameFacade.startup();
        rpcMethodRegistry.startup();
        remoteRpcHandlerService.startup();
    }

}

package com.hc.nettygame.common.bootstrap;

import com.hc.nettygame.common.service.lookup.NetTcpSessionLoopUpService;
import com.hc.nettygame.common.service.message.facade.GameFacade;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import com.hc.nettygame.common.service.rpc.client.RpcClientConnectService;
import com.hc.nettygame.common.service.rpc.server.RemoteRpcHandlerService;
import com.hc.nettygame.common.service.rpc.server.RpcMethodRegistry;
import com.hc.nettygame.common.service.rpc.server.RpcServerRegisterConfig;
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
    @Autowired
    private RpcClientConnectService rpcClientConnectService;
    @Autowired
    private RpcServerRegisterConfig rpcServerRegisterConfig;

    public void startup() throws Exception {
        init();
        afterInit();
    }

    public void init() throws Exception {
        initConfig();
        messageRegistry.startup();
        netTcpSessionLoopUpService.startup();
        gameFacade.startup();
        rpcMethodRegistry.startup();
        remoteRpcHandlerService.startup();
    }

    public void initConfig() throws Exception {
        rpcServerRegisterConfig.init();
    }

    public void afterInit() throws Exception {
        rpcClientConnectService.startup();
    }

}

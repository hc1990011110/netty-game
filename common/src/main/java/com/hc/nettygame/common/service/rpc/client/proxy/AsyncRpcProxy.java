package com.hc.nettygame.common.service.rpc.client.proxy;

import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.rpc.client.*;
import com.hc.nettygame.common.service.rpc.client.net.RpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/9.
 */

@Service
@Scope("prototype")
public class AsyncRpcProxy<T> implements IAsyncRpcProxy {
    private final Class<T> clazz;
    @Autowired
    private RpcClientConnectService rpcClientConnectService;
    @Autowired
    private RpcRequestFactory rpcRequestFactory;

    @Autowired
    public AsyncRpcProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public RPCFuture call(String funcName, Object... args) {
        RpcContextHolderObject rpcContextHolderObject = RpcContextHolder.getContext();
        AbstractRpcConnectManager abstractRpcConnectManager = rpcClientConnectService.getRpcConnectManager(rpcContextHolderObject.getBoEnum());
        RpcClient rpcClient = abstractRpcConnectManager.chooseClient(rpcContextHolderObject.getServerId());
        RpcRequest request = rpcRequestFactory.createRequest(this.clazz.getName(), funcName, args);
        RPCFuture rpcFuture = rpcClient.sendRequest(request);
        return rpcFuture;
    }


}

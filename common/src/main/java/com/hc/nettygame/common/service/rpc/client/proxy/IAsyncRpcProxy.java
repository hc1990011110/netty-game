package com.hc.nettygame.common.service.rpc.client.proxy;

import com.hc.nettygame.common.service.rpc.client.RPCFuture;

/**
 * Created by hc on 2017/3/9.
 */
public interface IAsyncRpcProxy {
    public RPCFuture call(String funcName, Object... args);
}

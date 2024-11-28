package com.hc.nettygame.common.service.rpc.client.proxy;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.rpc.client.*;
import com.hc.nettygame.common.service.rpc.client.net.RpcClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Scope("prototype")
public class ObjectProxy<T> implements InvocationHandler {
    @Autowired
    private RpcClientConnectService rpcClientConnectService;
    private final Logger logger = Loggers.rpcLogger;
    private final Class<T> clazz;
    private final int timeOut;

    @Autowired
    public ObjectProxy(Class<T> clazz, int timeOut) {
        this.clazz = clazz;
        this.timeOut = timeOut;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //基本上用不到equql, hashcode, tostring等底层函数
//        if (Object.class == method.getDeclaringClass()) {
//            String name = method.getName();
//            if ("equals".equals(name)) {
//                return proxy == args[0];
//            } else if ("hashCode".equals(name)) {
//                return System.identityHashCode(proxy);
//            } else if ("toString".equals(name)) {
//                return proxy.getClass().getName() + "@" +
//                        Integer.toHexString(System.identityHashCode(proxy)) +
//                        ", with InvocationHandler " + this;
//            } else {
//                throw new IllegalStateException(String.valueOf(method));
//            }
//        }

        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);
        if (logger.isDebugEnabled()) {
            // Debug
            logger.debug(method.getDeclaringClass().getName());
            logger.debug(method.getName());
            for (int i = 0; i < method.getParameterTypes().length; ++i) {
                logger.debug(method.getParameterTypes()[i].getName());
            }
            for (Object arg : args) {
                logger.debug(arg.toString());
            }
        }

        RpcContextHolderObject rpcContextHolderObject = RpcContextHolder.getContext();
        AbstractRpcConnectManager abstractRpcConnectManager = rpcClientConnectService.getRpcConnectMannger(rpcContextHolderObject.getBoEnum());
        RpcClient rpcClient = abstractRpcConnectManager.chooseClient(rpcContextHolderObject.getServerId());
        RPCFuture rpcFuture = rpcClient.sendRequest(request);
        if (timeOut > 0) {
            return rpcFuture.get(timeOut, TimeUnit.MILLISECONDS);
        }
        return rpcFuture.get();
    }
}

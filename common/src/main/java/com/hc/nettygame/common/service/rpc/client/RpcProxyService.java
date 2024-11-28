package com.hc.nettygame.common.service.rpc.client;


import com.hc.nettygame.common.annotation.RpcServiceAnnotation;
import com.hc.nettygame.common.annotation.RpcServiceBoEnum;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.rpc.client.proxy.AsyncRpcProxy;
import com.hc.nettygame.common.service.rpc.client.proxy.IAsyncRpcProxy;
import com.hc.nettygame.common.service.rpc.client.proxy.ObjectProxy;
import com.hc.nettygame.common.service.rpc.server.RpcMethodRegistry;
import com.hc.nettygame.common.service.rpc.server.RpcServerRegisterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * RPC Client（Create RPC proxy）
 */
@Service
public class RpcProxyService implements IService {
    private static ThreadPoolExecutor threadPoolExecutor;
    @Value("${netty.rpcTimeOut:0}")
    private Integer rpcTimeOut;
    @Value("${netty.rpcSendProxyThreadSize:16}")
    private Integer rpcSendProxyThreadSize;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private RpcMethodRegistry rpcMethodRegistry;
    @Autowired
    private RpcServerRegisterConfig rpcServerRegisterConfig;

    @SuppressWarnings("unchecked")
    public <T> T createProxy(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                context.getBean(ObjectProxy.class, interfaceClass, rpcTimeOut)
        );
    }

    public <T> IAsyncRpcProxy createAsync(Class<T> interfaceClass) {
        return context.getBean(AsyncRpcProxy.class, interfaceClass);
    }

    public void submit(Runnable task) {
        threadPoolExecutor.submit(task);
    }

    @Override
    public String getId() {
        return ServiceName.RpcSenderProxy;
    }

    @Override
    public void startup() throws Exception {
        threadPoolExecutor = new ThreadPoolExecutor(rpcSendProxyThreadSize, rpcSendProxyThreadSize, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
    }

    @Override
    public void shutdown() throws Exception {
        threadPoolExecutor.shutdown();
    }

    /**
     * 如果本机已经提供了远程对应的rpc服务，进行本地调用
     *
     * @param interfaceClass
     * @return
     */
    public <T> T createRemoteProxy(Class<T> interfaceClass) {
        String serviceName = interfaceClass.getName();
        Object bean = rpcMethodRegistry.getServiceBean(serviceName);
        if (bean == null) {
            //如果是空，进行rpc调用
            return null;
        }

        RpcServiceAnnotation rpcServiceAnnotation = bean.getClass().getAnnotation(RpcServiceAnnotation.class);
        if (rpcServiceAnnotation == null) {
            //找不到rpc服务
            return null;
        }

        RpcServiceBoEnum rpcServiceBoEnum = bean.getClass().getAnnotation(RpcServiceBoEnum.class);
        if (rpcServiceBoEnum == null) {
            //找不到rpc服务
            return null;
        }

        //是否本地提供服务
        BOEnum boEnum = rpcServiceBoEnum.bo();
        if (rpcServerRegisterConfig.getSdRpcServiceProvider().validServer(boEnum.getBoId())) {
            return (T) bean;
        }

        return createProxy(interfaceClass);
    }

}


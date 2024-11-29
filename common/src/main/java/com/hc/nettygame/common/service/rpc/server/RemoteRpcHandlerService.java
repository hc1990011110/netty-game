package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.util.ExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/8.
 */
@Service
public class RemoteRpcHandlerService implements IService {
    @Value("${netty.rpcOpen}")
    private boolean rpcOpen;
    @Value("${netty.rpcThreadPoolSize}")
    private Integer rpcThreadPoolSize;
    @Value("${netty.rpcThreadPoolQueueSize}")
    private Integer rpcThreadPoolQueueSize;
    @Autowired
    private RpcHandlerThreadPool rpcHandlerThreadPool;

    @Override
    public String getId() {
        return ServiceName.RemoteRpcService;
    }

    @Override
    public void startup() throws Exception {
        if (rpcOpen) {
            //开启服务
            rpcHandlerThreadPool.createExecutor(rpcThreadPoolSize, rpcThreadPoolQueueSize);
        }
    }

    @Override
    public void shutdown() throws Exception {
        ExecutorUtil.shutdownAndAwaitTermination(rpcHandlerThreadPool.getExecutor());
    }

    public void submit(Runnable runnable) {
        rpcHandlerThreadPool.getExecutor().submit(runnable);
    }
}

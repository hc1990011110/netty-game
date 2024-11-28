package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.util.ExecutorUtil;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * rpc客户端RPCFuture管理服务
 */
@Service
public class RPCFutureService implements IService {

    @Override
    public String getId() {
        return ServiceName.RPCFutureService;
    }

    private ScheduledExecutorService executorService;

    @Override
    public void startup() throws Exception {
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ConcurrentHashMap<String, RPCFuture> pendingRPC = getPendingRPC();
                Set<Entry<String, RPCFuture>> entrySet = pendingRPC.entrySet();
                for (Entry<String, RPCFuture> entry : entrySet) {
                    RPCFuture rpcFuture = entry.getValue();
                    if (rpcFuture.isTimeout()) {
                        String requestId = entry.getKey();
                        boolean removeFlag = removeRPCFuture(requestId, rpcFuture);
                        if (removeFlag) {
//							rpcFuture.done(rpcResponse);
                        }
                    }
                }
            }
        }, 1, 1, TimeUnit.MINUTES);
    }

    @Override
    public void shutdown() throws Exception {
        ExecutorUtil.shutdownAndAwaitTermination(executorService, 60L, TimeUnit.MILLISECONDS);
    }

    @Getter
    private final ConcurrentHashMap<String, RPCFuture> pendingRPC = new ConcurrentHashMap<>();

    public RPCFuture getRPCFuture(String requestId) {
        if (pendingRPC.get(requestId) != null) {
            return pendingRPC.get(requestId);
        }
        return null;
    }

    public void addRPCFuture(String requestId, RPCFuture rpcFuture) {
        pendingRPC.put(requestId, rpcFuture);
    }

    public boolean removeRPCFuture(String requestId, RPCFuture rpcFuture) {
        return pendingRPC.remove(requestId, rpcFuture);
    }

    public void clearPendRPC() {
        pendingRPC.clear();
    }

}


package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.service.rpc.client.net.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
@Service
public abstract class AbstractRpcConnectManager {
    @Autowired
    private ApplicationContext context;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRpcConnectManager.class);

    private ThreadPoolExecutor threadPoolExecutor;

    private final Map<Integer, RpcClient> serverNodes = new HashMap<>();

    private final AtomicInteger roundRobin = new AtomicInteger();

    @Value("${netty.rpcConnectThreadSize:16}")
    private Integer rpcConnectThreadSize;

    public void initManager() {
        threadPoolExecutor = new ThreadPoolExecutor(rpcConnectThreadSize, rpcConnectThreadSize, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
    }

//    public synchronized void initServers(List<SdServer> allServerAddress) throws InterruptedException {
//        //增加同步，当前
//        if (allServerAddress != null) {
////                serverNodes.clear();
//            for (SdServer sdServer : allServerAddress) {
//                if (serverNodes.containsKey(sdServer.getServerId())) {
//                    continue;
//                }
//                RpcNodeInfo rpcNodeInfo = new RpcNodeInfo();
//                rpcNodeInfo.setServerId(String.valueOf(sdServer.getServerId()));
//                rpcNodeInfo.setHost(sdServer.getIp());
//                rpcNodeInfo.setPort(String.valueOf(sdServer.getRpcPort()));
//                RpcClient rpcClient = context.getBean(RpcClient.class, rpcNodeInfo, threadPoolExecutor);
//                serverNodes.put(sdServer.getServerId(), rpcClient);
//            }
//        }
//    }

//    public synchronized void initZookeeperRpcServers(List<ZooKeeperNodeInfo> zooKeeperNodeInfoList) throws InterruptedException {
//        //增加同步，当前
//        if (zooKeeperNodeInfoList != null) {

    /// /                serverNodes.clear();
//            for (ZooKeeperNodeInfo zooKeeperNodeInfo : zooKeeperNodeInfoList) {
//                if (serverNodes.containsKey(zooKeeperNodeInfo.getServerId())) {
//                    continue;
//                }
//                RpcNodeInfo rpcNodeInfo = new RpcNodeInfo();
//                rpcNodeInfo.setServerId(zooKeeperNodeInfo.getServerId());
//                rpcNodeInfo.setHost(zooKeeperNodeInfo.getHost());
//                rpcNodeInfo.setPort(zooKeeperNodeInfo.getPort());
//                RpcClient rpcClient = context.getBean(RpcClient.class, rpcNodeInfo, threadPoolExecutor);
//                serverNodes.put(Integer.parseInt(zooKeeperNodeInfo.getServerId()), rpcClient);
//            }
//        }
//    }
    public RpcClient chooseClient(int serverId) {

        if (serverId == 0) {
            List<RpcClient> handlers = new ArrayList(this.serverNodes.values());
            int size = handlers.size();
            int index = (roundRobin.getAndAdd(1) + size) % size;
            return handlers.get(index);
        } else {
            try {
                RpcClient rpcClient = this.serverNodes.get(serverId);
                return rpcClient;
            } catch (Exception e) {
                LOGGER.error("Waiting for available node is interrupted! ", e);
                throw new RuntimeException("Can't connect any servers!", e);
            }
        }
    }

    public void stop() {
        for (RpcClient rpcClient : serverNodes.values()) {
            rpcClient.close();
        }
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }
}
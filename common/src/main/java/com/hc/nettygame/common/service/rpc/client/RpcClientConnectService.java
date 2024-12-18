package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.rpc.client.impl.DbRpcConnectManager;
import com.hc.nettygame.common.service.rpc.client.impl.NodeRpcConnectManager;
import com.hc.nettygame.common.service.rpc.client.impl.WorldRpcConnectManager;
import com.hc.nettygame.common.service.rpc.server.RpcServerRegisterConfig;
import com.hc.nettygame.common.service.rpc.server.SdServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hc on 2017/3/8.
 * rpc的服务发现
 */
@Service
public class RpcClientConnectService implements IService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClientConnectService.class);

    @Autowired
    private WorldRpcConnectManager worldRpcConnectManager;
    @Autowired
    private NodeRpcConnectManager nodeRpcConnectManager;
    @Autowired
    private DbRpcConnectManager dbRpcConnectManager;
    @Autowired()
    private RpcServerRegisterConfig rpcServerRegisterConfig;

    public void initWorldConnectedServer(List<SdServer> sdServerList) throws Exception {
        if (sdServerList == null || sdServerList.isEmpty()) {
            return;
        }
        worldRpcConnectManager.initServers(sdServerList);
        LOGGER.info("worldRpcConnectManager.initServers {}", (Object) sdServerList.stream().map(sdServer -> new Integer[]{sdServer.getServerId(), sdServer.getPort()}).toArray(Integer[][]::new));
    }

    public void initNodeConnectedServer(List<SdServer> sdServerList) throws Exception {
        if (sdServerList == null || sdServerList.isEmpty()) {
            return;
        }
        nodeRpcConnectManager.initServers(sdServerList);
        LOGGER.info("nodeRpcConnectManager.initServers {}", (Object) sdServerList.stream().map(sdServer -> new Integer[]{sdServer.getServerId(), sdServer.getPort()}).toArray(Integer[][]::new));
    }

    public void initDbConnectServer(List<SdServer> sdServerList) throws Exception {
        if (sdServerList == null || sdServerList.isEmpty()) {
            return;
        }
        dbRpcConnectManager.initServers(sdServerList);
        LOGGER.info("dbRpcConnectManager.initServers {}", (Object) sdServerList.stream().map(sdServer -> new Integer[]{sdServer.getServerId(), sdServer.getPort()}).toArray(Integer[][]::new));
    }

    @Override
    public String getId() {
        return ServiceName.RpcServiceDiscovery;
    }

    @Override
    public void startup() throws Exception {
        nodeRpcConnectManager.initManager();
        worldRpcConnectManager.initManager();
        dbRpcConnectManager.initManager();
        init();
    }

    @Override
    public void shutdown() throws Exception {
        nodeRpcConnectManager.stop();
        worldRpcConnectManager.stop();
        dbRpcConnectManager.stop();
    }

    public void init() throws Exception {
        initNodeConnectedServer(rpcServerRegisterConfig.getSdNodeServers());
        initWorldConnectedServer(rpcServerRegisterConfig.getSdWorldServers());
        initDbConnectServer(rpcServerRegisterConfig.getSdDbServers());
    }


    public AbstractRpcConnectManager getRpcConnectManager(BOEnum boEnum) {
        if (boEnum == BOEnum.NODE) {
            return nodeRpcConnectManager;
        }
        if (boEnum == BOEnum.DB) {
            return dbRpcConnectManager;
        }
        return worldRpcConnectManager;
    }

//    public AbstractRpcConnectManager getRpcConnectManager(ZooKeeperNodeBoEnum zooKeeperNodeBoEnu) {
//        AbstractRpcConnectManager abstractRpcConnectManager = worldRpcConnectManager;
//        if (zooKeeperNodeBoEnu == ZooKeeperNodeBoEnum.GAME) {
//        } else if (zooKeeperNodeBoEnu == ZooKeeperNodeBoEnum.DB) {
//        }
//        return worldRpcConnectManager;
//    }

//    public void notifyConnect(ZooKeeperNodeBoEnum zooKeeperNodeBoEnum, List<ZooKeeperNodeInfo> zooKeeperNodeInfoList) throws InterruptedException {
//        getRpcConnectMannger(zooKeeperNodeBoEnum).initZookeeperRpcServers(zooKeeperNodeInfoList);
//    }


}


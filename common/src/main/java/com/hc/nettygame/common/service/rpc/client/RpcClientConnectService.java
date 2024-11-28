package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.rpc.client.impl.DbRpcConnnectMananger;
import com.hc.nettygame.common.service.rpc.client.impl.GameRpcConnecetMananger;
import com.hc.nettygame.common.service.rpc.client.impl.WorldRpcConnectManager;
import com.hc.nettygame.common.service.rpc.server.zookeeper.ZooKeeperNodeBoEnum;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/8.
 * rpc的服务发现
 */
@Service
public class RpcClientConnectService implements IService {

    private static final Logger LOGGER = Loggers.rpcLogger;

    protected Object lock = new Object();


    @Autowired
    private WorldRpcConnectManager worldRpcConnectManager;

    @Autowired
    private GameRpcConnecetMananger gameRpcConnecetMananger;

    @Autowired
    private DbRpcConnnectMananger dbRpcConnnectMananger;

//    public void initWorldConnectedServer(List<SdServer> sdServerList) throws Exception {
//        worldRpcConnectManager.initServers(sdServerList);
//    }
//
//    public void initGameConnectedServer(List<SdServer> sdServerList) throws Exception {
//        gameRpcConnecetMananger.initServers(sdServerList);
//    }
//
//    public void initDbConnectServer(List<SdServer> sdServerList) throws Exception {
//        dbRpcConnnectMananger.initServers(sdServerList);
//    }

    @Override
    public String getId() {
        return ServiceName.RpcServiceDiscovery;
    }

    @Override
    public void startup() throws Exception {
        worldRpcConnectManager.initManager();
        gameRpcConnecetMananger.initManager();
        dbRpcConnnectMananger.initManager();
        init();
    }

    @Override
    public void shutdown() throws Exception {
        worldRpcConnectManager.stop();
        gameRpcConnecetMananger.stop();
        dbRpcConnnectMananger.stop();
    }

    public void init() throws Exception {
//        GameServerConfigService gameServerConfigService = LocalMananger.getInstance().getLocalSpringServiceManager().getGameServerConfigService();
//        initWorldConnectedServer(gameServerConfigService.getRpcServerRegisterConfig().getSdWorldServers());
//        initGameConnectedServer(gameServerConfigService.getRpcServerRegisterConfig().getSdGameServers());
//        initDbConnectServer(gameServerConfigService.getRpcServerRegisterConfig().getSdDbServers());
    }


    public AbstractRpcConnectManager getRpcConnectMannger(BOEnum boEnum) {
        AbstractRpcConnectManager abstractRpcConnectManager = worldRpcConnectManager;
        if (boEnum == BOEnum.GAME) {
            return gameRpcConnecetMananger;
        }
        if (boEnum == BOEnum.DB) {
            return dbRpcConnnectMananger;
        }
        return worldRpcConnectManager;
    }

    public AbstractRpcConnectManager getRpcConnectMannger(ZooKeeperNodeBoEnum zooKeeperNodeBoEnu) {
        AbstractRpcConnectManager abstractRpcConnectManager = worldRpcConnectManager;
        if (zooKeeperNodeBoEnu == ZooKeeperNodeBoEnum.GAME) {
        } else if (zooKeeperNodeBoEnu == ZooKeeperNodeBoEnum.DB) {
        }
        return worldRpcConnectManager;
    }

//    public void notifyConnect(ZooKeeperNodeBoEnum zooKeeperNodeBoEnum, List<ZooKeeperNodeInfo> zooKeeperNodeInfoList) throws InterruptedException {
//        getRpcConnectMannger(zooKeeperNodeBoEnum).initZookeeperRpcServers(zooKeeperNodeInfoList);
//    }


}


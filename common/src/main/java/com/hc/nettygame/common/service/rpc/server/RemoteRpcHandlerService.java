//package com.hc.nettygame.common.service.rpc.server;
//
//import com.hc.nettygame.common.bootstrap.LocalMananger;
//import com.hc.nettygame.common.config.GameServerConfig;
//import com.hc.nettygame.common.constant.ServiceName;
//import com.hc.nettygame.common.service.IService;
//import com.hc.nettygame.common.service.config.GameServerConfigService;
//import com.hc.nettygame.common.util.ExecutorUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
/// **
// * Created by hc on 2017/3/8.
// */
//@Service
//public class RemoteRpcHandlerService implements IService {
//
//    @Autowired
//    private RpcHandlerThreadPool rpcHandlerThreadPool;
//
//    @Override
//    public String getId() {
//        return ServiceName.RemoteRpcService;
//    }
//
//    @Override
//    public void startup() throws Exception {
//        GameServerConfigService gameServerConfigService = LocalMananger.getInstance().getLocalSpringServiceManager().getGameServerConfigService();
//        GameServerConfig gameServerConfig = gameServerConfigService.getGameServerConfig();
//        if (gameServerConfig.isRpcOpen()) {
//            //开启服务
//            rpcHandlerThreadPool.createExecutor(gameServerConfig.getRpcThreadPoolSize(), gameServerConfig.getRpcThreadPoolQueueSize());
//        }
//    }
//
//    @Override
//    public void shutdown() throws Exception {
//        ExecutorUtil.shutdownAndAwaitTermination(rpcHandlerThreadPool.getExecutor());
//    }
//
//    public void submit(Runnable runnable) {
//        rpcHandlerThreadPool.getExecutor().submit(runnable);
//    }
//}

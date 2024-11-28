package com.hc.nettygame.common.service.rpc.client.net;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.net.RpcResponse;
import com.hc.nettygame.common.service.rpc.client.RPCFuture;
import com.hc.nettygame.common.service.rpc.client.RPCFutureService;
import com.hc.nettygame.common.service.rpc.server.RpcNodeInfo;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * Created by hc on 17/3/14.
 * 检查客户端连接
 */
@Component
@Scope("prototype")
public class RpcClient {
    private final Logger logger = Loggers.rpcLogger;
    @Getter
    private final RpcClientConnection rpcClientConnection;
    @Autowired
    private RPCFutureService rpcFutureService;
    @Autowired
    private ApplicationContext context;

    @Autowired
    public RpcClient(RpcNodeInfo rpcNodeInfo, ExecutorService threadPool) {
        rpcClientConnection = new RpcClientConnection(this, rpcNodeInfo, threadPool);
    }

    public RPCFuture sendRequest(RpcRequest request) {
        RPCFuture rpcFuture = context.getBean(RPCFuture.class, request);
        rpcFutureService.addRPCFuture(request.getRequestId(), rpcFuture);
        rpcClientConnection.writeRequest(request);
        return rpcFuture;
    }

    public NioSocketChannel getChannel() {
        return rpcClientConnection.getChannel();
    }

    public void close() {
        logger.info("rpc client close");
        if (rpcClientConnection != null) {
            rpcClientConnection.close();
        }
    }

    public void handleRpcResponser(RpcResponse rpcResponse) {
        String requestId = rpcResponse.getRequestId();
        RPCFuture rpcFuture = rpcFutureService.getRPCFuture(requestId);
        if (rpcFuture != null) {
            boolean removeFlag = rpcFutureService.removeRPCFuture(requestId, rpcFuture);
            if (removeFlag) {
                rpcFuture.done(rpcResponse);
            } else {
                //表示服务器已经处理过了,可能已经超时了
                logger.error("rpcFuture is remove " + requestId);
            }
        }
    }

}

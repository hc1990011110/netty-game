package com.hc.nettygame.common.service.rpc.client.net;

import com.hc.nettygame.common.service.rpc.server.RpcNodeInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * Created by hc on 17/3/14.
 * 服务器连接
 */
@Component
@Scope("prototype")
public class RpcServerConnectTask implements Runnable {

    private final Logger LOGGER = LoggerFactory.getLogger(RpcServerConnectTask.class);

    private final InetSocketAddress remotePeer;

    private final EventLoopGroup eventLoopGroup;

    private final RpcClient rpcClient;

    @Autowired
    private RpcClientInitializer rpcClientInitializer;

    @Autowired
    public RpcServerConnectTask(RpcNodeInfo rpcNodeInfo, EventLoopGroup eventLoopGroup, RpcClient rpcClient) {
        this.remotePeer = new InetSocketAddress(rpcNodeInfo.getHost(), rpcNodeInfo.getIntPort());
        this.eventLoopGroup = eventLoopGroup;
        this.rpcClient = rpcClient;
    }

    @Override
    public void run() {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .handler(rpcClientInitializer);
        ChannelFuture channelFuture = b.connect(remotePeer);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    LOGGER.info("connect to remote server. remote peer = " + remotePeer + " success");
                    RpcClientHandler handler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                    handler.setRpcClient(rpcClient);
                    rpcClient.getRpcClientConnection().setChannel((NioSocketChannel) channelFuture.channel());
                } else {
                    LOGGER.info("connect to remote server. remote peer = " + remotePeer + "fail");
                }
            }

        });
        try {
            channelFuture.await();
        } catch (InterruptedException e) {
            LOGGER.error(e.toString(), e);
        }

        //连接结束
        LOGGER.debug("connect to remote server. remote peer = " + remotePeer);
    }
}

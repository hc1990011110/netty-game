package com.hc.nettygame.common.service.net;


import com.hc.nettygame.common.constant.Loggers;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Getter;
import org.slf4j.Logger;

import java.net.InetSocketAddress;

@Getter
public class AbstractNettyTcpServerService extends AbstractNettyServerService {
    private final Logger LOGGER = Loggers.serverLogger;
    private final ChannelInitializer channelInitializer;
    protected InetSocketAddress serverAddress;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture serverChannelFuture;

    public AbstractNettyTcpServerService(String serviceId, int serverPort, ChannelInitializer channelInitializer) {
        super(serviceId, serverPort);
        this.channelInitializer = channelInitializer;
    }

    @Override
    public boolean startService() throws Exception {
        boolean serviceFlag = super.startService();
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(0);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap = serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_REUSEADDR, true) //重用地址
                    .childOption(ChannelOption.SO_RCVBUF, 65536)
                    .childOption(ChannelOption.SO_SNDBUF, 65536)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))  // heap buf 's better
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(channelInitializer);

            serverChannelFuture = serverBootstrap.bind(serverPort).sync();

            //TODO这里会阻塞main线程，暂时先注释掉
//            serverChannelFuture.channel().closeFuture().sync();
            serverChannelFuture.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            serviceFlag = false;
        }
        return serviceFlag;
    }

    @Override
    public boolean stopService() throws Exception {
        boolean flag = super.stopService();
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        return flag;
    }

    public void finish() throws InterruptedException {
//        serverChannelFuture.channel().closeFuture().sync();
    }
}

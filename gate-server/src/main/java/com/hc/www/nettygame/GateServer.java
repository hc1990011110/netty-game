package com.hc.www.nettygame;

import com.hc.www.nettygame.constant.GlobalConstants;
import com.hc.www.nettygame.constant.Loggers;
import com.hc.www.nettygame.exception.StartUpException;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GateServer implements CommandLineRunner {
    private final Logger LOGGER = Loggers.serverLogger;
    /**
     * tcp服务
     */
    private GameNettyTcpServerService gameNettyTcpServerService;
    private ChannelInitializer<NioSocketChannel> nettyTcpChannelInitializer;

    public static void main(String[] args) {
        SpringApplication.run(GateServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       this.initNetService();
    }

    public void initNetService() throws Exception {
        gameNettyTcpServerService = new GameNettyTcpServerService("aaa",6
                , GlobalConstants.Thread.NET_TCP_BOSS, GlobalConstants.Thread.NET_TCP_WORKER, new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
//                ch.pipeline().addLast(new GameServerHandler());
            }
        });
        boolean startUpFlag = gameNettyTcpServerService.startService();
        if(!startUpFlag){
            throw  new StartUpException("tcp server startup error");
        }
        LOGGER.info("gameNettyTcpServerService start " + startUpFlag);
    }
}

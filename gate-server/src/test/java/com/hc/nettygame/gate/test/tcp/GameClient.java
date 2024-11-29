package com.hc.nettygame.gate.test.tcp;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.message.command.MessageCommandFactory;
import com.hc.nettygame.common.service.message.decoder.NetProtoBufMessageTCPDecoder;
import com.hc.nettygame.common.service.message.decoder.NetProtoBufTcpMessageDecoderFactory;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufMessageTCPEncoder;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufTcpMessageEncoderFactory;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MessageRegistry.class, MessageCommandFactory.class, NetProtoBufMessageTCPEncoder.class, NetProtoBufTcpMessageEncoderFactory.class, NetProtoBufMessageTCPDecoder.class, NetProtoBufTcpMessageDecoderFactory.class})
public class GameClient implements CommandLineRunner {
    private final Logger LOGGER = Loggers.serverLogger;

    @Value("${netty.tcp-port}")
    private int serverPort;

    @Autowired
    private GameClientChannelInitializer gameClientChannelInitializer;
    @Autowired
    private MessageRegistry messageRegistry;

    public static void main(String[] args) {
        SpringApplication.run(GameClient.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        startup();
        connect("127.0.0.1", serverPort);
    }

    public void startup() throws Exception {
        messageRegistry.startup();
    }

    public void connect(String addr, int port) throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .handler(gameClientChannelInitializer);
            ChannelFuture f = b.connect(addr, port).sync();
            LOGGER.info("连接服务器:{},本地地址:{}", f.channel().remoteAddress(), f.channel().localAddress());
            f.channel().closeFuture().sync();//等待客户端关闭连接

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}


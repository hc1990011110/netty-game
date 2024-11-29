package com.hc.nettygame.common.service.net;


import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.service.message.decoder.NetProtoBufMessageTCPDecoder;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufMessageTCPEncoder;
import com.hc.nettygame.common.service.net.handler.GameLoggingHandler;
import com.hc.nettygame.common.service.net.handler.GameNetMessageTcpServerHandler;
import com.hc.nettygame.common.service.net.handler.async.AsyncNettyGameNetMessageTcpServerHandler;
import com.hc.nettygame.common.service.net.handler.async.AsyncNettyTcpHandlerService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component()
public class GameTcpServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private AsyncNettyTcpHandlerService asyncNettyTcpHandlerService;
    @Value("${netty.tcpMessageQueueDirectDispatch}")
    private boolean tcpMessageQueueDirectDispatch;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        ChannelPipeline channelPipLine = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipLine.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 0, 0));
        channelPipLine.addLast("encoder", context.getBean(NetProtoBufMessageTCPEncoder.class));
        channelPipLine.addLast("decoder", context.getBean(NetProtoBufMessageTCPDecoder.class));
//        int readerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_READ_TIMEOUT;
//        int writerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_WRITE_TIMEOUT;
        int readerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        int writerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        int allIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;

        if ("dev".equalsIgnoreCase(activeProfile)) {
            channelPipLine.addLast("logger", new GameLoggingHandler(LogLevel.DEBUG));
        }
        channelPipLine.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds));
        if (tcpMessageQueueDirectDispatch) {
            channelPipLine.addLast("handler", context.getBean(GameNetMessageTcpServerHandler.class));
        } else {
            channelPipLine.addLast(asyncNettyTcpHandlerService.getDefaultEventExecutorGroup(), context.getBean(AsyncNettyGameNetMessageTcpServerHandler.class));
        }
    }
}

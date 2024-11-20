package com.hc.nettygame.common;


import com.hc.nettygame.common.constant.GlobalConstants;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

@Component("GameTcpServerChannelInitializer")
public class GameTcpServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        ChannelPipeline channelPipLine = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
//        channelPipLine.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 0, 0));
//        channelPipLine.addLast("encoder", new NetProtoBufMessageTCPEncoder());
//        channelPipLine.addLast("decoder", new NetProtoBufMessageTCPDecoder());
//        int readerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_READ_TIMEOUT;
//        int writerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_WRITE_TIMEOUT;
        int readerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        int writerIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        int allIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;

        channelPipLine.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds));
//        channelPipLine.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
        channelPipLine.addLast("handler", new GameNetMessageTcpServerHandler());
    }
}

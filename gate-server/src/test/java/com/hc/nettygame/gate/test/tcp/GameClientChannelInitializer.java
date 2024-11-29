package com.hc.nettygame.gate.test.tcp;


import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.service.message.decoder.NetProtoBufMessageTCPDecoder;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufMessageTCPEncoder;
import com.hc.nettygame.common.service.net.handler.GameLoggingHandler;
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

@Component
public class GameClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Autowired
    private ApplicationContext context;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline channelPipLine = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipLine.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 0, 0));
        channelPipLine.addLast("encoder", context.getBean(NetProtoBufMessageTCPEncoder.class));
        channelPipLine.addLast("decoder", context.getBean(NetProtoBufMessageTCPDecoder.class));

        int readerIdleTimeSeconds = 0;
        int writerIdleTimeSeconds = 0;
        int allIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        if ("dev".equalsIgnoreCase(activeProfile)) {
            channelPipLine.addLast("logger", new GameLoggingHandler(LogLevel.DEBUG));
        }
        channelPipLine.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds));
        channelPipLine.addLast("handler", new GameClientHandler());
    }
}

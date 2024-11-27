package com.hc.nettygame.gate.test.tcp;


import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.service.message.decoder.NetProtoBufMessageTCPDecoder;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufMessageTCPEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private NetProtoBufMessageTCPEncoder netProtoBufMessageTCPEncoder;
    @Autowired
    private NetProtoBufMessageTCPDecoder netProtoBufMessageTCPDecoder;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        ChannelPipeline channelPipLine = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipLine.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 2, 4, 0, 0));
        channelPipLine.addLast("encoder", netProtoBufMessageTCPEncoder);
        channelPipLine.addLast("decoder", netProtoBufMessageTCPDecoder);

        int readerIdleTimeSeconds = 0;
        int writerIdleTimeSeconds = 0;
        int allIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;

        channelPipLine.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds));
//        channelPipLine.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
        channelPipLine.addLast("handler", new GameClientHandler());
    }
}

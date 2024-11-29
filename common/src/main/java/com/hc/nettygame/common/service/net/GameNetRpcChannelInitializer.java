package com.hc.nettygame.common.service.net;


import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.service.message.decoder.RpcDecoder;
import com.hc.nettygame.common.service.message.encoder.RpcEncoder;
import com.hc.nettygame.common.service.net.handler.GameNetRpcServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/3/8.
 */
@Service()
public class GameNetRpcChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private ApplicationContext context;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        ChannelPipeline channelPipeLine = nioSocketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipeLine.addLast("frame", new LengthFieldBasedFrameDecoder(maxLength, 0, 4, 0, 0));
        channelPipeLine.addLast("decoder", context.getBean(RpcDecoder.class, RpcRequest.class));
        channelPipeLine.addLast("encoder", context.getBean(RpcEncoder.class, RpcResponse.class));
        int readerIdleTimeSeconds = 0;
        int writerIdleTimeSeconds = 0;
        int allIdleTimeSeconds = GlobalConstants.Net.SESSION_HEART_ALL_TIMEOUT;
        channelPipeLine.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds));
        channelPipeLine.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
        channelPipeLine.addLast("handler", context.getBean(GameNetRpcServerHandler.class));
    }
}

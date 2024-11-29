package com.hc.nettygame.common.service.rpc.client.net;

import com.hc.nettygame.common.service.message.decoder.RpcDecoder;
import com.hc.nettygame.common.service.message.encoder.RpcEncoder;
import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.net.RpcResponse;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private ApplicationContext context;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeLine = socketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipeLine.addLast(new LengthFieldBasedFrameDecoder(maxLength, 0, 4, 0, 0));
        channelPipeLine.addLast(context.getBean(RpcEncoder.class, RpcRequest.class));
        channelPipeLine.addLast(context.getBean(RpcDecoder.class, RpcResponse.class));
        channelPipeLine.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
        channelPipeLine.addLast(new RpcClientHandler());
    }
}

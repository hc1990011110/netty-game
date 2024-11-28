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

/**
 *
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        int maxLength = Integer.MAX_VALUE;
        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(maxLength, 0, 4, 0, 0));
        channelPipeline.addLast(new RpcEncoder(RpcRequest.class));
        channelPipeline.addLast(new RpcDecoder(RpcResponse.class));
        channelPipeline.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
        channelPipeline.addLast(new RpcClientHandler());
    }
}

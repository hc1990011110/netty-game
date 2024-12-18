package com.hc.nettygame.common.service.rpc.client.net;

import com.hc.nettygame.common.service.net.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    private final Logger LOGGER = LoggerFactory.getLogger(RpcClientHandler.class);

    //那个服务器
    @Setter
    private RpcClient rpcClient;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
        rpcClient.handleRpcResponser(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("rpc client caught exception", cause);
        rpcClient.close();
    }

}

package com.hc.nettygame.common;


import com.hc.nettygame.common.constant.Loggers;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;


public class GameNetMessageTcpServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger LOGGER = Loggers.serverLogger;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.debug(msg.toString());
    }



}

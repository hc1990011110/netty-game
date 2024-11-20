package com.hc.nettygame.common;


import com.hc.nettygame.common.constant.Loggers;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;


public class GameNetMessageTcpServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger LOGGER = Loggers.serverLogger;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            String received = byteBuf.toString(CharsetUtil.UTF_8);
            LOGGER.info("Received raw data: " + received);
            byteBuf.release(); // 释放内存
        } else {
            LOGGER.info("Unexpected message type: " + msg.getClass());
        }
    }


}

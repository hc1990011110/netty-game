package com.hc.nettygame.gate.test.tcp;

import com.hc.nettygame.common.message.logic.tcp.client.OnlineLoginClientTcpMessage;
import com.hc.nettygame.common.message.logic.tcp.server.OnlineLoginServerTcpMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameClientHandler extends ChannelInboundHandlerAdapter {
    public static Logger LOGGER = LoggerFactory.getLogger(GameClientHandler.class);
//    private final ByteBuf firstMessage;

    public GameClientHandler() {
//        firstMessage = Unpooled.buffer(1024);
//        byte[] sendString = "hello world".getBytes();
//        firstMessage.writeInt(sendString.length);
//        firstMessage.writeBytes(sendString);
//        firstMessage.writeInt(234);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        OnlineHeartClientTcpMessage onlineHeartClientTcpMessage = new OnlineHeartClientTcpMessage();
//        onlineHeartClientTcpMessage.setId(Integer.MAX_VALUE);
//        ctx.writeAndFlush(onlineHeartClientTcpMessage);

        OnlineLoginClientTcpMessage onlineLoginClientTcpMessage = new OnlineLoginClientTcpMessage();
        onlineLoginClientTcpMessage.setId(Integer.MAX_VALUE);
        ctx.writeAndFlush(onlineLoginClientTcpMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ctx.write(msg);
        OnlineLoginServerTcpMessage message = (OnlineLoginServerTcpMessage) msg;
        LOGGER.info("GameClientHandler channelRead playerId:{} token:{}", message.getPlayerId(), message.getToken());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}


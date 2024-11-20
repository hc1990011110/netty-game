package com.hc.nettygame.gate.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


public class GameClientHandler extends ChannelInboundHandlerAdapter {
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

//        OnlineLoginClientTcpMessage onlineLoginClientTcpMessage = new OnlineLoginClientTcpMessage();
//        onlineLoginClientTcpMessage.setFuckName("fuckyou");
//        ctx.writeAndFlush(onlineLoginClientTcpMessage);
        ByteBuf buffer = Unpooled.copiedBuffer("fuckyou", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ctx.write(msg);
        System.out.println(msg);
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


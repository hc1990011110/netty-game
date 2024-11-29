package com.hc.nettygame.common.service.net.handler;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.GameHandlerException;
import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.service.lookup.NetTcpSessionLoopUpService;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.factory.TcpMessageFactory;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import com.hc.nettygame.common.service.net.session.builder.NettyTcpSessionBuilder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractGameNetMessageTcpServerHandler extends ChannelInboundHandlerAdapter {
    public static Logger logger = Loggers.handlerLogger;
    @Autowired
    private TcpMessageFactory tcpMessageFactory;
    @Autowired
    private NettyTcpSessionBuilder nettyTcpSessionBuilder;
    @Autowired
    private NetTcpSessionLoopUpService netTcpSessionLoopUpService;


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelRegistered();
        NettyTcpSession nettyTcpSession = (NettyTcpSession) nettyTcpSessionBuilder.buildSession(ctx.channel());
        boolean flag = netTcpSessionLoopUpService.addNettySession(nettyTcpSession);
        if (!flag) {
            //被限制不能加入
            AbstractNetMessage abstractNetMessage = tcpMessageFactory.createCommonErrorResponseMessage(-1, GameHandlerException.COMMON_ERROR_MAX_CONNECT_TCP_SESSION_NUMBER);
            nettyTcpSession.write(abstractNetMessage);
            nettyTcpSession.close();
            ctx.close();
            return;

        }
        addUpdateSession(nettyTcpSession);
    }

    public abstract void addUpdateSession(NettyTcpSession nettyTcpSession);


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws NetMessageException {
        // Close the connection when an exception is raised.
        if (cause instanceof java.io.IOException) {
            return;
        }

        if (logger.isErrorEnabled()) {
            logger.error("channel exceptionCaught", cause);
        }

        boolean exceptionCloseSessionFlag = true;

        if (exceptionCloseSessionFlag) {
            //设置下线
            disconnect(ctx.channel());
            //销毁上下文
            ctx.close();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object var) throws Exception {
        super.userEventTriggered(ctx, var);
        if (var instanceof IdleStateEvent) {
            //说明是空闲事件
            disconnect(ctx.channel());
        }
    }

    private void disconnect(Channel channel) throws NetMessageException {
        long sessionId = channel.attr(NettyTcpSessionBuilder.channel_session_id).get();
        NettyTcpSession nettySession = (NettyTcpSession) netTcpSessionLoopUpService.lookup(sessionId);
        if (nettySession == null) {
            logger.error("tcp netsession null channelId is:" + channel.id().asLongText());
            return;
        }

        nettySession.close();
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        long sessonId = ctx.channel().attr(NettyTcpSessionBuilder.channel_session_id).get();
        NettyTcpSession nettyTcpSession = (NettyTcpSession) netTcpSessionLoopUpService.lookup(sessonId);
        disconnect(ctx.channel());

        if (nettyTcpSession == null) {
            ctx.fireChannelUnregistered();
            return;
        }

        netTcpSessionLoopUpService.removeNettySession(nettyTcpSession);
        //因为updateService会自己删除，这里不需要逻辑

        ctx.fireChannelUnregistered();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}

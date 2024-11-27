package com.hc.nettygame.common.service.net.handler.async;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.logic.net.NetMessageProcessLogic;
import com.hc.nettygame.common.service.lookup.NetTcpSessionLoopUpService;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.net.MessageAttributeEnum;
import com.hc.nettygame.common.service.net.handler.AbstractGameNetMessageTcpServerHandler;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import com.hc.nettygame.common.service.net.session.builder.NettyTcpSessionBuilder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by hc on 2017/5/22.
 * 使用AsyncNettyTcpHandlerService的handler
 * <p>
 * 不会进行session的游戏内循环经查，断网后直接删除缓存，抛出掉线事件
 */
@Component
@Scope("prototype")
public class AsyncNettyGameNetMessageTcpServerHandler extends AbstractGameNetMessageTcpServerHandler {
    public static Logger LOGGER = Loggers.sessionLogger;

    @Autowired
    private NetTcpSessionLoopUpService netTcpSessionLoopUpService;
    @Autowired
    private NetMessageProcessLogic netMessageProcessLogic;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractNetProtoBufMessage netMessage = (AbstractNetProtoBufMessage) msg;
        Channel channel = ctx.channel();
        //直接进行处理

        long sessonId = channel.attr(NettyTcpSessionBuilder.channel_session_id).get();
        NettyTcpSession nettySession = (NettyTcpSession) netTcpSessionLoopUpService.lookup(sessonId);
        if (nettySession == null) {
            LOGGER.error("tcp netsession null channelId is:" + channel.id().asLongText());
            //已经丢失session， 停止处理
            return;
        }
        netMessage.setAttribute(MessageAttributeEnum.DISPATCH_SESSION, nettySession);
        LOGGER.info("AsyncNettyGameNetMessageTcpServerHandler channelRead {}", msg);
        //进行处理
        netMessageProcessLogic.processMessage(netMessage, nettySession);
    }

    @Override
    public void addUpdateSession(NettyTcpSession nettyTcpSession) {

    }
}

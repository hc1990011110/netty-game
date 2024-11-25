package com.hc.nettygame.common;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.message.command.MessageCommand;
import com.hc.nettygame.common.message.registry.MessageRegistry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameNetMessageTcpServerHandler extends ChannelInboundHandlerAdapter {
    private final Logger LOGGER = Loggers.serverLogger;
    @Autowired
    private MessageRegistry messageRegistry;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractNetProtoBufMessage netMessage = (AbstractNetProtoBufMessage) msg;
        short commandId = netMessage.getNetMessageHead().getCmd();
        MessageCommand messageCommand = messageRegistry.getMessageCommand(commandId);
        LOGGER.info("Received msg commandId :{} class:{}", messageCommand.getCommand_id(), netMessage.getClass().getSimpleName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws NetMessageException {
        // Close the connection when an exception is raised.
        if (cause instanceof java.io.IOException) {
            return;
        }

        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("channel exceptionCaught", cause);
        }

//        if (exceptionCloseSessionFlag) {
//            //设置下线
//            disconnect(ctx.channel());
//
//            //销毁上下文
//            ctx.close();
//        }
    }

//    private static void disconnect(Channel channel) throws NetMessageException {
//        NetTcpSessionLoopUpService netTcpSessionLoopUpService = LocalMananger.getInstance().getLocalSpringServiceManager().getNetTcpSessionLoopUpService();
//        long sessonId = channel.attr(NettyTcpSessionBuilder.channel_session_id).get();
//        NettyTcpSession nettySession = (NettyTcpSession) netTcpSessionLoopUpService.lookup(sessonId);
//        if (nettySession == null) {
//            logger.error("tcp netsession null channelId is:" + channel.id().asLongText());
//            return;
//        }
//
//        nettySession.close();
//    }

}

package com.hc.nettygame.common.service.net.handler;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameNetMessageTcpServerHandler extends AbstractGameNetMessageTcpServerHandler {
    private final Logger LOGGER = Loggers.serverLogger;
    @Autowired
    private MessageRegistry messageRegistry;


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractNetProtoBufMessage netMessage = (AbstractNetProtoBufMessage) msg;
        LOGGER.info("GameNetMessageTcpServerHandler channelRead {}", msg);
        //获取管道
//        IServerPipeLine iServerPipeLine = LocalMananger.getInstance().getLocalSpringBeanManager().getDefaultTcpServerPipeLine();
//        iServerPipeLine.dispatchAction(ctx.channel(), netMessage);
    }

    @Override
    public void addUpdateSession(NettyTcpSession nettyTcpSession) {
        //加入到updateservice
//        UpdateService updateService = LocalMananger.getInstance().getUpdateService();
//        NettyTcpSerssionUpdate nettyTcpSerssionUpdate = new NettyTcpSerssionUpdate(nettyTcpSession);;
//        EventParam<NettyTcpSerssionUpdate> param = new EventParam<NettyTcpSerssionUpdate>(nettyTcpSerssionUpdate);
//        CycleEvent cycleEvent = new CycleEvent(Constants.EventTypeConstans.readyCreateEventType, nettyTcpSerssionUpdate.getUpdateId(), param);
//        updateService.addReadyCreateEvent(cycleEvent);
    }


}

package com.hc.nettygame.common.service.net.handler;


import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GameNetMessageTcpServerHandler extends AbstractGameNetMessageTcpServerHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(GameNetMessageTcpServerHandler.class);


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

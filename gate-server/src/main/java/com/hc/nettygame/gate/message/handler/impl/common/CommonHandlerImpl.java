package com.hc.nettygame.gate.message.handler.impl.common;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.logic.tcp.client.OnlineHeartTcpClientMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import com.hc.nettygame.common.service.message.factory.TcpMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hc on 17/2/15.
 */
@Component
public class CommonHandlerImpl extends AbstractMessageHandler {
    @Autowired
    private TcpMessageFactory tcpMessageFactory;

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_CLIENT_TCP_MESSAGE)
    public AbstractNetMessage handleOnlineHeartMessage(OnlineHeartTcpClientMessage message) throws Exception {
        return tcpMessageFactory.createCommonResponseMessage(message.getSerial());
    }
}

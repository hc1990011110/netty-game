package com.hc.nettygame.gate.message.handler.impl.online;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.logic.tcp.client.OnlineLoginClientTcpMessage;
import com.hc.nettygame.common.message.logic.tcp.server.OnlineLoginServerTcpMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import com.hc.nettygame.common.service.net.MessageAttributeEnum;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hc on 17/2/21.
 */
public class OnlineTcpHandlerImpl extends AbstractMessageHandler {

    private final AtomicLong id = new AtomicLong();

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
    public AbstractNetMessage handleOnlineLoginClientTcpMessage(OnlineLoginClientTcpMessage message) throws Exception {
        OnlineLoginServerTcpMessage onlineLoginServerTcpMessage = new OnlineLoginServerTcpMessage();
        long playerId = 6666 + id.incrementAndGet();
        int token = 333;
        onlineLoginServerTcpMessage.setPlayerId(playerId);
        onlineLoginServerTcpMessage.setToken(token);
//        if (Loggers.sessionLogger.isDebugEnabled()) {
        Loggers.sessionLogger.info("playerId {}, token {}, login", playerId, token);
//        }
        NettyTcpSession clientSesion = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);

        return onlineLoginServerTcpMessage;
    }


}

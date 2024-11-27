package com.hc.nettygame.gate.message.handler.impl.http;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.logic.http.client.OnlineHeartClientHttpMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;

/**
 * Created by jiangwenping on 2017/9/30.
 */
public class HttpHandlerImpl extends AbstractMessageHandler {

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_HTTP_CLIENT_MESSAGE)
    public AbstractNetMessage handleOnlineLoginClientHttpMessage(OnlineHeartClientHttpMessage message) throws Exception {
        OnlineHeartClientHttpMessage onlineHeartClientHttpMessage = new OnlineHeartClientHttpMessage();
        onlineHeartClientHttpMessage.setId(8);
//        OnlineHeartClientHttpMessage onlineLoginServerTcpMessage = new OnlineLoginServerTcpMessage();
//        long playerId = 6666;
//        int tocken = 333;
//        onlineLoginServerTcpMessage.setPlayerId(playerId);
//        onlineLoginServerTcpMessage.setTocken(tocken);
//        if (Loggers.sessionLogger.isDebugEnabled()) {
//            Loggers.sessionLogger.debug( "playerId " + playerId + "tocken " + tocken + "login");
//        }
//        NettyTcpSession clientSesion = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
//        GamePlayer gamePlayer = new GamePlayer(clientSesion.getNettyTcpNetMessageSender(), playerId, tocken);
//        GamePlayerLoopUpService gamePlayerLoopUpService = LocalMananger.getInstance().getLocalSpringServiceManager().getGamePlayerLoopUpService();
//        gamePlayerLoopUpService.addT(gamePlayer);
//        return onlineLoginServerTcpMessage;

        return onlineHeartClientHttpMessage;
    }
}

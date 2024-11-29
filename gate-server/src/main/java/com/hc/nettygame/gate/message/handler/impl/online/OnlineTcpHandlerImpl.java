package com.hc.nettygame.gate.message.handler.impl.online;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.logic.tcp.client.OnlineLoginClientTcpMessage;
import com.hc.nettygame.common.message.logic.tcp.server.OnlineLoginServerTcpMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import com.hc.nettygame.common.service.rpc.client.RpcContextHolder;
import com.hc.nettygame.common.service.rpc.client.RpcContextHolderObject;
import com.hc.nettygame.common.service.rpc.client.RpcProxyService;
import com.hc.nettygame.gate.rpc.service.client.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hc on 17/2/21.
 */
@Service
public class OnlineTcpHandlerImpl extends AbstractMessageHandler {
    @Autowired
    private RpcProxyService rpcProxyService;
    private final AtomicLong id = new AtomicLong();

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
    public AbstractNetMessage handleOnlineLoginClientTcpMessage(OnlineLoginClientTcpMessage message) throws Exception {
        OnlineLoginServerTcpMessage onlineLoginServerTcpMessage = new OnlineLoginServerTcpMessage();
        long playerId = 6666 + id.incrementAndGet();
        int token = 333;
        onlineLoginServerTcpMessage.setPlayerId(playerId);
        onlineLoginServerTcpMessage.setToken(token);
//        if (Loggers.sessionLogger.isDebugEnabled()) {
        Loggers.sessionLogger.info("id:{} playerId:{}, token:{} login", message.getId(), playerId, token);
//        }
//        NettyTcpSession clientSesion = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
        RpcContextHolderObject rpcContextHolderObject = new RpcContextHolderObject(BOEnum.WORLD, 9000);
        RpcContextHolder.setContextHolder(rpcContextHolderObject);
        HelloService helloService = rpcProxyService.createProxy(HelloService.class);
        String result = helloService.hello("mother fucker");
        logger.debug("FUCK: " + result);
        return onlineLoginServerTcpMessage;
    }


}

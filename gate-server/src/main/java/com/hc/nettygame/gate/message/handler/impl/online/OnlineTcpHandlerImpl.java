package com.hc.nettygame.gate.message.handler.impl.online;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.logic.tcp.client.OnlineLoginClientTcpMessage;
import com.hc.nettygame.common.message.logic.tcp.server.OnlineLoginServerTcpMessage;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.command.MessageCommandIndex;
import com.hc.nettygame.common.service.rpc.client.RpcProxyService;
import com.hc.nettygame.common.service.rpc.service.client.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hc on 17/2/21.
 */
@Service
public class OnlineTcpHandlerImpl extends AbstractMessageHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(OnlineTcpHandlerImpl.class);
    private final AtomicLong id = new AtomicLong();
    @Autowired
    private RpcProxyService rpcProxyService;
    @DubboReference(group = "world") // 远程引用 Dubbo 服务
    private HelloService helloService;

    @MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
    public AbstractNetMessage handleOnlineLoginClientTcpMessage(OnlineLoginClientTcpMessage message) throws Exception {
        OnlineLoginServerTcpMessage onlineLoginServerTcpMessage = new OnlineLoginServerTcpMessage();
        long playerId = 6666 + id.incrementAndGet();
        int token = 333;
        onlineLoginServerTcpMessage.setPlayerId(playerId);
        onlineLoginServerTcpMessage.setToken(token);
//        if (Loggers.sessionLogger.isDebugEnabled()) {
        LOGGER.info("id:{} playerId:{}, token:{} login", message.getId(), playerId, token);
//        }
//        NettyTcpSession clientSesion = (NettyTcpSession) message.getAttribute(MessageAttributeEnum.DISPATCH_SESSION);
//        RpcContextHolderObject rpcContextHolderObject = new RpcContextHolderObject(BOEnum.NODE, 8000);
//        RpcContextHolder.setContextHolder(rpcContextHolderObject);
//        HelloService helloService = rpcProxyService.createProxy(HelloService.class);
////        HelloService helloService = rpcProxyService.createRemoteProxy(HelloService.class);
        String result = helloService.hello("mother fucker");
        LOGGER.info("FUCK: {}", result);
        return onlineLoginServerTcpMessage;
    }


}

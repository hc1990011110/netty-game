package com.hc.nettygame.common.service.net.session;

import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hc on 2017/2/9.
 */
public class NettyTcpNetMessageSender implements INetMessageSender {
    private final Logger LOGGER = LoggerFactory.getLogger(NettyTcpNetMessageSender.class);
    private final NettySession nettySession;

    public NettyTcpNetMessageSender(NettySession nettySession) {
        this.nettySession = nettySession;
    }

    @Override
    public boolean sendMessage(AbstractNetMessage message) throws NetMessageException {
        try {
            nettySession.write(message);
        } catch (Exception e) {
            //增加session 消息输出的错误日志
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(e.toString(), e);
            }
            throw new NetMessageException("write tcp netmessage exception", e);
        }

        return true;
    }

    @Override
    public void close() throws NetMessageException {

        LOGGER.debug("Going to close tcp connection in class: {}", this
                .getClass().getName());
//        Event event = Events.event(null, Events.DISCONNECT);
        Channel channel = nettySession.channel;
        if (channel.isActive()) {
            channel.close();
//            channel.write(event).addListener(ChannelFutureListener.CLOSE);
        } else {
            channel.close();
//            LOGGER.debug("Unable to write the Event {} with type {} to socket",
//                    event, event.getType());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to write the Event {} with type {} to socket");
            }
        }
    }
}

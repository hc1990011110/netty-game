package com.hc.nettygame.common.service.net.session.builder;

import com.hc.nettygame.common.service.net.session.ISession;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import com.hc.nettygame.common.service.uuid.LongIdGenerator;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by hc on 2017/2/9.
 * 创造tcpsession 同时标记channel上的sessionId
 */

@Component
public class NettyTcpSessionBuilder implements ISessionBuilder {
    public static final AttributeKey<Long> channel_session_id = AttributeKey
            .valueOf("channel_session_id");
    @Autowired
    private ApplicationContext context;
    @Autowired
    private LongIdGenerator longIdGenerator;

    @Override
    public ISession buildSession(Channel channel) {
        NettyTcpSession nettyTcpSession = context.getBean(NettyTcpSession.class, channel, longIdGenerator);
        channel.attr(channel_session_id).set(nettyTcpSession.getSessionId());
        return nettyTcpSession;
    }
}

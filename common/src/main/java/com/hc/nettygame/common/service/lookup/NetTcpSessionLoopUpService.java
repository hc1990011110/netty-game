package com.hc.nettygame.common.service.lookup;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.limit.AtomicLimitNumber;
import com.hc.nettygame.common.service.net.session.NettySession;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hc on 2017/2/10.
 * session提供服务
 */
@Service
public class NetTcpSessionLoopUpService implements IChannleLookUpService, IService {

    protected static final Logger LOGGER = Loggers.serverStatusStatistics;

    @Setter
    @Getter
    protected ConcurrentHashMap<Long, NettySession> sessions = new ConcurrentHashMap<Long, NettySession>();

    private AtomicLimitNumber atomicLimitNumber;

    @Override
    public NettySession lookup(long channelId) {
        return sessions.get(channelId);
    }

    @Override
    public boolean addNettySession(NettyTcpSession nettyTcpSession) {
        long current = atomicLimitNumber.increment();
        if (!checkMaxNumber(current)) {
            atomicLimitNumber.decrement();
            return false;
        }
        sessions.put(nettyTcpSession.getSessionId(), nettyTcpSession);
        LOGGER.info("add nettySesioin {} sessionId {}", nettyTcpSession.getChannel().id().asLongText(), nettyTcpSession.getSessionId());
        return true;
    }

    public boolean checkMaxNumber(long current) {
        int maxNumber = 10000;
        return current <= maxNumber;
    }

    @Override
    public boolean removeNettySession(NettyTcpSession nettyTcpSession) {
        atomicLimitNumber.decrement();
        boolean result = sessions.remove(nettyTcpSession.getSessionId()) != null;
        LOGGER.info("remove nettySesioin {} sessionId {}", nettyTcpSession.getChannel().id().asLongText(), nettyTcpSession.getSessionId());
        return result;
    }

    @Override
    public String getId() {
        return ServiceName.NetTcpSessionLoopUpService;
    }

    @Override
    public void startup() throws Exception {
        atomicLimitNumber = new AtomicLimitNumber();
    }

    @Override
    public void shutdown() throws Exception {
        sessions.clear();
    }

}

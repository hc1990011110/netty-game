package com.hc.nettygame.common.service.lookup;


import com.hc.nettygame.common.service.net.session.NettySession;
import com.hc.nettygame.common.service.net.session.NettyTcpSession;

/**
 * Created by hc on 17/2/13.
 */
public interface IChannleLookUpService {

    /**
     * 查找
     *
     * @param sessionId
     * @return
     */
    public NettySession lookup(long sessionId);

    /**
     * 增加
     *
     * @param nettyTcpSession
     */
    public boolean addNettySession(NettyTcpSession nettyTcpSession);

    /**
     * 移除
     *
     * @param nettyTcpSession
     * @return
     */
    public boolean removeNettySession(NettyTcpSession nettyTcpSession);
}

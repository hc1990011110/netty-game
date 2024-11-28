package com.hc.nettygame.common.service.net.session;

import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.service.message.AbstractNetMessage;

/**
 * Created by hc on 2017/2/9.
 * 消息处理器
 */
public interface INetMessageSender {
    /**
     * 发送消息
     *
     * @param abstractNetMessage
     * @return
     */
    boolean sendMessage(AbstractNetMessage abstractNetMessage) throws NetMessageException;

    /**
     * 关闭
     */
    void close() throws NetMessageException;
}

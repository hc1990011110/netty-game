package com.hc.nettygame.common.service.message;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hc on 2017/1/24.
 * 网络基本消息
 */
public abstract class AbstractNetMessage implements INetMessage {

    /**
     * 增加默认属性(附带逻辑调用需要的属性)
     */
    private final ConcurrentHashMap<Object, Object> attributes = new ConcurrentHashMap<Object, Object>(3);
    @Getter
    @Setter
    private NetMessageHead netMessageHead;
    @Setter
    @Getter
    private NetMessageBody netMessageBody;

    public int getSerial() {
        return netMessageHead.getSerial();
    }

    /**
     * 逻辑处理时候附带的参数
     *
     * @param key
     * @param value
     * @return
     */
    public Object setAttribute(Object key, Object value) {
        return attributes.put(key, value);
    }

    public Object getAttribute(Object key) {
        return attributes.get(key);
    }

    public void removeAttribute(Object key) {
        this.attributes.remove(key);
    }

    public int getCmd() {
        return getNetMessageHead().getCmd();
    }
}

package com.hc.nettygame.common.message.handler;

import java.lang.reflect.Method;

/**
 * Created by hc on 17/2/8.
 */
public interface IMessageHandler {
    public Method getMessageHandler(int command);
}

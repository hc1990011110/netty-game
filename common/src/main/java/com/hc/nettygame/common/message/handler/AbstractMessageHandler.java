package com.hc.nettygame.common.message.handler;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hc on 17/2/8.
 */
@Setter
@Getter
public abstract class AbstractMessageHandler implements IMessageHandler {
    /**
     * 日志
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class);


    private Map<Integer, Method> handlerMethods;

    public AbstractMessageHandler() {
        init();
    }


    public void init() {
        handlerMethods = new HashMap<Integer, Method>();
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MessageCommandAnnotation.class)) {
                MessageCommandAnnotation messageCommandAnnotation = method.getAnnotation(MessageCommandAnnotation.class);
                if (messageCommandAnnotation != null) {
                    handlerMethods.put(messageCommandAnnotation.command(), method);
                }
            }
        }
    }

    public Method getMessageHandler(int op) {
        return handlerMethods.get(op);
    }
}


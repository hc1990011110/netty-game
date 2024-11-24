package com.hc.nettygame.common.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一定义系统使用的slf4j的Logger
 */
public final class Loggers {
    /**
     * Server相关的日志
     */
    public static final Logger serverLogger = LoggerFactory.getLogger("server");
    /**
     * Game Server相关的日志
     */
    public static final Logger gameLogger = LoggerFactory.getLogger("game");

    /**
     * session相关的日志
     */
    public static final Logger sessionLogger = LoggerFactory.getLogger("session");


    private Loggers() {
    }
}

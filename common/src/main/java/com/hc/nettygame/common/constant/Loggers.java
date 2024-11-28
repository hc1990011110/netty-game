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
     * Game Server相关的日志
     */
    public static final Logger handlerLogger = LoggerFactory.getLogger("handler");
    /**
     * session相关的日志
     */
    public static final Logger sessionLogger = LoggerFactory.getLogger("session");
    /**
     * error相关的日志
     */
    public static final Logger errorLogger = LoggerFactory.getLogger("error");
    /**
     * 服务器状态统计
     */
    public static final Logger serverStatusStatistics = LoggerFactory.getLogger("statistics");
    /**
     * util相关的日志
     */
    public static final Logger utilLogger = LoggerFactory.getLogger("util");
    /**
     * rpc相关的日志
     */
    public static final Logger rpcLogger = LoggerFactory.getLogger("rpc");
    /**
     * thread相关的日志
     */
    public static final Logger threadLogger = LoggerFactory.getLogger("thread");

    private Loggers() {
    }
}

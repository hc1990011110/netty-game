package com.hc.nettygame.common.service.rpc.server;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/3/8.
 */
public final class RpcSystemConfig {
    public static final String SystemPropertyThreadPoolRejectedPolicyAttr = "com.newlandframework.rpc.parallel.rejected.policy";
    public static final String SystemPropertyThreadPoolQueueNameAttr = "com.newlandframework.rpc.parallel.queue";
    public static final int PARALLEL = Math.max(2, Runtime.getRuntime().availableProcessors());

    @Setter
    @Getter
    private static boolean monitorServerSupport = false;

    private RpcSystemConfig() {
    }

}

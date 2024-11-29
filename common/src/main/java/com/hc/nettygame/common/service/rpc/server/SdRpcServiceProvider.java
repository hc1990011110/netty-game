package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.enums.BOEnum;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hc on 17/3/31.
 * rpc服务提供模块
 */
@Getter
@Setter
@Service
public class SdRpcServiceProvider {
    private static final Logger LOGGER = Loggers.rpcLogger;
    private Set<BOEnum> servers;
    private Set<Integer> serverIds;

    //是否世界开放
    public boolean isWorldOpen() {
        return serverIds.contains(BOEnum.WORLD.getBoId());
    }

    public boolean isNodeOpen() {
        return serverIds.contains(BOEnum.NODE.getBoId());
    }

    public boolean isDbOpen() {
        return serverIds.contains(BOEnum.DB.getBoId());
    }

    public boolean validServer(int boId) {
        return serverIds.contains(boId);
    }


}

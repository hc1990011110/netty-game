package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.enums.BOEnum;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hc on 17/4/1.
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "rpc")
public class RpcServerRegisterConfig {
    private static final Logger LOGGER = Loggers.rpcLogger;

    private List<SdServer> sdNodeServers;
    private List<SdServer> sdWorldServers;
    private List<SdServer> sdDbServers;
    private SdRpcServiceProvider sdRpcServiceProvider;

    @PostConstruct
    public void initServerIds() {
        if (sdRpcServiceProvider.getServers() != null) {
            Set<Integer> serverIds = sdRpcServiceProvider.getServers().stream()
                    .map(BOEnum::getBoId) // 提取 boId
                    .collect(Collectors.toSet());
            sdRpcServiceProvider.setServerIds(serverIds);
        }
        LOGGER.info("sdRpcServiceProvider:{} {} {}", sdRpcServiceProvider.isWorldOpen(), sdRpcServiceProvider.isNodeOpen(), sdRpcServiceProvider.isDbOpen());
    }

    public boolean validServer(int boId) {
        return sdRpcServiceProvider.validServer(boId);
    }
}

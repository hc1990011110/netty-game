package com.hc.nettygame.common.service.rpc.server;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 17/4/1.
 */
@Setter
@Getter
public class RpcNodeInfo {

    /**
     * 服务器id
     */
    private String serverId;
    private String host;
    private String port;

    public int getIntPort() {
        return Integer.parseInt(port);
    }
}

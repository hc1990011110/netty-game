package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.enums.BOEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 17/3/15.
 */
@Setter
@Getter
public class RpcContextHolderObject {

    private BOEnum boEnum;
    private int ServerId;

    public RpcContextHolderObject(BOEnum boEnum, int serverId) {
        this.boEnum = boEnum;
        ServerId = serverId;
    }

}

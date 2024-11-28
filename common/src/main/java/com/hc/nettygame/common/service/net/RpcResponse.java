package com.hc.nettygame.common.service.net;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/3/7.
 * rpc的返回对象
 */
@Setter
@Getter
public class RpcResponse {

    private String requestId;
    private String error;
    private Object result;

    public boolean isError() {
        return error != null;
    }

}

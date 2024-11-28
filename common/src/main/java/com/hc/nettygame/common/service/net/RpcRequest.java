package com.hc.nettygame.common.service.net;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/3/7.
 * rpc请求
 */
@Setter
@Getter
public class RpcRequest {

    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

}

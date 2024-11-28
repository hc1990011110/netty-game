package com.hc.nettygame.common.service.rpc.client;

/**
 *
 */
public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);

}

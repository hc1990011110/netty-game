package com.hc.nettygame.common.service;

/**
 * Created by hc on 2017/2/4.
 * 基础服务
 */
public interface IService {
    public String getId();

    public void startup() throws Exception;

    public void shutdown() throws Exception;
}

package com.hc.nettygame.common.service.net;


import com.hc.nettygame.common.bootstrap.ServerServiceManager;
import com.hc.nettygame.common.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象服务基类
 */

public abstract class AbstractServerService implements IServerService {
    private final String serviceId;
    protected byte serviceState;
    @Autowired
    private ServerServiceManager serverServiceManager;

    public AbstractServerService(String serviceId) {
        this.serviceId = serviceId;
    }

    /* (non-Javadoc)
     * @see com.pwrd.core.service.IService#getServiceId()
     */
    @Override
    public final String getServiceId() {
        return serviceId;
    }

    @Override
    public boolean initialize() {
        serverServiceManager.registerService(serviceId, this);
        return true;
    }

    @Override
    public void release() {
        //从全局服务管理器移除自己
        serverServiceManager.removeService(serviceId);
    }

    @Override
    public boolean startService() throws Exception {
        return true;
    }

    @Override
    public boolean stopService() throws Exception {
        return true;
    }

    @Override
    public final byte getState() {
        return serviceState;
    }

    @Override
    public final boolean isRunning() {
        return true;
    }
}
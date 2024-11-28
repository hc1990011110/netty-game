package com.hc.nettygame.common.service.net;


import java.net.InetSocketAddress;

/**
 * Created by hc on 2017/2/4.
 * 抽象的tcp服务
 */
public abstract class AbstractNettyServerService extends AbstractServerService {

    protected int serverPort;
    protected InetSocketAddress serverAddress;

    public AbstractNettyServerService(String serviceId, int serverPort) {
        super(serviceId);
        this.serverPort = serverPort;
        this.serverAddress = new InetSocketAddress(serverPort);
    }
}

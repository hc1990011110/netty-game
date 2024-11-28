package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.util.FileUtil;
import com.hc.nettygame.common.util.JdomUtils;
import lombok.Getter;
import lombok.Setter;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hc on 17/4/1.
 */
@Service
public class RpcServerRegisterConfig {

    private static final Logger LOGGER = Loggers.rpcLogger;

    protected final Object lock = new Object();

    @Setter
    @Getter
    protected List<SdServer> sdWorldServers;
    @Setter
    @Getter
    protected List<SdServer> sdGameServers;
    @Setter
    @Getter
    protected List<SdServer> sdDbServers;

    @Setter
    @Getter
    private SdRpcServiceProvider sdRpcServiceProvider;

    public void init() throws Exception {

        Element rootElement = JdomUtils.getRootElement(FileUtil.getConfigURL(GlobalConstants.ConfigFile.RPC_SERVER_REGISTER_CONFIG).getFile());

        Map<Integer, SdServer> serverMap = new HashMap<>();

        List<SdServer> sdWorldServers = new ArrayList<SdServer>();
        Element element = rootElement.getChild(BOEnum.WORLD.toString().toLowerCase());
        List<Element> childrenElements = element.getChildren("server");
        for (Element childElement : childrenElements) {
            SdServer sdServer = new SdServer();
            sdServer.load(childElement);
            sdWorldServers.add(sdServer);
        }

        List<SdServer> sdGameServers = new ArrayList<SdServer>();
        element = rootElement.getChild(BOEnum.GAME.toString().toLowerCase());
        childrenElements = element.getChildren("server");
        for (Element childElement : childrenElements) {
            SdServer sdServer = new SdServer();
            sdServer.load(childElement);
            sdGameServers.add(sdServer);
        }

        List<SdServer> sdDbServers = new ArrayList<SdServer>();
        element = rootElement.getChild(BOEnum.DB.toString().toLowerCase());
        childrenElements = element.getChildren("server");
        for (Element childElement : childrenElements) {
            SdServer sdServer = new SdServer();
            sdServer.load(childElement);
            sdDbServers.add(sdServer);
        }

        synchronized (this.lock) {
            this.sdWorldServers = sdWorldServers;
            this.sdGameServers = sdGameServers;
            this.sdDbServers = sdDbServers;
        }

        SdRpcServiceProvider sdRpcServiceProvider = new SdRpcServiceProvider();
        rootElement = JdomUtils.getRootElement(FileUtil.getConfigURL(GlobalConstants.ConfigFile.RPC_SERVEICE_CONFIG).getFile());
        childrenElements = rootElement.getChildren("service");
        for (Element childElement : childrenElements) {
            sdRpcServiceProvider.load(childElement);
        }

        synchronized (this.lock) {
            this.sdRpcServiceProvider = sdRpcServiceProvider;
        }
    }

    public boolean validServer(int boId) {
        return sdRpcServiceProvider.validServer(boId);
    }
}

package com.hc.nettygame.common.service.rpc.server;

import lombok.Getter;
import lombok.Setter;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

/**
 * @author jwp
 * 服务器
 */
@Setter
@Getter
public class SdServer {


    /**
     * 服务器配置id
     */
    private int serverId;
    private String ip;
    private int port;
    /**
     * 域名
     */
    private String domain;
    /**
     * 域名端口
     */
    private int domainPort;

    /**
     * 权重
     */
    private int weight;

    /**
     * 最大数量
     */
    private int maxNumber;


    /**
     * 通讯短端口
     */
    private int rpcPort;
    /**
     * 通讯链接数量
     */
    private int rpcClientNumber;

    public void load(Element element) throws DataConversionException {
        serverId = element.getAttribute("serverId").getIntValue();
        domain = element.getAttributeValue("domain");
        domainPort = element.getAttribute("domainPort").getIntValue();
        ip = element.getAttributeValue("ip");
        port = element.getAttribute("port").getIntValue();
        weight = element.getAttribute("weight").getIntValue();
        maxNumber = element.getAttribute("maxNumber").getIntValue();
        rpcPort = element.getAttribute("rpcPort").getIntValue();
        rpcClientNumber = element.getAttribute("rpcClientNumber").getIntValue();
    }


}

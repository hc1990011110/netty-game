package com.hc.nettygame.world.rpc.service.server;

import com.hc.nettygame.common.service.rpc.service.client.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Created by hc on 2017/3/7.
 */
@DubboService
public class DubboHelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Dubbo Hello From World! " + name;
    }
}


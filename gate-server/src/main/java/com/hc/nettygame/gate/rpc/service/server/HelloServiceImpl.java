package com.hc.nettygame.gate.rpc.service.server;

import com.hc.nettygame.gate.rpc.service.client.HelloService;
import org.springframework.stereotype.Repository;

/**
 * Created by hc on 2017/3/7.
 */
//@RpcServiceAnnotation(HelloService.class)
//@RpcServiceBoEnum(bo = BOEnum.WORLD)
@Repository
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello From Gate! " + name;
    }
}


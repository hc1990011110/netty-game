package com.hc.nettygame.node.rpc.service.server;

import com.hc.nettygame.node.rpc.service.client.HelloService;
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
        return "Hello From Node! " + name;
    }
}


package com.hc.nettygame.node.rpc.service.server;

import com.hc.nettygame.common.annotation.RpcServiceAnnotation;
import com.hc.nettygame.common.annotation.RpcServiceBoEnum;
import com.hc.nettygame.common.enums.BOEnum;
import com.hc.nettygame.common.service.rpc.service.client.HelloService;
import org.springframework.stereotype.Repository;

/**
 * Created by hc on 2017/3/7.
 */
@RpcServiceAnnotation(HelloService.class)
@RpcServiceBoEnum(bo = BOEnum.NODE)
@Repository
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello From Node! " + name;
    }
}


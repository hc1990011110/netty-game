package com.hc.nettygame.gate.controller;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.rpc.service.client.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单管理Controller
 * Created by hc on 2018/8/30.
 */
@Controller
@RequestMapping("/order")
public class OmsPortalOrderController {
    public static final Logger LOGGER = Loggers.gameLogger;

    @DubboReference // 远程引用 Dubbo 服务
    private HelloService helloService;


    //    @Operation(summary = "根据ID获取订单详情")
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public String detail(@PathVariable Long orderId) {
        String result = helloService.hello("mother fucker " + orderId);
        LOGGER.info("FUCK: {}", result);
        return result;
    }

}

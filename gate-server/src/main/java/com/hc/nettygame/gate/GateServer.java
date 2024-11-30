package com.hc.nettygame.gate;

import com.hc.nettygame.common.bootstrap.LocalManager;
import com.hc.nettygame.common.bootstrap.NetManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hc.nettygame.common", "com.hc.nettygame.gate"})
@EnableDubbo
public class GateServer implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(GateServer.class);
    @Autowired
    private LocalManager localManager;
    @Autowired
    private NetManager netManager;

    public static void main(String[] args) {
        SpringApplication.run(GateServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        localManager.startup();
        netManager.startup();
    }


}

package com.hc.nettygame.node;


import com.hc.nettygame.common.NetManager;
import com.hc.nettygame.common.constant.Loggers;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hc.nettygame.common"})
public class NodeServer implements CommandLineRunner {
    private final Logger LOGGER = Loggers.serverLogger;

    @Autowired
    private NetManager netManager;

    public static void main(String[] args) {
        SpringApplication.run(NodeServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        netManager.startup();
    }
}

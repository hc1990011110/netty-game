package com.hc.nettygame.common.bootstrap;

import com.hc.nettygame.common.service.message.registry.MessageRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalMananger {

    @Autowired
    private MessageRegistry messageRegistry;

    public void startup() throws Exception {
        messageRegistry.startup();
    }

}

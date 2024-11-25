package com.hc.nettygame.common;

import com.hc.nettygame.common.message.registry.MessageRegistry;
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

package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.annotation.RpcServiceAnnotation;
import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.scanner.ClassScanner;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.Reloadable;
import com.hc.nettygame.common.service.rpc.serialize.protostuff.ProtostuffSerializeI;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hc on 2017/3/7.
 */
@Service
public class RpcMethodRegistry implements Reloadable, IService {
    public static Logger logger = Loggers.serverLogger;
    private final ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();
    public ClassScanner classScanner = new ClassScanner();
    @Value("${netty.rpcServicePackage:com.hc.nettygame.gate.rpc.service.server}")
    private String rpcServicePackage;
    @Autowired
    private ProtostuffSerializeI rpcSerialize;

    @Override
    public String getId() {
        return ServiceName.RpcMethodRegistry;
    }

    @Override
    public void startup() throws Exception {
        reload();
    }

    @Override
    public void shutdown() throws Exception {

    }

    @Override
    public void reload() throws Exception {
        loadPackage(rpcServicePackage, GlobalConstants.FileExtendConstants.Ext);
    }

    public void loadPackage(String namespace, String ext) throws Exception {
        String[] fileNames = classScanner.scannerPackage(namespace, ext);
        // 加载class,获取协议命令
        if (fileNames != null) {
            for (String fileName : fileNames) {
                String realClass = namespace
                        + '.'
                        + fileName.subSequence(0, fileName.length()
                        - (ext.length()));
                Class<?> messageClass = Class.forName(realClass);

                logger.info("rpc load:{}", messageClass);
                RpcServiceAnnotation rpcServiceAnnotation = messageClass.getAnnotation(RpcServiceAnnotation.class);
                if (rpcServiceAnnotation != null) {
                    String interfaceName = messageClass.getAnnotation(RpcServiceAnnotation.class).value().getName();
                    Object serviceBean = rpcSerialize.newInstance(messageClass);
                    registryMap.put(interfaceName, serviceBean);
                    logger.info("rpc register:" + messageClass);
                }
            }
        }
    }

    public Object getServiceBean(String className) {
        return registryMap.get(className);
    }

}

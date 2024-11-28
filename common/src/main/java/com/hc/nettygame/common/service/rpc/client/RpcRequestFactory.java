package com.hc.nettygame.common.service.rpc.client;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.service.net.RpcRequest;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by hc on 2017/3/9.
 */
@Service
public class RpcRequestFactory {

    public Logger logger = Loggers.rpcLogger;

    public RpcRequest createRequest(String className, String methodName, Object[] args) {
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParameters(args);

        Class[] parameterTypes = new Class[args.length];
        // Get the right class type
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = getClassType(args[i]);
        }
        request.setParameterTypes(parameterTypes);

        if (logger.isDebugEnabled()) {
            logger.debug(className);
            logger.debug(methodName);
            for (Class parameterType : parameterTypes) {
                logger.debug(parameterType.getName());
            }
            for (Object arg : args) {
                logger.debug(arg.toString());
            }
        }

        return request;
    }

    private static Class<?> getClassType(Object obj) {
        Class<?> classType = obj.getClass();
        String typeName = classType.getName();
        switch (typeName) {
            case "java.lang.Integer":
                return Integer.TYPE;
            case "java.lang.Long":
                return Long.TYPE;
            case "java.lang.Float":
                return Float.TYPE;
            case "java.lang.Double":
                return Double.TYPE;
            case "java.lang.Character":
                return Character.TYPE;
            case "java.lang.Boolean":
                return Boolean.TYPE;
            case "java.lang.Short":
                return Short.TYPE;
            case "java.lang.Byte":
                return Byte.TYPE;
        }

        return classType;
    }
}

package com.hc.nettygame.common.service.net.handler;

import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.net.RpcResponse;
import com.hc.nettygame.common.service.rpc.server.RemoteRpcHandlerService;
import com.hc.nettygame.common.service.rpc.server.RpcMethodRegistry;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Created by hc on 2017/3/7.
 * rpc协议处理handler
 */
@Service
@Scope("prototype")
public class GameNetRpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private final Logger LOGGER = LoggerFactory.getLogger(GameNetRpcServerHandler.class);
    @Autowired
    private RemoteRpcHandlerService remoteRpcHandlerService;
    @Autowired
    private RpcMethodRegistry rpcMethodRegistry;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final RpcRequest request) throws Exception {
        remoteRpcHandlerService.submit(new Runnable() {
            @Override
            public void run() {
//                if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Receive request {}", request.getRequestId());
//                }
                RpcResponse response = new RpcResponse();
                response.setRequestId(request.getRequestId());
                try {
                    Object result = handle(request);
                    response.setResult(result);
                } catch (Throwable t) {
                    response.setError(t.toString());
                    LOGGER.error("RPC Server handle request error", t);
                }
                ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                        if (LOGGER.isDebugEnabled()) {
                        LOGGER.info("Send response for request {}", request.getRequestId());
//                        }
                    }
                });
            }
        });
    }

    private Object handle(RpcRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = rpcMethodRegistry.getServiceBean(className);
        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(serviceClass.getName());
            LOGGER.debug(methodName);
            for (Class<?> parameterType : parameterTypes) {
                LOGGER.debug(parameterType.getName());
            }
            for (Object parameter : parameters) {
                LOGGER.debug(parameter.toString());
            }
        }

//        // Cglib reflect 反射
//        FastClass serviceFastClass = FastClass.create(serviceClass);
//        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
//        return serviceFastMethod.invoke(serviceBean, parameters);

        //jdk1.7 原生反射速度大于cglib 取消cglib
        Method method = serviceClass.getMethod(methodName, parameterTypes);
        return method.invoke(serviceBean, parameters);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("server caught exception", cause);
        }
        ctx.close();
    }
}


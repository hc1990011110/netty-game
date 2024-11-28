package com.hc.nettygame.common.service.net.handler.async;


import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.service.IService;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by hc on 2017/5/22.
 * netty自带异步tcp handler服务
 */
@Service
public class AsyncNettyTcpHandlerService implements IService {
    @Value("${netty.gameExecutorCorePoolSize:4}")
    private Integer gameExecutorCorePoolSize;
    /**
     * handler线程组
     */
    @Getter
    @Setter
    private DefaultEventExecutorGroup defaultEventExecutorGroup;

    @Override
    public String getId() {
        return ServiceName.AsyncTcpHandlerService;
    }

    @Override
    public void startup() throws Exception {
        defaultEventExecutorGroup = new DefaultEventExecutorGroup(gameExecutorCorePoolSize);
    }

    @Override
    public void shutdown() throws Exception {
        if (defaultEventExecutorGroup != null) {
            defaultEventExecutorGroup.shutdownGracefully();
        }
    }

}

package com.hc.nettygame.common.service.rpc.client.net;

import com.hc.nettygame.common.service.net.RpcRequest;
import com.hc.nettygame.common.service.rpc.server.RpcNodeInfo;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hc on 17/3/14.
 * 管理连接
 */
@Component
@Scope("prototype")
public class RpcClientConnection {
    private final Logger LOGGER = LoggerFactory.getLogger(RpcClientConnection.class);
    private final ReentrantLock statusLock;
    /**
     * 重连线程池工具
     */
    private final ExecutorService threadPool;
    private final RpcClient rpcClient;
    private final RpcNodeInfo rpcNodeInfo;
    //    /**
//     * 重连标识 因为重连的时候没有加锁，会导致检查链接失败，丢失包信息
//     */
//    private volatile boolean reConnect = false;
    EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
    @Autowired
    private ApplicationContext context;
    @Getter
    @Setter
    private NioSocketChannel channel;
    /**
     * 是否启用重连
     */
    private volatile boolean reConnectOn = true;

    @Autowired
    public RpcClientConnection(RpcClient rpcClient, RpcNodeInfo rpcNodeInfo, ExecutorService threadPool) {
        if (threadPool == null) {
            throw new IllegalArgumentException("All parameters must accurate.");
        }
        this.rpcClient = rpcClient;
        this.rpcNodeInfo = rpcNodeInfo;
        this.threadPool = threadPool;
        this.statusLock = new ReentrantLock();
    }

    /**
     * 创建打开连接
     *
     * @return
     */
    public boolean open() {
        // 判断是否已经连接
        if (isConnected()) {
            throw new IllegalStateException("Already connected. Disconnect first.");
        }
        // 创建Socket连接
        try {
            InetSocketAddress remotePeer = new InetSocketAddress(rpcNodeInfo.getHost(), rpcNodeInfo.getIntPort());
            //连接结束
            LOGGER.info("connect to remote server. remote peer = " + remotePeer);
            Future<?> future = threadPool.submit(context.getBean(RpcServerConnectTask.class, rpcNodeInfo, eventLoopGroup, rpcClient));
            future.get();
            if (isConnected()) {
                return false;
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Connect success.");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    //是否连接
    public boolean isConnected() {
        if (channel == null) {
            return false;
        }
        return channel.isActive();
    }


    /**
     * 发送一条消息
     *
     * @param rpcRequest
     * @return
     */
    public boolean writeRequest(RpcRequest rpcRequest) {
        if (!isConnected() && reConnectOn) {
            // 是否正在重连中
//            if (!reConnect) {
            // 重新连接
            tryReConnect();
//            }
            //依然链接不上,返回false
            if (!isConnected()) {
                return false;
            }
        }
        // 发送消息
        if (channel != null) {
//            if (logger.isDebugEnabled()) {
            LOGGER.info("【Send】" + rpcRequest);
//            }
            channel.writeAndFlush(rpcRequest);
            return true;
        }
        return false;
    }

    public void tryReConnect() {

        statusLock.lock();  // block until condition holds
        try {
            if (!isConnected()) {
//                reConnect = true;
                try {
                    //强制链接,进行等待
                    Future<?> future = threadPool.submit(new ReConnect());
                    future.get();
                } catch (Exception e) {
//                    reConnect = false;
                }
            }
        } catch (Exception e) {
//            reConnect = false;
        } finally {
            statusLock.unlock();
        }
    }

    /**
     * 启动自动重连
     */
    public void setReconnectOn() {
        this.reConnectOn = true;
    }

    /**
     * 关闭自动重连
     */
    public void setReconnectOff() {
        this.reConnectOn = false;
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        // 因为需要重连，不能关闭eventLoopGroup
//        eventLoopGroup.shutdownGracefully();
    }

    /**
     * 重连线程内部类
     *
     * @author Fancy
     */
    private class ReConnect implements Runnable {

        public void run() {
            try {
                open();
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Restart connection error.");
                }
            }
        }
    }
}

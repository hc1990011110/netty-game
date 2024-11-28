package com.hc.nettygame.common.service.rpc.server;

import com.hc.nettygame.common.annotation.BlockingQueueType;
import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.constant.Loggers;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by hc on 2017/3/8.
 */
@Service
public class RpcHandlerThreadPool {

    private final Logger LOGGER = Loggers.threadLogger;
    @Getter
    @Setter
    private ExecutorService executor;


    private static BlockingQueue<Runnable> createBlockingQueue(int queues) {
        BlockingQueueType queueType = BlockingQueueType.fromString(System.getProperty(RpcSystemConfig.SystemPropertyThreadPoolQueueNameAttr, "LinkedBlockingQueue"));
        return switch (queueType) {
            case LINKED_BLOCKING_QUEUE -> new LinkedBlockingQueue<Runnable>();
            case ARRAY_BLOCKING_QUEUE -> new ArrayBlockingQueue<Runnable>(RpcSystemConfig.PARALLEL * queues);
            case SYNCHRONOUS_QUEUE -> new SynchronousQueue<Runnable>();
        };
    }

    public Executor createExecutor(int threads, int queues) {
        String name = GlobalConstants.Thread.RPC_HANDLER;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS, createBlockingQueue(queues));
        this.executor = executor;
        return executor;
    }

}


package com.hc.nettygame.common.constant;

import java.util.concurrent.TimeUnit;

/**
 * 全局变量
 */
public class GlobalConstants {

    /**
     * 默认变量
     */
    public static final class Constants {
        public static final int session_prcoss_message_max_size = 10;
        public static final int life_cycle_interval = 6000;

        private Constants() {
        }
    }

    /**
     * 心跳
     */
    public static final class HeartBeart {
        public static final int cycle_time = 50;
        /**
         * 误差大小
         */
        public static final int cycle_diff_time = 150;

        private HeartBeart() {
        }
    }

    /**
     * 异步线程
     */
    public static final class Damond {
        public static final int cycle_time = 500;
        /**
         * 误差大小
         */
        public static final int cycle_diff_time = 500;
        /**
         * 清理欲分配房间
         */
        public static final int clear_pre_room_cycle_time = 30000;

        public static final int pvp_match_cycle_time = 1000;

        private Damond() {
        }
    }


    /**
     * 队列线程执行大小
     */
    public static final class QueueMessageExecutor {
        public static final boolean processLeft = true;

        private QueueMessageExecutor() {
        }
    }

    /**
     * redis Key的基本配置
     */
    public static final class RedisKeyConfig {
        /**
         * 正常缓存有效时间
         */
        public static final int NORMAL_LIFECYCLE = 86400;
        //mget时，key的最大值
        public static final int MGET_MAX_KEY = 50;
        /**
         * 正常缓存有效时间一个月
         */
        public static final int NORMAL_MONTH_LIFECYCLE = 86400 * 24;

        private RedisKeyConfig() {
        }
    }

    /**
     * redis Key的基本配置
     */
    public static final class ConfigFile {
        /**
         * rpc
         */
        public static final String RPC_SERVER_REGISTER_CONFIG = "rpc-server-register.xml";
        /**
         * rpcservice
         */
        public static final String RPC_SERVEICE_CONFIG = "rpc-service-register.xml";

        private ConfigFile() {
        }
    }

    /**
     * JSONFile
     */
    public static final class JSONFile {
        public static final String dict_package = "package";
        public static final String dict_fils = "dict";
        public static final String multiKey = "multiKey";
        public static final String body = "body";

        private JSONFile() {
        }
    }


    /**
     * Thread的名字前缀
     */
    public static final class Thread {
        public static final String NET_TCP_BOSS = "net_tcp_boss";
        public static final String NET_TCP_WORKER = "net_tcp_worker";
        public static final String RPC_HANDLER = "rpc_handler";

        private Thread() {
        }
    }

    /**
     * 网络
     */
    public static final class Net {

        /**
         * 心跳间隔
         */
        public static final int HEART_BASE_SIZE = 1;
        /**
         * 心跳写时间超时(单位秒)
         */
        public static final int SESSION_HEART_WRITE_TIMEOUT = HEART_BASE_SIZE * 60;
        /**
         * 心跳写时间超时(单位秒)
         */
        public static final int SESSION_HEART_READ_TIMEOUT = HEART_BASE_SIZE * 60;
        /**
         * 心跳读写时间超时(单位秒)
         */
        public static final int SESSION_HEART_ALL_TIMEOUT = HEART_BASE_SIZE * 60;

        private Net() {
        }
    }


    /**
     * 上传协议
     */
    public static final class FileExtendConstants {
        public static final String Ext = ".class";

        private FileExtendConstants() {
        }
    }

    ;

    public static final class ZooKeeperConstants {
        public static final String registryAdress = "registry.address";
        public static final String ZK_REGISTRY_PATH = "/rpc_registry";
        public static final String ZK_DATA_PATH = "/data";
        public static final int ZK_SESSION_TIMEOUT = 5000;

        private ZooKeeperConstants() {
        }
    }


    /**
     * 网络管道
     */
    public static final class ChannelPipeline {
        public static final String WebSocketServerHandler = "WebSocketServerHandler";
        public static final String WebSocketFrameServerHandler = "WebSocketFrameServerHandler";

        private ChannelPipeline() {
        }
    }

    public static final class WheelTimer {
        public static final int tickDuration = 20;
        public static final TimeUnit timeUnit = TimeUnit.SECONDS;
        public static final int ticksPerWheel = 60;

        private WheelTimer() {
        }
    }
}
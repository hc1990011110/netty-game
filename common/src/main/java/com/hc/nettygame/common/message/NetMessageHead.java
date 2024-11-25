package com.hc.nettygame.common.message;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/1/24.
 * 网络消息头
 * 魔法头short+版本号byte+长度int+协议命令号short+唯一序列号
 */
@Setter
@Getter
public class NetMessageHead {

    public static final short MESSAGE_HEADER_FLAG = 0x2425;

    /**
     * 魔法头
     */
    private short head;
    /**
     * 版本号
     */
    private byte version;
    /**
     * 长度
     */
    private int length;
    /**
     * 命令
     */
    private short cmd;
    /**
     * 序列号
     */
    private int serial;


    public NetMessageHead() {
        this.head = MESSAGE_HEADER_FLAG;
    }

}

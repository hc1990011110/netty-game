package com.hc.nettygame.common.service.message.command;

import lombok.Getter;

/**
 * Created by hc on 2017/2/4.
 */
public class MessageCommand {
    /**
     * 协议号
     */
    @Getter
    public final int command_id;

    /**
     * constant的id
     */
    @Getter
    public final int bo_id;

    /**
     * 是否需要过滤
     */
    private final boolean is_need_filter;

    public MessageCommand(int commandId, int boId, boolean is_need_filter) {
        this.command_id = commandId;
        this.bo_id = boId;
        this.is_need_filter = is_need_filter;
    }

    public boolean is_need_filter() {
        return is_need_filter;
    }

    public String toString() {
        return "command_id:" + command_id + " bo_id:" + bo_id + " is_need_filter:" + is_need_filter;
    }

}
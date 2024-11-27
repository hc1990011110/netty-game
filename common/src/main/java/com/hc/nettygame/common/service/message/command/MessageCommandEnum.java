package com.hc.nettygame.common.service.message.command;


import com.hc.nettygame.common.enums.BOEnum;

/**
 * Created by hc on 17/2/20.
 */
public enum MessageCommandEnum {

    ONLINE_HEART_MESSAGE(MessageCommandIndex.ONLINE_HEART_CLIENT_TCP_MESSAGE, BOEnum.WORLD, false),
    COMMON_RESPONSE_MESSAGE(MessageCommandIndex.COMMON_RESPONSE_MESSAGE, BOEnum.WORLD, false),
    COMMON_ERROR_RESPONSE_MESSAGE(MessageCommandIndex.COMMON_ERROR_RESPONSE_MESSAGE, BOEnum.WORLD, false),
    ONLINE_HEART_UDP_MESSAGE(MessageCommandIndex.ONLINE_HEART_CLIENT_UDP_MESSAGE, BOEnum.WORLD, true),
    ONLINE_LOGIN_TCP_CLIENT_MESSAGE(MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE, BOEnum.WORLD, false),
    ONLINE_LOGIN_TCP_SERVER_MESSAGE(MessageCommandIndex.ONLINE_LOGIN_TCP_SERVER_MESSAGE, BOEnum.WORLD, false),
    ;
    /**
     * 协议号
     */
    public final int command_id;

    /**
     * boconstant的id
     */
    public final int bo_id;

    /**
     * 是否需要过滤
     */
    public final boolean is_need_filter;

    MessageCommandEnum(int commandId, BOEnum boEnum, boolean is_need_filter) {
        this.command_id = commandId;
        this.bo_id = boEnum.getBoId();
        this.is_need_filter = is_need_filter;
    }

    public static String getMessageCommandName(int commandId) {
        MessageCommandEnum commands = getMessageCommand(commandId);
        return commands.name();

    }

    public static MessageCommandEnum getMessageCommand(int commandId) {
        MessageCommandEnum commands = ONLINE_HEART_MESSAGE;
        MessageCommandEnum[] set = values();
        for (MessageCommandEnum tempCommand : set) {
            if (tempCommand.command_id == commandId) {
                commands = tempCommand;
                break;
            }
        }
        return commands;

    }

    public static int getBoIdByCommandId(int commandId) {
        MessageCommandEnum commands = getMessageCommand(commandId);
        return commands.bo_id;
    }

}

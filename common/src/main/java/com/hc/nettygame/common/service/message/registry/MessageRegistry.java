package com.hc.nettygame.common.service.message.registry;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.scanner.ClassScanner;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.Reloadable;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.message.command.MessageCommand;
import com.hc.nettygame.common.service.message.command.MessageCommandEnum;
import com.hc.nettygame.common.service.message.command.MessageCommandFactory;
import com.hc.nettygame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hc on 17/2/8.
 */
@Service
public class MessageRegistry implements Reloadable, IService {

    public static Logger LOGGER = LoggerFactory.getLogger(MessageRegistry.class);
    private final ConcurrentHashMap<Short, MessageCommand> messageCommandMap = new ConcurrentHashMap<Short, MessageCommand>();
    private final Map<Integer, Class<? extends AbstractNetProtoBufMessage>> messages = new HashMap<Integer, Class<? extends AbstractNetProtoBufMessage>>();
    public ClassScanner classScanner = new ClassScanner();
    @Autowired
    private MessageCommandFactory messageCommandFactory;
    @Value("${netty.netMsgNameSpace}")
    private String netMsgNameSpace;

    public void putMessageCommands(int key, Class putClass) {
        messages.put(key, putClass);
    }

    /**
     * 获取消息对象
     *
     * @param commandId
     * @return
     * @throws Exception
     */
    public final AbstractNetProtoBufMessage getMessage(int commandId) {
        if (commandId < 0) {
            return null;
        }

        try {
            Class<? extends AbstractNetProtoBufMessage> cls = messages.get(commandId);
            if (cls == null) {
                return null;
            }
            AbstractNetProtoBufMessage message = cls.newInstance();
            return message;
        } catch (Exception e) {
            LOGGER.error("getMessage(int) - commandId=" + commandId + ". ", e);
        }
        return null;
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

                LOGGER.info("message load:{}", messageClass);

                MessageCommandAnnotation annotation = messageClass
                        .getAnnotation(MessageCommandAnnotation.class);
                if (annotation != null) {
                    putMessageCommands(annotation.command(), messageClass);
                }
            }
        }
    }

    public final void loadMessageCommand() {
        MessageCommandEnum[] set = MessageCommandEnum.values();
        for (MessageCommandEnum messageCommandEnum : set) {
            MessageCommand messageCommand = new MessageCommand(messageCommandEnum.command_id, messageCommandEnum.bo_id, messageCommandEnum.is_need_filter);
            messageCommandMap.put((short) messageCommandEnum.command_id, messageCommand);
            LOGGER.info("messageCommandEnum load:{}", messageCommandEnum);
        }
        MessageCommand[] messageCommands = messageCommandFactory.getAllCommands();
        for (MessageCommand messageCommand : messageCommands) {
            messageCommandMap.put((short) messageCommand.getCommand_id(), messageCommand);
            LOGGER.info("messageCommand load:{}", messageCommand);
        }
    }

    public MessageCommand getMessageCommand(short commandId) {
        return messageCommandMap.get(commandId);
    }

    public void reload() throws Exception {
        loadMessageCommand();
        List<String> splits = StringUtils.getListBySplit(netMsgNameSpace, ",");
        for (String key : splits) {
            loadPackage(key,
                    GlobalConstants.FileExtendConstants.Ext);
        }
    }

    @Override
    public String getId() {
        return ServiceName.MessageRegistry;
    }

    @Override
    public void startup() throws Exception {
        reload();
    }

    @Override
    public void shutdown() throws Exception {

    }
}


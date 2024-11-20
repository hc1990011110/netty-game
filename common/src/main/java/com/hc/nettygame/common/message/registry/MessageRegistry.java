package com.hc.nettygame.common.message.registry;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.message.command.MessageCommand;
import com.hc.nettygame.common.scanner.ClassScanner;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.Reloadable;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jiangwenping on 17/2/8.
 */
@Service
public class MessageRegistry implements Reloadable, IService {

    public static Logger logger = Loggers.serverLogger;
    private final ConcurrentHashMap<Short, MessageCommand> messageCommandMap = new ConcurrentHashMap<Short, MessageCommand>();
    private final Map<Integer, Class<? extends AbstractNetProtoBufMessage>> messages = new HashMap<Integer, Class<? extends AbstractNetProtoBufMessage>>();
    public ClassScanner classScanner = new ClassScanner();

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
            logger.error("getMessage(int) - commandId=" + commandId + ". ", e);
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

                logger.info("message load:" + messageClass);

                MessageCommandAnnotation annotation = messageClass
                        .getAnnotation(MessageCommandAnnotation.class);
                if (annotation != null) {
                    putMessageCommands(annotation.command(), messageClass);
                }
            }
        }
    }

    public final void loadMessageCommand() {
//        MessageCommandEnum[] set = MessageCommandEnum.values();
//        for(int i = 0; i< set.length; i++){
//            MessageCommandEnum messageCommandEnum = set[i];
//            MessageCommand messageCommand = new MessageCommand(messageCommandEnum.command_id, messageCommandEnum.bo_id, messageCommandEnum.is_need_filter);
//            messageCommandMap.put((short) messageCommandEnum.command_id, messageCommand);
//            logger.info("messageCommands load:" + messageCommandEnum);
//        }

//        LocalSpringBeanManager localSpringBeanManager = LocalMananger.getInstance().getLocalSpringBeanManager();
//        MessageCommandFactory messageCommandFactory = localSpringBeanManager.getMessageCommandFactory();
//        MessageCommand[] messageCommands = messageCommandFactory.getAllCommands();
//        for (MessageCommand messageCommand : messageCommands) {
//            messageCommandMap.put((short) messageCommand.getCommand_id(), messageCommand);
//            logger.info("messageCommands load:" + messageCommand);
//        }
    }

    public MessageCommand getMessageCommand(short commandId) {
        return messageCommandMap.get(commandId);
    }

    public void reload() throws Exception {
        loadMessageCommand();
//        GameServerConfigService gameServerConfigService = LocalMananger.getInstance().getLocalSpringServiceManager().getGameServerConfigService();
//        String netMsgNameSpace = gameServerConfigService.getGameServerConfig().getNetMsgNameSpace();
//        List<String> splits = StringUtils.getListBySplit(netMsgNameSpace, ",");
//        for (String key : splits) {
//            loadPackage(key,
//                    GlobalConstants.FileExtendConstants.Ext);
//        }
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

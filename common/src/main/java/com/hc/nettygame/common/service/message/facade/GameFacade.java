package com.hc.nettygame.common.service.message.facade;


import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.constant.GlobalConstants;
import com.hc.nettygame.common.constant.ServiceName;
import com.hc.nettygame.common.exception.GameHandlerException;
import com.hc.nettygame.common.message.handler.AbstractMessageHandler;
import com.hc.nettygame.common.message.handler.IMessageHandler;
import com.hc.nettygame.common.scanner.ClassScanner;
import com.hc.nettygame.common.service.IService;
import com.hc.nettygame.common.service.Reloadable;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hc on 17/2/8.
 */
@Service
public class GameFacade implements IFacade, Reloadable, IService {
    /**
     * Logger for this class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(GameFacade.class);
    public ClassScanner classScanner = new ClassScanner();
    public String[] fileNames;
    protected Map<Integer, IMessageHandler> handlers = new HashMap<Integer, IMessageHandler>();
    @Value("${netty.netMessageHandlerNameSpace}")
    private String netMessageHandlerNameSpace;
    @Autowired
    private ApplicationContext context;

    /**
     * 获取消息对象
     *
     * @return
     * @throws Exception
     */
    public IMessageHandler getMessageHandler(Class<?> classes) {
        try {
            if (classes == null) {
                return null;
            }
            // 优先从 Spring 容器中获取 Bean
            String simpleName = classes.getSimpleName(); // 获取类名（不含包名）
            String beanName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1); // 首字母小写
            if (context.containsBeanDefinition(beanName)) {
                return (IMessageHandler) context.getBean(classes);
            }
            return (IMessageHandler) classes.newInstance();
        } catch (Exception e) {
            LOGGER.error("getMessageHandler - classes=" + classes.getName()
                    + ". ", e);
        }
        return null;
    }

    public void addHandler(int httpCode, IMessageHandler handler) {
        handlers.put(httpCode, handler);
    }

    @Override
    public AbstractNetMessage dispatch(AbstractNetMessage message)
            throws GameHandlerException {
        try {
            int cmd = message.getCmd();
            IMessageHandler handler = handlers.get(cmd);
            Method method = handler.getMessageHandler(cmd);
            method.setAccessible(true);
            Object object = method.invoke(handler,
                    message);
            AbstractNetMessage result = null;
            if (object != null) {
                result = (AbstractNetMessage) object;
            }
            return result;
        } catch (Exception e) {
            throw new GameHandlerException(e, message.getSerial());
        }
    }

    public void loadPackage(String namespace, String ext)
            throws Exception {
        if (fileNames == null) {
            fileNames = classScanner.scannerPackage(namespace, ext);
        }
        // 加载class,获取协议命令
//        DefaultClassLoader defaultClassLoader = LocalMananger.getInstance().getLocalSpringServiceManager().getDefaultClassLoader();
//        defaultClassLoader.resetDynamicGameClassLoader();

        if (fileNames != null) {
            for (String fileName : fileNames) {
                String realClass = namespace
                        + '.'
                        + fileName.subSequence(0, fileName.length()
                        - (ext.length()));
//                Class<?> messageClass = null;
//                FileClassLoader fileClassLoader = defaultClassLoader.getDefaultClassLoader();
//                if (!defaultClassLoader.isJarLoad()) {
//                    defaultClassLoader.initClassLoaderPath(realClass, ext);
//                    byte[] bytes = fileClassLoader.getClassData(realClass);
//                    messageClass = dynamicGameClassLoader.findClass(realClass, bytes);
//                } else {
//                    //读取 game_server_handler.jar包所在位置
//                    URL url = ClassLoader.getSystemClassLoader().getResource("./");
//                    File file = new File(url.getPath());
//                    File parentFile = new File(file.getParent());
//                    String jarPath = parentFile.getPath() + File.separator + "lib/game_server_handler.jar";
//                    logger.info("message load jar path:" + jarPath);
//                    JarFile jarFile = new JarFile(new File(jarPath));
//                    fileClassLoader.initJarPath(jarFile);
//                    byte[] bytes = fileClassLoader.getClassData(realClass);
//                    messageClass = dynamicGameClassLoader.findClass(realClass, bytes);
//                }
                Class<?> messageClass = Class.forName(realClass);
                LOGGER.info("handler load: " + messageClass);

                IMessageHandler iMessageHandler = getMessageHandler(messageClass);
                AbstractMessageHandler handler = (AbstractMessageHandler) iMessageHandler;
                handler.init();
                Method[] methods = messageClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(MessageCommandAnnotation.class)) {
                        MessageCommandAnnotation messageCommandAnnotation = method
                                .getAnnotation(MessageCommandAnnotation.class);
                        if (messageCommandAnnotation != null) {
                            addHandler(messageCommandAnnotation.command(), iMessageHandler);
                        }
                    }
                }

            }
        }
    }

    @Override
    public void reload() throws Exception {
        try {
            loadPackage(netMessageHandlerNameSpace, GlobalConstants.FileExtendConstants.Ext);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
        }
    }

    @Override
    public String getId() {
        return ServiceName.GameFacade;
    }

    @Override
    public void startup() throws Exception {
        reload();
    }

    @Override
    public void shutdown() throws Exception {

    }
}

package com.hc.nettygame.common.service.net.session;

import com.hc.nettygame.common.bootstrap.LocalMananger;
//import com.hc.nettygame.common.IUpdatable;
import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.message.AbstractNetMessage;
//import com.hc.nettygame.common.message.process.NetProtoBufMessageProcess;
import com.hc.nettygame.common.service.uuid.LongIdGenerator;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jwp on 2017/2/9.
 * netty tcp的session
 */
@Getter
@Component
public class NettyTcpSession extends NettySession{// implements IUpdatable {

    private final long sessionId;
    @Autowired
    private LongIdGenerator longIdGenerator;
    /**
     * 消息发送
     */
    @Setter
    private NettyTcpNetMessageSender nettyTcpNetMessageSender;

    /**
     * 消息处理器
     */
//    private NetProtoBufMessageProcess netProtoBufMessageProcess;

    /**
     * 网络状态检查
     */
//    @Setter
//    private TcpNetStateUpdate tcpNetStateUpdate;

    /**
     * 网络消息切换开关，当玩家进入房间的时候，关闭开关，所有消息处理放入房间内，保证房间内协议处理单线程
     */
    @Setter
    private volatile boolean netMessageProcessSwitch = true;

    public NettyTcpSession(Channel channel) {
        super(channel);
        sessionId = longIdGenerator.generateId();
        nettyTcpNetMessageSender = new NettyTcpNetMessageSender(this);
//        netProtoBufMessageProcess = new NetProtoBufMessageProcess(this);
//        tcpNetStateUpdate = new TcpNetStateUpdate();
    }


//    @Override
    public boolean update() {
//        netProtoBufMessageProcess.update();
        processNetMessage(false);
//        tcpNetStateUpdate.update();
        return false;
    }

    /**
     * 增加消息处理切换。
     * @param switchFlag
     */
    public void processNetMessage(boolean switchFlag){
        if(netMessageProcessSwitch || switchFlag){
//            netProtoBufMessageProcess.update();
        }
    }



    public void addNetMessage(AbstractNetMessage abstractNetMessage){
//        this.netProtoBufMessageProcess.addNetMessage(abstractNetMessage);
    }

    public void close() throws NetMessageException {
        //设置网络状态
//        this.tcpNetStateUpdate.setDisconnecting();
//        this.netProtoBufMessageProcess.close();
        this.nettyTcpNetMessageSender.close();
    }

}

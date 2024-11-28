package com.hc.nettygame.common.service.net.session;

import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.NetMessageException;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

/**
 * Created by hc on 2017/2/9.
 * netty会话
 */
@Setter
@Getter
public abstract class NettySession implements ISession {

    private static final Logger errorLogger = Loggers.errorLogger;

    protected volatile Channel channel;


    private long playerId;

    public NettySession(Channel s) {
        channel = s;
    }

    @Override
    public boolean isConnected() {
        if (channel != null) {
            return channel.isActive();
        }
        return false;
    }

    @Override
    public void write(AbstractNetMessage msg) throws Exception {
        if (msg != null) {
            try {
                channel.writeAndFlush(msg);
            } catch (Exception e) {
                errorLogger.info("session write msg exception", e);
                throw new NetMessageException(e);
            }

//			if(msg instanceof ISliceMessage){
//				final ISliceMessage<BaseMinaMessage> _slices = (ISliceMessage<BaseMinaMessage>) msg;
//				for(final BaseMinaMessage _msg:_slices.getSliceMessages()){
//					// 统计消息数据
//					StatisticsLoggerHelper.logMessageSent(msg);
//					session.write(_msg);
//				}
//			}else{
//				StatisticsLoggerHelper.logMessageSent(msg);
//				session.write(msg);
//			}
        }
    }

    @Override
    public void close(boolean immediately) {
        if (channel != null) {
            channel.close();
        }
    }


    public boolean closeOnException() {
        return true;
    }

    @Override
    public void write(byte[] msg) throws Exception {
        if (channel != null) {
            try {
                channel.writeAndFlush(msg);
            } catch (Exception e) {
                errorLogger.info("session write bytes exception", e);
                throw new NetMessageException(e);
            }
        }
    }

}

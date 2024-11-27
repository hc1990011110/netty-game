package com.hc.nettygame.common.logic.net;


import com.hc.nettygame.common.constant.Loggers;
import com.hc.nettygame.common.exception.GameHandlerException;
import com.hc.nettygame.common.service.message.AbstractNetMessage;
import com.hc.nettygame.common.service.message.AbstractNetProtoBufMessage;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufHttpMessageEncoderFactory;
import com.hc.nettygame.common.service.message.encoder.NetProtoBufTcpMessageEncoderFactory;
import com.hc.nettygame.common.service.message.facade.GameFacade;
import com.hc.nettygame.common.service.message.factory.TcpMessageFactory;
import com.hc.nettygame.common.service.net.session.NettySession;
import com.hc.nettygame.common.util.ErrorsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by hc on 17/2/22.
 * 消息的真正处理
 */
@Service
public class NetMessageProcessLogic {
    protected static final Logger logger = Loggers.sessionLogger;
    protected static final Logger statLog = Loggers.serverStatusStatistics;
    @Autowired
    private GameFacade gameFacade;
    @Autowired
    private TcpMessageFactory tcpMessageFactory;
    @Autowired
    private NetProtoBufHttpMessageEncoderFactory netProtoBufHttpMessageEncoderFactory;

    public void processMessage(AbstractNetMessage message, NettySession nettySession) {
        long begin = System.nanoTime();
        try {
            AbstractNetProtoBufMessage respone;
            respone = (AbstractNetProtoBufMessage) gameFacade.dispatch(message);
            if (respone != null) {
                respone.setSerial(message.getNetMessageHead().getSerial());
                nettySession.write(respone);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                Loggers.errorLogger.error(ErrorsUtil.error("Error",
                        "#.QueueMessageExecutorProcessor.process", "param"), e);
            }

            if (e instanceof GameHandlerException) {
                GameHandlerException gameHandlerException = (GameHandlerException) e;
                AbstractNetMessage errorMessage = tcpMessageFactory.createCommonErrorResponseMessage(gameHandlerException.getSerial(), gameHandlerException.COMMON_ERROR_STATE);
                try {
                    nettySession.write(errorMessage);
                } catch (Exception writeException) {
                    Loggers.errorLogger.error(ErrorsUtil.error("Error",
                            "#.QueueMessageExecutorProcessor.writeErrorMessage", "param"), e);
                }

            }

        } finally {
            if (logger.isInfoEnabled()) {
                // 特例，统计时间跨度
                long time = (System.nanoTime() - begin) / (1000 * 1000);
                if (time > 1) {
                    statLog.info("#CORE.MSG.PROCESS.STATICS Message id:"
                            + message.getNetMessageHead().getCmd() + " Time:"
                            + time + "ms");
                }
            }

        }
    }


    public HttpResponse processMessage(AbstractNetMessage message, HttpRequest request) {

        FullHttpResponse httpResponse = null;
        AbstractNetProtoBufMessage respone = null;
        long begin = System.nanoTime();
        try {
            respone = (AbstractNetProtoBufMessage) gameFacade.dispatch(message);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                Loggers.errorLogger.error(ErrorsUtil.error("Error",
                        "#.QueueMessageExecutorProcessor.process", "param"), e);
            }

            if (e instanceof GameHandlerException) {
                GameHandlerException gameHandlerException = (GameHandlerException) e;
                AbstractNetMessage errorMessage = tcpMessageFactory.createCommonErrorResponseMessage(gameHandlerException.getSerial(), gameHandlerException.COMMON_ERROR_STATE);
            }

        } finally {
            if (logger.isInfoEnabled()) {
                // 特例，统计时间跨度
                long time = (System.nanoTime() - begin) / (1000 * 1000);
                if (time > 1) {
                    statLog.info("#CORE.MSG.PROCESS.STATICS Message id:"
                            + message.getNetMessageHead().getCmd() + " Time:"
                            + time + "ms");
                }
            }

        }
        
        if (respone != null) {
            respone.setSerial(message.getNetMessageHead().getSerial());
            try {
                httpResponse = new DefaultFullHttpResponse(
                        HTTP_1_1, OK,
                        Unpooled.copiedBuffer(netProtoBufHttpMessageEncoderFactory.createByteBuf(respone)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return httpResponse;
    }


    public void processWebSocketMessage(AbstractNetMessage message, Channel channel) {

        AbstractNetProtoBufMessage respone;
        long begin = System.nanoTime();
        try {
            respone = (AbstractNetProtoBufMessage) gameFacade.dispatch(message);
            if (respone != null) {
                respone.setSerial(message.getNetMessageHead().getSerial());

                NetProtoBufTcpMessageEncoderFactory netProtoBufTcpMessageEncoderFactory = new NetProtoBufTcpMessageEncoderFactory();
                ByteBuf byteBuf = netProtoBufTcpMessageEncoderFactory.createByteBuf(respone);

                BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(byteBuf);
                channel.writeAndFlush(binaryWebSocketFrame);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                Loggers.errorLogger.error(ErrorsUtil.error("Error",
                        "#.QueueMessageExecutorProcessor.process", "param"), e);
            }

            if (e instanceof GameHandlerException) {
                GameHandlerException gameHandlerException = (GameHandlerException) e;
                AbstractNetMessage errorMessage = tcpMessageFactory.createCommonErrorResponseMessage(gameHandlerException.getSerial(), gameHandlerException.COMMON_ERROR_STATE);
            }

        } finally {
            if (logger.isInfoEnabled()) {
                // 特例，统计时间跨度
                long time = (System.nanoTime() - begin) / (1000 * 1000);
                if (time > 1) {
                    statLog.info("#CORE.MSG.PROCESS.STATICS Message id:"
                            + message.getNetMessageHead().getCmd() + " Time:"
                            + time + "ms");
                }
            }

        }

    }

}

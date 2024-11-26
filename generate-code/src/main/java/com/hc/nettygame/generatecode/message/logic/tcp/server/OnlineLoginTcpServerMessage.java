package com.hc.nettygame.generatecode.message.logic.tcp.server;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.tcp.server.TcpServerMessageProBuf.*;

/**
* 登录
*
* @author CodeGenerator, don't modify this file please.
*/
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_SERVER_MESSAGE)
public class OnlineLoginTcpServerMessage extends AbstractNetProtoBufTcpMessage {

/**请求*/
		private OnlineLoginTcpServerProBuf req;
	

@Override
public void decoderNetProtoBufMessageBody() throws Exception {
byte[] bytes = getNetMessageBody().getBytes();
	OnlineLoginTcpServerProBuf req = OnlineLoginTcpServerProBuf.parseFrom(bytes);
this.req=req;
}


//以下为客户端专用代码======================================================
@Override
public void encodeNetProtoBufMessageBody() throws Exception {
byte[] bytes = req.toByteArray();
getNetMessageBody().setBytes(bytes);
}

@Override
public void release() {

}
			public void setReq(OnlineLoginTcpServerProBuf req){
	this.req = req;
	}
	public OnlineLoginTcpServerProBuf getReq(){
	return this.req;
	}
	}

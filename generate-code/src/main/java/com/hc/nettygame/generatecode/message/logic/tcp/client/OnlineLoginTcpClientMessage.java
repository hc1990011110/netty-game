package com.hc.nettygame.generatecode.message.logic.tcp.client;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.tcp.client.TcpClientMessageProBuf.*;

/**
* 登录
*
* @author CodeGenerator, don't modify this file please.
*/
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_LOGIN_TCP_CLIENT_MESSAGE)
public class OnlineLoginTcpClientMessage extends AbstractNetProtoBufTcpMessage {

/**请求*/
		private OnlineLoginTcpClientProBuf req;
	

@Override
public void decoderNetProtoBufMessageBody() throws Exception {
byte[] bytes = getNetMessageBody().getBytes();
	OnlineLoginTcpClientProBuf req = OnlineLoginTcpClientProBuf.parseFrom(bytes);
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
			public void setReq(OnlineLoginTcpClientProBuf req){
	this.req = req;
	}
	public OnlineLoginTcpClientProBuf getReq(){
	return this.req;
	}
	}

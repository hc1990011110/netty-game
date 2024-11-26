package com.hc.nettygame.generatecode.message.logic.http.client;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.AbstractNetProtoBufHttpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.http.client.HttpClientMessageProBuf.*;

/**
* 心跳
*
* @author CodeGenerator, don't modify this file please.
*/
@MessageCommandAnnotation(command = MessageCommandIndex.ONLINE_HEART_HTTP_CLIENT_MESSAGE)
public class OnlineHeartHttpClientMessage extends AbstractNetProtoBufHttpMessage {

/**请求*/
        private OnlineHeartHttpClientProBuf req;
    

@Override
public void decoderNetProtoBufMessageBody() throws Exception {
byte[] bytes = getNetMessageBody().getBytes();
    OnlineHeartHttpClientProBuf req = OnlineHeartHttpClientProBuf.parseFrom(bytes);
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
    		public void setReq(OnlineHeartHttpClientProBuf req){
	this.req = req;
	}
	public OnlineHeartHttpClientProBuf getReq(){
	return this.req;
	}
	}

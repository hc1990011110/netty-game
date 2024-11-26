package com.hc.nettygame.generatecode.message.logic.common;

import com.hc.nettygame.common.annotation.MessageCommandAnnotation;
import com.hc.nettygame.common.message.AbstractNetProtoBufTcpMessage;
import com.hc.nettygame.common.message.command.MessageCommandIndex;
import com.hc.nettygame.generatecode.message.auto.common.CommonMessageProBuf.*;

/**
* 通用错误返回
*
* @author CodeGenerator, don't modify this file please.
*/
@MessageCommandAnnotation(command = MessageCommandIndex.COMMON_ERROR_RESPONSE_MESSAGE)
public class CommonErrorResponseServerMessage extends AbstractNetProtoBufTcpMessage {

/**请求*/
		private CommonErrorResponseServerProBuf req;
	

@Override
public void decoderNetProtoBufMessageBody() throws Exception {
byte[] bytes = getNetMessageBody().getBytes();
	CommonErrorResponseServerProBuf req = CommonErrorResponseServerProBuf.parseFrom(bytes);
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
			public void setReq(CommonErrorResponseServerProBuf req){
	this.req = req;
	}
	public CommonErrorResponseServerProBuf getReq(){
	return this.req;
	}
	}

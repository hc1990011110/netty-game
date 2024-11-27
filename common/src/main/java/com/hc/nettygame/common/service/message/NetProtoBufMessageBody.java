package com.hc.nettygame.common.service.message;

import com.google.protobuf.AbstractMessage;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/1/26.
 * protobuf的messagebody实体
 */
@Setter
@Getter
public class NetProtoBufMessageBody extends NetMessageBody {

    //将字节读取为protobuf的抽象对象
    private AbstractMessage abstractMessage;

}

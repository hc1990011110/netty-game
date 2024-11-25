package com.hc.nettygame.common.message;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/1/24.
 */
@Setter
@Getter
public class NetMessageBody {

    /**
     * 存储数据
     */
    private byte[] bytes;

}

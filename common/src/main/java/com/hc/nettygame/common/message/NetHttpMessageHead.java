package com.hc.nettygame.common.message;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hc on 2017/9/28.
 * http消息头部
 */
@Setter
@Getter
public class NetHttpMessageHead extends NetMessageHead {

    private long playerId;
    private String token = "";

}

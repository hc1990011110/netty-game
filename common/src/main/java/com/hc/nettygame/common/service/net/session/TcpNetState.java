package com.hc.nettygame.common.service.net.session;

/**
 * Created by hc on 17/2/21.
 */
public enum TcpNetState {
    //链接状态
    CONNECTED,
    //掉线中
    DISCONNECTING,
    //掉线
    DISCONNECTED,
    //销毁
    DESTROY;
}

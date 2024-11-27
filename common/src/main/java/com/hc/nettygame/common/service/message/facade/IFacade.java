package com.hc.nettygame.common.service.message.facade;


import com.hc.nettygame.common.exception.GameHandlerException;
import com.hc.nettygame.common.service.message.AbstractNetMessage;

/**
 * Created by hc on 17/2/8.
 */
public interface IFacade {
    public AbstractNetMessage dispatch(AbstractNetMessage message) throws GameHandlerException;
}

package com.hc.nettygame.common.exception;


public class StartUpException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StartUpException(String name){
        super(name);
    }
    public StartUpException(String name,Throwable t){
        super(name,t);
    }
}

package com.hc.nettygame.common.service.rpc.serialize;

/**
 * Created by hc on 2017/3/8.
 * rpc对象序列化
 */
public interface IRpcSerialize {

    public <T> byte[] serialize(T obj);

    public <T> T deserialize(byte[] data, Class<T> cls);
}

package com.sprout.baselibrary.net;

public interface INetCallBack<T> {
    void success(T t);

    void onFail(String error);
}

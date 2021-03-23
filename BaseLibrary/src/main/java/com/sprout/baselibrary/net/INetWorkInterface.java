package com.sprout.baselibrary.net;

import java.util.HashMap;

public interface INetWorkInterface {
    <T> void get(String url,  INetCallBack<T> callBack);
}

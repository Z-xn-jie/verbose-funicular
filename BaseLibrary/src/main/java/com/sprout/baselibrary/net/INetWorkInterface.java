package com.sprout.baselibrary.net;

import java.util.HashMap;

import okhttp3.RequestBody;

public interface INetWorkInterface {
    <T> void get(String url,  INetCallBack<T> callBack);
    <T> void postBody(String url, HashMap<String,String> body, INetCallBack<T> callBack);
    <T> void postRequestHeaderBody(String url, HashMap<String,String> header, RequestBody requestBody, INetCallBack<T> callBack);
}

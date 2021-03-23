package com.sprout.searchcomponent.model;

import com.sprout.baselibrary.contract.ILogin;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.baselibrary.utils.RetrofitUtil;

import java.util.HashMap;

public class ImpLoginModel implements ILogin.IModel {
    @Override
    public <T> void getLoginBean(String url, HashMap<String, String> body, INetCallBack<T> callBack) {
        RetrofitUtil.getInstance().postBody(url,body,callBack);
    }
}

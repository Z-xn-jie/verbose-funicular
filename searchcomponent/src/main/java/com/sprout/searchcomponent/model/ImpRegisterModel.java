package com.sprout.searchcomponent.model;

import com.sprout.baselibrary.contract.IRegister;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.baselibrary.utils.RetrofitUtil;

import java.util.HashMap;

public class ImpRegisterModel implements IRegister.IModel {

    @Override
    public <T> void getRegisterBean(String url, HashMap<String, String> body, INetCallBack<T> callBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getBase();
        instance.postBody(url,body,callBack);
    }
}

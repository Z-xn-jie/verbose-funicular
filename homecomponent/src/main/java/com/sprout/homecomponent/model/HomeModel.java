package com.sprout.homecomponent.model;

import com.sprout.baselibrary.base.BaseModel;
import com.sprout.baselibrary.contract.IHome;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.baselibrary.utils.RetrofitUtil;

import okhttp3.RequestBody;

public class HomeModel implements IHome.IModel {

    @Override
    public <T> void getCityBean(String url, INetCallBack<T> callBack) {
        RetrofitUtil.getInstance().get(url,callBack);
    }
}

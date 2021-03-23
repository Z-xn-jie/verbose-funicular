package com.sprout.baselibrary.contract;

import com.sprout.baselibrary.base.BaseModel;
import com.sprout.baselibrary.base.BaseView;
import com.sprout.baselibrary.bean.CityBean;
import com.sprout.baselibrary.bean.LoginBean;
import com.sprout.baselibrary.net.INetCallBack;

import java.util.HashMap;

public interface ILogin {
    interface IView extends BaseView {
        void cityResult(LoginBean bean);
    }
    interface IPresenter {
        void transfer(HashMap<String,String> body);
    }
    interface IModel extends BaseModel {
        <T> void getLoginBean(String url, HashMap<String,String> body, INetCallBack<T> callBack);
    }
}

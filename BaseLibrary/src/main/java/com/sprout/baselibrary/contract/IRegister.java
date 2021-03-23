package com.sprout.baselibrary.contract;

import com.sprout.baselibrary.base.BaseModel;
import com.sprout.baselibrary.base.BaseView;
import com.sprout.baselibrary.bean.CityBean;
import com.sprout.baselibrary.bean.RegisterBean;
import com.sprout.baselibrary.net.INetCallBack;

import java.util.HashMap;

public interface IRegister {
    interface IView extends BaseView {
        void cityResult(RegisterBean bean);
    }
    interface IPresenter {
        void transfer(HashMap<String,String> body);
    }
    interface IModel extends BaseModel {
        <T> void getRegisterBean(String url, HashMap<String,String> body,INetCallBack<T> callBack);
    }
}

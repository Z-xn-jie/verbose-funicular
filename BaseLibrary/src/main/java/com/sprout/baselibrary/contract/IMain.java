package com.sprout.baselibrary.contract;

import com.sprout.baselibrary.base.BaseModel;
import com.sprout.baselibrary.base.BaseView;
import com.sprout.baselibrary.bean.CityBean;
import com.sprout.baselibrary.bean.MoneyBean;
import com.sprout.baselibrary.net.INetCallBack;

import java.util.HashMap;

public interface IMain {
    interface IView extends BaseView {
        void cityResult(MoneyBean bean);
    }
    interface IPresenter {
        void transfer(HashMap<String,String> body);
    }
    interface IModel extends BaseModel {
        <T> void getCityBean(String url,HashMap<String,String> body, INetCallBack<T> callBack);
    }
}

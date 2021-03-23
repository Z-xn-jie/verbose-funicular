package com.sprout.baselibrary.contract;

import com.sprout.baselibrary.base.BaseModel;
import com.sprout.baselibrary.base.BaseView;
import com.sprout.baselibrary.bean.CityBean;
import com.sprout.baselibrary.net.INetCallBack;

public interface IHome {
    interface IView extends BaseView {
        void cityResult(CityBean bean);
    }
    interface IPresenter {
        void transfer(int count);
    }
    interface IModel extends BaseModel {
        <T> void getCityBean(String url, INetCallBack<T> callBack);
    }
}

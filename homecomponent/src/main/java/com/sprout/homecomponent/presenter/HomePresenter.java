package com.sprout.homecomponent.presenter;


import com.sprout.baselibrary.base.BasePresenter;
import com.sprout.baselibrary.bean.CityBean;
import com.sprout.baselibrary.contract.IHome;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.homecomponent.model.HomeModel;

public class HomePresenter extends BasePresenter<IHome.IView, IHome.IModel> implements IHome.IPresenter {


    @Override
    public IHome.IModel getModel() {
        return new HomeModel();
    }

    @Override
    public void transfer(int count) {


        String url = "trends/trendsList?command=1&page="+count+"&size=10&channelid=1";
        mModel.getCityBean(url, new INetCallBack<CityBean>() {
            @Override
            public void success(CityBean cityBean) {
               mView.cityResult(cityBean);
            }

            @Override
            public void onFail(String error) {

            }
        });


    }
}

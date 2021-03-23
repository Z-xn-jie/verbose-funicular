package com.sprout.searchcomponent.presenter;

import com.sprout.baselibrary.base.BasePresenter;
import com.sprout.baselibrary.bean.LoginBean;
import com.sprout.baselibrary.contract.ILogin;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.searchcomponent.model.ImpLoginModel;

import java.util.HashMap;

import static com.sprout.baselibrary.net.UrlConstant.URL_LOGIN;

public class ImpLoginPresenter extends BasePresenter<ILogin.IView,ILogin.IModel> implements ILogin.IPresenter {
    @Override
    protected ILogin.IModel getModel() {
        return new ImpLoginModel();
    }

    @Override
    public void transfer(HashMap<String, String> body) {
        mModel.getLoginBean(URL_LOGIN, body, new INetCallBack<LoginBean>() {
            @Override
            public void success(LoginBean loginBean) {
                mView.cityResult(loginBean);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}

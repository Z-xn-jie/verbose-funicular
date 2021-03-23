package com.sprout.searchcomponent.presenter;

import com.sprout.baselibrary.base.BasePresenter;
import com.sprout.baselibrary.bean.RegisterBean;
import com.sprout.baselibrary.contract.IRegister;
import com.sprout.baselibrary.net.INetCallBack;
import com.sprout.searchcomponent.model.ImpRegisterModel;

import java.util.HashMap;

import retrofit2.http.Url;

import static com.sprout.baselibrary.net.UrlConstant.URL_REGISTER;

public class ImpRegisterPresenter extends BasePresenter<IRegister.IView,IRegister.IModel> implements IRegister.IPresenter{
    @Override
    protected IRegister.IModel getModel() {
        return new ImpRegisterModel();
    }

    @Override
    public void transfer(HashMap<String, String> body) {
        mModel.getRegisterBean(URL_REGISTER, body, new INetCallBack<RegisterBean>() {
            @Override
            public void success(RegisterBean registerBean) {
                mView.cityResult(registerBean);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}

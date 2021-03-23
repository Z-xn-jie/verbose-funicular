package com.sprout.baselibrary.base;

 public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V mView;
    protected M mModel;

    void attachView(V view) {
        this.mView = view;
        this.mModel = getModel();
    }

    protected abstract M getModel();

    void detachView() {
        mView = null;
        mModel = null;
    }
}
package com.sprout.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseDataActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    private Unbinder bind;
    protected T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        if(presenter == null){
            presenter = getPresenter();
        }
        if(presenter!=null){
            presenter.attachView(this);
        }
        initMain();
    }
    protected abstract T getPresenter();

    protected abstract void initMain();

    protected abstract int getLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        presenter.detachView();
    }

}
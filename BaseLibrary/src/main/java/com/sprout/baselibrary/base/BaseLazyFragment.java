package com.sprout.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sprout.baselibrary.contract.IHome;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import kotlin.jvm.Synchronized;

public abstract class BaseLazyFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected Context context = getContext();
    protected Unbinder bind;
    protected T presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext()).inflate(getLayoutID(),null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        if (getContext() == null) {
            context = getContext();
        }
        if(presenter == null){
            presenter = getPresenter();
            presenter.attachView(this);
        }
        initMain();
    }

    protected abstract T getPresenter();

    protected abstract void initMain();

    protected abstract int getLayoutID();

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        presenter.detachView();
    }
}
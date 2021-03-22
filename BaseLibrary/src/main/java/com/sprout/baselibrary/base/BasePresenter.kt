package com.sprout.baselibrary.base

import android.view.View

abstract class BasePresenter<V : BaseView, M : BaseModel> {
    var mView: V? = null
    var mModel: M? = null
    fun attachView(view: V) {
        mView = view
        mModel = getModel()
    }

    abstract fun getModel(): M

    fun detachView() {
        mView = null
        mModel = null
    }
}
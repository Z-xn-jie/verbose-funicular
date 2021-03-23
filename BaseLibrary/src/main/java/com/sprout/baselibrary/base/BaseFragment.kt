package com.example.book.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment  : Fragment() {
    /** 布局.xml文件 */
    protected abstract fun getContentViewLayoutID(): Int

    /** 第一次显示出来（用户第一次看到时） */
    protected abstract fun onFirstVisibleToUser()

    /** 每次显示出来（除去第一次） */
    protected abstract fun onVisibleToUser()

    /** 每次隐藏时 */
    protected abstract fun onInvisibleToUser()
    protected  var con: Context? = context
    private var isFirstVisible: Boolean = true
    private var isPrepared: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (getContentViewLayoutID() != 0) {
            inflater.inflate(getContentViewLayoutID(), container, false);
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(con == null){
            con = context
        }
    }
    override fun onResume() {
        super.onResume()
        if (isFirstVisible) {
            initPrepare();
            isFirstVisible = false;
        } else {
            onVisibleToUser()
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisibleToUser()
    }
    @Synchronized
    private fun initPrepare() {
        if (isPrepared) {
            onFirstVisibleToUser()
        } else {
            isPrepared = true
        }
    }


}
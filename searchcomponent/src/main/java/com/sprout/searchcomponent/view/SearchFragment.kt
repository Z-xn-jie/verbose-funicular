package com.sprout.searchcomponent.view

import android.content.Intent
import com.example.book.base.BaseFragment
import com.sprout.searchcomponent.R
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: BaseFragment() {
    var kv = MMKV.defaultMMKV()


    override fun getContentViewLayoutID(): Int = R.layout.fragment_search

    override fun onFirstVisibleToUser() {
        var  token = kv!!.decodeString("token")
        btn_ok.setOnClickListener {
            if(token==null) {
                startActivity(Intent(activity, LoginActivity::class.java))
            }else{
                btn_ok.text = "已登錄"
                btn_ok.isEnabled = false
            }
        }

    }

    override fun onVisibleToUser() {

    }

    override fun onInvisibleToUser() {

    }

}
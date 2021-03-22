package com.sprout.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initMain()
    }

    abstract fun initMain()

    abstract fun getLayoutId(): Int
}
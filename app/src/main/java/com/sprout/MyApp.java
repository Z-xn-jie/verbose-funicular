package com.sprout;

import android.app.Application;
import android.widget.Toast;

import com.tencent.mmkv.MMKV;

public class MyApp extends Application {
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        //MMKV初始化
        String initialize = MMKV.initialize(this);
        Toast.makeText(myApp, "mmkv root: " + initialize, Toast.LENGTH_SHORT).show();
    }
}

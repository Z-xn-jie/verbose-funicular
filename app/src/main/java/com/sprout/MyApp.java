package com.sprout;

import android.app.Application;
import android.widget.Toast;

import com.sprout.baselibrary.interaction.IBaseAppComponent;
import com.tencent.mmkv.MMKV;

public class MyApp extends Application implements IBaseAppComponent {
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
       initialize(this);
    }

    @Override
    public void initialize(Application application) {
        myApp = (MyApp) application;
        //MMKV初始化
        String initialize = MMKV.initialize(this);
        Toast.makeText(myApp, "mmkv root: " + initialize, Toast.LENGTH_SHORT).show();
        try {
            for (String app : AppComponontConfig.COMPONENTS) {
                // COMPONENTS是一个AppComponontConfig类里的静态字符串数组----->
                // ，包含了所有component的包名
                // 用包名反射，判断是否实现了IBaseAPPComponent
                Object aClass = Class.forName(app).newInstance();
                if (aClass instanceof IBaseAppComponent) {
                    // 确保component实现了此接口
                    ((IBaseAppComponent) aClass).initialize(this);// ---->
                    // 这里主app的在整体运行时需要提供全局统一的上下文
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

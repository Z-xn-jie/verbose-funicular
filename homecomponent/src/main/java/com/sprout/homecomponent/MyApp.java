package com.sprout.homecomponent;

import android.app.Application;


import com.sprout.baselibrary.interaction.IBaseAppComponent;
import com.sprout.baselibrary.interaction.IHomeService;
import com.sprout.baselibrary.interaction.ServiceFactory;


public class MyApp extends Application implements IBaseAppComponent {
    private static Application myApp;

    @Override
    public void onCreate() {
        super.onCreate();
       initialize(this);
    }

    @Override
    public void initialize(Application application) {
        myApp =  application;
    }
}

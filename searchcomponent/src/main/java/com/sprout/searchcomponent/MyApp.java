package com.sprout.searchcomponent;

import android.app.Application;

import com.sprout.baselibrary.interaction.IBaseAppComponent;


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

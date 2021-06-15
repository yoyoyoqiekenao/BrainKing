package com.example.brainking;

import android.app.Application;
import android.content.Context;

/**
 * @author : 徐无敌
 * date   : 2021/6/1514:04
 * desc   :
 */
public class MyApplication extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
    }
}

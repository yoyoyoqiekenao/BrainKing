package com.example.brainking;

import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * @author : 徐无敌
 * date   : 2021/6/1010:15
 * desc   :
 */
public class AppContext {

    private static Context context;

    public static Context getContext() {
        return Utils.getApp();
    }

    public static void setContext(Context context) {
        AppContext.context = context;
    }
}

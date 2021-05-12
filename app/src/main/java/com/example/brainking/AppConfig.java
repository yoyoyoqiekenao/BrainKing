package com.example.brainking;

import android.content.Context;

import com.example.brainking.net.RxRetrofit;
import com.example.brainking.net.UrlConstainer;
import com.example.brainking.util.PreUtils;

/**
 * @author : 徐无敌
 * date   : 2021/5/1217:16
 * desc   :
 */
public class AppConfig {

    public static void init(Context context){
        //初始化网络框架
        RxRetrofit.getInstance().initRxRetrofit(context, UrlConstainer.baseUrl);
        //初始化缓存
        PreUtils.init(context);
    }

}

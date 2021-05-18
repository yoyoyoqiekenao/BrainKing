package com.example.brainking.net;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : 徐无敌
 * date   : 2021/5/1216:42
 * desc   :
 */
public class RetrofitHelperUtils {

    public static final int CONNECTTIME = 10;
    public static final int READTIME = 10;
    private static RetrofitHelperUtils instance = null;
    private Retrofit mRetrofit = null;

    /**
     * 考虑线程的安全问题的单例
     */
    public static RetrofitHelperUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelperUtils.class) {
                instance = new RetrofitHelperUtils();
            }
        }
        return instance;
    }

    private RetrofitHelperUtils() {

        init();
    }

    private void init() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(final Class<T> service) {
        if (instance == null) {
            throw new NullPointerException("未初始化RetrofitHelper");
        }
        return instance.mRetrofit.create(service);
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient
                .addInterceptor(new SessionInterceptor());

        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);
        // 连接超时时间设置
        okHttpClient.connectTimeout(CONNECTTIME, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(READTIME, TimeUnit.SECONDS);
        return okHttpClient.build();
    }


}

package com.example.brainking.http;

 import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author : 徐无敌
 * date   : 2021/5/616:27
 * desc   :
 */
public class HttpDataSourceImpl implements HttpDataSource {

    private ApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;


    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public static HttpDataSourceImpl getInstance(ApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Observable<Object> login() {
        return  Observable.just(new Object()).delay(3, TimeUnit.SECONDS); //延迟3秒
    }
}

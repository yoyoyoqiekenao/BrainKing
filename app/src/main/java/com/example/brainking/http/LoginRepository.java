package com.example.brainking.http;

import io.reactivex.Observable;

import androidx.annotation.VisibleForTesting;

import me.goldze.mvvmhabit.base.BaseModel;

/**
 * @author : 徐无敌
 * date   : 2021/5/615:44
 * desc   : MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
public class LoginRepository extends BaseModel implements HttpDataSource {

    private volatile static LoginRepository INSTANCE = null;
    private final HttpDataSource mHttpDataSource;

    public LoginRepository(HttpDataSource mHttpDataSource) {
        this.mHttpDataSource = mHttpDataSource;
    }

    public static LoginRepository getInstance(HttpDataSource httpDataSource) {
        if (INSTANCE == null) {
            synchronized (LoginRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginRepository(httpDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public Observable<Object> login() {
        return mHttpDataSource.login();
    }
}

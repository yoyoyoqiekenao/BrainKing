package com.example.brainking.net;

import com.wuxiaolong.androidutils.library.LogUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @author : 徐无敌
 * date   : 2021/6/1517:06
 * desc   :
 */
public abstract class ApiCallback<M> extends DisposableObserver<M> {
    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onError(@NonNull Throwable e) {

        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            LogUtil.d("code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();

    }

    @Override
    public void onNext(M model) {
        onSuccess(model);

    }

    @Override
    public void onComplete() {
        onFinish();
    }
}

package com.example.brainking.base;

import com.example.brainking.net.ApiClient;
import com.example.brainking.net.ApiStores;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : 徐无敌
 * date   : 2021/6/1010:24
 * desc   :
 */
public class BasePresenter<V> {
    public V baseView;
    protected ApiStores apiStores;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView) {
        this.baseView = mvpView;
        apiStores = ApiClient.retrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.baseView = null;
        onUnSubscribe();
    }

    //RxJava取消注册，以避免内存泄露
    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observer);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}

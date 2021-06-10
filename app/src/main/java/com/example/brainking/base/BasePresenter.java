package com.example.brainking.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author : 徐无敌
 * date   : 2021/6/1010:24
 * desc   :
 */
public class BasePresenter <V extends BaseView>{
    CompositeSubscription mCompositeSubscription;
    public  V                   baseView;
    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        onUnsubscribe();
    }

    /**
     * 返回 view
     */
    public V getBaseView() {
        return baseView;
    }

    //RXjava注册
    public void addSubscription(Subscription subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public CompositeSubscription getCompositeSubscription(){
        return mCompositeSubscription;
    }
}

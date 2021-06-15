package com.example.brainking.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * @author : 徐无敌
 * date   : 2021/5/1015:55
 * desc   :
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(DisposableObserver observer) {
//        if (mCompositeDisposable == null) {
        mCompositeDisposable = new CompositeDisposable();
//        }
        mCompositeDisposable.add(observer);
    }
}

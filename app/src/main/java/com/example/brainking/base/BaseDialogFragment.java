package com.example.brainking.base;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class BaseDialogFragment extends DialogFragment {
    public Activity mActivity;
    private CompositeDisposable mCompositeDisposable;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
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

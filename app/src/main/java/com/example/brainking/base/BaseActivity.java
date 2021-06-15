package com.example.brainking.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;


import com.example.brainking.net.ApiClient;
import com.example.brainking.net.ApiStores;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;


/**
 * @author : 徐无敌
 * date   : 2021/5/1015:39
 * desc   :
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {


    public Activity mActivity;
    private CompositeDisposable mCompositeDisposable;
    private List<Call> calls;

    public ProgressDialog progressDialog;





    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mActivity = this;
    }

    @Override
    protected void onDestroy() {
        callCancel();
        onUnsubscribe();
        super.onDestroy();
    }

    public ApiStores apiStores() {
        return ApiClient.retrofit().create(ApiStores.class);
    }


    public void addCalls(Call call) {
        if (calls == null) {
            calls = new ArrayList<>();
        }
        calls.add(call);
    }

    private void callCancel() {
        if (calls != null && calls.size() > 0) {
            for (Call call : calls) {
                if (!call.isCanceled())
                    call.cancel();
            }
            calls.clear();
        }
    }

    public <T> void addSubscription(Observable<T> observable, DisposableObserver<T> observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void addSubscription(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void onUnsubscribe() {

        //取消注册，以避免内存泄露
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }

    }


    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
        }
    }

}

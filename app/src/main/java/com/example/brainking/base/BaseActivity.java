package com.example.brainking.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;


import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.brainking.net.ResponseBean;
import com.example.brainking.util.RequestLoadingUtils;
import com.gyf.immersionbar.ImmersionBar;




import butterknife.ButterKnife;



/**
 * @author : 徐无敌
 * date   : 2021/5/1015:39
 * desc   :
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {


    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        ButterKnife.bind(this);
        presenter = createPresenter();
        initView();
        initData();
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        RequestLoadingUtils.showProgressDialog(this, "加载中...");

    }

    @Override
    public void hideLoading() {
        RequestLoadingUtils.dismissProgressDialog();
    }
    @Override
    public void onErrorCode(ResponseBean bean) {
        ToastUtils.showLong("错误码：" + bean.getCode(), "错误信息：" + bean.getMessage(), "是否成功：" + bean.isSuccess());
    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 375);

    }

    /**
     * 保持不息屏
     */
    protected void keepScreenOn() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


}

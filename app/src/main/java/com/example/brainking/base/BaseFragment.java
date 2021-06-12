package com.example.brainking.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.brainking.AppContext;
import com.example.brainking.R;

import com.example.brainking.net.ResponseBean;
import com.example.brainking.util.RequestLoadingUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : 徐无敌
 * date   : 2021/5/1015:55
 * desc   :
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder unbinder;
    protected Context mContext;

    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        //得到context,在后面的子类Fragment中都可以直接调用
        mContext = AppContext.getContext();
        presenter = createPresenter();
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //do something
        unbinder.unbind();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initListener() {

    }

    @Override
    public void onErrorCode(ResponseBean bean) {
    }

    /**
     * 显示加载中
     */
    @Override
    public void showLoading() {
        RequestLoadingUtils.showProgressDialog(ActivityUtils.getActivityByContext(getContext()), "加载中...");

    }

    /**
     * 隐藏加载中
     */
    @Override
    public void hideLoading() {
        RequestLoadingUtils.dismissProgressDialog();

    }

    /**
     * 把状态栏设成透明
     */
    protected void setStatusBarTransparent() {
        /**
         * 全透状态栏
         */

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /**
     * 保持不息屏
     */
    protected void keepScreenOn() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

package com.example.brainking.base;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : 徐无敌
 * date   : 2021/6/1516:27
 * desc   :
 */
public abstract class BrainActivity<P extends BasePresenter> extends BaseActivity {
    protected P basePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = createPresenter();
        ImmersionBar.with(this).init();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.detachView();
        }
    }


    public void showLoading() {
        showProgressDialog();
    }


    public void hideLoading() {
        dismissProgressDialog();
    }
}

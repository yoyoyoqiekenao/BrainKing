package com.example.brainking.base;

import android.os.Bundle;
import android.view.View;

/**
 * @author : 徐无敌
 * date   : 2021/6/1516:40
 * desc   :
 */
public abstract class BrainFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}

package com.example.brainking.match.match;

import android.view.View;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends BaseFragment {
    @BindView(R.id.view)
    View mView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_match;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, getView());
        ImmersionBar.with(this).statusBarView(mView).init();
    }

    @Override
    protected void initData() {

    }
}

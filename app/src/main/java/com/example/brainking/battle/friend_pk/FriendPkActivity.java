package com.example.brainking.battle.friend_pk;

import android.view.View;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendPkActivity extends BaseActivity {
    @BindView(R.id.view)
    View mView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friendpk;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();
    }

    @Override
    protected void initData() {

    }
}

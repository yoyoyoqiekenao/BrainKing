package com.example.brainking.battle.friend_pk;

import android.view.View;
import android.widget.ImageView;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendPkActivity extends BaseActivity {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_create)
    ImageView iv_create;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friendpk;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        setOnClickListener(R.id.iv_create);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.iv_create){

        }
    }
}

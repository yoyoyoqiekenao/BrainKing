package com.example.brainking.battle.friend_pk;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendPkActivity extends BrainActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_create)
    ImageView iv_create;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendpk);
        initView();
    }

     protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        iv_create.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.iv_create){

        }
    }
}

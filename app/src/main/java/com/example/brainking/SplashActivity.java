package com.example.brainking;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : 徐无敌
 * date   : 2021/5/1210:53
 * desc   :
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_phone)
    RelativeLayout rl_phone;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        rl_phone.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_phone) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}

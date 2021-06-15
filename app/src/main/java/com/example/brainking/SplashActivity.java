package com.example.brainking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * @author : 徐无敌
 * date   : 2021/5/1210:53
 * desc   :
 */
public class SplashActivity extends BrainActivity implements View.OnClickListener {
    @Nullable
    @BindView(R.id.rl_phone)
    RelativeLayout rl_phone;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        rl_phone.setOnClickListener(this);
    }



    @Optional
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_phone) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}

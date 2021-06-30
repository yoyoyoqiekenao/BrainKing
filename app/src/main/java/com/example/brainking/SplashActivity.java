package com.example.brainking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.login.LoginActivity;
import com.example.brainking.util.SpUtils;

import java.security.PrivateKey;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import kotlin.contracts.ReturnsNotNull;

/**
 * @author : 徐无敌
 * date   : 2021/5/1210:53
 * desc   :
 */
public class SplashActivity extends BrainActivity implements View.OnClickListener {
    @Nullable
    @BindView(R.id.rl_phone)
    RelativeLayout rl_phone;
    @BindView(R.id.rl_agreement)
    RelativeLayout rl_agreement;
    @BindView(R.id.rl_wx)
    RelativeLayout rl_wx;

    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();

        }
    };


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
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            rl_phone.setVisibility(View.GONE);
            rl_agreement.setVisibility(View.GONE);
            rl_wx.setVisibility(View.GONE);
            mHandler.postDelayed(runnable, 3000);
        } else {
            rl_phone.setVisibility(View.VISIBLE);
            rl_agreement.setVisibility(View.VISIBLE);
            rl_wx.setVisibility(View.VISIBLE);
        }
    }


    @Optional
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_phone) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}

package com.example.brainking.home.userinfo;

import android.os.Bundle;

import com.example.brainking.base.BrainActivity;

public class UserInfoActivity extends BrainActivity<UserInfoPresenter> implements UserInfoView {
    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
    }
}

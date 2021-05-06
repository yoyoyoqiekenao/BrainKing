package com.example.brainking.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.brainking.BR;
import com.example.brainking.R;
import com.example.brainking.databinding.ActivitySplashBinding;
import com.example.brainking.http.LoginModelFactory;
import com.gyf.immersionbar.ImmersionBar;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author : 徐无敌
 * date   : 2021/5/611:14
 * desc   :
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public SplashViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，SplashViewModel(@NonNull Application application)构造方法
        LoginModelFactory factory = LoginModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SplashViewModel.class);
    }
}

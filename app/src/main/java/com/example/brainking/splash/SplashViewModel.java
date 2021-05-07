package com.example.brainking.splash;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.example.brainking.http.LoginRepository;
import com.example.brainking.login.LoginActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;

/**
 * @author : 徐无敌
 * date   : 2021/5/615:32
 * desc   :
 */
public class SplashViewModel extends BaseViewModel<LoginRepository> {


    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    //手机登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            jump();
        }


    });

    private void jump() {
        startActivity(LoginActivity.class);
    }


}

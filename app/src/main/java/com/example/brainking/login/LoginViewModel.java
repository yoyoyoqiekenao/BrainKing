package com.example.brainking.login;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.brainking.http.LoginRepository;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;

/**
 * @author : 徐无敌
 * date   : 2021/5/716:22
 * desc   :
 */
public class LoginViewModel extends BaseViewModel<LoginRepository> {

    /**
     * 用户名的绑定
     */
    public ObservableField<String> userName = new ObservableField<>("");
    /**
     * 密码的绑定
     */
    public ObservableField<String> password = new ObservableField<>("");
    /**
     * 用户名清除按钮的显示隐藏绑定
     */
    public ObservableInt clearBtnVisibility = new ObservableInt();

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //清除手机号, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });

    //返回上一页
    public BindingCommand finishOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
    //获取验证码
    public BindingCommand getVerificationCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.d("xuwudi", "获取验证码");
        }
    });

    //登陆
    public BindingCommand login = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.d("xuwudi", "登陆成功");
        }
    });
}

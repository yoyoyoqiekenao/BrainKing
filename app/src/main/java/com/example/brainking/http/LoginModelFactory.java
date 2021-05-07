package com.example.brainking.http;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.brainking.login.LoginViewModel;
import com.example.brainking.splash.SplashViewModel;

/**
 * @author : 徐无敌
 * date   : 2021/5/616:49
 * desc   :
 */
public class LoginModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile LoginModelFactory INSTANCE;
    private final Application mApplication;
    private final LoginRepository mRepository;

    public static LoginModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (LoginModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginModelFactory(application, Injection.getLoginRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LoginModelFactory(Application application, LoginRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

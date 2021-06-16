package com.example.brainking.login;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.LoginJson;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.VerCodeModel;
import com.example.brainking.net.ApiCallback;
import com.google.gson.internal.bind.JsonTreeReader;

/**
 * @author : 徐无敌
 * date   : 2021/6/1516:59
 * desc   :
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        attachView(view);
    }

    public void getVerCode(String mobile) {
        VerCodeJson json = new VerCodeJson();
        json.setMobile(mobile);
        baseView.showLoading();
        addSubscription(apiStores.getVerCode(json), new ApiCallback<VerCodeModel>() {
            @Override
            public void onSuccess(VerCodeModel model) {
                if (baseView != null) {
                    if ("200".equals(model.getCode())) {
                        baseView.getVerCodeSuccess(model);
                    } else {
                        baseView.getVerCodeFail(model.getMsg());
                    }

                }
            }

            @Override
            public void onFailure(String msg) {
                if (baseView != null) {
                    baseView.getVerCodeFail(msg);
                }
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }

    public void goLogin(String mobile, String verifyCode, String uuid) {
        LoginJson json = new LoginJson();
        json.setVerifyCode(verifyCode);
        json.setMobile(mobile);
        json.setUuid(uuid);

        baseView.showLoading();
        addSubscription(apiStores.goLogin(json), new ApiCallback<LoginModel>() {
            @Override
            public void onSuccess(LoginModel model) {
                if (baseView != null) {
                    if ("200".equals(model.getCode())) {
                        baseView.goLoginSuccess(model);
                    } else {
                        baseView.goLoginFail(model.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                if (baseView != null) {
                    baseView.goLoginFail(msg);
                }
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

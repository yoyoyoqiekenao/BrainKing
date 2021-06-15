package com.example.brainking.login;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.VerCodeModel;
import com.example.brainking.net.ApiCallback;

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
        VerCodeJson json=new VerCodeJson();
        json.setMobile(mobile);
        baseView.showLoading();
        addSubscription(apiStores.getVerCode(json), new ApiCallback<VerCodeModel>() {
            @Override
            public void onSuccess(VerCodeModel model) {
                if (baseView != null) {
                    baseView.getVerCodeSuccess(model);
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
}

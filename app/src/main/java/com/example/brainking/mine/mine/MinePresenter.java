package com.example.brainking.mine.mine;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.LoginOutModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.net.ApiCallback;

public class MinePresenter extends BasePresenter<MineView> {
    public MinePresenter(MineView view) {
        attachView(view);
    }

    public void loginOut() {
        baseView.showLoading();
        addSubscription(apiStores.logOut(), new ApiCallback<LoginOutModel>() {


            @Override
            public void onSuccess(LoginOutModel model) {
                if (model.getCode() == 200) {
                    baseView.loginOutSuccess(model);
                } else {
                    baseView.loginOutFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.loginOutFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }

    public void getUserInfo() {
        baseView.showLoading();
        addSubscription(apiStores.getUserInfo(), new ApiCallback<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel model) {
                if (200 == model.getCode()) {
                    baseView.getUserInfoSuccess(model);
                } else if (401 == model.getCode()) {
                    //baseView.goReLogin();
                } else {
                    baseView.getUserInfoFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getUserInfoFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

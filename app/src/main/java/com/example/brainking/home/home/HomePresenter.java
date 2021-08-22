package com.example.brainking.home.home;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.net.ApiCallback;

/**
 * @author : 徐无敌
 * date   : 2021/6/1615:40
 * desc   :
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView view) {
        attachView(view);
    }

    public void getUserInfo() {
        baseView.showLoading();
        addSubscription(apiStores.getUserInfo(), new ApiCallback<UserInfoModel>() {
            @Override
            public void onSuccess(UserInfoModel model) {
                if (200 == model.getCode()) {
                    baseView.getUserInfoSuccess(model);
                } else if (401 == model.getCode()) {
                    baseView.goReLogin();
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

    public void getLearnList_math() {
        baseView.showLoading();
        addSubscription(apiStores.getLearnList_math(), new ApiCallback<LearnListModel>() {
            @Override
            public void onSuccess(LearnListModel model) {
                if (baseView != null) {
                    if ("200".equals(model.getCode())) {
                        baseView.getLearnListSuccess(model);
                    } else {
                        baseView.getLearnListFail(model.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                if (baseView != null) {
                    baseView.getLearnListFail(msg);
                }
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });


    }


}

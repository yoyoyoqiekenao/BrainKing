package com.example.brainking.home.poems;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.net.ApiCallback;

public class PoemsPresenter extends BasePresenter<PoemsView> {
    public PoemsPresenter(PoemsView view) {
        attachView(view);
    }

    public void getLearnList_poems() {
        baseView.showLoading();
        addSubscription(apiStores.getLearnList_poems(), new ApiCallback<LearnListModel>() {
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

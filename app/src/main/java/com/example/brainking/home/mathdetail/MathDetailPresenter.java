package com.example.brainking.home.mathdetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MathDetailModel;
import com.example.brainking.net.ApiCallback;

public class MathDetailPresenter extends BasePresenter<MathDetailView> {
    public MathDetailPresenter(MathDetailView view) {
        attachView(view);
    }

    void getMathDetail() {
        baseView.showLoading();
        addSubscription(apiStores.getMathDetail(), new ApiCallback<MathDetailModel>() {
            @Override
            public void onSuccess(MathDetailModel model) {
                if (200 == model.getCode()) {
                    baseView.getMathDetailSuccess(model);
                } else {
                    baseView.getMathDetailFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getMathDetailFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

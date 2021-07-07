package com.example.brainking.home.poemsdetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.CollectJson;
import com.example.brainking.model.CollectModel;
import com.example.brainking.model.PoemsDetailModel;
import com.example.brainking.net.ApiCallback;

public class PoemsDetailPresenter extends BasePresenter<PoemsDetailView> {
    public PoemsDetailPresenter(PoemsDetailView view) {
        attachView(view);
    }

    void getPoemsDetail(int pid) {
        baseView.showLoading();
        addSubscription(apiStores.getPoemsDetail(pid), new ApiCallback<PoemsDetailModel>() {
            @Override
            public void onSuccess(PoemsDetailModel model) {
                if (200 == model.getCode()) {
                    baseView.getPoemsDetailSuccess(model);
                } else {
                    baseView.getPoemsDetailFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getPoemsDetailFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }

    void collectPoem(int id) {
        baseView.showLoading();
        CollectJson json = new CollectJson();
        json.setId(id);
        addSubscription(apiStores.collectPoem(json), new ApiCallback<CollectModel>() {
            @Override
            public void onSuccess(CollectModel model) {
                if (200 == model.getCode()) {
                    baseView.collectSuccess();
                } else {
                    baseView.collectFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.collectFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

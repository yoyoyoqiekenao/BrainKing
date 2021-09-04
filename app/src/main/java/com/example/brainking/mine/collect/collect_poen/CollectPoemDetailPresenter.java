package com.example.brainking.mine.collect.collect_poen;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.CollectDetailModel;
import com.example.brainking.net.ApiCallback;

public class CollectPoemDetailPresenter extends BasePresenter<CollectPoemDetailView> {
    public CollectPoemDetailPresenter(CollectPoemDetailView view) {
        attachView(view);
    }

    public void getCollectDetail(String subjectId, String type) {
        baseView.showLoading();
        addSubscription(apiStores.getCollectDetail(subjectId, type), new ApiCallback<CollectDetailModel>() {

            @Override
            public void onSuccess(CollectDetailModel model) {

                if (model.code == 200) {
                    baseView.getDetailSuccess(model);
                } else {
                    baseView.fail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.fail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

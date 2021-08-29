package com.example.brainking.mine.collect;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.CollectListModel;
import com.example.brainking.net.ApiCallback;

public class CollectPresenter extends BasePresenter<CollectView> {
    public CollectPresenter(CollectView view) {
        attachView(view);
    }

    public void getCollectList() {
        addSubscription(apiStores.getCollectList("1", "50"), new ApiCallback<CollectListModel>() {


            @Override
            public void onSuccess(CollectListModel model) {
                if (200 == model.getCode()) {
                    baseView.getCollectSuccess(model);
                } else {
                    baseView.getCollectFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getCollectFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}

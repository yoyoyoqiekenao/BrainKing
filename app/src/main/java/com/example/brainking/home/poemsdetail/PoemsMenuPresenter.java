package com.example.brainking.home.poemsdetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.PoemListModel;
import com.example.brainking.net.ApiCallback;

public class PoemsMenuPresenter extends BasePresenter<PoemsMenuView> {
    public PoemsMenuPresenter(PoemsMenuView view) {
        attachView(view);
    }

    void getPoemsList(int pid, int page) {

        addSubscription(apiStores.getPoemList(page, 15, pid), new ApiCallback<PoemListModel>() {
            @Override
            public void onSuccess(PoemListModel model) {
                if (200 == model.getCode()) {
                    baseView.getPoemListSuccess(model);
                } else {
                    baseView.getPoemListFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getPoemListFail(msg);
            }

            @Override
            public void onFinish() {
                //baseView.hideLoading();
            }
        });
    }
}

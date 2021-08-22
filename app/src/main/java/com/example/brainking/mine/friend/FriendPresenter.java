package com.example.brainking.mine.friend;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.net.ApiCallback;

public class FriendPresenter extends BasePresenter<FriendView> {
    public FriendPresenter(FriendView view) {
        attachView(view);
    }

    public void getFriendList() {
        baseView.showLoading();
        addSubscription(apiStores.getFriendList("1", "100"), new ApiCallback<FriendListModel>() {
            @Override
            public void onSuccess(FriendListModel model) {
                if (200 == model.getCode()) {
                    baseView.getFriendListSuccess(model);
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

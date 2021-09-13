package com.example.brainking.mine.friend;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.DeleteFriendJson;
import com.example.brainking.model.DeleteFriendModel;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.net.ApiCallback;

public class FriendPresenter extends BasePresenter<FriendView> {
    public FriendPresenter(FriendView view) {
        attachView(view);
    }

    public void deleteFriend(String id) {
        DeleteFriendJson json = new DeleteFriendJson();
        json.setFriendId(id);

        addSubscription(apiStores.deleteFriend(json), new ApiCallback<DeleteFriendModel>() {
            @Override
            public void onSuccess(DeleteFriendModel model) {
                if (model.getCode() == 200) {
                    baseView.deleteSuccess(model);
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

            }
        });
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

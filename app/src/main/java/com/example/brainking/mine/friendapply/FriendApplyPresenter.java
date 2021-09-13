package com.example.brainking.mine.friendapply;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.Friend_Pass_refuse_Json;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.model.Friend_Pass_refuse_Model;
import com.example.brainking.net.ApiCallback;

public class FriendApplyPresenter extends BasePresenter<FriendApplyView> {
    public FriendApplyPresenter(FriendApplyView view) {
        attachView(view);
    }


    public void PassRefuse(String friendId) {

        Friend_Pass_refuse_Json json = new Friend_Pass_refuse_Json();
        json.setFriendId(friendId);
        addSubscription(apiStores.passRefuse(json), new ApiCallback<Friend_Pass_refuse_Model>() {


            @Override
            public void onSuccess(Friend_Pass_refuse_Model model) {
                if (200 == model.getCode()) {
                    baseView.passRefuse(model);
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

    public void PassApply(String friendId) {

        Friend_Pass_refuse_Json json = new Friend_Pass_refuse_Json();
        json.setFriendId(friendId);
        addSubscription(apiStores.passAdd(json), new ApiCallback<Friend_Pass_refuse_Model>() {


            @Override
            public void onSuccess(Friend_Pass_refuse_Model model) {
                if (200 == model.getCode()) {
                    baseView.passAdd(model);
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

    public void getFriendApplyList() {
        baseView.showLoading();
        addSubscription(apiStores.getFriendApplyList("1", "100"), new ApiCallback<FriendListModel>() {
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

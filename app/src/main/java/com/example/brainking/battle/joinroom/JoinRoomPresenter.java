package com.example.brainking.battle.joinroom;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

public class JoinRoomPresenter extends BasePresenter<JoinRoomView> {
    public JoinRoomPresenter(JoinRoomView view) {
        attachView(view);
    }

    public void matchExit(String roomId) {
        addSubscription(apiStores.matchExit(roomId), new ApiCallback<MatchStartModel>() {
            @Override
            public void onSuccess(MatchStartModel model) {
                if (200 == model.getCode()) {
                    baseView.matchExitSuccess(model);
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
}

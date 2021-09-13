package com.example.brainking.battle.battle;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.BattleListModel;
import com.example.brainking.model.JoinRoomModel;
import com.example.brainking.net.ApiCallback;
import com.example.brainking.net.ApiStores;

public class BattlePresenter extends BasePresenter<BattleView> {
    public BattlePresenter(BattleView view) {
        attachView(view);
    }

    public void joinRoom(String roomId) {
        addSubscription(apiStores.joinRoom(roomId), new ApiCallback<JoinRoomModel>() {

            @Override
            public void onSuccess(JoinRoomModel model) {
                if (200 == model.getCode()) {
                    baseView.joinRoomSuccess(model);
                } else {
                    baseView.Failed(model.msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.Failed(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getBattleList() {
        addSubscription(apiStores.getBattleList(), new ApiCallback<BattleListModel>() {
            @Override
            public void onSuccess(BattleListModel model) {
                if (200 == model.getCode()) {
                    baseView.getBattleListSuccess(model);
                } else {
                    baseView.Failed(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.Failed(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}

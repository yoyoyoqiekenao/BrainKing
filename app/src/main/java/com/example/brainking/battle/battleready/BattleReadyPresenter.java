package com.example.brainking.battle.battleready;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.CancelRoomModel;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

public class BattleReadyPresenter extends BasePresenter<BattleReadyView> {
    public BattleReadyPresenter(BattleReadyView view) {
        attachView(view);
    }


    public void cancelRoom(String roomId) {
        addSubscription(apiStores.cancelRoom(roomId), new ApiCallback<CancelRoomModel>() {
            @Override
            public void onSuccess(CancelRoomModel model) {
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

    public void multiReady(String roomId) {
        baseView.showLoading();
        addSubscription(apiStores.multiReady(roomId), new ApiCallback<CreateRoomModel>() {
            @Override
            public void onSuccess(CreateRoomModel model) {
                if (200 == model.getCode()) {

                    baseView.multiReadySuccess(model);
                } else {
                    baseView.multiReadyFail(model.getMsg());
                }
                baseView.hideLoading();
            }

            @Override
            public void onFailure(String msg) {
                baseView.multiReadyFail(msg);
                baseView.hideLoading();
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

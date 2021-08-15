package com.example.brainking.battle.battleready;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.net.ApiCallback;

public class BattleReadyPresenter extends BasePresenter<BattleReadyView> {
    public BattleReadyPresenter(BattleReadyView view) {
        attachView(view);
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

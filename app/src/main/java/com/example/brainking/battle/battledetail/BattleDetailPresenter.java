package com.example.brainking.battle.battledetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.net.ApiCallback;

public class BattleDetailPresenter extends BasePresenter<BattleDetailView> {
    public BattleDetailPresenter(BattleDetailView view) {
        attachView(view);
    }


    public void multiReady(String roomId) {
        addSubscription(apiStores.multiReady(roomId), new ApiCallback<CreateRoomModel>() {
            @Override
            public void onSuccess(CreateRoomModel model) {
                if (200 == model.getCode()) {
                    baseView.multiReadySuccess();
                } else {
                    baseView.multiReadyFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.multiReadyFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}

package com.example.brainking.battle.createroom;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

public class CreateRoomPresenter extends BasePresenter<CreateRoomView> {
    public CreateRoomPresenter(CreateRoomView view) {
        attachView(view);
    }

    public void createRoom() {
        addSubscription(apiStores.matchStart(), new ApiCallback<MatchStartModel>() {


            @Override
            public void onSuccess(MatchStartModel model) {
                if (200 == model.getCode()) {
                    baseView.matchStartSuccess(model);
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

package com.example.brainking.battle.createroom;

import android.util.Log;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.CreateBattleRoomJson;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

public class CreateRoomPresenter extends BasePresenter<CreateRoomView> {
    public CreateRoomPresenter(CreateRoomView view) {
        attachView(view);
    }

    public void createBattleRoom(String defficultLevel, String limitNum, String roomName) {

        CreateBattleRoomJson json = new CreateBattleRoomJson();
        json.setDefficultLevel(defficultLevel);
        json.setLimitNum(limitNum);
        json.setRoomName(roomName);

        addSubscription(apiStores.createBattleRoom(json), new ApiCallback<CreateRoomModel>() {


            @Override
            public void onSuccess(CreateRoomModel model) {
                if (200 == model.getCode()) {
                    baseView.matchStartSuccess(model);
                } else if (602 == model.getCode()) {
                    baseView.reConnect();
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

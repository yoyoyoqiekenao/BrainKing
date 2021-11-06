package com.example.brainking.battle.battledetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.MatchAnswerJson;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.ReConnectModel;
import com.example.brainking.net.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class BattleDetailPresenter extends BasePresenter<BattleDetailView> {
    public BattleDetailPresenter(BattleDetailView view) {
        attachView(view);
    }

    public void reConnect() {
        addSubscription(apiStores.reConnect(), new ApiCallback<ReConnectModel>() {
            @Override
            public void onSuccess(ReConnectModel model) {

                if (200 == model.getCode()) {
                    baseView.reConnectSuccess(model);
                }
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void matchAnswer(String id, String roomId) {

        MatchAnswerJson json = new MatchAnswerJson();
        List<String> list = new ArrayList<>();
        list.add(id);
        json.setRoomId(roomId);
        json.setAnswers(list);
        addSubscription(apiStores.matchAnswer(json), new ApiCallback<MatchAnswerModel>() {


            @Override
            public void onSuccess(MatchAnswerModel model) {
                if (200 == model.getCode()) {
                    baseView.matchAnswerSuccess(model);
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

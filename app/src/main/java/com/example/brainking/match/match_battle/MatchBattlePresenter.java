package com.example.brainking.match.match_battle;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.MatchAnswerJson;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class MatchBattlePresenter extends BasePresenter<MatchBattleView> {

    public MatchBattlePresenter(MatchBattleView view) {
        attachView(view);
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

    public void ready(String roomId) {
        baseView.showLoading();
        addSubscription(apiStores.matchReady(roomId), new ApiCallback<MatchStartModel>() {
            @Override
            public void onSuccess(MatchStartModel model) {
                baseView.hideLoading();
                if (200 == model.getCode()) {
                    baseView.mattchReadySuccess(model);
                } else {
                    baseView.fail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.hideLoading();
                baseView.fail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}

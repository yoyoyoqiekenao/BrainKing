package com.example.brainking.match.match_battle;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.net.ApiCallback;

public class MatchBattlePresenter extends BasePresenter<MatchBattleView> {

    public MatchBattlePresenter(MatchBattleView view) {
        attachView(view);
    }

    public void ready(String roomId) {
        addSubscription(apiStores.matchReady(roomId), new ApiCallback<MatchStartModel>() {
            @Override
            public void onSuccess(MatchStartModel model) {
                if (200 == model.getCode()) {
                    baseView.mattchReadySuccess(model);
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

package com.example.brainking.match.match_detail;

import android.util.Log;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.model.ReConnectModel;
import com.example.brainking.net.ApiCallback;

public class MatchDetailPresenter extends BasePresenter<MatchDetailView> {
    public MatchDetailPresenter(MatchDetailView view) {
        attachView(view);
    }

    public void createRoom(String battleType) {
        addSubscription(apiStores.matchStart(battleType), new ApiCallback<MatchStartModel>() {


            @Override
            public void onSuccess(MatchStartModel model) {
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

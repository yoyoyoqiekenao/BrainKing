package com.example.brainking.match.match;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchScoreModel;
import com.example.brainking.net.ApiCallback;

public class MatchPresenter extends BasePresenter<MatchView> {
    public MatchPresenter(MatchView view) {
        attachView(view);
    }

    public void getBattleFee() {
        addSubscription(apiStores.getMatchScore(), new ApiCallback<MatchScoreModel>() {
            @Override
            public void onSuccess(MatchScoreModel model) {
                if (200 == model.getCode()) {
                    baseView.getScoreSuccess(model);
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

package com.example.brainking.match.match;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchScoreModel;

public interface MatchView extends BaseView {

    void getScoreSuccess(MatchScoreModel model);

    void fail(String err);
}

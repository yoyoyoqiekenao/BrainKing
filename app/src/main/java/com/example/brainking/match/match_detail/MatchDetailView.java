package com.example.brainking.match.match_detail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchStartModel;

public interface MatchDetailView extends BaseView {
    void matchStartSuccess(MatchStartModel matchStartModel);

    void fail(String msg);

}

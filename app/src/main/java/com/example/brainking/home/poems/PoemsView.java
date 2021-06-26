package com.example.brainking.home.poems;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.LearnListModel;

public interface PoemsView extends BaseView {

    void getLearnListSuccess(LearnListModel model);

    void getLearnListFail(String msg);
}

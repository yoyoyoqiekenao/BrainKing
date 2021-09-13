package com.example.brainking.home.poemsdetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.PoemListModel;

public interface PoemsMenuView extends BaseView {
    void getPoemListSuccess(PoemListModel model);

    void getPoemListFail(String err);
}

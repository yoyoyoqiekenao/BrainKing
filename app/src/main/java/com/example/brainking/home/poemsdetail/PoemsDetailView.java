package com.example.brainking.home.poemsdetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.PoemsDetailModel;

public interface PoemsDetailView extends BaseView {
    void getPoemsDetailSuccess(PoemsDetailModel model);

    void getPoemsDetailFail(String err);

    void collectSuccess();

    void collectFail(String err);
}

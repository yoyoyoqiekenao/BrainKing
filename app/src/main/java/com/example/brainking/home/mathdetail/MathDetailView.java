package com.example.brainking.home.mathdetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MathDetailModel;

public interface MathDetailView extends BaseView {

    void getMathDetailSuccess(MathDetailModel mathDetailModel);

    void getMathDetailFail(String msg);

    void collectSuccess();

    void collectFail(String err);

}

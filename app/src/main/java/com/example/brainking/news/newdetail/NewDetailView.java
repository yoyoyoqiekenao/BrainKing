package com.example.brainking.news.newdetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MessageReadModel;
import com.example.brainking.model.NewDetailModel;
import com.example.brainking.model.SendMsgModel;

public interface NewDetailView extends BaseView {

    void getNewDetailSuccess(NewDetailModel model);

    void getNewDetailFail(String err);

    void sendMsgSuccess(SendMsgModel msgModel);

    void sendMsgFail(String err);

    void messageReadSuccess(MessageReadModel model);

    void messageReadFail(String msg);

}

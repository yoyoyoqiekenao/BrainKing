package com.example.brainking.news.newdetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.MessageReadJson;
import com.example.brainking.json.SendMsgJson;
import com.example.brainking.model.MessageReadModel;
import com.example.brainking.model.NewDetailModel;
import com.example.brainking.model.SendMsgModel;
import com.example.brainking.net.ApiCallback;

public class NewDetailPresenter extends BasePresenter<NewDetailView> {
    public NewDetailPresenter(NewDetailView view) {
        attachView(view);
    }

    public void MessageRead(String userId) {
        MessageReadJson json = new MessageReadJson();
        json.setUserId(userId);

        addSubscription(apiStores.messageRead(json), new ApiCallback<MessageReadModel>() {
            @Override
            public void onSuccess(MessageReadModel model) {
                if (model.getCode() == 200) {
                    baseView.messageReadSuccess(model);
                } else {
                    baseView.messageReadFail(model.msg);
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.messageReadFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getNewDetail(int pageNum, int toId) {
        addSubscription(apiStores.getNewDetail(pageNum, toId), new ApiCallback<NewDetailModel>() {
            @Override
            public void onSuccess(NewDetailModel model) {
                if (200 == model.getCode()) {
                    baseView.getNewDetailSuccess(model);
                } else {
                    baseView.getNewDetailFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getNewDetailFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void sendMsg(String msg, int toId) {
        SendMsgJson json = new SendMsgJson();
        json.setType(1);
        json.setMsg(msg);
        json.setToId(toId);
        addSubscription(apiStores.sendMsg(json), new ApiCallback<SendMsgModel>() {
            @Override
            public void onSuccess(SendMsgModel model) {
                if (200 == model.getCode()) {
                    baseView.sendMsgSuccess(model);
                } else {
                    baseView.sendMsgFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.sendMsgFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}

package com.example.brainking.news.news;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.MessageListModel;
import com.example.brainking.net.ApiCallback;

public class NewsPresenter extends BasePresenter<NewsView> {
    public NewsPresenter(NewsView view) {
        attachView(view);
    }

    public void getMessageList(int pageNum) {
        baseView.showLoading();
        addSubscription(apiStores.getMessageList(pageNum), new ApiCallback<MessageListModel>() {
            @Override
            public void onSuccess(MessageListModel model) {
                if (200 == model.getCode()) {
                    baseView.getMessageListSuccess(model);
                } else {
                    baseView.getMessageListFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getMessageListFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}

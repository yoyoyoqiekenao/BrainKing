package com.example.brainking.news.news;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MessageListModel;

public interface NewsView extends BaseView {
    void getMessageListSuccess(MessageListModel messageListModel);

    void getMessageListFail(String err);
}

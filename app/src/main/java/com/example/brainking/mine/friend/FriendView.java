package com.example.brainking.mine.friend;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.FriendListModel;

public interface FriendView extends BaseView {
    void getFriendListSuccess(FriendListModel model);

    void fail(String msg);
}

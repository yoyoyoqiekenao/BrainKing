package com.example.brainking.mine.friendapply;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.model.Friend_Pass_refuse_Model;

public interface FriendApplyView extends BaseView {
    void getFriendListSuccess(FriendListModel model);

    void fail(String msg);

    void passAdd(Friend_Pass_refuse_Model model);

    void passRefuse(Friend_Pass_refuse_Model model);
}

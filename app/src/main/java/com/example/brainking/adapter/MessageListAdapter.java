package com.example.brainking.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.MessageListModel;

import java.util.List;

public class MessageListAdapter extends BaseQuickAdapter<MessageListModel.DataDTO, BaseViewHolder> {
    public MessageListAdapter() {
        super(R.layout.item_messagelist);
    }


    @Override
    protected void convert(BaseViewHolder helper, MessageListModel.DataDTO item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_msg, item.getMsg());
        ImageView iv = helper.getView(R.id.iv_avatar);
        Glide.with(getContext()).load(item.getAvatar()).error(R.mipmap.iv_head).into(iv);

        if (item.getStatus() == 0) {
            helper.setVisible(R.id.iv_news, false);
        } else {
            helper.setVisible(R.id.iv_news, true);
        }
    }
}

package com.example.brainking.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.FriendListModel;

import org.jetbrains.annotations.NotNull;

public class FriendApplyAdapter extends BaseQuickAdapter<FriendListModel.RowsDTO, BaseViewHolder> {
    public FriendApplyAdapter() {
        super(R.layout.item_friend_apply);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, FriendListModel.RowsDTO rowsDTO) {
        Glide.with(getContext()).load(rowsDTO.getAvatar()).into((ImageView) baseViewHolder.getView(R.id.iv_head));
        baseViewHolder.setText(R.id.tv_name, rowsDTO.getNickName());
        if (TextUtils.isEmpty(rowsDTO.getRemark())) {
            baseViewHolder.setVisible(R.id.tv_remark, false);
        } else {
            baseViewHolder.setVisible(R.id.tv_remark, true);
            baseViewHolder.setText(R.id.tv_remark, rowsDTO.getRemark());
        }
    }
}

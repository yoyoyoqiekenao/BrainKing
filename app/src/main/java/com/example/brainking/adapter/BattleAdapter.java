package com.example.brainking.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.BattleListModel;


public class BattleAdapter extends BaseQuickAdapter<BattleListModel.DataDTO, BaseViewHolder> {
    public BattleAdapter() {
        super(R.layout.item_battle);
    }


    @Override
    protected void convert(BaseViewHolder helper, BattleListModel.DataDTO item) {
        helper.setText(R.id.tv_title, item.getRoomName());
        Glide.with(getContext()).load(item.getAvatar()).error(R.mipmap.iv_head).into((ImageView) helper.getView(R.id.iv_head));
    }
}

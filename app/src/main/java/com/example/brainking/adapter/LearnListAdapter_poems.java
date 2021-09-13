package com.example.brainking.adapter;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.LearnListModel;

import java.util.List;

public class LearnListAdapter_poems extends BaseQuickAdapter<LearnListModel.DataDTO, BaseViewHolder> {
    public LearnListAdapter_poems() {
        super(R.layout.item_learnlost_poems);
    }


    @Override
    protected void convert(BaseViewHolder helper, LearnListModel.DataDTO item) {
        helper.setText(R.id.tv_name, item.getName());


    }
}

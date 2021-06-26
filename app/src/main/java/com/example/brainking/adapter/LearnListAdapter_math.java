package com.example.brainking.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.LearnListModel;



public class LearnListAdapter_math extends BaseQuickAdapter<LearnListModel.DataDTO, BaseViewHolder> {
    public LearnListAdapter_math() {
        super(R.layout.item_learnlist_math);
    }

    @Override
    protected void convert(BaseViewHolder helper, LearnListModel.DataDTO item) {
        helper.setText(R.id.tv_grade, item.getName());
    }
}

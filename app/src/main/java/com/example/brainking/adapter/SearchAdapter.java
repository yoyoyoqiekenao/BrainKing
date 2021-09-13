package com.example.brainking.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.SearchModel;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<SearchModel.DataDTO, BaseViewHolder> {
    public SearchAdapter() {
        super(R.layout.item_search);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchModel.DataDTO item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_dynasty, item.getDynasty());
    }
}

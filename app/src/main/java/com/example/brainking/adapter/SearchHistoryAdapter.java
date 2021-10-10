package com.example.brainking.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.SearchHistoryModel;

import org.jetbrains.annotations.NotNull;

public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistoryModel.RowsDTO, BaseViewHolder> {
    public SearchHistoryAdapter() {
        super(R.layout.item_search_history);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SearchHistoryModel.RowsDTO data) {
        baseViewHolder.setText(R.id.tv, data.getSearchKey() + "");

    }
}

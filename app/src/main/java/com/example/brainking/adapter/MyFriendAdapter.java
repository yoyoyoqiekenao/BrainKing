package com.example.brainking.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;

import org.jetbrains.annotations.NotNull;

public class MyFriendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyFriendAdapter() {
        super(R.layout.item_myfriend);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {

    }
}

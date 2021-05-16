package com.example.brainking.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;

import org.jetbrains.annotations.NotNull;

public class BattleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BattleAdapter() {
        super(R.layout.item_battle);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {


    }
}

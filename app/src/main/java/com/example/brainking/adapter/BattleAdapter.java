package com.example.brainking.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.brainking.R;


public class BattleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public BattleAdapter() {
        super(R.layout.item_battle);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.addOnClickListener(R.id.tv_pk);
    }
}

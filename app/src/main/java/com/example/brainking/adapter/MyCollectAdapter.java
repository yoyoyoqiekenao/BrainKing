package com.example.brainking.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.CollectListModel;

import org.jetbrains.annotations.NotNull;

public class MyCollectAdapter extends BaseQuickAdapter<CollectListModel.RowsDTO, BaseViewHolder> {
    public MyCollectAdapter() {
        super(R.layout.item_collect);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CollectListModel.RowsDTO rowsDTO) {
        ImageView ivHead = baseViewHolder.getView(R.id.iv_head);
        if ("2".equals(rowsDTO.getType())) {
            Glide.with(getContext()).load(R.mipmap.collect_poem).into(ivHead);
        } else {
            Glide.with(getContext()).load(R.mipmap.collect_math).into(ivHead);
        }

        baseViewHolder.setText(R.id.tv_name, rowsDTO.getTitle() + "");
        baseViewHolder.setText(R.id.tv_remark, rowsDTO.getAuthor() + "");
    }
}

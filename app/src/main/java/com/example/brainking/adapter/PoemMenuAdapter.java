package com.example.brainking.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.PoemListModel;

import org.jetbrains.annotations.NotNull;

public class PoemMenuAdapter extends BaseQuickAdapter<PoemListModel.RowsDTO, BaseViewHolder> {

    private int mIndex;

    public PoemMenuAdapter() {
        super(R.layout.item_poem_menu);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, PoemListModel.RowsDTO rowsDTO) {
        baseViewHolder.setText(R.id.tv_name, rowsDTO.getTitle());
        baseViewHolder.setText(R.id.tv_author, rowsDTO.getAuthor());
        if (mIndex == baseViewHolder.getAdapterPosition()) {
            baseViewHolder.setVisible(R.id.iv_play, true);
        } else {
            baseViewHolder.setVisible(R.id.iv_play, false);
        }

        if (rowsDTO.getFree()) {
            baseViewHolder.setImageResource(R.id.iv_free, R.mipmap.iv_unlock);
        } else {
            baseViewHolder.setImageResource(R.id.iv_free, R.mipmap.iv_lock);

        }

    }

    public void setIndex(int position) {
        mIndex = position;
        notifyDataSetChanged();
    }
}

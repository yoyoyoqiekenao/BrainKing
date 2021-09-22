package com.example.brainking.adapter;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.LearnListModel;

import java.util.List;

public class LearnListAdapter_poems extends BaseQuickAdapter<LearnListModel.DataDTO, BaseViewHolder> {

    private int mIndex = -1;

    public LearnListAdapter_poems() {
        super(R.layout.item_learnlost_poems);
    }


    @Override
    protected void convert(BaseViewHolder helper, LearnListModel.DataDTO item) {
        helper.setText(R.id.tv_name, item.getName());
        RelativeLayout rl = helper.getView(R.id.rl);

        if (mIndex == helper.getAdapterPosition()) {
            rl.setBackgroundResource(R.mipmap.iv_poems_yellow);
        } else {
            rl.setBackgroundResource(R.mipmap.iv_poems_blue);
        }
    }

    public void setSelect(int position) {
        mIndex = position;
        notifyDataSetChanged();
    }
}

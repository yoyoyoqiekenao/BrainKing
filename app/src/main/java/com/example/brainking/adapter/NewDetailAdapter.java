package com.example.brainking.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.NewDetailModel;

import java.util.List;

public class NewDetailAdapter extends BaseMultiItemQuickAdapter<NewDetailModel.DataDTO, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public NewDetailAdapter(List<NewDetailModel.DataDTO> data) {
        super(data);
        addItemType(0, R.layout.item_newdetail_left);
        addItemType(1, R.layout.item_newdetail_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewDetailModel.DataDTO item) {
        switch (helper.getItemViewType()) {
            case 0:
                ImageView ivHead_to = helper.getView(R.id.iv_head);
                Glide.with(getContext()).load(item.getToUserAvatar()).into(ivHead_to);
                helper.setText(R.id.tv_msg, item.getMsg());
                break;
            case 1:
                ImageView ivHead_from = helper.getView(R.id.iv_head);
                Glide.with(getContext()).load(item.getFromUserAvatar()).into(ivHead_from);
                helper.setText(R.id.tv_msg, item.getMsg());
                break;
            default:
        }
    }
}

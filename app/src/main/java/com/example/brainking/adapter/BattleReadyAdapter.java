package com.example.brainking.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.BattleNormalModel;

public class BattleReadyAdapter extends BaseQuickAdapter<BattleNormalModel, BaseViewHolder> {
    public BattleReadyAdapter() {
        super(R.layout.item_battleready);
    }

    @Override
    protected void convert(BaseViewHolder helper, BattleNormalModel item) {


        helper.setText(R.id.tv_name, item.getName() + "");
        Glide.with(getContext()).load(item.getImg()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv_head));

    }
}

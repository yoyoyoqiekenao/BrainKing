package com.example.brainking.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.BattleDetailModel;

import java.util.List;

public class BattleDetailAdapter extends BaseQuickAdapter<BattleDetailModel, BaseViewHolder> {
    public BattleDetailAdapter() {
        super(R.layout.itemm_battledetail);
    }

    @Override
    protected void convert(BaseViewHolder helper, BattleDetailModel item) {
        helper.setText(R.id.tv_score, item.getTotalScore());
        helper.setText(R.id.tv_name, item.getName());
        Glide.with(mContext).load(item.getImg()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv_head));

    }
}

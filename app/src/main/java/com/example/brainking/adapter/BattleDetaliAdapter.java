package com.example.brainking.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;

import java.util.List;

public class BattleDetaliAdapter extends BaseQuickAdapter<BattleNormalModel, BaseViewHolder> {
    public BattleDetaliAdapter() {
        super(R.layout.item_battledetail);
    }

    @Override
    protected void convert(BaseViewHolder helper, BattleNormalModel item) {
        helper.setText(R.id.tv_name, item.getName() + "");
        Glide.with(mContext).load(item.getImg()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv_head));
    }
}

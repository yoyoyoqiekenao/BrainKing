package com.example.brainking.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.brainking.R;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;

import java.util.List;

public class BattleDetaliAdapter extends BaseQuickAdapter<MqttBattleDetailModel.WaitingUserDTO, BaseViewHolder> {
    public BattleDetaliAdapter() {
        super(R.layout.item_battledetail);
    }

    @Override
    protected void convert(BaseViewHolder helper, MqttBattleDetailModel.WaitingUserDTO item) {
        helper.setText(R.id.tv_name, item.getNickName() + "");
        Glide.with(mContext).load(item.getAvatar()).into((ImageView) helper.getView(R.id.iv_head));
    }
}

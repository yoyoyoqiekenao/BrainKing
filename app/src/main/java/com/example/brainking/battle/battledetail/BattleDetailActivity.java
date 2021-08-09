package com.example.brainking.battle.battledetail;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleDetaliAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BattleDetailActivity extends BrainActivity<BattleDetailPresenter> implements BattleDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rc)
    RecyclerView rc;


    private BattleDetaliAdapter mAdapter;

    @Override
    protected BattleDetailPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_detail);
        MyMqttService.startService(this);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);

        mAdapter = new BattleDetaliAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 6);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void battleDetail(MatchStartEvent event) {
        if (!TextUtils.isEmpty(new Gson().fromJson(event.getMsg(), MqttBattleDetailModel.class).getWaitingUser().get(0).getNickName())) {
            MqttBattleDetailModel model = new Gson().fromJson(event.getMsg(), MqttBattleDetailModel.class);
            mAdapter.setNewData(model.getWaitingUser());
        }
    }
}

package com.example.brainking.battle.battledetail;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
        EventBus.getDefault().register(this);


        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);

        mAdapter = new BattleDetaliAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 6);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(mAdapter);

        MyMqttService.startService(this);
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
        String str = event.getMsg();
         

        MqttBattleDetailModel model = new Gson().fromJson(str, MqttBattleDetailModel.class);
        Log.d("xuwudi","数据==="+model.toString());
       /* if (!TextUtils.isEmpty(new Gson().fromJson(event.getMsg(), MqttBattleDetailModel.class).getType()) && new Gson().fromJson(event.getMsg(), MqttBattleDetailModel.class).getType().equals("JoinRoom")) {
            Log.d("xuwudi3", "满足条件");
            MqttBattleDetailModel model = new Gson().fromJson(event.getMsg(), MqttBattleDetailModel.class);
            mAdapter.setNewData(model.getWaitingUser());
        } else {
            Log.d("xuwudi4", "不满足条件");
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

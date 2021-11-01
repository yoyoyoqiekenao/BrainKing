package com.example.brainking.battle.joinroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.brainking.R;
import com.example.brainking.adapter.BattleDetailAdapter;
import com.example.brainking.adapter.BattleReadyAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battledetail.BattleDetailActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.model.JoinRoomModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;
import com.example.brainking.mqttmodel.MqttPvpModel;
import com.example.brainking.mqttmodel.MqttQuitRoomModel;
import com.example.brainking.util.SpUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JoinRoomActivity extends BrainActivity<JoinRoomPresenter> implements JoinRoomView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rc)
    RecyclerView rc;


    private BattleReadyAdapter mAdapter;
    private List<BattleNormalModel> mNormalList = new ArrayList<>();

    private List<JoinRoomModel.DataDTO> mList = new ArrayList<>();
    private String mRoomId;

    @Override
    protected JoinRoomPresenter createPresenter() {
        return new JoinRoomPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinroom);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        mRoomId = getIntent().getStringExtra("roomId");


        rl_back.setOnClickListener(this);

        mList = (List<JoinRoomModel.DataDTO>) getIntent().getSerializableExtra("list");
        for (int i = 0; i < mList.size(); i++) {
            mNormalList.add(new BattleNormalModel(mList.get(i).getNickName(), mList.get(i).getAvatar(), mList.get(i).getUserId(),""));
        }

        mAdapter = new BattleReadyAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(mAdapter);
        mAdapter.setList(mNormalList);

     }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void battleDetail(MatchStartEvent event) {
        String str = event.getMsg();


        if ("JoinRoom".equals(new Gson().fromJson(str, MqttBattleDetailModel.class).getType())) {
            MqttBattleDetailModel model = new Gson().fromJson(str, MqttBattleDetailModel.class);
            mNormalList.add(new BattleNormalModel(model.getJoinUser().getNickName(), model.getJoinUser().getAvatar(), model.getJoinUser().getUserId(),""));
            mAdapter.setList(mNormalList);
        }
        if ("QuitRoom".equals(new Gson().fromJson(str, MqttQuitRoomModel.class).getType())) {
            MqttQuitRoomModel model = new Gson().fromJson(str, MqttQuitRoomModel.class);
            for (int i = 0; i < mNormalList.size(); i++) {
                if (model.getUserId().equals(mNormalList.get(i).getUserId())) {
                    mNormalList.remove(i);
                    mAdapter.setList(mNormalList);
                }
            }
        }
        if ("Pvp".equals(new Gson().fromJson(str, MqttPvpModel.class).getType())) {
            MqttPvpModel model = new Gson().fromJson(str, MqttPvpModel.class);
            Intent intent = new Intent(this, BattleDetailActivity.class);
            intent.putExtra("list", (Serializable) mNormalList);
            intent.putExtra("roomId", model.getRoomId());
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void matchExitSuccess(MatchStartModel model) {
        finish();
    }

    @Override
    public void fail(String err) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                createPresenter().matchExit(mRoomId);
                break;
            default:
        }
    }
}

package com.example.brainking.battle.battleready;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleReadyAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battledetail.BattleDetailActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;
import com.example.brainking.util.SpUtils;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BattleReadyActivity extends BrainActivity<BattleReadyPresenter> implements BattleReadyView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.tv_start)
    TextView tv_start;


    private BattleReadyAdapter mAdapter;
    private String mRoomId;
    private String mNum;

    private List<BattleNormalModel> mNormalList = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    mAdapter.addData(mNormalList);
                    break;
                default:
            }
        }
    };

    @Override
    protected BattleReadyPresenter createPresenter() {
        return new BattleReadyPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_ready);
        EventBus.getDefault().register(this);

        mRoomId = getIntent().getStringExtra("roomId");
        mNum = getIntent().getStringExtra("num");

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);
        tv_start.setOnClickListener(this);


        mNormalList.add(new BattleNormalModel(SpUtils.getInstance().getString("name"), SpUtils.getInstance().getString("headImg"),SpUtils.getInstance().getString("userId")));
        mAdapter = new BattleReadyAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(mAdapter);
        mAdapter.setNewData(mNormalList);


        MyMqttService.startService(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_start:
                if (mNormalList != null && mNormalList.size() > 1) {
                    createPresenter().multiReady(mRoomId);
                } else {
                    Toast.makeText(this, "最小人数为2人", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void battleDetail(MatchStartEvent event) {
        String str = event.getMsg();

        MqttBattleDetailModel model = new Gson().fromJson(str, MqttBattleDetailModel.class);

        if ("JoinRoom".equals(model.getType())) {
            mNormalList.add(new BattleNormalModel(model.getJoinUser().getNickName(), model.getJoinUser().getAvatar(),model.getJoinUser().getUserId()));
            mAdapter.setNewData(mNormalList);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void multiReadySuccess(CreateRoomModel model) {

        Intent intent = new Intent(this, BattleDetailActivity.class);
        intent.putExtra("list", (Serializable) mNormalList);
        intent.putExtra("roomId", model.getData());
        startActivity(intent);
        finish();
    }

    @Override
    public void multiReadyFail(String msg) {
        Log.d("xuwudi", "多人对战失败" + msg);
    }
}

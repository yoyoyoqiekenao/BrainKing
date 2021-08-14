package com.example.brainking.battle.battledetail;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleDetaliAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battle.BattlePresenter;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.mqttmodel.MqttBattleDetailModel;
import com.example.brainking.util.SpUtils;
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
    @BindView(R.id.tv_start)
    TextView tv_start;


    private BattleDetaliAdapter mAdapter;
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
    protected BattleDetailPresenter createPresenter() {
        return new BattleDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_detail);
        EventBus.getDefault().register(this);

        mRoomId = getIntent().getStringExtra("roomId");
        mNum = getIntent().getStringExtra("num");

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);
        tv_start.setOnClickListener(this);


        mNormalList.add(new BattleNormalModel(SpUtils.getInstance().getString("name"), SpUtils.getInstance().getString("headImg")));
        mAdapter = new BattleDetaliAdapter();
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
                createPresenter().multiReady(mRoomId);
                break;
            default:
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void battleDetail(MatchStartEvent event) {
        String str = event.getMsg();

        MqttBattleDetailModel model = new Gson().fromJson(str, MqttBattleDetailModel.class);
        Log.d("x2", "解析的数据===" + model.toString());
        if ("JoinRoom".equals(model.getType())) {
            mNormalList.add(new BattleNormalModel(model.getJoinUser().getNickName(), model.getJoinUser().getAvatar()));
            mAdapter.setNewData(mNormalList);
        }
        Log.d("x3", "ListSize===" + mNormalList.toString());

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
    public void multiReadySuccess() {
        Log.d("xuwudi", "多人对战开始");
    }

    @Override
    public void multiReadyFail(String msg) {
        Log.d("xuwudi", "多人对战失败"+msg);
    }
}

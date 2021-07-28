package com.example.brainking.match.match_detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.match.match_battle.MatchBattleActivity;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttMatchStartModel;
import com.example.brainking.util.SpUtils;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchDetailActivity extends BrainActivity<MatchDetailPresenter> implements MatchDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_time)
    TextView tv_time;

    private ObjectAnimator mObjectAnimator_1, mObjectAnimator_2;


    private final Timer timer = new Timer();
    private TimerTask task;
    private int mIndex = 5;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    tv_time.setText(mIndex + "S");
                    if (mIndex == 0) {
                        timer.cancel();
                    }
                    break;
                default:
            }
        }
    };

    @Override
    protected MatchDetailPresenter createPresenter() {
        return new MatchDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchdetail);
        ImmersionBar.with(this).statusBarView(mView).init();
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        Intent intent = new Intent(this, MyMqttService.class);
        intent.putExtra("clientId", SpUtils.getInstance().getString("userId"));
        startService(intent);
        //MyMqttService.startService(this);


        rlBack.setOnClickListener(this);
        mObjectAnimator_1 = ObjectAnimator.ofFloat(iv_left, "alpha", 0f, 1f);
        mObjectAnimator_1.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(mObjectAnimator_1);
        mObjectAnimator_1.start();


        mObjectAnimator_2 = ObjectAnimator.ofFloat(iv_right, "alpha", 0f, 1f);
        mObjectAnimator_2.setDuration(2000);
        AnimatorSet animatorSet_2 = new AnimatorSet();
        animatorSet_2.playTogether(mObjectAnimator_2);
        mObjectAnimator_2.start();


        task = new TimerTask() {
            @Override
            public void run() {
                mIndex = mIndex - 1;
                mHandler.sendEmptyMessage(1);
            }
        };
        timer.schedule(task, 1000, 1000);


        basePresenter.createRoom();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRoomId(MatchStartEvent event) {
        String str = event.getMsg();
        MqttMatchStartModel model = new Gson().fromJson(str, MqttMatchStartModel.class);
        Log.d("xuwudi", "接受的数据===" + model.toString());
        if (!TextUtils.isEmpty(model.getRoomId())) {
            Intent intent = new Intent(MatchDetailActivity.this, MatchBattleActivity.class);
            intent.putExtra("roomId", model.getRoomId());
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

        EventBus.getDefault().unregister(this);
        MyMqttService.stopService(this);
    }

    @Override
    public void matchStartSuccess(MatchStartModel matchStartModel) {

    }

    @Override
    public void fail(String msg) {

    }


}

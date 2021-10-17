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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.match.match_battle.MatchBattleActivity;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttMatchStartModel;
import com.example.brainking.mqttmodel.MqttReadyModel;
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
    @BindView(R.id.tv_name_left)
    TextView tv_name_left;
    @BindView(R.id.ll_left)
    RelativeLayout ll_left;
    @BindView(R.id.tv_name_right)
    TextView tv_name_right;
    @BindView(R.id.ll_right)
    RelativeLayout ll_right;
    @BindView(R.id.tv_fees_left)
    TextView tv_fees_left;
    @BindView(R.id.tv_fees_right)
    TextView tv_fees_right;

    private ObjectAnimator mObjectAnimator_1, mObjectAnimator_2;


    private final Timer timer = new Timer();
    private TimerTask task;
    private int mIndex = 5;


    private String mRoomId;
    private String mName;
    private String mImg;


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
                case 2:
                    Intent intent = new Intent(MatchDetailActivity.this, MatchBattleActivity.class);
                    intent.putExtra("roomId", mRoomId);
                    intent.putExtra("name", mName);
                    intent.putExtra("img", mImg);
                    startActivity(intent);
                    finish();
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

        //Glide.with(this).load(SpUtils.getInstance().getString("headImg")).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_left);
        //tv_name_left.setText(SpUtils.getInstance().getString("name"));

        rlBack.setOnClickListener(this);


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


        MqttReadyModel model_ = new Gson().fromJson(str, MqttReadyModel.class);
        if (model_ != null) {
            ll_left.setVisibility(View.VISIBLE);
            ll_right.setVisibility(View.VISIBLE);

            mName = model_.getPlayers().get(1).getUserName();
            mImg = model_.getPlayers().get(1).getAvatar();
            mRoomId = model_.getRoomId();

            tv_name_left.setText("Lv." + model_.getPlayers().get(0).getScore() + " " + model_.getPlayers().get(0).getUserName());
            tv_fees_left.setText("出场费: " + model_.getPlayers().get(0).getFees() + "个星星");
            Glide.with(this).load(model_.getPlayers().get(0).getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_left);
            mObjectAnimator_1 = ObjectAnimator.ofFloat(ll_left, "alpha", 0f, 1f);
            mObjectAnimator_1.setDuration(2000);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(mObjectAnimator_1);
            mObjectAnimator_1.start();


            tv_name_right.setText("Lv." + model_.getPlayers().get(1).getScore() + " " + mName);
            tv_fees_right.setText("出场费: " + model_.getPlayers().get(1).getFees() + "个星星");
            Glide.with(this).load(mImg).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_right);
            mObjectAnimator_2 = ObjectAnimator.ofFloat(ll_right, "alpha", 0f, 1f);
            mObjectAnimator_2.setDuration(2000);
            AnimatorSet animatorSet_2 = new AnimatorSet();
            animatorSet_2.playTogether(mObjectAnimator_2);
            mObjectAnimator_2.start();

            mHandler.sendEmptyMessageDelayed(2, 3000);
        } else {

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (timer != null) {
            timer.cancel();
        }

        EventBus.getDefault().unregister(this);

    }

    @Override
    public void matchStartSuccess(MatchStartModel matchStartModel) {

    }

    @Override
    public void fail(String msg) {

    }


}

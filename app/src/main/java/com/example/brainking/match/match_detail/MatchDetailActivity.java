package com.example.brainking.match.match_detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.gyf.immersionbar.ImmersionBar;

import org.eclipse.paho.android.service.MqttService;

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

        MyMqttService.startService(this);

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

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}

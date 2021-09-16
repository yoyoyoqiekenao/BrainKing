package com.example.brainking.match.match_battle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttAnswerNoticeModel;
import com.example.brainking.mqttmodel.MqttOptionModel;
import com.example.brainking.mqttmodel.MqttResultModel;
import com.example.brainking.util.SpUtils;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchBattleActivity extends BrainActivity<MatchBattlePresenter> implements MatchBattleView, View.OnClickListener {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_answer_1)
    TextView tv_answer_1;
    @BindView(R.id.tv_answer_2)
    TextView tv_answer_2;
    @BindView(R.id.tv_answer_3)
    TextView tv_answer_3;
    @BindView(R.id.tv_answer_4)
    TextView tv_answer_4;
    @BindView(R.id.tv_score_right)
    TextView tv_score_right;
    @BindView(R.id.tv_score_left)
    TextView tv_score_left;
    @BindView(R.id.ll_answer)
    LinearLayout ll_answer;
    @BindView(R.id.progress_view)
    View progress_view;
    @BindView(R.id.iv_result)
    ImageView iv_result;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_name_left)
    TextView tv_name_left;
    @BindView(R.id.tv_name_right)
    TextView tv_name_right;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;


    private String mAnswer_1;
    private String mAnswer_2;
    private String mAnswer_3;
    private String mAnswer_4;

    private String mAnswer;


    private String mRoomId = "";

    private boolean isReallyExit = false;
    private int mTime = 10;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    if (mTime > 0) {
                        tv_time.setText(mTime + "");
                        mTime--;
                        mHandler.sendEmptyMessageDelayed(1, 1000);

                    } else if (mTime == 0) {

                    }
                    break;
                case 2:
                    rl_time.setVisibility(View.VISIBLE);
                    break;
                default:
            }
        }
    };

    @Override
    protected MatchBattlePresenter createPresenter() {
        return new MatchBattlePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchbattle);

        ImmersionBar.with(this).statusBarView(mView).init();
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);


        mRoomId = getIntent().getStringExtra("roomId");
        basePresenter.ready(mRoomId);

        tv_answer_1.setOnClickListener(this);
        tv_answer_2.setOnClickListener(this);
        tv_answer_3.setOnClickListener(this);
        tv_answer_4.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ready(MatchStartEvent event) {
        String str = event.getMsg();

        //题目
        if ("subject".equals(new Gson().fromJson(str, MqttOptionModel.class).getType())) {
            MqttOptionModel optionModel = new Gson().fromJson(str, MqttOptionModel.class);
            tv_title.setText(optionModel.getTitle());

            for (int i = 0; i < optionModel.getOption().size(); i++) {
                if (optionModel.getOption().get(i).isRight) {
                    mAnswer = optionModel.getOption().get(i).getId();
                }
            }

            mTime = 10;
            mHandler.sendEmptyMessageDelayed(1, 1000);

            tv_answer_1.setText(optionModel.getOption().get(0).getContent());
            tv_answer_2.setText(optionModel.getOption().get(1).getContent());
            tv_answer_3.setText(optionModel.getOption().get(2).getContent());
            tv_answer_4.setText(optionModel.getOption().get(3).getContent());

            mAnswer_1 = optionModel.getOption().get(0).getId();
            mAnswer_2 = optionModel.getOption().get(1).getId();
            mAnswer_3 = optionModel.getOption().get(2).getId();
            mAnswer_4 = optionModel.getOption().get(3).getId();

            tv_answer_1.setClickable(true);
            tv_answer_2.setClickable(true);
            tv_answer_3.setClickable(true);
            tv_answer_4.setClickable(true);

            tv_answer_1.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_2.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_3.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_4.setBackgroundResource(R.drawable.rectangle_ffffff_50);
        } else if ("AnswerNotice".equals(new Gson().fromJson(str, MqttAnswerNoticeModel.class).getType())) {
            //分数
            MqttAnswerNoticeModel answerModel = new Gson().fromJson(str, MqttAnswerNoticeModel.class);
            if (answerModel.getUserId().equals(SpUtils.getInstance().getString("userId"))) {
                tv_score_left.setText(answerModel.getTotalScore() + "");
            } else {
                tv_score_right.setText(answerModel.getTotalScore() + "");
            }
        } else if ("FinalResult".equals(new Gson().fromJson(str, MqttResultModel.class).getType())) {
            //答题结果
            tv_title.setVisibility(View.INVISIBLE);
            ll_answer.setVisibility(View.GONE);
            progress_view.setVisibility(View.GONE);
            tv_score_right.setVisibility(View.GONE);
            tv_score_left.setVisibility(View.GONE);
            mHandler.sendEmptyMessage(2);

            MqttResultModel mqttResultModel = new Gson().fromJson(str, MqttResultModel.class);
            if (mqttResultModel.getResultType().equals("win")) {
                iv_result.setImageResource(R.mipmap.iv_win);
            } else {
                iv_result.setImageResource(R.mipmap.iv_lose);
            }

            iv_result.setVisibility(View.VISIBLE);
            tv_next.setVisibility(View.VISIBLE);

            basePresenter.matchExit(mRoomId);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    @Override
    public void mattchReadySuccess(MatchStartModel model) {
        // if (model.isData()) {
        Toast.makeText(this, model.getMsg(), Toast.LENGTH_SHORT).show();
        //  }
    }

    @Override
    public void fail(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void matchExitSuccess(MatchStartModel model) {
        //Toast.makeText(this, "退出房间", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void matchAnswerSuccess(MatchAnswerModel matchAnswerModel) {
        //tv_score_left.setText(matchAnswerModel.getData().getTotalScore() + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_1:
                if (mAnswer_1.equals(mAnswer)) {
                    tv_answer_1.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                } else {
                    tv_answer_1.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                }

                basePresenter.matchAnswer(mAnswer_1, mRoomId);

                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.tv_answer_2:
                if (mAnswer_2.equals(mAnswer)) {
                    tv_answer_2.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                } else {
                    tv_answer_2.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                }

                basePresenter.matchAnswer(mAnswer_2, mRoomId);
                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.tv_answer_3:
                if (mAnswer_3.equals(mAnswer)) {
                    tv_answer_3.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                } else {
                    tv_answer_3.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                }

                basePresenter.matchAnswer(mAnswer_3, mRoomId);
                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.tv_answer_4:

                if (mAnswer_4.equals(mAnswer)) {
                    tv_answer_4.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                } else {
                    tv_answer_4.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                }

                tv_answer_4.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                basePresenter.matchAnswer(mAnswer_4, mRoomId);
                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.tv_next:
                finish();
                break;
            default:
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isReallyExit == false) {
                Toast.makeText(this, "您已经在房间中,是否确认退出？", Toast.LENGTH_SHORT).show();
                isReallyExit = true;
            } else {
                basePresenter.matchExit(mRoomId);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


}

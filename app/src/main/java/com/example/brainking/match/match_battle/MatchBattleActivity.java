package com.example.brainking.match.match_battle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttAnswerNoticeModel;
import com.example.brainking.mqttmodel.MqttOptionModel;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchBattleActivity extends BrainActivity<MatchBattlePresenter> implements MatchBattleView {


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


    private String mRoomId = "";

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
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ready(MatchStartEvent event) {
        String str = event.getMsg();
        if (!"null".equals(new Gson().fromJson(str, MqttOptionModel.class).getTitle())) { //题目的message
            MqttOptionModel optionModel = new Gson().fromJson(str, MqttOptionModel.class);
            tv_title.setText(optionModel.getTitle());
            tv_answer_1.setText(optionModel.getOption().get(0).getContent());
            tv_answer_2.setText(optionModel.getOption().get(1).getContent());
            tv_answer_3.setText(optionModel.getOption().get(2).getContent());
            tv_answer_4.setText(optionModel.getOption().get(3).getContent());
        }
        if (!"null".equals(new Gson().fromJson(str, MqttAnswerNoticeModel.class).getType())) { //对手的答题数据
            MqttAnswerNoticeModel mqttAnswerNoticeModel = new Gson().fromJson(str, MqttAnswerNoticeModel.class);
            tv_score_right.setText(mqttAnswerNoticeModel.getTotalScore() + "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
}

package com.example.brainking.match.match_battle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.mqttmodel.MqttAnswerNoticeModel;
import com.example.brainking.mqttmodel.MqttOptionModel;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.eclipse.paho.android.service.MqttService;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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

    private String mAnswer_1;
    private String mAnswer_2;
    private String mAnswer_3;
    private String mAnswer_4;


    private String mRoomId = "";

    private boolean isReallyExit = false;

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
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ready(MatchStartEvent event) {
        String str = event.getMsg();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = jsonObject.getJSONObject(str);
            String type = jsonObject.getString("type");
            Log.d("xuwudi", "获取的type===" + type);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Log.d("xuwudi", "数据====" + str);
        if (!TextUtils.isEmpty(new Gson().fromJson(str, MqttOptionModel.class).getTitle())) { //题目的message
            MqttOptionModel optionModel = new Gson().fromJson(str, MqttOptionModel.class);
            tv_title.setText(optionModel.getTitle());
            tv_answer_1.setText(optionModel.getOption().get(0).getContent());
            tv_answer_2.setText(optionModel.getOption().get(1).getContent());
            tv_answer_3.setText(optionModel.getOption().get(2).getContent());
            tv_answer_4.setText(optionModel.getOption().get(3).getContent());

            mAnswer_1 = optionModel.getOption().get(0).getId();
            mAnswer_2 = optionModel.getOption().get(1).getId();
            mAnswer_3 = optionModel.getOption().get(2).getId();
            mAnswer_4 = optionModel.getOption().get(3).getId();

            Log.d("xuwudi", "数据1===" + optionModel.toString());
        }

        if (TextUtils.isEmpty(new Gson().fromJson(str, MqttAnswerNoticeModel.class).getChoice())) { //对手的答题数据

        } else {
            MqttAnswerNoticeModel mqttAnswerNoticeModel = new Gson().fromJson(str, MqttAnswerNoticeModel.class);
            tv_score_right.setText(mqttAnswerNoticeModel.getTotalScore() + "");
            Log.d("xuwudi", "数据2===" + mqttAnswerNoticeModel.getTotalScore());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        basePresenter.matchExit(mRoomId);
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
        MyMqttService.stopService(this);
        Toast.makeText(this, "退出房间", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void matchAnswerSuccess(MatchAnswerModel matchAnswerModel) {
        tv_score_left.setText(matchAnswerModel.getData().getTotalScore() + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_1:
                basePresenter.matchAnswer(mAnswer_1, mRoomId);
                break;
            case R.id.tv_answer_2:
                basePresenter.matchAnswer(mAnswer_2, mRoomId);
                break;
            case R.id.tv_answer_3:
                basePresenter.matchAnswer(mAnswer_3, mRoomId);
                break;
            case R.id.tv_answer_4:
                basePresenter.matchAnswer(mAnswer_4, mRoomId);
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

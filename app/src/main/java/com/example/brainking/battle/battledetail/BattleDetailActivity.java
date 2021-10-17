package com.example.brainking.battle.battledetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleDetailAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.BattleDetailModel;
import com.example.brainking.model.BattleNormalModel;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.mqttmodel.MqttAnswerNoticeModel;
import com.example.brainking.mqttmodel.MqttOptionModel;
import com.example.brainking.mqttmodel.MqttResultModel;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//多人对战答题
public class BattleDetailActivity extends BrainActivity<BattleDetailPresenter> implements BattleDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.tv_finish)
    TextView tv_finish;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_answer)
    LinearLayout ll_answer;
    @BindView(R.id.tv_answer_1)
    TextView tv_answer_1;
    @BindView(R.id.tv_answer_2)
    TextView tv_answer_2;
    @BindView(R.id.tv_answer_3)
    TextView tv_answer_3;
    @BindView(R.id.tv_answer_4)
    TextView tv_answer_4;
    @BindView(R.id.iv_result)
    ImageView iv_result;
    @BindView(R.id.iv_answer1_success)
    ImageView iv_answer1_success;
    @BindView(R.id.iv_answer2_success)
    ImageView iv_answer2_success;
    @BindView(R.id.iv_answer3_success)
    ImageView iv_answer3_success;
    @BindView(R.id.iv_answer4_success)
    ImageView iv_answer4_success;
    @BindView(R.id.iv_answer1_error)
    ImageView iv_answer1_error;
    @BindView(R.id.iv_answer2_error)
    ImageView iv_answer2_error;
    @BindView(R.id.iv_answer3_error)
    ImageView iv_answer3_error;
    @BindView(R.id.iv_answer4_error)
    ImageView iv_answer4_error;

    private List<BattleNormalModel> normalModels = new ArrayList<>();
    private List<BattleDetailModel> mList = new ArrayList<>();


    private BattleDetailAdapter mAdapter;

    private String mAnswer_1;
    private String mAnswer_2;
    private String mAnswer_3;
    private String mAnswer_4;

    private String mRoomId;

    private String mAnswer;

    @Override
    protected BattleDetailPresenter createPresenter() {
        return new BattleDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_detail);
        EventBus.getDefault().register(this);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        normalModels = (List<BattleNormalModel>) getIntent().getSerializableExtra("list");
        mRoomId = getIntent().getStringExtra("roomId");


        for (int i = 0; i < normalModels.size(); i++) {
            BattleDetailModel model = new BattleDetailModel(normalModels.get(i).getName(), normalModels.get(i).getImg(), "0", normalModels.get(i).getUserId());
            mList.add(model);
        }

        mAdapter = new BattleDetailAdapter();
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc.setLayoutManager(manager);
        rc.setAdapter(mAdapter);
        mAdapter.setList(mList);


        tv_finish.setOnClickListener(this);
        tv_answer_1.setOnClickListener(this);
        tv_answer_2.setOnClickListener(this);
        tv_answer_3.setOnClickListener(this);
        tv_answer_4.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInfo(MatchStartEvent event) {


        if ("FinalResult".equals(new Gson().fromJson(event.getMsg(), MqttResultModel.class).getType())) {
            tv_title.setVisibility(View.INVISIBLE);
            ll_answer.setVisibility(View.INVISIBLE);
            tv_finish.setVisibility(View.VISIBLE);
            iv_result.setVisibility(View.VISIBLE);
            MqttResultModel model = new Gson().fromJson(event.getMsg(), MqttResultModel.class);
            if ("win".equals(model.getResultType())) {
                iv_result.setImageResource(R.mipmap.iv_win);
            } else if("lose".equals(model.getResultType())){
                iv_result.setImageResource(R.mipmap.iv_lose);
            }else {
                iv_result.setImageResource(R.mipmap.iv_equal);
            }
        }
        if ("subject".equals(new Gson().fromJson(event.getMsg(), MqttOptionModel.class).getType())) {
            MqttOptionModel model = new Gson().fromJson(event.getMsg(), MqttOptionModel.class);

            for (int i = 0; i < model.getOption().size(); i++) {
                if (model.getOption().get(i).isRight) {
                    mAnswer = model.getOption().get(i).getId();
                }
            }

            tv_title.setText(model.getTitle());
            tv_answer_1.setText(model.getOption().get(0).getContent());
            tv_answer_2.setText(model.getOption().get(1).getContent());
            tv_answer_3.setText(model.getOption().get(2).getContent());
            tv_answer_4.setText(model.getOption().get(3).getContent());

            mAnswer_1 = model.getOption().get(0).getId();
            mAnswer_2 = model.getOption().get(1).getId();
            mAnswer_3 = model.getOption().get(2).getId();
            mAnswer_4 = model.getOption().get(3).getId();

            tv_answer_1.setClickable(true);
            tv_answer_2.setClickable(true);
            tv_answer_3.setClickable(true);
            tv_answer_4.setClickable(true);

            tv_answer_1.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_2.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_3.setBackgroundResource(R.drawable.rectangle_ffffff_50);
            tv_answer_4.setBackgroundResource(R.drawable.rectangle_ffffff_50);
        }

        if ("AnswerNotice".equals(new Gson().fromJson(event.getMsg(), MqttAnswerNoticeModel.class).getType())) {
            MqttAnswerNoticeModel model = new Gson().fromJson(event.getMsg(), MqttAnswerNoticeModel.class);
            for (int i = 0; i < mList.size(); i++) {
                if (model.getUserId().equals(mList.get(i).getUserId())) {
                    BattleDetailModel model1 = mList.get(i);
                    model1.setTotalScore(model.getTotalScore());
                    mList.set(i, model1);
                    mAdapter.setList(mList);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_finish:
                finish();
                break;
            case R.id.tv_answer_1:

                if (mAnswer_1.equals(mAnswer)) {
                    tv_answer_1.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                    iv_answer1_success.setVisibility(View.VISIBLE);
                    iv_answer1_error.setVisibility(View.GONE);
                } else {
                    tv_answer_1.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer1_success.setVisibility(View.GONE);
                    iv_answer1_error.setVisibility(View.VISIBLE);
                }

               //tv_answer_1.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                basePresenter.matchAnswer(mAnswer_1, mRoomId);

                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.tv_answer_2:
                if (mAnswer_2.equals(mAnswer)) {
                    tv_answer_2.setBackgroundResource(R.drawable.gradient_11d5c9_00db9e);
                    iv_answer2_success.setVisibility(View.VISIBLE);
                    iv_answer2_error.setVisibility(View.GONE);
                } else {
                    tv_answer_2.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer2_success.setVisibility(View.GONE);
                    iv_answer2_error.setVisibility(View.VISIBLE);
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
                    iv_answer3_success.setVisibility(View.VISIBLE);
                    iv_answer3_error.setVisibility(View.GONE);
                } else {
                    tv_answer_3.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer3_success.setVisibility(View.GONE);
                    iv_answer3_error.setVisibility(View.VISIBLE);
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
                    iv_answer4_success.setVisibility(View.VISIBLE);
                    iv_answer4_error.setVisibility(View.GONE);
                } else {
                    tv_answer_4.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer4_success.setVisibility(View.GONE);
                    iv_answer4_error.setVisibility(View.VISIBLE);
                }
                basePresenter.matchAnswer(mAnswer_4, mRoomId);
                tv_answer_1.setClickable(false);
                tv_answer_2.setClickable(false);
                tv_answer_3.setClickable(false);
                tv_answer_4.setClickable(false);
                break;
            case R.id.rl_back:
                finish();
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void matchAnswerSuccess(MatchAnswerModel matchAnswerModel) {

    }

    @Override
    public void fail(String err) {

    }
}

package com.example.brainking.battle.battledetail;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.brainking.model.ReConnectModel;
import com.example.brainking.mqttmodel.MqttAnswerNoticeModel;
import com.example.brainking.mqttmodel.MqttOptionModel;
import com.example.brainking.mqttmodel.MqttResultModel;
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
    @BindView(R.id.ll_fee)
    LinearLayout ll_fee;
    @BindView(R.id.tv_fees)
    TextView tv_fees;
    @BindView(R.id.tv_num)
    TextView tv_num;

    private List<BattleNormalModel> normalModels = new ArrayList<>();
    private List<BattleDetailModel> mList = new ArrayList<>();

    private String mType;

    private BattleDetailAdapter mAdapter;

    private String mAnswer_1;
    private String mAnswer_2;
    private String mAnswer_3;
    private String mAnswer_4;

    private String mRoomId;

    private String mAnswer;

    public MediaPlayer mediaPlayer;
    private SoundPool mSoundPool;
    private int mVoiceId_right;
    private int mVoiceId_error;

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

        mType = getIntent().getStringExtra("type");
        if (!TextUtils.isEmpty(mType)) {
            basePresenter.reConnect();
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.more_music);
        // 设置媒体流类型
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置循环播放
        mediaPlayer.setLooping(true);
        playMusic();

        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //传入最多播放音频数量,
            builder.setMaxStreams(1);
            //AudioAttributes是一个封装音频各种属性的方法
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            //设置音频流的合适的属性
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            //加载一个AudioAttributes
            builder.setAudioAttributes(attrBuilder.build());
            mSoundPool = builder.build();
        } else {
            /**
             * 第一个参数：int maxStreams：SoundPool对象的最大并发流数
             * 第二个参数：int streamType：AudioManager中描述的音频流类型
             *第三个参数：int srcQuality：采样率转换器的质量。 目前没有效果。 使用0作为默认值。
             */
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        mVoiceId_right = mSoundPool.load(this, R.raw.right_voice, 1);
        mVoiceId_error = mSoundPool.load(this, R.raw.error_voice, 1);

        normalModels = (List<BattleNormalModel>) getIntent().getSerializableExtra("list");
        mRoomId = getIntent().getStringExtra("roomId");
        if (!TextUtils.isEmpty(mRoomId)) {
            for (int i = 0; i < normalModels.size(); i++) {
                BattleDetailModel model = new BattleDetailModel(normalModels.get(i).getName(), normalModels.get(i).getImg(), "0", normalModels.get(i).getUserId());
                mList.add(model);
            }
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


    private void playMusic() {


        mediaPlayer.start();

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    rePlay();
                }
                return false;
            }
        });
    }

    //重新播放
    private void rePlay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            return;
        }
        playMusic();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInfo(MatchStartEvent event) {


        if ("FinalResult".equals(new Gson().fromJson(event.getMsg(), MqttResultModel.class).getType())) {
            tv_title.setVisibility(View.INVISIBLE);
            ll_answer.setVisibility(View.INVISIBLE);
            tv_finish.setVisibility(View.VISIBLE);
            iv_result.setVisibility(View.VISIBLE);
            ll_fee.setVisibility(View.VISIBLE);
            tv_num.setVisibility(View.GONE);
            MqttResultModel model = new Gson().fromJson(event.getMsg(), MqttResultModel.class);
            if ("win".equals(model.getResultType())) {
                iv_result.setImageResource(R.mipmap.iv_win);
            } else if ("lose".equals(model.getResultType())) {
                iv_result.setImageResource(R.mipmap.iv_lose);
            } else {
                iv_result.setImageResource(R.mipmap.iv_equal);
            }
            tv_fees.setText(model.getAddScore() + "");
        }
        if ("subject".equals(new Gson().fromJson(event.getMsg(), MqttOptionModel.class).getType())) {
            MqttOptionModel model = new Gson().fromJson(event.getMsg(), MqttOptionModel.class);

            tv_num.setText(model.getCurrentIndex() + "/" + model.getTotal());

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
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                } else {
                    tv_answer_1.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer1_success.setVisibility(View.GONE);
                    iv_answer1_error.setVisibility(View.VISIBLE);
                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
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
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                } else {
                    tv_answer_2.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer2_success.setVisibility(View.GONE);
                    iv_answer2_error.setVisibility(View.VISIBLE);
                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
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
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                } else {
                    tv_answer_3.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer3_success.setVisibility(View.GONE);
                    iv_answer3_error.setVisibility(View.VISIBLE);
                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
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
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                } else {
                    tv_answer_4.setBackgroundResource(R.drawable.gradient_f43750_ff637f);
                    iv_answer4_success.setVisibility(View.GONE);
                    iv_answer4_error.setVisibility(View.VISIBLE);
                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
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
        EventBus.getDefault().unregister(this);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mSoundPool != null) {
            mSoundPool.release();
        }
    }

    @Override
    public void matchAnswerSuccess(MatchAnswerModel matchAnswerModel) {

    }

    @Override
    public void fail(String err) {

    }

    @Override
    public void reConnectSuccess(ReConnectModel model) {
        MyMqttService.startService(this, SpUtils.getInstance().getString("userId"), model.getData().getRoomId());
        for (int i = 0; i < model.getData().getPlayers().size(); i++) {
            BattleDetailModel model_battle = new BattleDetailModel(model.getData().getPlayers().get(i).getNickName(), model.getData().getPlayers().get(i).getAvatar(), String.valueOf(model.getData().getPlayers().get(i).getTotalScore()), String.valueOf(model.getData().getPlayers().get(i).getUserId()));
            mList.add(model_battle);
        }
        mAdapter.setList(mList);


        for (int i = 0; i < model.getData().getSubject().getOption().size(); i++) {
            if (model.getData().getSubject().getOption().get(i).getRight()) {
                mAnswer = model.getData().getSubject().getOption().get(i).getId();
            }
        }

        tv_title.setText(model.getData().getSubject().getTitle());
        tv_answer_1.setText(model.getData().getSubject().getOption().get(0).getContent());
        tv_answer_2.setText(model.getData().getSubject().getOption().get(1).getContent());
        tv_answer_3.setText(model.getData().getSubject().getOption().get(2).getContent());
        tv_answer_4.setText(model.getData().getSubject().getOption().get(3).getContent());

        mAnswer_1 = model.getData().getSubject().getOption().get(0).getId();
        mAnswer_2 = model.getData().getSubject().getOption().get(1).getId();
        mAnswer_3 = model.getData().getSubject().getOption().get(2).getId();
        mAnswer_4 = model.getData().getSubject().getOption().get(3).getId();

        tv_answer_1.setClickable(true);
        tv_answer_2.setClickable(true);
        tv_answer_3.setClickable(true);
        tv_answer_4.setClickable(true);

        tv_answer_1.setBackgroundResource(R.drawable.rectangle_ffffff_50);
        tv_answer_2.setBackgroundResource(R.drawable.rectangle_ffffff_50);
        tv_answer_3.setBackgroundResource(R.drawable.rectangle_ffffff_50);
        tv_answer_4.setBackgroundResource(R.drawable.rectangle_ffffff_50);

    }
}

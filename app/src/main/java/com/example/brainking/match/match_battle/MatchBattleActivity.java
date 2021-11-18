package com.example.brainking.match.match_battle;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
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


import java.math.BigDecimal;


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
    @BindView(R.id.ll_view)
    LinearLayout ll_view;
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
    @BindView(R.id.view_left)
    View view_left;
    @BindView(R.id.rl_totalScore)
    RelativeLayout rl_totalScore;
    @BindView(R.id.tv_totalScoreLeft)
    TextView tv_totalScoreLeft;
    @BindView(R.id.tv_totalScoreRight)
    TextView tv_totalScoreRight;
    @BindView(R.id.view_left2)
    View view_left2;
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
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_fees)
    TextView tv_fees;
    @BindView(R.id.tv_num)
    TextView tv_num;


    private String mAnswer_1;
    private String mAnswer_2;
    private String mAnswer_3;
    private String mAnswer_4;

    private String mAnswer;
    private int leftTotal = 0;
    private int rightTotal = 0;

    private String mName;
    private String mImg;

    public MediaPlayer mediaPlayer;
    private SoundPool mSoundPool;
    private int mVoiceId_right;
    private int mVoiceId_error;
    private int mVoiceId_win;
    private int mVoiceId_lose;
    private int mVoiceId_equals;

    private String mType;
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
                    rl_time.setVisibility(View.GONE);
                    break;
                case 3:
                    if (leftTotal == 0) {
                        return;
                    }
                    if (rightTotal == 0 && leftTotal > 0) {
                        view_left.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 100f));
                        return;
                    }


                    view_left.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) div(leftTotal, (leftTotal + rightTotal), 2)));
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
        mVoiceId_win = mSoundPool.load(this, R.raw.win, 1);
        mVoiceId_lose = mSoundPool.load(this, R.raw.lose, 1);
        mVoiceId_equals = mSoundPool.load(this, R.raw.equals, 1);


        mRoomId = getIntent().getStringExtra("roomId");
        mName = getIntent().getStringExtra("name");
        mImg = getIntent().getStringExtra("img");
        mType = getIntent().getStringExtra("type");

        Glide.with(this).load(SpUtils.getInstance().getString("headImg")).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_left);
        tv_name_left.setText(SpUtils.getInstance().getString("name"));
        Glide.with(this).load(mImg).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_right);
        tv_name_right.setText(mName + "");

        if (mRoomId != null) {
            basePresenter.ready(mRoomId);
        }
        if (mType != null) {
            Log.d("xuwudi", "开始重连");
            basePresenter.reConnect();
        }

        tv_answer_1.setOnClickListener(this);
        tv_answer_2.setOnClickListener(this);
        tv_answer_3.setOnClickListener(this);
        tv_answer_4.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        rlBack.setOnClickListener(this);

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

    //停止播放
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ready(MatchStartEvent event) {
        String str = event.getMsg();

        //题目
        if ("subject".equals(new Gson().fromJson(str, MqttOptionModel.class).getType())) {
            MqttOptionModel optionModel = new Gson().fromJson(str, MqttOptionModel.class);
            tv_title.setText(optionModel.getTitle());
            tv_num.setText(optionModel.getCurrentIndex() + "/" + optionModel.getTotal());

            for (int i = 0; i < optionModel.getOption().size(); i++) {
                if (optionModel.getOption().get(i).isRight) {
                    mAnswer = optionModel.getOption().get(i).getId();
                }
            }

            mHandler.removeCallbacksAndMessages(null);
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
                leftTotal = Integer.parseInt(answerModel.getTotalScore());
                mHandler.sendEmptyMessage(3);
            } else {
                tv_score_right.setText(answerModel.getTotalScore() + "");
                rightTotal = Integer.parseInt(answerModel.getTotalScore());
                mHandler.sendEmptyMessage(3);
            }

        } else if ("FinalResult".equals(new Gson().fromJson(str, MqttResultModel.class).getType())) {
            //答题结果
            tv_title.setVisibility(View.INVISIBLE);
            ll_answer.setVisibility(View.GONE);
            ll_view.setVisibility(View.GONE);
            tv_score_right.setVisibility(View.GONE);
            tv_score_left.setVisibility(View.GONE);
            tv_num.setVisibility(View.GONE);
            mHandler.sendEmptyMessage(2);

            MqttResultModel mqttResultModel = new Gson().fromJson(str, MqttResultModel.class);
            mediaPlayer.stop();
            if (mqttResultModel.getResultType().equals("win")) {
                iv_result.setImageResource(R.mipmap.iv_win);
                mSoundPool.play(mVoiceId_win, 1, 1, 1, 0, 1);
            } else if (mqttResultModel.getResultType().equals("lose")) {
                iv_result.setImageResource(R.mipmap.iv_lose);
                mSoundPool.play(mVoiceId_lose, 1, 1, 1, 0, 1);
            } else {
                mSoundPool.play(mVoiceId_equals, 1, 1, 1, 0, 1);
                iv_result.setImageResource(R.mipmap.iv_equal);
            }
            tv_fees.setText(mqttResultModel.getAddScore() + "");
            tv_totalScoreLeft.setText(mqttResultModel.getPlayer().get(0).getScore() + "分");
            tv_totalScoreRight.setText(mqttResultModel.getPlayer().get(1).getScore() + "分");
            double a = Double.parseDouble(mqttResultModel.getPlayer().get(0).getScore());
            double b = Double.parseDouble(mqttResultModel.getPlayer().get(1).getScore());
            view_left2.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) div(a, (a + b), 2)));


            iv_result.setVisibility(View.VISIBLE);
            tv_next.setVisibility(View.VISIBLE);
            rl_totalScore.setVisibility(View.VISIBLE);

            //basePresenter.matchExit(mRoomId);
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
    public void mattchReadySuccess(MatchStartModel model) {
        // if (model.isData()) {
        Toast.makeText(this, model.getMsg(), Toast.LENGTH_SHORT).show();
        //  }
    }

    @Override
    public void fail(String err) {
        //Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void matchExitSuccess(MatchStartModel model) {
        //Toast.makeText(this, "退出房间", Toast.LENGTH_SHORT).show();
        //finish();
    }

    @Override
    public void matchAnswerSuccess(MatchAnswerModel matchAnswerModel) {
        //tv_score_left.setText(matchAnswerModel.getData().getTotalScore() + "");
    }

    @Override
    public void reConnectSuccess(ReConnectModel model) {
        //Log.d("xuwudi", "roomId====" + model.getData().getRoomId());
        MyMqttService.startService(this, SpUtils.getInstance().getString("userId"), model.getData().getRoomId());

        Glide.with(this).load(model.getData().getPlayers().get(0).getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_right);
        tv_name_right.setText(model.getData().getPlayers().get(0).getNickName() + "");

        tv_score_left.setText(model.getData().getPlayers().get(1).getTotalScore() + "");
        tv_score_right.setText(model.getData().getPlayers().get(0).getTotalScore() + "");
        tv_title.setText(model.getData().getSubject().getTitle());

        mHandler.removeCallbacksAndMessages(null);
        mTime = 10;
        mHandler.sendEmptyMessageDelayed(1, 1000);

        for (int i = 0; i < model.getData().getSubject().getOption().size(); i++) {
            if (model.getData().getSubject().getOption().get(i).getRight()) {
                mAnswer = model.getData().getSubject().getOption().get(i).getId();
            }
        }

        mRoomId = model.getData().getRoomId();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                basePresenter.matchExit(mRoomId);
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
            case R.id.tv_next:
                finish();
                break;
            default:
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            basePresenter.matchExit(mRoomId);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
    }


}

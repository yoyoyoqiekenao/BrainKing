package com.example.brainking.home.mathdetail;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.MathDetailModel;
import com.example.brainking.util.CountDownView;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MathDetailActivity extends BrainActivity<MathDetailPresenter> implements MathDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_judge)
    RelativeLayout rl_judge;
    @BindView(R.id.ll_completion)
    LinearLayout ll_completion;
    @BindView(R.id.ll_multiple)
    LinearLayout ll_multiple;
    @BindView(R.id.ll_single)
    LinearLayout ll_single;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    //填空题view
    @BindView(R.id.tv_0)
    TextView tv0;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_point)
    TextView tv_point;
    @BindView(R.id.rl_delete)
    RelativeLayout rl_delete;
    @BindView(R.id.tv_answer_completion)
    TextView tv_answer_completion;
    @BindView(R.id.tv_title_completion)
    TextView tv_title_completion;

    //判断题
    @BindView(R.id.tv_judge_a)
    TextView tv_judge_a;
    @BindView(R.id.tv_judge_b)
    TextView tv_judge_b;
    @BindView(R.id.tv_title_judge)
    TextView tv_title_judge;

    //单选题
    @BindView(R.id.tv_title_single)
    TextView tv_title_single;
    @BindView(R.id.tv_chooseA_single)
    TextView tv_chooseA_single;
    @BindView(R.id.tv_chooseB_single)
    TextView tv_chooseB_single;
    @BindView(R.id.tv_chooseC_single)
    TextView tv_chooseC_single;
    @BindView(R.id.tv_chooseD_single)
    TextView tv_chooseD_single;

    //多选题
    @BindView(R.id.tv_title_multiple)
    TextView tv_title_multiple;
    @BindView(R.id.tv_chooseA_multiple)
    TextView tv_chooseA_multiple;
    @BindView(R.id.tv_chooseB_multiple)
    TextView tv_chooseB_multiple;
    @BindView(R.id.tv_chooseC_multiple)
    TextView tv_chooseC_multiple;
    @BindView(R.id.tv_chooseD_multiple)
    TextView tv_chooseD_multiple;

    //答案解析
    @BindView(R.id.tv_analysis)
    TextView tv_analysis;
    @BindView(R.id.tv_isShow)
    TextView tvShow;

    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_time)
    CountDownView tv_time;
    @BindView(R.id.iv_isCollect)
    ImageView iv_isCollect;

    @BindView(R.id.tv_auto)
    TextView tv_auto;


    private SoundPool mSoundPool;
    private int mVoiceId_right;
    private int mVoiceId_error;


    private int mPid;

    //是否隐藏解析
    private boolean isHide = true;
    //当前题型
    private int mType;

    private List<String> completionList = new ArrayList<>();
    private String answer_complete;
    private boolean isAddPoint = true;

    //判断题参考答案
    private String mJudgeAnswer;
    //判断题自己选的答案
    private String mJudgeAnswer_;

    //单选题答案
    private String mSingleAnswer;
    //单选题自己选的答案
    private String mSingleAnswer_;

    //多选题答案
    private String mMultipleAnswer;
    //多选题自己选的答案
    private String mMultipleAnswer_;

    private boolean isCheckA = false;
    private boolean isCheckB = false;
    private boolean isCheckC = false;
    private boolean isCheckD = false;

    //填空题答案
    private String mCompletionAnswer;
    //填空题自己选的答案
    private String mCompletionAnswer_ = "";

    private boolean isAuto = false;

    private int mTime = 180;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (mTime > 0) {
                        tv_time.setText(mTime + "s");
                        mTime--;
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    } else if (mTime == 0) {
                        Toast.makeText(MathDetailActivity.this, "您已超时", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    mTime = 600;
                    mHandler.sendEmptyMessageDelayed(1, 1000);
                    break;
                default:
            }

        }
    };


    @Override
    protected MathDetailPresenter createPresenter() {
        return new MathDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathdetail);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();
        mPid = getIntent().getIntExtra("pid", -1);

        tv_time.initTime(10, 0);
        tv_time.setOnTimeCompleteListener(new CountDownView.OnTimeCompleteListener() {
            @Override
            public void onTimeComplete() {
                basePresenter.getMathDetail(mPid);
            }
        });
        basePresenter.getMathDetail(mPid);

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


        tvShow.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        tv_auto.setOnClickListener(this);
    }

    @Override
    public void getMathDetailSuccess(MathDetailModel model) {

        //mHandler.removeCallbacksAndMessages(null);
        //mHandler.sendEmptyMessage(2);
        tv_time.reStart();
        //每次刷新数据的时候都隐藏解析
        isHide = false;
        tv_analysis.setVisibility(View.GONE);
        tv_analysis.setText(model.getData().getAnalysis() + "");

        mType = model.getData().getType();

        if (1 == model.getData().getType()) {
            //判断
            showJudgmentView(model);
        } else if (2 == model.getData().getType()) {
            //单选
            showSingleChoose(model);
        } else if (3 == model.getData().getType()) {
            //多选
            showMultipleChoose(model);
        } else if (4 == model.getData().getType()) {
            //填空
            showCompletionView(model);
        }

        Log.d("xuwudi", "type====" + mType);

    }

    //展示多选题界面
    private void showMultipleChoose(MathDetailModel model) {
        ll_completion.setVisibility(View.GONE);
        ll_single.setVisibility(View.GONE);
        ll_multiple.setVisibility(View.VISIBLE);
        rl_judge.setVisibility(View.GONE);

        tv_title_multiple.setText(model.getData().getTitle());
        mMultipleAnswer = model.getData().getAnswer().getAnswer();

        tv_chooseA_multiple.setOnClickListener(this);
        tv_chooseB_multiple.setOnClickListener(this);
        tv_chooseC_multiple.setOnClickListener(this);
        tv_chooseD_multiple.setOnClickListener(this);

        isCheckA = false;
        isCheckB = false;
        isCheckC = false;
        isCheckD = false;
    }

    //展示单选题的界面
    private void showSingleChoose(MathDetailModel model) {
        ll_completion.setVisibility(View.GONE);
        ll_single.setVisibility(View.VISIBLE);
        ll_multiple.setVisibility(View.GONE);
        rl_judge.setVisibility(View.GONE);

        mSingleAnswer_ = model.getData().getAnswer().getAnswer();
        tv_chooseA_single.setText("A:" + model.getData().getAnswer().getSelected().get(0).getA());
        tv_chooseB_single.setText("B:" + model.getData().getAnswer().getSelected().get(1).getB());
        tv_chooseC_single.setText("C:" + model.getData().getAnswer().getSelected().get(2).getC());
        tv_chooseD_single.setText("D:" + model.getData().getAnswer().getSelected().get(3).getD());
        tv_title_single.setText(model.getData().getTitle());

        tv_chooseA_single.setOnClickListener(this);
        tv_chooseB_single.setOnClickListener(this);
        tv_chooseC_single.setOnClickListener(this);
        tv_chooseD_single.setOnClickListener(this);


    }

    //展示填空题的界面
    private void showCompletionView(MathDetailModel model) {
        ll_completion.setVisibility(View.VISIBLE);
        ll_single.setVisibility(View.GONE);
        ll_multiple.setVisibility(View.GONE);
        rl_judge.setVisibility(View.GONE);

        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv_point.setOnClickListener(this);
        rl_delete.setOnClickListener(this);
        iv_isCollect.setOnClickListener(this);

        mCompletionAnswer = model.getData().getAnswer().getAnswer();
        tv_title_completion.setText(model.getData().getTitle());


    }

    @Override
    public void getMathDetailFail(String msg) {

    }

    @Override
    public void collectSuccess() {

    }

    @Override
    public void collectFail(String err) {

    }

    //判断题
    private void showJudgmentView(MathDetailModel model) {
        rl_judge.setVisibility(View.VISIBLE);
        ll_multiple.setVisibility(View.GONE);
        ll_single.setVisibility(View.GONE);
        ll_completion.setVisibility(View.GONE);

        tv_title_judge.setText(model.getData().getTitle());

        mJudgeAnswer = model.getData().getAnswer().getAnswer();

        tv_judge_a.setOnClickListener(this);
        tv_judge_b.setOnClickListener(this);

        tv_judge_a.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
        tv_judge_b.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

        tv_judge_a.setTextColor(getResources().getColor(R.color.color_00AEE9));
        tv_judge_b.setTextColor(getResources().getColor(R.color.color_00AEE9));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_0) {
            mCompletionAnswer_ = mCompletionAnswer_ + "0";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_1) {
            mCompletionAnswer_ = mCompletionAnswer_ + "1";
            tv_answer_completion.setText(mCompletionAnswer_);

            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_2) {
            mCompletionAnswer_ = mCompletionAnswer_ + "2";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_3) {

            mCompletionAnswer_ = mCompletionAnswer_ + "3";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_4) {

            mCompletionAnswer_ = mCompletionAnswer_ + "4";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_5) {

            mCompletionAnswer_ = mCompletionAnswer_ + "5";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_6) {

            mCompletionAnswer_ = mCompletionAnswer_ + "6";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_7) {

            mCompletionAnswer_ = mCompletionAnswer_ + "7";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_8) {

            mCompletionAnswer_ = mCompletionAnswer_ + "8";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_9) {

            mCompletionAnswer_ = mCompletionAnswer_ + "9";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
        } else if (v.getId() == R.id.tv_point) {
            mCompletionAnswer_ = mCompletionAnswer_ + ".";
            tv_answer_completion.setText(mCompletionAnswer_);
            if (mCompletionAnswer_.length() == mCompletionAnswer.length()) {
                if (isAuto) {
                    nextMatch(mType);
                }
            }
            /*//首位不能是.
            if (completionList.size() == 0) {
                Toast.makeText(this, "首位不可以是.", Toast.LENGTH_SHORT);
                return;
            }
            completionList.add(".");

            for (int i = 0; i < completionList.size(); i++) {
                String s = completionList.get(i);
                answer_complete = answer_complete + s;
            }
            tv_answer_completion.setText(answer_complete + "");*/

        } else if (v.getId() == R.id.rl_delete) {
            mCompletionAnswer_ = "";
            tv_answer_completion.setText("");

        } else if (v.getId() == R.id.tv_isShow) {
            if (isHide == true) {
                tv_analysis.setVisibility(View.GONE);
                isHide = false;
            } else {
                tv_analysis.setVisibility(View.VISIBLE);
                isHide = true;
            }
        } else if (v.getId() == R.id.tv_next) {
            nextMatch(mType);
        } else if (v.getId() == R.id.tv_judge_a) {
            mJudgeAnswer_ = "A";
            tv_judge_a.setBackgroundResource(R.drawable.rectangle_00aee9_8);
            tv_judge_b.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

            tv_judge_a.setTextColor(getResources().getColor(R.color.color_ffffff));
            tv_judge_b.setTextColor(getResources().getColor(R.color.color_00AEE9));

            if (isAuto) {
                nextMatch(mType);
            }

        } else if (v.getId() == R.id.tv_judge_b) {
            mJudgeAnswer_ = "B";

            tv_judge_a.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_judge_b.setBackgroundResource(R.drawable.rectangle_00aee9_8);

            tv_judge_a.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_judge_b.setTextColor(getResources().getColor(R.color.color_ffffff));
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseA_single) {
            mSingleAnswer = "A";

            tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_00aee9_8);
            tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

            tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_ffffff));
            tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseB_single) {
            mSingleAnswer = "B";

            tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_00aee9_8);
            tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

            tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_ffffff));
            tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseC_single) {
            mSingleAnswer = "C";

            tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_00aee9_8);
            tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

            tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_ffffff));
            tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseD_single) {
            mSingleAnswer = "D";

            tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_00aee9_8);

            tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
            tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_ffffff));
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.rl_back) {
            finish();
        } else if (v.getId() == R.id.tv_chooseA_multiple) {
            if (isCheckA == false) {
                tv_chooseA_multiple.setBackgroundResource(R.drawable.rectangle_00aee9_8);
                tv_chooseA_multiple.setTextColor(getResources().getColor(R.color.color_ffffff));
                isCheckA = true;
            } else {
                tv_chooseA_multiple.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                tv_chooseA_multiple.setTextColor(getResources().getColor(R.color.color_00AEE9));
                isCheckA = false;
            }
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseB_multiple) {

            if (isCheckB == false) {
                tv_chooseB_multiple.setBackgroundResource(R.drawable.rectangle_00aee9_8);
                tv_chooseB_multiple.setTextColor(getResources().getColor(R.color.color_ffffff));
                isCheckB = true;
            } else {
                tv_chooseB_multiple.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                tv_chooseB_multiple.setTextColor(getResources().getColor(R.color.color_00AEE9));
                isCheckB = false;
            }
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseC_multiple) {
            if (isCheckC == false) {
                tv_chooseC_multiple.setBackgroundResource(R.drawable.rectangle_00aee9_8);
                tv_chooseC_multiple.setTextColor(getResources().getColor(R.color.color_ffffff));
                isCheckC = true;
            } else {
                tv_chooseC_multiple.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                tv_chooseC_multiple.setTextColor(getResources().getColor(R.color.color_00AEE9));
                isCheckC = false;
            }
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.tv_chooseB_multiple) {
            if (isCheckD == false) {
                tv_chooseD_multiple.setBackgroundResource(R.drawable.rectangle_00aee9_8);
                tv_chooseD_multiple.setTextColor(getResources().getColor(R.color.color_ffffff));
                isCheckD = true;
            } else {
                tv_chooseD_multiple.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                tv_chooseD_multiple.setTextColor(getResources().getColor(R.color.color_00AEE9));
                isCheckD = false;
            }
            if (isAuto) {
                nextMatch(mType);
            }
        } else if (v.getId() == R.id.iv_isCollect) {

        } else if (v.getId() == R.id.tv_auto) {
            if (isAuto) {
                isAuto = false;
                tv_auto.setText("手动模式");
                tv_auto.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
            } else {
                isAuto = true;
                tv_auto.setText("自动模式");
                tv_auto.setBackgroundResource(R.drawable.rectangle_00aee9_8);
            }

        }
    }

    private void nextMatch(int type) {
        switch (type) {
            case 1:
                //判断题
                if (mJudgeAnswer_.equals(mJudgeAnswer)) {
                    Toast toast = Toast.makeText(this, "答题正确", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    mJudgeAnswer_ = "";
                    mJudgeAnswer = "";
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                } else {
                    mJudgeAnswer_ = "";
                    mJudgeAnswer = "";
                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                    //Toast.makeText(this, "答题错误，请查看答案解析", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                //单选题
                if (mSingleAnswer.equals(mSingleAnswer_)) {
                    Toast toast = Toast.makeText(this, "答题正确", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    mSingleAnswer_ = "";
                    mSingleAnswer = "";
                    tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

                    tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                } else {
                    mSingleAnswer_ = "";
                    mSingleAnswer = "";
                    tv_chooseA_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseB_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseC_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);
                    tv_chooseD_single.setBackgroundResource(R.drawable.rectangle_e1f7ff_8);

                    tv_chooseA_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseB_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseC_single.setTextColor(getResources().getColor(R.color.color_00AEE9));
                    tv_chooseD_single.setTextColor(getResources().getColor(R.color.color_00AEE9));

                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                    //Toast.makeText(this, "答题错误，请查看答案解析", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                //多选题
                Log.d("xuwudi", "答案====" + mMultipleAnswer_.toString());
                basePresenter.getMathDetail(mPid);
                break;
            case 4:
                //填空题
                if (mCompletionAnswer.equals(mCompletionAnswer_)) {
                    Toast toast = Toast.makeText(this, "答题正确", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    mCompletionAnswer_ = "";
                    mCompletionAnswer = "";
                    tv_answer_completion.setText("");
                    mSoundPool.play(mVoiceId_right, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                } else {
                    mCompletionAnswer_ = "";
                    mCompletionAnswer = "";
                    tv_answer_completion.setText("");

                    mSoundPool.play(mVoiceId_error, 1, 1, 1, 0, 1);
                    basePresenter.getMathDetail(mPid);
                    //Toast.makeText(this, "答题错误，请查看答案解析", Toast.LENGTH_SHORT).show();
                }
                break;
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

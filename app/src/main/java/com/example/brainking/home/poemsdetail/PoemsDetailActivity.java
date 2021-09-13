package com.example.brainking.home.poemsdetail;

import android.animation.ObjectAnimator;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.PoemListModel;
import com.example.brainking.model.PoemsDetailModel;
import com.gyf.immersionbar.ImmersionBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoemsDetailActivity extends BrainActivity<PoemsDetailPresenter> implements PoemsDetailView, View.OnClickListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_author)
    TextView tv_author;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_annotation)
    TextView tv_annotation;
    @BindView(R.id.tv_translation)
    TextView tv_translation;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.iv_isCollect)
    ImageView iv_isCollect;
    @BindView(R.id.iv_musicNote)
    ImageView iv_musicNote;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.iv_isPlay)
    ImageView iv_isPlay;

    private int mPid;
    private String mAudioUrl;

    // 媒体播放器
    public MediaPlayer mediaPlayer;
    private boolean isPause = false;

    private float x1, x2, y1, y2;

    //是否收藏
    private boolean isCollect;
    //当前诗词Id
    private int mId;

    //旋转动画
    private ObjectAnimator mRotation;
    //是否旋转
    private boolean isRotation;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    iv_isPlay.setImageResource(R.mipmap.iv_poem_stop);
                    break;
                case 1:
                    iv_isPlay.setImageResource(R.mipmap.iv_poem_play);
                    break;
                default:
            }
        }
    };

    @Override
    protected PoemsDetailPresenter createPresenter() {
        return new PoemsDetailPresenter(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poemsdetail);
        ImmersionBar.with(this).statusBarView(mView).init();
        ButterKnife.bind(this);

        mRotation = ObjectAnimator.ofFloat(iv_musicNote, "rotation", 0f, 360f);

        mPid = getIntent().getIntExtra("pid", 0);

        rlBack.setOnClickListener(this);
        iv_isCollect.setOnClickListener(this);
        iv_musicNote.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
        iv_isPlay.setOnClickListener(this);

        //暂时使用pid=11
        //basePresenter.getPoemsDetail(mPid);
        basePresenter.getPoemsList(mPid);
        basePresenter.getPoemsDetail(11);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开时
                    x2 = event.getX();
                    y2 = event.getY();
                    if (y1 - y2 > 50) {
                        //Log.d("xuwudi", "向上滑动");
                    } else if (y2 - y1 > 50) {
                        //     Log.d("xuwudi", "向下滑动");
                    } else if (x1 - x2 > 50) {
                        basePresenter.getPoemsDetail(11);
                        Log.d("xuwudi", "向左滑动");
                    } else if (x2 - x1 > 50) {
                        basePresenter.getPoemsDetail(11);
                        Log.d("xuwudi", "向右滑动");
                    }
                }
                return false;
            }
        });


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        } else if (v.getId() == R.id.iv_isCollect) {
            basePresenter.collectPoem(mId);
        } else if (v.getId() == R.id.iv_musicNote) {
            if (isRotation) {
                isRotation = false;
                mRotation.end();
                stop();
            } else {
                isRotation = true;
                rePlay();
                mRotation.setRepeatCount(ObjectAnimator.INFINITE);
                mRotation.setInterpolator(new LinearInterpolator());
                mRotation.setDuration(5000);
                mRotation.start();
            }

        } else if (v.getId() == R.id.iv_left) {
            basePresenter.getPoemsDetail(11);
        } else if (v.getId() == R.id.iv_right) {
            basePresenter.getPoemsDetail(11);
        } else if (v.getId() == R.id.iv_isPlay) {
            pause();
        }
    }

    @Override
    public void getPoemsDetailSuccess(PoemsDetailModel model) {
        if (mediaPlayer != null) {
            stop();
        }
        isPause = false;
        iv_isPlay.setImageResource(R.mipmap.iv_poem_stop);
        isRotation = true;
        mId = model.getData().getId();
        isCollect = model.getData().isCollect();
        if (isCollect == true) {
            iv_isCollect.setImageResource(R.mipmap.iv_collect_true);
        } else {
            iv_isCollect.setImageResource(R.mipmap.iv_collect_false);
        }

        tv_title.setText(model.getData().getTitle());
        tv_author.setText(model.getData().getDynasty() + "  " + model.getData().getAuthor());
        tv_content.setText(model.getData().getContent());
        tv_annotation.setText(model.getData().getAnnotation());
        tv_translation.setText(model.getData().getTranslation());

        mAudioUrl = model.getData().getAudioUrl();

        //获取数据成功后自动播放当前音频文件
        mRotation.setRepeatCount(ObjectAnimator.INFINITE);
        mRotation.setInterpolator(new LinearInterpolator());
        mRotation.setDuration(5000);
        mRotation.start();


        play();
    }

    @Override
    public void getPoemsDetailFail(String err) {
        Toast.makeText(PoemsDetailActivity.this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void collectSuccess() {
        if (isCollect == false) {
            iv_isCollect.setImageResource(R.mipmap.iv_collect_true);
            isCollect = true;
        } else {
            iv_isCollect.setImageResource(R.mipmap.iv_collect_false);
            isCollect = false;
        }
    }

    @Override
    public void collectFail(String err) {

    }

    @Override
    public void getPoemListSuccess(PoemListModel model) {

    }

    @Override
    public void getPoemListFail(String err) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.d("xuwudi", "缓存更新中==" + percent);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    //停止播放
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    //重新播放
    private void rePlay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            return;
        }
        play();
    }

    private void play() {

        mediaPlayer = new MediaPlayer();
        // 设置媒体流类型
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置循环播放
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnPreparedListener(this);

        if (!TextUtils.isEmpty(mAudioUrl)) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(mAudioUrl);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "当前音频文件为空", Toast.LENGTH_SHORT).show();
        }


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

    /**
     * 暂停
     */
    protected void pause() {
        if (isPause == false) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                isPause = true;
                mHandler.sendEmptyMessage(1);
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.start();
                isPause = false;
                mHandler.sendEmptyMessage(0);
            }
        }


    }

}

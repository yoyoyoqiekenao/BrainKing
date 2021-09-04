package com.example.brainking.mine.collect.collect_poen;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.CollectDetailModel;
import com.gyf.immersionbar.ImmersionBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectPoemDetailActivity extends BrainActivity<CollectPoemDetailPresenter> implements CollectPoemDetailView, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, View.OnClickListener {

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
    @BindView(R.id.iv_play)
    ImageView iv_play;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private String mSubjectId;

    private String mAudioUrl;
    // 媒体播放器
    public MediaPlayer mediaPlayer;
    private boolean isPause = false;

    @Override
    protected CollectPoemDetailPresenter createPresenter() {
        return new CollectPoemDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_poem_detail);
        ImmersionBar.with(this).statusBarView(mView).init();
        ButterKnife.bind(this);

        mSubjectId = getIntent().getStringExtra("subjectId");

        getCollectDetail(mSubjectId);

        rlBack.setOnClickListener(this);
    }

    private void getCollectDetail(String id) {
        basePresenter.getCollectDetail(id, "2");
    }

    @Override
    public void getDetailSuccess(CollectDetailModel model) {
        if (model != null && model.getData() != null) {
            tv_title.setText(model.getData().getTitle());
            tv_author.setText(model.getData().getAuthor());
            tv_content.setText(model.getData().getContent());
            tv_annotation.setText(model.getData().getAnnotation());
            tv_translation.setText(model.getData().getTranslation());

            mAudioUrl = model.getData().getAudioUrl();
            //获取数据成功后自动播放当前音频文件
            play();
        }
    }

    @Override
    public void fail(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
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

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
        }
    }
}

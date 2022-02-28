package com.example.brainking;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.brainking.adapter.ExamplePagerAdapter;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battle.BattleFragment;
import com.example.brainking.home.home.HomeFragment;
import com.example.brainking.match.match.MatchFragment;
import com.example.brainking.mine.mine.MineFragment;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.news.news.NewsFragment;
import com.example.brainking.util.SpUtils;
import com.example.brainking.views.NoScrollViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import razerdp.basepopup.BasePopupWindow;

public class MainActivity extends BrainActivity<MainPresenter> implements MainView, View.OnClickListener {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.viewpager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.rl_home)
    RelativeLayout rl_home;
    @BindView(R.id.rl_match)
    RelativeLayout rl_match;
    @BindView(R.id.rl_battle)
    RelativeLayout rl_battle;
    @BindView(R.id.rl_news)
    RelativeLayout rl_news;
    @BindView(R.id.rl_mine)
    RelativeLayout rl_mine;

    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.tv_match)
    TextView tv_match;
    @BindView(R.id.iv_match)
    ImageView iv_match;
    @BindView(R.id.tv_battle)
    TextView tv_battle;
    @BindView(R.id.iv_battle)
    ImageView iv_battle;
    @BindView(R.id.tv_news)
    TextView tv_news;
    @BindView(R.id.iv_news)
    ImageView iv_news;
    @BindView(R.id.tv_mine)
    TextView tv_mine;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;

    @BindView(R.id.rootView)
    RelativeLayout rootView;



    private static final String[] CHANNELS = new String[]{"首页", "晋级赛", "对战大厅", "消息", "我的"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ExamplePagerAdapter mExamplePagerAdapter;
    private List<Fragment> mList = new ArrayList<>();
    private static final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int BASIC_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        initMagicIndicator();

        createPresenter().getUserInfo();
    }



    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    private void initMagicIndicator() {
        mList.add(new HomeFragment());
        mList.add(new MatchFragment());
        mList.add(new BattleFragment());
        mList.add(new NewsFragment());
        mList.add(new MineFragment());

        mExamplePagerAdapter = new ExamplePagerAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mExamplePagerAdapter);
        mViewPager.setOffscreenPageLimit(5);

        mViewPager.setCanScroll(false);

        rl_home.setOnClickListener(this);
        rl_match.setOnClickListener(this);
        rl_battle.setOnClickListener(this);
        rl_news.setOnClickListener(this);
        rl_mine.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_home) {

            tv_home.setTextColor(getResources().getColor(R.color.color_3B4DD0));
            tv_match.setTextColor(getResources().getColor(R.color.color_333333));
            tv_battle.setTextColor(getResources().getColor(R.color.color_333333));
            tv_news.setTextColor(getResources().getColor(R.color.color_333333));
            tv_mine.setTextColor(getResources().getColor(R.color.color_333333));

            iv_home.setImageResource(R.mipmap.iv_home_select);
            iv_match.setImageResource(R.mipmap.iv_match_unselect);
            iv_battle.setImageResource(R.mipmap.iv_battle_unselect);
            iv_news.setImageResource(R.mipmap.iv_news_unselect);
            iv_mine.setImageResource(R.mipmap.iv_mine_unselect);

            mViewPager.setCurrentItem(0);
        } else if (v.getId() == R.id.rl_match) {
            tv_home.setTextColor(getResources().getColor(R.color.color_333333));
            tv_match.setTextColor(getResources().getColor(R.color.color_3B4DD0));
            tv_battle.setTextColor(getResources().getColor(R.color.color_333333));
            tv_news.setTextColor(getResources().getColor(R.color.color_333333));
            tv_mine.setTextColor(getResources().getColor(R.color.color_333333));

            iv_home.setImageResource(R.mipmap.iv_home_unselect);
            iv_match.setImageResource(R.mipmap.iv_match_select);
            iv_battle.setImageResource(R.mipmap.iv_battle_unselect);
            iv_news.setImageResource(R.mipmap.iv_news_unselect);
            iv_mine.setImageResource(R.mipmap.iv_mine_unselect);
            mViewPager.setCurrentItem(1);
        } else if (v.getId() == R.id.rl_battle) {
            tv_home.setTextColor(getResources().getColor(R.color.color_333333));
            tv_match.setTextColor(getResources().getColor(R.color.color_333333));
            tv_battle.setTextColor(getResources().getColor(R.color.color_3B4DD0));
            tv_news.setTextColor(getResources().getColor(R.color.color_333333));
            tv_mine.setTextColor(getResources().getColor(R.color.color_333333));

            iv_home.setImageResource(R.mipmap.iv_home_unselect);
            iv_match.setImageResource(R.mipmap.iv_match_unselect);
            iv_battle.setImageResource(R.mipmap.iv_battle_select);
            iv_news.setImageResource(R.mipmap.iv_news_unselect);
            iv_mine.setImageResource(R.mipmap.iv_mine_unselect);
            mViewPager.setCurrentItem(2);
        } else if (v.getId() == R.id.rl_news) {
            tv_home.setTextColor(getResources().getColor(R.color.color_333333));
            tv_match.setTextColor(getResources().getColor(R.color.color_333333));
            tv_battle.setTextColor(getResources().getColor(R.color.color_333333));
            tv_news.setTextColor(getResources().getColor(R.color.color_3B4DD0));
            tv_mine.setTextColor(getResources().getColor(R.color.color_333333));

            iv_home.setImageResource(R.mipmap.iv_home_unselect);
            iv_match.setImageResource(R.mipmap.iv_match_unselect);
            iv_battle.setImageResource(R.mipmap.iv_battle_unselect);
            iv_news.setImageResource(R.mipmap.iv_news_select);
            iv_mine.setImageResource(R.mipmap.iv_mine_unselect);
            mViewPager.setCurrentItem(3);
        } else if (v.getId() == R.id.rl_mine) {
            tv_home.setTextColor(getResources().getColor(R.color.color_333333));
            tv_match.setTextColor(getResources().getColor(R.color.color_333333));
            tv_battle.setTextColor(getResources().getColor(R.color.color_333333));
            tv_news.setTextColor(getResources().getColor(R.color.color_333333));
            tv_mine.setTextColor(getResources().getColor(R.color.color_3B4DD0));

            iv_home.setImageResource(R.mipmap.iv_home_unselect);
            iv_match.setImageResource(R.mipmap.iv_match_unselect);
            iv_battle.setImageResource(R.mipmap.iv_battle_unselect);
            iv_news.setImageResource(R.mipmap.iv_news_unselect);
            iv_mine.setImageResource(R.mipmap.iv_mine_select);
            mViewPager.setCurrentItem(4);
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoModel model) {
        SpUtils.getInstance().putString("name", model.getData().getNickName());
        SpUtils.getInstance().putString("headImg", model.getData().getAvatar());

        //MyMqttService.startService(this, SpUtils.getInstance().getString("userId"));
    }

    @Override
    public void getUserInfoFail(String msg) {

    }
}
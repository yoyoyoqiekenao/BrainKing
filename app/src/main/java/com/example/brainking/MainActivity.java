package com.example.brainking;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.brainking.adapter.ExamplePagerAdapter;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battle.BattleFragment;
import com.example.brainking.home.home.HomeFragment;
import com.example.brainking.match.match.MatchFragment;
import com.example.brainking.mine.mine.MineFragment;
import com.example.brainking.news.news.NewsFragment;
import com.example.brainking.views.NoScrollViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BrainActivity implements View.OnClickListener {

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

    private static final String[] CHANNELS = new String[]{"首页", "晋级赛", "对战大厅", "消息", "我的"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ExamplePagerAdapter mExamplePagerAdapter;
    private List<Fragment> mList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initMagicIndicator();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        /*mMagicIndicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final ImageView titleImg = (ImageView) customLayout.findViewById(R.id.title_img);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                //titleImg.setImageResource(R.mipmap.ic_launcher);
                titleText.setText(mDataList.get(index));
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(Color.parseColor("#3B4DD0"));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Color.BLACK);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.iv_home_unselect);
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.iv_match_unselect);
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.iv_battle_unselect);
                                break;
                            case 3:
                                titleImg.setImageResource(R.mipmap.iv_news_unselect);
                                break;
                            case 4:
                                titleImg.setImageResource(R.mipmap.iv_mine_unselect);
                                break;
                            default:
                        }
                       // titleImg.setScaleX(1.3f + (0.8f - 1.3f) * leavePercent);
                       // titleImg.setScaleY(1.3f + (0.8f - 1.3f) * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        switch (index) {
                            case 0:
                                titleImg.setImageResource(R.mipmap.iv_home_select);
                                break;
                            case 1:
                                titleImg.setImageResource(R.mipmap.iv_match_select);
                                break;
                            case 2:
                                titleImg.setImageResource(R.mipmap.iv_battle_select);
                                break;
                            case 3:
                                titleImg.setImageResource(R.mipmap.iv_news_select);
                                break;
                            case 4:
                                titleImg.setImageResource(R.mipmap.iv_mine_select);
                                break;
                        }

                        titleImg.setScaleX(0.8f + (1.3f - 0.8f) * enterPercent);
                        titleImg.setScaleY(0.8f + (1.3f - 0.8f) * enterPercent);
                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);*/
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
}
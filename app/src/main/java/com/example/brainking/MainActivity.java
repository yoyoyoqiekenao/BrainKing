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

public class MainActivity extends BrainActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

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
        mMagicIndicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
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
                        titleImg.setScaleX(1.3f + (0.8f - 1.3f) * leavePercent);
                        titleImg.setScaleY(1.3f + (0.8f - 1.3f) * leavePercent);
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
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }


}
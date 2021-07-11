package com.example.brainking.match.match_detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchDetailActivity extends BrainActivity<MatchDetailPresenter> implements MatchDetailView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;

    private ObjectAnimator mObjectAnimator_1, mObjectAnimator_2;


    private Handler mHandler = new Handler();

    @Override
    protected MatchDetailPresenter createPresenter() {
        return new MatchDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchdetail);
        ImmersionBar.with(this).statusBarView(mView).init();
        ButterKnife.bind(this);


        rlBack.setOnClickListener(this);
        mObjectAnimator_1 = ObjectAnimator.ofFloat(iv_left, "alpha", 0f, 1f);
        mObjectAnimator_1.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(mObjectAnimator_1);
        mObjectAnimator_1.start();


        mObjectAnimator_2 = ObjectAnimator.ofFloat(iv_right, "alpha", 0f, 1f);
        mObjectAnimator_2.setDuration(2000);
        AnimatorSet animatorSet_2 = new AnimatorSet();
        animatorSet_2.playTogether(mObjectAnimator_2);
        mObjectAnimator_2.start();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        }
    }
}

package com.example.brainking.match.match;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.mathdetail.MathDetailActivity;
import com.example.brainking.match.match_detail.MatchDetailActivity;
import com.example.brainking.model.MatchScoreModel;
import com.gyf.immersionbar.ImmersionBar;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends BrainFragment<MatchPresenter> implements MatchView, View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_math)
    RelativeLayout rl_math;
    @BindView(R.id.rl_poem)
    RelativeLayout rl_poem;
    @BindView(R.id.rl_idiom)
    RelativeLayout rl_idiom;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.view_score)
    View view_score;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_match, null);
        initView(view);
        return view;
    }


    protected void initView(View view) {
        ButterKnife.bind(this, view);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_idiom.setOnClickListener(this);
        rl_math.setOnClickListener(this);
        rl_poem.setOnClickListener(this);
        rl_time.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        createPresenter().getBattleFee();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_idiom) {
            Intent intent = new Intent(getContext(), MatchDetailActivity.class);
            intent.putExtra("id", "3");
            startActivity(intent);

        } else if (v.getId() == R.id.rl_math) {
            Intent intent = new Intent(getContext(), MatchDetailActivity.class);
            intent.putExtra("id", "1");
            startActivity(intent);
        } else if (v.getId() == R.id.rl_poem) {
            Intent intent = new Intent(getContext(), MatchDetailActivity.class);
            intent.putExtra("id", "2");
            startActivity(intent);
        } else if (v.getId() == R.id.rl_time) {
            Intent intent = new Intent(getContext(), MatchDetailActivity.class);
            intent.putExtra("id", "4");
            startActivity(intent);
        }
    }

    @Override
    public void getScoreSuccess(MatchScoreModel model) {
        tv_score.setText(model.getData().getScore() + "");

        view_score.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,
                (float) div(Double.parseDouble(model.getData().getScore()), 100, 2)));
    }

    @Override
    public void fail(String err) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected MatchPresenter createPresenter() {
        return new MatchPresenter(this);
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
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue() * 10;
    }
}

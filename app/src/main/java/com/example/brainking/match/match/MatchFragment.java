package com.example.brainking.match.match;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.mathdetail.MathDetailActivity;
import com.example.brainking.match.match_detail.MatchDetailActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends BrainFragment implements View.OnClickListener {
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


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
    public void onClick(View v) {
        if (v.getId() == R.id.rl_idiom) {
            startActivity(new Intent(getContext(), MatchDetailActivity.class));
        } else if (v.getId() == R.id.rl_math) {
            startActivity(new Intent(getContext(), MatchDetailActivity.class));
        } else if (v.getId() == R.id.rl_poem) {
            startActivity(new Intent(getContext(), MatchDetailActivity.class));
        } else if (v.getId() == R.id.rl_time) {
            startActivity(new Intent(getContext(), MatchDetailActivity.class));
        }
    }
}

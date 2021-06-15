package com.example.brainking.match.match;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchFragment extends BrainFragment {
    @BindView(R.id.view)
    View mView;

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
    }


}

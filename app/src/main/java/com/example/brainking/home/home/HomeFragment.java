package com.example.brainking.home.home;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.poems.PomesActivity;
import com.gyf.immersionbar.ImmersionBar;


import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BrainFragment implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_language)
    RelativeLayout rl_language;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.rl_poems)
    RelativeLayout rl_poems;

    private PopupWindow mPop;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }


    protected void initView(View view) {
        ButterKnife.bind(this, view);
        ImmersionBar.with(getActivity()).statusBarView(mView).init();

        rl_language.setOnClickListener(this);
        rl_poems.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_language) {
            showPop();
        } else if (view.getId() == R.id.rl_poems) {
            startActivity(new Intent(getContext(), PomesActivity.class));
        }
    }

    private void showPop() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_grade, null);
        mPop = new PopupWindow(getContext());
        mPop.setContentView(view);
        mPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置PopupWindow的背景
        mPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPop.setOutsideTouchable(true);
        mPop.setTouchable(true);
        mPop.setFocusable(true); // pop 显示时， 不让外部 view 响应点击事件

        mPop.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }
}

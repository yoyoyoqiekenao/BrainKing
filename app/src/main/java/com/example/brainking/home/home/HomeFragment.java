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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.brainking.R;
import com.example.brainking.adapter.LearnListAdapter_math;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.mathdetail.MathDetailActivity;
import com.example.brainking.home.poems.PoemsActivity;
import com.example.brainking.model.LearnListModel;
import com.gyf.immersionbar.ImmersionBar;


import butterknife.BindView;
import butterknife.ButterKnife;

//首页
public class HomeFragment extends BrainFragment<HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_math)
    RelativeLayout rl_math;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.rl_poems)
    RelativeLayout rl_poems;

    private PopupWindow mPop;
    private LearnListAdapter_math mAdapter_match;


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
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

        rl_math.setOnClickListener(this);
        rl_poems.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_math) {
            mvpPresenter.getLearnList_math();
        } else if (view.getId() == R.id.rl_poems) {
            startActivity(new Intent(getContext(), PoemsActivity.class));
        }
    }


    @Override
    public void getLearnListSuccess(LearnListModel model) {

        mAdapter_match = new LearnListAdapter_math();
        mAdapter_match.setNewData(model.getData());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_grade, null);
        mPop = new PopupWindow(getContext());
        mPop.setContentView(view);
        mPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = view.findViewById(R.id.rc_math);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter_match);

        mAdapter_match.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPop.dismiss();
                Intent intent = new Intent(getContext(), MathDetailActivity.class);
                intent.putExtra("pid", model.getData().get(position).getPid());
                startActivity(intent);
            }
        });

        // 设置PopupWindow的背景
        mPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPop.setOutsideTouchable(true);
        mPop.setTouchable(true);
        mPop.setFocusable(true); // pop 显示时， 不让外部 view 响应点击事件

        mPop.showAtLocation(rootView, Gravity.CENTER, 0, 0);


    }

    @Override
    public void getLearnListFail(String msg) {
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

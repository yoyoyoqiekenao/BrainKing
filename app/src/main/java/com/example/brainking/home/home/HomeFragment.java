package com.example.brainking.home.home;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.adapter.LearnListAdapter_math;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.mathdetail.MathDetailActivity;
import com.example.brainking.home.poems.PoemsActivity;
import com.example.brainking.home.search.SearchActivity;
import com.example.brainking.home.userinfo.UserInfoActivity;
import com.example.brainking.login.LoginActivity;
import com.example.brainking.mine.playabout.PlayAboutActivity;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.util.SpUtils;
import com.example.brainking.views.CountDownView;
import com.gyf.immersionbar.ImmersionBar;


import java.math.BigDecimal;

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
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.iv_update)
    ImageView iv_update;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.iv_edit)
    ImageView iv_edit;
    @BindView(R.id.rl_playAbout)
    RelativeLayout rl_playAbout;
    @BindView(R.id.tv_math)
    TextView tv_math;
    @BindView(R.id.tv_poem)
    TextView tv_poem;
    @BindView(R.id.tv_fighting)
    TextView tv_fighting;
    @BindView(R.id.view_fighting)
    View view_fighting;


    private PopupWindow mPop;
    private LearnListAdapter_math mAdapter_match;

    private String mImg;
    private String mName;
    private String mRemark;

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
        iv_update.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
        iv_edit.setOnClickListener(this);
        rl_playAbout.setOnClickListener(this);

        AssetManager mgr = getContext().getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/type_1.ttf");
        tv_math.setTypeface(tf);
        tv_poem.setTypeface(tf);

        //createPresenter().getUserInfo();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_math) {
            mvpPresenter.getLearnList_math();
        } else if (view.getId() == R.id.rl_poems) {
            startActivity(new Intent(getContext(), PoemsActivity.class));
        } else if (view.getId() == R.id.iv_update) {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra("img", mImg);
            intent.putExtra("name", mName);
            intent.putExtra("remark", mRemark);
            startActivity(intent);
        } else if (view.getId() == R.id.rl_search) {
            startActivity(new Intent(getContext(), SearchActivity.class));
        } else if (view.getId() == R.id.iv_edit) {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra("img", mImg);
            intent.putExtra("name", mName);
            intent.putExtra("remark", mRemark);
            startActivity(intent);
        } else if (view.getId() == R.id.rl_playAbout) {
            startActivity(new Intent(getContext(), PlayAboutActivity.class));
        }
    }


    @Override
    public void getLearnListSuccess(LearnListModel model) {

        mAdapter_match = new LearnListAdapter_math();
        mAdapter_match.setList(model.getData());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_grade, null);
        mPop = new PopupWindow(getContext());
        mPop.setContentView(view);
        mPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = view.findViewById(R.id.rc_math);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter_match);

        mAdapter_match.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPop.dismiss();
                Intent intent = new Intent(getContext(), MathDetailActivity.class);
                intent.putExtra("pid", model.getData().get(position).getId());
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
    public void getUserInfoSuccess(UserInfoModel model) {
        mImg = model.getData().getAvatar();
        mName = model.getData().getNickName();
        mRemark = model.getData().getRemark();


        Glide.with(getContext()).load(model.getData().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        tv_name.setText(model.getData().getNickName());
        tv_remark.setText(model.getData().getRemark());
        tv_fighting.setText(model.getData().getFightinga() + "/" + model.getData().getFightingb());
        view_fighting.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) div(Double.valueOf(model.getData().getFightinga()), Double.valueOf(model.getData().getFightingb()), 2)));
    }

    @Override
    public void getUserInfoFail(String msg) {

    }

    @Override
    public void goReLogin() {
        SpUtils.getInstance().clear();
        MyMqttService.stopService(getContext());
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        createPresenter().getUserInfo();
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

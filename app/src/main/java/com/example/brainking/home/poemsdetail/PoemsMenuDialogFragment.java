package com.example.brainking.home.poemsdetail;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.PoemMenuAdapter;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainDialogFragment;
import com.example.brainking.model.PoemListModel;
import com.example.brainking.util.NavigationUtil;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoemsMenuDialogFragment extends BrainDialogFragment<PoemsMenuPresenter> implements PoemsMenuView {

    @BindView(R.id.smartLayout)
    SmartRefreshLayout smartLayout;
    @BindView(R.id.rc_menu)
    RecyclerView rc_menu;

    private int mIndex;
    private int mPage;
    private int mPid;
    private List<PoemListModel.RowsDTO> mList = new ArrayList<>();
    private PoemMenuAdapter mAdapter;
    private PoemMenuListener menuListener;


    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //去掉dialog的标题，需要在setContentView()之前
        this.getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getDialog().getWindow();
        //去掉dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);

        View view = inflater.inflate(R.layout.dialog_poem_list, container);
        ButterKnife.bind(this, view);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                //.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .navigationBarColor(R.color.white)
                .init();

        mList = (List<PoemListModel.RowsDTO>) getArguments().getSerializable("list");
        mIndex = getArguments().getInt("index");
        mPid = getArguments().getInt("pid");
        mPage = getArguments().getInt("page");

        ClassicsFooter classicsFooter = new ClassicsFooter(getContext());
        smartLayout.setRefreshFooter(classicsFooter);
        smartLayout.setEnableRefresh(false);
        smartLayout.setEnableLoadMore(true);

        mAdapter = new PoemMenuAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_menu.setLayoutManager(manager);
        rc_menu.setAdapter(mAdapter);
        mAdapter.setList(mList);
        mAdapter.setIndex(mIndex);
        rc_menu.scrollToPosition(mIndex);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                mAdapter.setIndex(position);
                mIndex = position;
                menuListener.menuSelect(mList, mIndex, mPage);
            }
        });

        smartLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                mPage = mPage + 1;
                createPresenter().getPoemsList(mPid, mPage);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = NavigationUtil.getScreenHeith(getContext()) / 7 * 3;
        lp.gravity = Gravity.BOTTOM;
        //设置dialog的动画
        lp.windowAnimations = R.style.BottomDialogAnimation;
        getDialog().getWindow().setAttributes(lp);

        super.onResume();
    }


    @Override
    public void getPoemListSuccess(PoemListModel model) {
        smartLayout.finishLoadMore();
        mList.addAll(model.getRows());
        mAdapter.setList(mList);
    }

    @Override
    public void getPoemListFail(String err) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected PoemsMenuPresenter createPresenter() {
        return new PoemsMenuPresenter(this);
    }

    public interface PoemMenuListener {
        void menuSelect(List<PoemListModel.RowsDTO> list, int index, int page);
    }

    public void setPoemMenuSelect(PoemMenuListener listener) {
        menuListener = listener;
    }
}

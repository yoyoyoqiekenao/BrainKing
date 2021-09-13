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

import com.example.brainking.R;
import com.example.brainking.util.NavigationUtil;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;

public class PoemsMenuDialogFragment extends DialogFragment {
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
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .navigationBarColor(R.color.white)
                .init();

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
}

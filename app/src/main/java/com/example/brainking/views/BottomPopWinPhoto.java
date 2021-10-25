package com.example.brainking.views;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.brainking.R;


/**
 * 发布动态（图片，视频）弹窗
 */
public class BottomPopWinPhoto  extends PopupWindow {
    private View view;

    public BottomPopWinPhoto(final Activity mContext, BottomPotSelectLinstener th_bottomPotSelectLinstener) {
        this.view = LayoutInflater.from(mContext).inflate(R.layout.popwin_bottom_photo, null);
        TextView textView1 = view.findViewById(R.id.tv1);
        TextView textView2 = view.findViewById(R.id.tv2);
        TextView textView = view.findViewById(R.id.tv3);

        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
                //backgroundAlpha(mContext, 1f);
                setBackgroundAlpha(mContext, 1f);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //backgroundAlpha(mContext, 1f);
                setBackgroundAlpha(mContext, 1f);
                th_bottomPotSelectLinstener.getSelectStr("拍照");
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //backgroundAlpha(mContext, 1f);
                setBackgroundAlpha(mContext, 1f);
                th_bottomPotSelectLinstener.getSelectStr("相册");
            }
        });

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                //backgroundAlpha(mContext, 1f);
                setBackgroundAlpha(mContext, 1f);
            }
        });

        // 设置外部可点击
        this.setOutsideTouchable(true);

        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);


        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        //backgroundAlpha(mContext, 0.5f);
        setBackgroundAlpha(mContext, 0.5f);
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(Activity activity, float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    /**
     * 设置页面的透明度
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }


    public interface Return {
        void run(String str);
    }
}

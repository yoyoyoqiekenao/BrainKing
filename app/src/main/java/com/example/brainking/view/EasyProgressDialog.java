package com.example.brainking.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brainking.AppContext;
import com.example.brainking.R;

/**
 * @author : 徐无敌
 * date   : 2021/6/1010:34
 * desc   :
 */
public class EasyProgressDialog  extends Dialog {
    private Context mContext;

    private String mMessage;

    private int mLayoutId;

    private TextView message;

    private ImageView iv_loading;
    private LoadingView loading;


    public EasyProgressDialog(Context context, int style, int layout) {
        super(context, style);
        mContext = context;
        WindowManager.LayoutParams Params = getWindow().getAttributes();
        Params.width = WindowManager.LayoutParams.MATCH_PARENT;
        Params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(Params);
        mLayoutId = layout;
    }

    public EasyProgressDialog(Context context, int layout, String msg) {
        this(context, R.style.easy_dialog_style, layout);
        setMessage(msg);
    }

    public EasyProgressDialog(Context context, String msg) {
        this(context, R.style.easy_dialog_style, R.layout.loading);
        setMessage(msg);
    }

    public EasyProgressDialog(Context context) {
        this(context, R.style.easy_dialog_style, R.layout.loading);
    }

    public void setMessage(String msg) {
        mMessage = msg;
    }

    public void updateLoadingMessage(String msg) {
        mMessage = msg;
        showMessage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mLayoutId);
        message = (TextView) findViewById(R.id.tv_loadMsg);
        iv_loading = (ImageView) findViewById(R.id.loading_iv);
        loading = findViewById(R.id.loading);
        loading.start();
        showMessage();
        /*Animation operatingAnim = android.view.animation.AnimationUtils.loadAnimation(mContext, R.anim.loading_anim);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        iv_loading.startAnimation(operatingAnim);*/
        Glide.with(AppContext.getContext()).load(R.drawable.loadings).into(iv_loading);
    }

    private void showMessage() {
        if (message != null && !TextUtils.isEmpty(mMessage)) {
            message.setVisibility(View.VISIBLE);
            message.setText(mMessage);
        }
    }
    /*@Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loading = null;
        loading.stop();
    }*/

}

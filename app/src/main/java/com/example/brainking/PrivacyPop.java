package com.example.brainking;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.brainking.util.SpUtils;

import razerdp.basepopup.BasePopupWindow;

public class PrivacyPop extends BasePopupWindow implements View.OnClickListener {

    private WebView webView;
    private TextView tv_left, tv_right;

    private onClick mClick;

    public interface onClick {
        void click();
    }

    public PrivacyPop(Context context, onClick click) {
        super(context);
        setContentView(R.layout.pop_privacy);

        mClick = click;

        webView = findViewById(R.id.web);
        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);

        webView.loadUrl("http://www.wdsd66.cn/protocol/privacy_agreement.html");

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                mClick.click();
                break;
            case R.id.tv_right:
                SpUtils.getInstance().putBoolean("isAgree",true);
                dismiss();
                break;
            default:
        }
    }
}

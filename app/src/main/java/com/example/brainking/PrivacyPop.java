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
    private TextView tv_left, tv_right,tv1,tv2;

    private onClick mClick;

    public interface onClick {
        void click();
        void gotoWeb(String url,String title);
    }

    public PrivacyPop(Context context, onClick click) {
        super(context);
        setContentView(R.layout.pop_privacy);

        mClick = click;

        webView = findViewById(R.id.web);
        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        webView.loadUrl("http://www.wdsd66.cn/protocol/privacy_agreement.html");

        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                mClick.gotoWeb("http://www.wdsd66.cn/protocol/user_agreement.html","用户协议");
                break;
            case R.id.tv2:
                mClick.gotoWeb("http://www.wdsd66.cn/protocol/privacy_agreement.html","隐私政策");
                break;
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

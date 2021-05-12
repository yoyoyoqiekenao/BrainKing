package com.example.brainking.net.interceptor;

import android.util.Log;

import com.example.brainking.net.UrlConstainer;
import com.example.brainking.util.PreUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : 徐无敌
 * date   : 2021/5/1216:44
 * desc   :保存Cookie
 */
public class SaveCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> mCookieList = response.headers("Set-Cookie");
        //保存Cookie
        if (!mCookieList.isEmpty() && request.url().toString().endsWith(UrlConstainer.LOGIN)) {
            StringBuilder sb = new StringBuilder();
            for (String cookie : mCookieList) {
                //注意Cookie请求头字段中的每个Cookie之间用逗号或分号分隔
                sb.append(cookie).append(",");
            }
            PreUtils.put(response.request().url().host(), sb.toString());
            Log.e(SaveCookieInterceptor.class.getSimpleName(), "intercept: url : " + request.url());
        }
        return response;
    }
}

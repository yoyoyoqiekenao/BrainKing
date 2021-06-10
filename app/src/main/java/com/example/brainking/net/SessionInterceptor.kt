package com.example.brainking.net

import android.text.TextUtils
import com.blankj.utilcode.util.SPUtils
import com.example.brainking.AppContext
import com.example.brainking.util.UserAgentUtil.getUserAgent
import okhttp3.Interceptor
import okhttp3.Response

/**
 *    @author : 徐无敌
 *    date   : 2021/5/1817:21
 *    desc   :
 */
class SessionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("token"))) {
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString("token"))
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", getUserAgent(AppContext.getContext()))
                    .build()
            return chain.proceed(request)
        } else {
            val request = chain.request()
                    .newBuilder()
                    // .addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString("token"))
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", getUserAgent(AppContext.getContext()))
                    .build()
            return chain.proceed(request)
        }

    }
}
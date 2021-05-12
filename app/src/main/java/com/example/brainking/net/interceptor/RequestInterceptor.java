package com.example.brainking.net.interceptor;

import com.example.brainking.util.AppContext;
import com.example.brainking.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : 徐无敌
 * date   : 2021/5/1217:15
 * desc   :
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //无网络时从缓存中获取
        if (!NetworkUtils.isAvailable(AppContext.getContext())) {
            //无网络时,设置超时为30天
            int maxStale = 30 * 24 * 60 * 60;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("Pragma")
                    .header("Cache-Control", "only-if-cached,max-stale=" + maxStale)
                    .build();
        }
        return chain.proceed(request);
    }
}

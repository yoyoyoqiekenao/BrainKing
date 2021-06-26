package com.example.brainking.net;

import com.example.brainking.json.LoginJson;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.VerCodeModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:06
 * desc   :
 */
public interface ApiStores {

    //public static final String BASE_URL = "http://42.192.234.149:8080/";
    //测试环境
    public static final String BASE_URL = "http://192.168.16.109:8080/";


    /**
     * 获取验证码
     *
     * @param
     * @return
     */

    @POST("social/sendSms")
    Observable<VerCodeModel> getVerCode(@Body VerCodeJson json);

    /**
     * 登录接口
     *
     * @param json
     * @return
     */
    @POST("social/login/mobile")
    Observable<LoginModel> goLogin(@Body LoginJson json);

    /**
     * 获取小学语文列表
     *
     * @return
     */
    @GET("learn/category/list?pid=1")
    Observable<LearnListModel> getLearnList_language();

    /**
     * 获取诗词列表
     *
     * @return
     */
    @GET("learn/category/list?pid=2")
    Observable<LearnListModel> getLearnList_poems();
}

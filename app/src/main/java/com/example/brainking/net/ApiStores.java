package com.example.brainking.net;

import com.example.brainking.json.LoginJson;
import com.example.brainking.json.VerCodeJson;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.VerCodeModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:06
 * desc   :
 */
public interface ApiStores {

    public static final String BASE_URL = "http://42.192.234.149:8080/";


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

}

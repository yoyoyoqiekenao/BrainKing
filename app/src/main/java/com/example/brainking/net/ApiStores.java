package com.example.brainking.net;

import com.example.brainking.model.VerCodeModel;

import io.reactivex.Observable;
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
     * @param mobile
     * @return
     */
     @FormUrlEncoded
    @POST("social/sendSms")
    Observable<VerCodeModel> getVerCode(@Field("mobile") String mobile);

}

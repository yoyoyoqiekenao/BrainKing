package com.example.brainking.net;

import com.example.brainking.entity.PwdLoginEntity;
import com.example.brainking.json.PwdLoginJson;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:06
 * desc   :
 */
public class API {

    public static final String BASE_URL = "http://192.168.0.104/";

    public interface XWDApi {
        /**
         * 账号密码登录
         *
         * @param
         * @param
         * @return
         */
        @POST("auth/token/login")
        Observable<ResponseBean<PwdLoginEntity>> Login(@Body PwdLoginJson bean);
    }
}

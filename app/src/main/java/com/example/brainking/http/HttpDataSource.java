package com.example.brainking.http;

import io.reactivex.Observable;

/**
 * @author : 徐无敌
 * date   : 2021/5/615:41
 * desc   :
 */
public interface HttpDataSource {

    //模拟登录
    Observable<Object> login();

    //获取验证码
    Observable<Object> getVerificationCode();
}

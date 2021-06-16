package com.example.brainking.json;

/**
 * @author : 徐无敌
 * date   : 2021/6/1610:47
 * desc   :
 */
public class LoginJson {
    private String verifyCode;
    private String mobile;
    private String uuid;

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

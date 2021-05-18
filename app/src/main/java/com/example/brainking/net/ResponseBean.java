package com.example.brainking.net;

import java.io.Serializable;

/**
 * @author : 徐无敌
 * date   : 2021/5/1817:09
 * desc   :
 */
public class ResponseBean<T> implements Serializable {

    /**
     * data : null
     * code : 0
     * desc : 成功
     * success : true
     */

    private T data;
    private int code;
    private String message;
    private boolean success;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return (code==200);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

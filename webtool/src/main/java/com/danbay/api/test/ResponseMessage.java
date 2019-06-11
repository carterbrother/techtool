package com.danbay.api.test;

/**
 * Created by carter on 2017/2/24. Copyright © 2016 －2017 旦倍科技
 */
public class ResponseMessage {
    private int code = 0;
    private String message= "";
    private Object data = "";

    public ResponseMessage() {
    }

    public ResponseMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

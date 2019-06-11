package com.danbay.api.test;

/**
 * Created by carter on 2017/2/24. Copyright © 2016 －2017 旦倍科技
 */
public class ResponseMessage2 {
    private int status = 0;
    private String message= "";

    public ResponseMessage2() {
    }


    public ResponseMessage2(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

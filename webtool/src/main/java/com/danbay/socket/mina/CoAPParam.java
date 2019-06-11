package com.danbay.socket.mina;

/**
 * Created by carter on 2017/3/18. Copyright © 2016 －2017 旦倍科技
 * coap请求参数对象
 */
public class CoAPParam {


    private String urlPath;

    private int type;
    private String alias;
    private  String pwd;
    private  String fatherDeviceID;
    private String deviceID;


    public int getType() {
        return type;
    }

    public String getAlias() {
        return alias;
    }

    public String getPwd() {
        return pwd;
    }

    public CoAPParam() {
    }

    public String getFatherDeviceID() {
        return fatherDeviceID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setNew_pwd(String urlPath, int type, String alias, String pwd, String fatherDeviceID,String deviceID) {
        this.urlPath = urlPath;
        this.type = type;
        this.alias = alias;
        this.pwd = pwd;
        this.fatherDeviceID = fatherDeviceID;
        this.deviceID=deviceID;
    }

    public void setGet_alias(String urlPath, String fatherDeviceID, String deviceID) {
        this.urlPath = urlPath;
        this.fatherDeviceID = fatherDeviceID;
        this.deviceID = deviceID;
    }
}

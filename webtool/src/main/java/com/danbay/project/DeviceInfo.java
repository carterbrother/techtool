package com.danbay.project;

/**
 * Created by carter on 2017/5/15. Copyright © 2016 －2017 旦倍科技
 * 设备信息实体；
 *
 */
public class DeviceInfo {

    private String ip;//ip地址
    private String mac;//mac地址
    private String sn_id;//sn_id 即设备型号
    private String ver_id;//设备版本号
    private String device_id;//设备id

    public DeviceInfo() {
    }

    public DeviceInfo(String ip, String mac, String snid) {
        this.ip = ip;
        this.mac = mac;
        this.sn_id = snid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


    public String getSn_id() {
        return sn_id;
    }

    public void setSn_id(String sn_id) {
        this.sn_id = sn_id;
    }

    public String getVer_id() {
        return ver_id;
    }

    public void setVer_id(String version_id) {
        this.ver_id = version_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}



package com.danbay.project.callback2;

import com.danbay.project.DeviceInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 */
public interface DeviceCallback {

//    /**
//     * 允许设备入网
//     * @param ip   网关IP
//     * @param whiteList  入网的MAC地址
//     * @return
//     */
//     boolean alloc_ap( String ip, String [] whiteList);
//
//
//    /**
//     * 局域网设备发现列表；
//     * @param timeout  超时时间，单位秒 ， 一般设置为30S；
//     * @return
//     */
//     List<DeviceInfo> device_discover(int timeout );


     List<DeviceInfo> returnDeviceList(List<DeviceInfo>  deviceInfoList);

     AtomicBoolean    returnAllocApResult(AtomicBoolean allocApResult);

}

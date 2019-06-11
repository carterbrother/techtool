package com.danbay.project.callback2;

import com.danbay.project.DeviceInfo;
import com.danbay.utils.StringUtils;
import com.danbay.utils.coap.CoAPUtils;
import com.google.common.collect.Lists;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.serialization.DataParser;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.danbay.utils.coap.CoAPUtils.getUriQueryStringMap;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 */
public class Solver{


    public void  execute(DeviceCallback deviceCallback,int timeout,DatagramSocket datagramSocket)
    {
        System.out.println("请在"+timeout+"秒内给我设备列表？");



        Future<List<DeviceInfo>> future = Executors.newSingleThreadExecutor().submit(() -> {
                long start = System.currentTimeMillis();
                boolean flag = System.currentTimeMillis() - start <=timeout*1000;

                List<DeviceInfo> deviceInfos = Lists.newLinkedList();
                while (flag){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                    flag = System.currentTimeMillis() - start <=timeout*1000;
                    if(!flag )
                    {
                        break;
                    }


                    DatagramPacket returnPacket = new DatagramPacket(new byte[512],512);
                    datagramSocket.receive(returnPacket);

                    byte[] returnPacketData = returnPacket.getData();

                    String returnString = new String(returnPacketData);

                    if(StringUtils.isEmpty(returnString)) break;

                    Request request =  new DataParser(returnPacketData).parseRequest();

                    System.out.println("returnString= "+returnString);

                    DeviceInfo deviceInfo = new DeviceInfo();
                    Map<String,String> uriQueryStringMap = getUriQueryStringMap(request);
                    String device_id= uriQueryStringMap.get("dev_id");
                    deviceInfo.setDevice_id(device_id);

                    String mac= uriQueryStringMap.get("mac_id");
                    deviceInfo.setMac(mac);
                    String ip = returnPacket.getAddress().getHostAddress();
                    deviceInfo.setIp(ip);
                    String sn_id =  uriQueryStringMap.get("fac_id");
                    deviceInfo.setSn_id(sn_id);
                    String ver_id = uriQueryStringMap.get("ver_id");
                    deviceInfo.setVer_id(ver_id);
                   deviceInfos.add(deviceInfo);
                }

                System.out.println("-----------------执行完毕------");
                return deviceInfos;
        });

        try {
            List<DeviceInfo> deviceInfoList =  future.get(timeout+1, TimeUnit.SECONDS);
            deviceCallback.returnDeviceList(deviceInfoList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


    public void  do_allow(DeviceCallback deviceCallback,DatagramSocket datagramSocket)
    {

        Future<AtomicBoolean> submit = Executors.newSingleThreadExecutor().submit(() -> {

            byte[] buf = new byte[512];
            DatagramPacket p = new DatagramPacket(buf,512);
                datagramSocket.receive(p);


                System.out.println("打开白名单返回数据："+new String(p.getData()));

                Response response = new DataParser(p.getData()).parseResponse();

                System.out.println("网关回包："+ CoAPUtils.getResponseString(response));

            return  new AtomicBoolean(response.getCode().toString().equalsIgnoreCase("2.05"));
    });

        try {
            deviceCallback.returnAllocApResult(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }




}

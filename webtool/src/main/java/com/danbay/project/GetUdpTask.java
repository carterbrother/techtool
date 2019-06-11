package com.danbay.project;

import com.danbay.utils.StringUtils;
import com.google.common.collect.Lists;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.serialization.DataParser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;

import static com.danbay.utils.coap.CoAPUtils.getUriQueryStringMap;

/**
 * Created by carter on 2017/5/16. Copyright © 2016 －2017 旦倍科技
 */
public class GetUdpTask extends Thread {

    private DatagramSocket datagramSocket;
    private int timeout= 15;

    public  GetUdpTask (DatagramSocket datagramSocket, int timeout){
        this.datagramSocket = datagramSocket;
        this.timeout = timeout;
    }


    @Override
    public void run() {

        try {
        long start = System.currentTimeMillis();

        boolean flag = System.currentTimeMillis() - start <=timeout*1000;

        DeviceUtil.device_discover_return = Lists.newLinkedList();
        while (flag){

            try {
                Thread.sleep(2000);
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
            DeviceUtil.device_discover_return.add(deviceInfo);
        }

        System.out.println("-----------------执行完毕------");




    } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


    }
}

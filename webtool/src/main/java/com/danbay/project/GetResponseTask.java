package com.danbay.project;

import com.danbay.utils.coap.CoAPUtils;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.serialization.DataParser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by carter on 2017/5/17. Copyright © 2016 －2017 旦倍科技
 * 读取中控回包的任务；
 */
public class GetResponseTask extends Thread {

    DatagramSocket datagramSocket ;

    public GetResponseTask(DatagramSocket datagramSocket )
    {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {


        byte[] buf = new byte[512];
        DatagramPacket p = new DatagramPacket(buf,512);
        try {
            datagramSocket.receive(p);


            System.out.println("打开白名单返回数据："+new String(p.getData()));

            Response response = new DataParser(p.getData()).parseResponse();

            System.out.println("网关回包："+CoAPUtils.getResponseString(response));

            DeviceUtil.alloc_ap_Success.set(response.getCode().toString().equalsIgnoreCase("2.05"));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

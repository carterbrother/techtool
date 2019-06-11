package com.danbay.project;

import com.danbay.utils.RndUtils;
import com.danbay.utils.coap.CoAPUtils;
import com.google.common.collect.Lists;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.serialization.DataSerializer;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by carter on 2017/5/15. Copyright © 2016 －2017 旦倍科技
 *
 * 提供给app使用的实用方法类
 *
 */
public   class DeviceUtil {


    public static List<DeviceInfo> device_discover_return= Lists.newArrayList();//udp返回信息；
    public static AtomicBoolean    alloc_ap_Success = new AtomicBoolean(false);

    public static AtomicLong      start = new AtomicLong(System.currentTimeMillis());


    /**
     * 允许设备入网
     * @param ip
     * @param whiteList
     * @return
     */
    public static   boolean alloc_ap( String ip, String [] whiteList ){

        Request pdu = new Request(CoAP.Code.POST, CoAP.Type.CON);
        pdu.getOptions().setUriPath("ctrl/allow_ap");
        pdu.setToken(String.valueOf(RndUtils.getRandNum(100000)).getBytes());
        pdu.setMID(RndUtils.getRandNum(65534));
        pdu.getOptions().setUriHost("MAC_GATEWAY");

        StringBuffer stringBuffer = new StringBuffer();
        Arrays.asList(whiteList).forEach(w->{stringBuffer.append(w).append("\n");});

        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        pdu.setPayload(stringBuffer.toString());

        System.out.println("打开白名单请求数据："+CoAPUtils.getRequestString(pdu));
        byte[] buf = new DataSerializer().serializeRequest(pdu);

        //发送一个udp的包

        InetSocketAddress netSocketAddress = new InetSocketAddress(ip,8899);
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length,netSocketAddress);

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);


            //收包
            new GetResponseTask(datagramSocket).start();

            return  alloc_ap_Success.get();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    /**
     * 发现网关的入网情况
     * @param timeout DeviceInfo[]
     * @return
     */
    public static List<DeviceInfo>  device_discover( int timeout ){


        //1,构造一个用户数据包；

        Request pdu = new Request(CoAP.Code.GET, CoAP.Type.NON);
        pdu.getOptions().setUriPath("device_discover");
        pdu.setToken(String.valueOf(RndUtils.getRandNum(100000)).getBytes());

        byte[] buf = new DataSerializer().serializeRequest(pdu);
        SocketAddress netSocketAddress = new InetSocketAddress("255.255.255.255",8899);
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length,netSocketAddress);


        DatagramSocket datagramSocket = null;
        try {
            //2,发送数据包
            datagramSocket = new DatagramSocket();
            datagramSocket.setBroadcast(true);//设置为广播模式；
            datagramSocket.send(datagramPacket);


            //3,等待回包
            new GetUdpTask(datagramSocket,timeout).start();
        }catch (Exception ex)
        {

        }

        return device_discover_return;



    }




    public static void main(String[] args){

        long start = System.currentTimeMillis();
        boolean flag = System.currentTimeMillis()-start>=5000;
        while (flag)
        {
           flag=System.currentTimeMillis()-start>=5000;
            System.out.println("do job ...");
        }


    }

}

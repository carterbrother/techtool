package com.danbay.project.callback2;

import com.danbay.project.DeviceInfo;
import com.danbay.utils.RndUtils;
import com.danbay.utils.coap.CoAPUtils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.serialization.DataSerializer;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 * 1，android调用 询问设备发现列表是什么？
 */
public class Android implements DeviceCallback{

    //在这里开始发起询问  询问设备发现列表是什么？比如按钮触发；
    public void start_ask_device_discover()
    {
        device_discover(30);
    }


    public void  device_discover(int timeout ){

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
            new Solver().execute(this,timeout,datagramSocket);

        }catch (Exception ex)
        {

        }

        System.out.println("调用方可以做其它的事情；");

    }


    //在这个方法里展示最终的内容，增加一个监听，当有内容的时候进行分析；
    public List<DeviceInfo> returnDeviceList( List<DeviceInfo>  deviceInfoList)
    {
        System.out.println("返回信息了，接着处理设备发现；返回的总设备数量："+ deviceInfoList.size());
        return  deviceInfoList;
    }


    //在这里开始发起询问  询问设备发现列表是什么？比如按钮触发；
    public void start_ask_alloc_ap(String gateway_ip,String[] macArray)
    {
        device_discover(30);
    }


   public void  alloc_ap( String ip, String [] whiteList)
    {
        Request pdu = new Request(CoAP.Code.POST, CoAP.Type.CON);
        pdu.getOptions().setUriPath("ctrl/allow_ap");
        pdu.setToken(String.valueOf(RndUtils.getRandNum(100000)).getBytes());
        pdu.setMID(RndUtils.getRandNum(65534));
        pdu.getOptions().setUriHost("MAC_GATEWAY");

        StringBuffer stringBuffer = new StringBuffer();
        Arrays.asList(whiteList).forEach(w->{stringBuffer.append(w).append("\n");});

        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        pdu.setPayload(stringBuffer.toString());

        System.out.println("打开白名单请求数据："+ CoAPUtils.getRequestString(pdu));
        byte[] buf = new DataSerializer().serializeRequest(pdu);

        //发送一个udp的包

        InetSocketAddress netSocketAddress = new InetSocketAddress(ip,8899);
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length,netSocketAddress);

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);


            new Solver().do_allow(this,datagramSocket);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public AtomicBoolean returnAllocApResult(AtomicBoolean allocApResult) {
        System.out.println("返回信息了，接着处理入网操作；返回的入网结果是："+ allocApResult.get());
        return  allocApResult;
    }

    public static void main(String[] args) {
        new Android().start_ask_device_discover();
    }


}

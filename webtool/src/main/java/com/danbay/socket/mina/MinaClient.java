package com.danbay.socket.mina;

import com.danbay.constant.enu.socket.WordConstants;
import com.danbay.utils.JsonUtils;
import com.google.common.collect.Maps;
import org.apache.mina.core.session.IoSession;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.network.serialization.DataSerializer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by carter on 2017/3/17. Copyright © 2016 －2017 旦倍科技
 */
public class MinaClient  {


    private static final   int mid_Max = Integer.valueOf('\uffff')-1;
    private AtomicInteger mid = new AtomicInteger(mid_Max);
    private AtomicLong   token = new AtomicLong(1000);

    public boolean  sendCommand(CoAPParam coAPParam)
    {
        //构造命令对象
        Request coAPRequest = buildRequest(coAPParam);

        //打开客户端的连接
        IoSession session = IoSessionPool.getInstance().getClientSession();

        //发送指令到服务端
        byte[] requestBytes = new DataSerializer().serializeRequest(coAPRequest);
        session.write(requestBytes);

        //关闭会话
        closeClientSession(session);

        return true;

    }

    /**
     * 关闭客户端会话
     * @param session
     */
    private void closeClientSession(IoSession session) {
        //共享连接，所以这里不关闭
//        session.closeNow();
    }



    private Request buildRequest(CoAPParam coAPParam) {

        if(mid.get()<1)
        {
            mid.set(mid_Max);
        }

        if(WordConstants.NEW_PWD.equalsIgnoreCase(coAPParam.getUrlPath()))
        {//构造新建密码的coap请求

            Request coAPRequest = new Request(CoAP.Code.POST, CoAP.Type.CON);
            coAPRequest.setMID(mid.getAndDecrement());
            coAPRequest.setToken(token.toString().getBytes());

            OptionSet optionSet = new OptionSet();
            optionSet.setUriPath(coAPParam.getUrlPath());
            optionSet.setUriQuery("dev_id=" +coAPParam.getDeviceID());
            coAPRequest.setOptions(optionSet);

            Map<String,Object> payloadMap = Maps.newHashMap();
            payloadMap.put(WordConstants.FATHERID,coAPParam.getFatherDeviceID());
            payloadMap.put(WordConstants.TYPE, /*"0x0"+*/coAPParam.getType());
            payloadMap.put(WordConstants.PWD, coAPParam.getPwd());
            payloadMap.put(WordConstants.ALIAS, coAPParam.getAlias());
            coAPRequest.setPayload(JsonUtils.getJson(payloadMap));

            return  coAPRequest;


        }else if(WordConstants.GET_ALIAS.equalsIgnoreCase(coAPParam.getUrlPath()))
        {
            Request coAPRequest = new Request(CoAP.Code.GET, CoAP.Type.CON);
            coAPRequest.setMID(mid.getAndDecrement());
            coAPRequest.setToken(token.toString().getBytes());

            OptionSet optionSet = new OptionSet();
            optionSet.setUriPath(coAPParam.getUrlPath());
            optionSet.setUriQuery("dev_id=" +coAPParam.getDeviceID());
            coAPRequest.setOptions(optionSet);

            Map<String,Object> payloadMap = Maps.newHashMap();
            payloadMap.put(WordConstants.FATHERID,coAPParam.getFatherDeviceID());
            coAPRequest.setPayload(JsonUtils.getJson(payloadMap));

            return  coAPRequest;

        }else if(WordConstants.EDIT_PWD.equalsIgnoreCase(coAPParam.getUrlPath()))
        {//构造新建密码的coap请求

            Request coAPRequest = new Request(CoAP.Code.POST, CoAP.Type.CON);
            coAPRequest.setMID(mid.getAndDecrement());
            coAPRequest.setToken(token.toString().getBytes());

            OptionSet optionSet = new OptionSet();
            optionSet.setUriPath(coAPParam.getUrlPath());
            optionSet.setUriQuery("dev_id=" +coAPParam.getDeviceID());
            coAPRequest.setOptions(optionSet);

            Map<String,Object> payloadMap = Maps.newHashMap();
            payloadMap.put(WordConstants.FATHERID,coAPParam.getFatherDeviceID());
            payloadMap.put(WordConstants.TYPE, /*"0x0"+*/coAPParam.getType());
            payloadMap.put(WordConstants.PWD, coAPParam.getPwd());
            payloadMap.put(WordConstants.ALIAS, coAPParam.getAlias());
            coAPRequest.setPayload(JsonUtils.getJson(payloadMap));

            return  coAPRequest;


        }else if(WordConstants.DELETE_PWD.equalsIgnoreCase(coAPParam.getUrlPath()))
        {//构造新建密码的coap请求

            Request coAPRequest = new Request(CoAP.Code.POST, CoAP.Type.CON);
            coAPRequest.setMID(mid.getAndDecrement());
            coAPRequest.setToken(token.toString().getBytes());

            OptionSet optionSet = new OptionSet();
            optionSet.setUriPath(coAPParam.getUrlPath());
            optionSet.setUriQuery("dev_id=" +coAPParam.getDeviceID());
            coAPRequest.setOptions(optionSet);

            Map<String,Object> payloadMap = Maps.newHashMap();
            payloadMap.put(WordConstants.FATHERID,coAPParam.getFatherDeviceID());
            payloadMap.put(WordConstants.TYPE, /*"0x0"+*/coAPParam.getType());
//            payloadMap.put(WordConstants.PWD, coAPParam.getPwd());
            payloadMap.put(WordConstants.ALIAS, coAPParam.getAlias());
            coAPRequest.setPayload(JsonUtils.getJson(payloadMap));

            return  coAPRequest;


        }



        return null;
    }


    public static void  main(String[] args)
    {

        System.out.println(Integer.valueOf('\uffff'));
    }





}

package com.danbay.test_device;

import com.danbay.utils.*;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/5/31. Copyright © 2016 －2017 旦倍科技
 */
@Controller("WireController")
@RequestMapping("/test_device/wire")
public class WireController {


    @RequestMapping("/index.do")
    public ModelAndView index()
    {
        return  new ModelAndView("test_device/gateway");
    }

    @RequestMapping("/gateway/allow_ap.do")
    public void test(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("dev_id","fdf9388308dea4ffd41e8a3096bb2982");
        parametersMap.put("mch_id","10001");
        parametersMap.put("app_key","qxcbeunljokmhf6lyr2qq2o6fjmvzucu");
        parametersMap.put("mac_address","xxxx");
        parametersMap.put("sign",getSign(parametersMap,"f72looj7onxw85sthgemw3h1yk59v7pn"));


        NetResponse netResponse = HttpUtils.post2("http://localhost:9083/device/gateway/control/allow_ap.do",parametersMap);
        RequestUtils.write(response, JsonUtils.getJson(netResponse));

    }


    public static String getSign(Map<String,String> signParamMap, String app_secret){

        Objects.requireNonNull(signParamMap);
        Objects.requireNonNull(app_secret);

        StringBuffer stringAStringBuffer = new StringBuffer();
        //找出所有值非空的key;
        signParamMap.entrySet().parallelStream().filter(item-> StringUtils.isNotEmpty(item.getValue())).sorted(Comparator.comparing(Map.Entry::getKey)).map(item->item.getKey()+"="+item.getValue()+"&").collect(Collectors.toList()).forEach(item->stringAStringBuffer.append(item));

        //拼接key
        stringAStringBuffer.append("key=").append(app_secret);

        //md5加密并转换为大写；
        String  sign =  com.danbay.utils.security.MD5.EncryptMD5(stringAStringBuffer.toString()).toUpperCase();


        if(Logger.getRootLogger().isDebugEnabled())
        {
            System.out.println("明文："+stringAStringBuffer.toString() +" sign: "+ sign);
        }

        return sign;
    }

    public static void main(String[] args) {
//        Map<String, String> parametersMap = Maps.newConcurrentMap();
//
//        parametersMap.put("dev_id","fdf9388308dea4ffd41e8a3096bb2982");
//        parametersMap.put("mch_id","10001");
//        parametersMap.put("app_key","qxcbeunljokmhf6lyr2qq2o6fjmvzucu");
//        String sign = getSign(parametersMap, "f72looj7onxw85sthgemw3h1yk59v7pn");
//        parametersMap.put("sign", sign);

//        System.out.println(sign);



//        Map<String, String> parametersMap = Maps.newConcurrentMap();
//
//        parametersMap.put("dev_id","fdf9388308dea4ffd41e8a3096bb2982");
//        parametersMap.put("mch_id","10001");
//        parametersMap.put("app_key","qxcbeunljokmhf6lyr2qq2o6fjmvzucu");
//        parametersMap.put("mac_address","xxxx");
//        final String sign = getSign(parametersMap, "f72looj7onxw85sthgemw3h1yk59v7pn");
//        parametersMap.put("sign", sign);
//
//        System.out.println(sign);


        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("dev_id","fdf9388308dea4ffd41e8a3096bb2982");
        parametersMap.put("mch_id","10001");
        parametersMap.put("app_key","qxcbeunljokmhf6lyr2qq2o6fjmvzucu");
        parametersMap.put("token_id","2");
        final String sign = getSign(parametersMap, "f72looj7onxw85sthgemw3h1yk59v7pn");
        parametersMap.put("sign", sign);

        System.out.println(sign);



    }


}

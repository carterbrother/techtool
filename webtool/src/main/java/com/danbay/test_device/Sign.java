package com.danbay.test_device;

import com.danbay.utils.StringUtils;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/6/2. Copyright © 2016 －2017 旦倍科技
 * 加密
 */
public class Sign {

    public static String getSign(Map<String,String> signParamMap, String app_secret){

        Objects.requireNonNull(signParamMap);
        Objects.requireNonNull(app_secret);

        StringBuffer stringAStringBuffer = new StringBuffer();
        //找出所有值非空的key;
        signParamMap.entrySet().parallelStream().filter(item-> StringUtils.isNotEmpty(item.getValue())).filter(item->!item.getKey().equals("sign")).sorted(Comparator.comparing(Map.Entry::getKey)).map(item->item.getKey()+"="+item.getValue()+"&").collect(Collectors.toList()).forEach(item->stringAStringBuffer.append(item));

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

}

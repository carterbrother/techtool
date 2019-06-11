package com.danbay.test_device.util;

import com.danbay.utils.*;
import com.danbay.utils.model.ResponseObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by carter on 2017/6/2. Copyright © 2016 －2017 旦倍科技
 */
public final class RequestHttpUtil {

    /**
     * 统一的访问设备控制平台的方法
     * @param response
     * @param parametersMap
     * @param url
     */
    public static   void request2DevicePlatform(HttpServletResponse response, Map<String, String> parametersMap , String url) {
        NetResponse netResponse = HttpUtils.post2(ConfigUtil.getPlatform_url()+url,parametersMap);


        if(null == netResponse || StringUtils.isEmpty(netResponse.getResponseContent()))
        {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setStatus("500");
            responseObject.setMessage("设备控制平台无返回");
            RequestUtils.write(response, JsonUtils.getJson(responseObject));
        }else{
            ResponseObject responseObject = new ResponseObject();
            responseObject.setStatus("200");
            responseObject.setMessage("设备控制平台返回OK");
            responseObject.setResult(netResponse.getResponseContent());
            RequestUtils.write(response, JsonUtils.getJson(responseObject));
        }
    }
}

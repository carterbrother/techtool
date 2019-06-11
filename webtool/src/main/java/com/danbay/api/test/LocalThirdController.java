package com.danbay.api.test;

import com.danbay.utils.*;
import com.google.common.base.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by carter on 2017/2/24. Copyright © 2016 －2017 旦倍科技
 */
@Controller("LocalThirdController")
public class LocalThirdController {

    final  String url_system_deviceInfo_getDeviceIistAll_online="http://www.danbay.cn/system/deviceInfo/getDeviceIistAll";
    final  String url_system_deviceInfo_getDeviceIistAll="http://127.0.0.1:8680/deviceInfo/getDeviceIistAll";


    @RequestMapping("/system/deviceInfo/getDeviceIistAll")
    public void system_deviceInfo_getDeviceIistAll(HttpServletRequest request , HttpServletResponse response)
    {
        String mtoken = CommonUtils.getStringValue(request.getParameter("mtoken"),"");

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("mtoken",mtoken);


        String urlPath = url_system_deviceInfo_getDeviceIistAll;
        if("product".equalsIgnoreCase(CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("app.evn"))))
        {
            urlPath=url_system_deviceInfo_getDeviceIistAll_online;
        }

        NetResponse netResponse = HttpUtils.post(urlPath,paramMap);


        ResponseMessage responseMessage = new ResponseMessage(-1,"no","");
        if(netResponse == null || netResponse.getResponseCode()!=200 || Strings.isNullOrEmpty(netResponse.getResponseContent()))
        {

        }else
        {
            responseMessage.setCode(0);
            responseMessage.setMessage("ok");
            responseMessage.setData(netResponse.getResponseContent());

        }
        RequestUtils.write(response, JsonUtils.getJson(responseMessage));

    }

}

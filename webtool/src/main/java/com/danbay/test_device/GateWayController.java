package com.danbay.test_device;

import com.danbay.test_device.util.ConfigUtil;
import com.danbay.utils.*;
import com.danbay.utils.model.ResponseObject;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.danbay.test_device.util.RequestHttpUtil.request2DevicePlatform;

/**
 * Created by carter on 2017/5/31. Copyright © 2016 －2017 旦倍科技
 */
@Controller("TestGateWayController")
@RequestMapping("/test_device/gateway")
public class GateWayController {


    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("test_device/gateway");
        request.getSession().getAttribute("gateway_dev_id");
        return modelAndView;
    }

    @RequestMapping("/setDeviceId.do")
    public void setDeviceId(HttpServletRequest request,HttpServletResponse response)
    {
        ResponseObject responseObject = new ResponseObject();

        String dev_id = CommonUtils.getStringValue(request.getParameter("dev_id"));

        if(StringUtils.isEmpty(dev_id))
        {
            responseObject.setStatus("500");
            responseObject.setMessage("设备id为空");
            RequestUtils.write(response,JsonUtils.getJson(responseObject));
            return;
        }

        //保存到session中；
        request.getSession().setAttribute("gateway_dev_id",dev_id);

        //返回结果
        responseObject.setStatus("200");
        responseObject.setMessage("设置智能网关设备id成功");
        responseObject.setResult(dev_id);

        RequestUtils.write(response,JsonUtils.getJson(responseObject));

    }



    @RequestMapping("/allow_ap.do")
    public void test(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("mac_address",CommonUtils.getStringValue(request.getParameter("mac_address")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

       request2DevicePlatform(response,parametersMap,"gateway/control/allow_ap.do");

    }


    @RequestMapping("/reboot.do")
    public void reboot(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));


        request2DevicePlatform(response, parametersMap, "gateway/control/reboot.do");

    }

    @RequestMapping("/upgrade.do")
    public void upgrade(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("url",CommonUtils.getStringValue(request.getParameter("url")));
        parametersMap.put("md5",CommonUtils.getStringValue(request.getParameter("md5")));
        parametersMap.put("force",CommonUtils.getStringValue(request.getParameter("force")));
        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));


        request2DevicePlatform(response, parametersMap, "gateway/control/upgrade.do");

    }


    @RequestMapping("/command_query.do")
    public void command_query(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("token_id",CommonUtils.getStringValue(request.getParameter("token_id")));
        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));


        request2DevicePlatform(response, parametersMap, "query_command.do");

    }




}

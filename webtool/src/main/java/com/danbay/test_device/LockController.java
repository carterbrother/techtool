package com.danbay.test_device;

import com.danbay.test_device.util.ConfigUtil;
import com.danbay.utils.CommonUtils;
import com.danbay.utils.JsonUtils;
import com.danbay.utils.RequestUtils;
import com.danbay.utils.StringUtils;
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
@Controller("LockController")
@RequestMapping("/test_device/lock")
public class LockController {


    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("test_device/lock");

        return modelAndView;
    }


    @RequestMapping("/setDeviceId.do")
    public void setDeviceId(HttpServletRequest request,HttpServletResponse response)
    {
        ResponseObject responseObject = new ResponseObject();

        String dev_id = CommonUtils.getStringValue(request.getParameter("lock_dev_id"));

        if(StringUtils.isEmpty(dev_id))
        {
            responseObject.setStatus("500");
            responseObject.setMessage("门锁设备id为空");
            RequestUtils.write(response, JsonUtils.getJson(responseObject));
            return;
        }

        //保存到session中；
        request.getSession().setAttribute("lock_dev_id",dev_id);

        //返回结果
        responseObject.setStatus("200");
        responseObject.setMessage("设置智能门锁设备id成功");
        responseObject.setResult(dev_id);

        RequestUtils.write(response,JsonUtils.getJson(responseObject));

    }


    @RequestMapping("/new_pwd.do")
    public void new_pwd(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("pwd",CommonUtils.getStringValue(request.getParameter("pwd")));
        parametersMap.put("type",CommonUtils.getStringValue(request.getParameter("type")));
        parametersMap.put("alias",CommonUtils.getStringValue(request.getParameter("alias")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/new_pwd.do");

    }

    @RequestMapping("/del_pwd.do")
    public void del_pwd(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("type",CommonUtils.getStringValue(request.getParameter("type")));
        parametersMap.put("alias",CommonUtils.getStringValue(request.getParameter("alias")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/del_pwd.do");

    }


    @RequestMapping("/edit_pwd.do")
    public void edit_pwd(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("pwd",CommonUtils.getStringValue(request.getParameter("pwd")));
        parametersMap.put("type",CommonUtils.getStringValue(request.getParameter("type")));
        parametersMap.put("alias",CommonUtils.getStringValue(request.getParameter("alias")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/edit_pwd.do");

    }



    @RequestMapping("/stop_pwd.do")
    public void stop_pwd(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));
        parametersMap.put("state",CommonUtils.getStringValue(request.getParameter("state")));
        parametersMap.put("type",CommonUtils.getStringValue(request.getParameter("type")));
        parametersMap.put("alias",CommonUtils.getStringValue(request.getParameter("alias")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/stop_pwd.do");

    }

    @RequestMapping("/get_alias.do")
    public void get_alias(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/get_alias.do");

    }

    @RequestMapping("/update_alias.do")
    public void update_alias(HttpServletRequest request, HttpServletResponse response)
    {

        //模拟访问设备控制平台
        Map<String, String> parametersMap = Maps.newConcurrentMap();

        parametersMap.put("father_id",CommonUtils.getStringValue(request.getParameter("father_id")));
        parametersMap.put("dev_id",CommonUtils.getStringValue(request.getParameter("dev_id")));


        parametersMap.put("type",CommonUtils.getStringValue(request.getParameter("type")));
        parametersMap.put("old_alias",CommonUtils.getStringValue(request.getParameter("old_alias")));
        parametersMap.put("new_alias",CommonUtils.getStringValue(request.getParameter("new_alias")));

        parametersMap.put("mch_id", ConfigUtil.getMch_id());
        parametersMap.put("app_key",ConfigUtil.getApp_key());
        parametersMap.put("sign",Sign.getSign(parametersMap,ConfigUtil.getApp_secret()));

        request2DevicePlatform(response,parametersMap,"lock/control/update_alias.do");

    }





}

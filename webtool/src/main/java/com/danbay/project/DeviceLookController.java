package com.danbay.project;

import com.danbay.api.test.ResponseMessage;
import com.danbay.utils.CollectionUtils;
import com.danbay.utils.CommonUtils;
import com.danbay.utils.JsonUtils;
import com.danbay.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by carter on 2017/5/15. Copyright © 2016 －2017 旦倍科技
 * 扫描局域网得到中控下面的设备列表；
 *
 */
@Controller("DeviceLookController")
@RequestMapping("/app/project")
public class DeviceLookController {

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        return  new ModelAndView("project/index");
    }


    /**
     * 中控，运行某设备入网；
     * @param request  请求对象
     * @param response 响应对象
     */
    @RequestMapping("/alloc_ap")
    public void  alloc_ap(HttpServletRequest request, HttpServletResponse response)
    {


        String ip = CommonUtils.getStringValue(request.getParameter("ip"));
        String mac= CommonUtils.getStringValue(request.getParameter("mac"));

       boolean zigbeeStartSuccess =   DeviceUtil.alloc_ap(ip,new String[] {mac});

        ResponseMessage responseMessage = new ResponseMessage(404,"没找到","");

        if(zigbeeStartSuccess)
        {
            responseMessage.setCode(200);
            responseMessage.setMessage("打开网关"+ip + "的白名单成功" + mac);
        }

        String result = JsonUtils.getJson(responseMessage);
        RequestUtils.write(response,result,request);

    }


    /**
     * 发现得到中控的入网情况列表
     * @param request  请求对象
     * @param response 响应对象
     */
    @RequestMapping("/device_discover")
    public void  device_discover(HttpServletRequest request, HttpServletResponse response)
    {

        ResponseMessage responseMessage = new ResponseMessage(404,"没找到","");

        List<DeviceInfo> deviceInfoList = com.danbay.project.DeviceUtil.device_discover(30);






        if(CollectionUtils.isNotNullOrEmpty(deviceInfoList))
        {
            responseMessage.setCode(200);
            responseMessage.setMessage("成功返回数据");
            responseMessage.setData(deviceInfoList);

            deviceInfoList.forEach(d-> System.out.println(JsonUtils.getJson(d)));



        }


        String result = JsonUtils.getJson(responseMessage);
        RequestUtils.write(response,result,request);


    }



}

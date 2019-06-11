package com.danbay.task;

import com.danbay.service.danbay.device.api.ApiMessagePushService;
import com.danbay.service.danbay.device.ctrl.CtrlLockService;
import com.danbay.utils.JsonUtils;
import com.danbay.utils.RequestUtils;
import com.danbay.utils.RndUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by carter on 2017/5/18. Copyright © 2016 －2017 旦倍科技
 * 测试任务调度；
 *
 */
@Controller("TaskTestController")
public class TaskTestController {

    @RequestMapping("/task/test/new_task")
    public void newHttpTask(HttpServletRequest request, HttpServletResponse response)
    {
        //接受参数
        Map<String,String> requestMap = RequestUtils.getMapFromRequest(request);

        //保存任务；

        String taskDest= "http://localhost:9980/api/notify/opendoor.do";
        new ApiMessagePushService().newTask(requestMap.get("deviceId"), JsonUtils.getJson(requestMap),"测试",taskDest);

        RequestUtils.write(response,"ok");

    }

    @RequestMapping("/task/test/new_tcp_task")
    public void newTcpTask(HttpServletRequest request, HttpServletResponse response)
    {
        //接受参数

        new  CtrlLockService().editPwd("a92f404dd287434980fb6cf7cf1a9046",2, RndUtils.getLengthNum(6),"gj","云端测试","127.0.0.1:9994");
//
//        final DeviceCtrlRequestDto.DeviceCtrlRequest xxxx = DeviceCtrlRequestDto.DeviceCtrlRequest.newBuilder().setCodeType(DeviceCtrlRequestDto.DeviceCtrlRequest.CodeType.CON)
//                .setCode(DeviceCtrlRequestDto.DeviceCtrlRequest.Code.GET)
//                .setUrlPath("xxxx")
//                .build();
//
//        System.out.println(xxxx.getType());

        RequestUtils.write(response,"ok");

    }






}

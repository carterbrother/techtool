package com.danbay.api.test;

import com.danbay.utils.CommonUtils;
import com.danbay.utils.JsonUtils;
import com.danbay.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/5/9. Copyright © 2016 －2017 旦倍科技
 * 接受通知demo
 */
@Controller("NotifyController")
@RequestMapping("/api/notify")
public class NotifyController {

    @RequestMapping("/opendoor")
    public void openDoor(HttpServletRequest request, HttpServletResponse response)
    {
        //1，接受参数
        String deviceId = CommonUtils.getStringValue(request.getParameter("deviceId"));
        int    pwdID =    CommonUtils.getIntValue(request.getParameter("pwdID"));
        String time = CommonUtils.getStringValue(request.getParameter("time"));
        int    type =    CommonUtils.getIntValue(request.getParameter("type"));


        //2,进行业务处理

        boolean doSuccess = do_openDoor(deviceId,pwdID,time,type);

        ResponseMessage2 responseMessage= new ResponseMessage2(200,"请求成功");
        if(!doSuccess)
        {
            responseMessage.setStatus(500);
            responseMessage.setMessage("请求失败");
        }
        RequestUtils.write(response, JsonUtils.getJson(responseMessage));

    }

    private boolean do_openDoor(String deviceId, int pwdID, String time, int type) {
        System.out.println("接受的开门信息是：deviceId(设备id)="+deviceId+" pwdID(密码id)="+pwdID+" time(操作时间：格式举例  160318112300)="+time+" type(密码类型： 密码类型： 0  临时密码开门 1  管理员密码开门 2  管家密码开门 3 房客密码开门)="+type);


        long result =1;
//                System.currentTimeMillis()%3;

        if(result<2) {
            return result==1;
        } else{
            throw new  IllegalArgumentException("随机的异常抛出");
        }

    }

    @RequestMapping("/warning")
    public void warning(HttpServletRequest request, HttpServletResponse response)
    {
        //1，接受参数
        String deviceId = CommonUtils.getStringValue(request.getParameter("deviceId"));
        int    code =    CommonUtils.getIntValue(request.getParameter("code"));
        String time = CommonUtils.getStringValue(request.getParameter("time"));


        //2,进行业务处理

        boolean doSuccess = do_warning(deviceId,code,time);

        ResponseMessage2 responseMessage= new ResponseMessage2(200,"请求成功");
        if(!doSuccess)
        {
            responseMessage.setStatus(500);
            responseMessage.setMessage("请求失败");
        }
        RequestUtils.write(response, JsonUtils.getJson(responseMessage));

    }

    private boolean do_warning(String deviceId, int code, String time) {
        System.out.println("接受的门锁报警信息是：deviceId(设备id)="+deviceId+" code(报警类型： 报警类型： 1  密码错误报警 2 低电报警3  防撬报警 4 斜舌报警)="+code+" time(操作时间：格式举例  160318112300)="+time);
        return System.currentTimeMillis()%3==1;
    }

    @RequestMapping("/access")
    public void access(HttpServletRequest request, HttpServletResponse response)
    {
        //1，接受参数
        String deviceId = CommonUtils.getStringValue(request.getParameter("deviceId"));
        int    action =    CommonUtils.getIntValue(request.getParameter("action"));
        int    pwdID =    CommonUtils.getIntValue(request.getParameter("pwdID"));
        String op_code = CommonUtils.getStringValue(request.getParameter("op_code"));
        String time = CommonUtils.getStringValue(request.getParameter("time"));
        int    type =    CommonUtils.getIntValue(request.getParameter("type"));


        //2,进行业务处理

        boolean doSuccess = do_access(deviceId,action,pwdID,op_code,time,type);

        ResponseMessage2 responseMessage= new ResponseMessage2(200,"请求成功");
        if(!doSuccess)
        {
            responseMessage.setStatus(500);
            responseMessage.setMessage("请求失败");
        }
        RequestUtils.write(response, JsonUtils.getJson(responseMessage));

    }

    private boolean do_access(String deviceId, int action, int pwdID, String op_code, String time, int type) {
        System.out.println("接受的门锁报警信息是：deviceId(设备id)="+deviceId+" action(1 新增 2 修改 3 删除 4 冻结 5 解除冻结0 恢复默认值)="+action+" time(操作时间：格式举例  160318112300)="+time+" pwdId()="+pwdID+" op_code()="+op_code+" type()="+type);
        return System.currentTimeMillis()%4==1;
    }

}

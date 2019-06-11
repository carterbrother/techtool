package com.danbay.websocket;

import com.danbay.utils.CommonUtils;
import com.danbay.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/6/12. Copyright © 2016 －2017 旦倍科技
 */
@Controller("NotifyControllerxxxx")
public class NotifyController {


    @RequestMapping("/wsnotify.do")
    public void  notifyWs(HttpServletRequest request, HttpServletResponse response)
    {

        //给客户端发送一个消息；实时的通知显示；
        String sessionId = CommonUtils.getStringValue(request.getParameter("sessionId"),"");
        String commandType = CommonUtils.getStringValue(request.getParameter("commandType"),"");
        String commandResult = CommonUtils.getStringValue(request.getParameter("commandResult"),"");

        if(DemoTask.sessionCopyOnWriteMap.containsKey(sessionId))
        {
            DemoTask.sessionCopyOnWriteMap.get(sessionId).getAsyncRemote().sendText(commandType+"处理"+commandResult);
        }


        RequestUtils.write(response,"do ok ");


    }


}

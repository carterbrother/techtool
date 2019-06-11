package com.danbay.tool.controller;

/**
 * Created by Administrator on 2015/6/15.
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.springframework.web.servlet.mvc.Controller;

/**
 */

@Controller
public class MessageController {

    /**
     * 消息提示功能页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/error/message.do")
    public ModelAndView error(HttpServletRequest request,HttpServletResponse response)
    {
        String showTitle = request.getParameter("title");
        String showMessage = request.getParameter("message");
        String redirectUrl = request.getParameter("url");
        String result = request.getParameter("result");
        String showCss = "fail_style";
        if(result.equalsIgnoreCase("true"))
        {
            showCss = "success_style";
        }

        //定义试图
        ModelAndView mav = new ModelAndView("error/message");
        mav .addObject("showTitle",showTitle);
        mav .addObject("showMessage",showMessage);
        mav .addObject("redirectUrl",redirectUrl);
        mav .addObject("result",result);
        mav .addObject("showCss",showCss);
        return mav;
    }
}

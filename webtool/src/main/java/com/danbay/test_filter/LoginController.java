package com.danbay.test_filter;

import com.danbay.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 */
@Controller("LoginController")
@RequestMapping("/test_filter")
public class LoginController {


    @RequestMapping("/login.do")
    public ModelAndView  login(HttpServletRequest request, HttpServletResponse response)
    {
         if ("post".equalsIgnoreCase(request.getMethod()))
        {//进行登录判断；

            String username = CommonUtils.getStringValue(request.getParameter("txtAccount"));
            String password = CommonUtils.getStringValue(request.getParameter("txtPassword"));
            boolean loginSuccess= UserPowerUtil.user_login(username ,password,request,response);
            if(loginSuccess)
            {
                return new ModelAndView("test_filter/main");
            }
                //登录失败
            return new ModelAndView("test_filter/login");
        }else
        {//直接进到登录页面；

            boolean hasLogin = UserPowerUtil.check_login(request,response);
            if(hasLogin)
            {
                return new ModelAndView(new RedirectView("/test_filter/main.do"));
            }

             return new ModelAndView("test_filter/login");
         }

    }


    @RequestMapping("/main.do")
    public ModelAndView  main(HttpServletRequest request, HttpServletResponse response)
    {
        return new ModelAndView("test_filter/main");
    }


    @RequestMapping("/noPower.do")
    public ModelAndView  noPower(HttpServletRequest request, HttpServletResponse response)
    {
       return new ModelAndView("test_filter/noPower");
    }


    @RequestMapping("/logout.do")
    public ModelAndView  logout(HttpServletRequest request, HttpServletResponse response)
    {
        boolean logoutSuccess = UserPowerUtil.user_logout(request,response);
        if(logoutSuccess)
        {
            return new ModelAndView(new RedirectView("/test_filter/login.do"));
        }

        return  null;
    }




}

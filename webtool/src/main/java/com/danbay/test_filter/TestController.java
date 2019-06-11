package com.danbay.test_filter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 * 功能测试控制器
 */
@Controller("com.danbay.test_filter.TestController")
@RequestMapping("/test_filter")
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response)
    {
        return new ModelAndView("test_filter/test");
    }

    @RequestMapping("/yyy")
    public ModelAndView yyy(HttpServletRequest request, HttpServletResponse response)
    {
        return new ModelAndView("test_filter/yyy");
    }


}

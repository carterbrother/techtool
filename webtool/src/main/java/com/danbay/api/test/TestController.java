package com.danbay.api.test;

import com.danbay.utils.CommonUtils;
import com.danbay.utils.PropertiesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/2/21. Copyright © 2016 －2017 旦倍科技
 */
@Controller("TestController")
@RequestMapping("/api/test/")
public class TestController {

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
    {


        String viewPath = "api/login";
        if("product".equalsIgnoreCase(CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("app.evn"))))
        {
            viewPath+="_online";
        }

        return  new ModelAndView(viewPath);

    }

    /**
     * 登录成功接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("success")
    public ModelAndView loginSuccess(HttpServletRequest request, HttpServletResponse response)
    {

        String viewPath = "api/myHomeIndex";
        if("product".equalsIgnoreCase(CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("app.evn"))))
        {
            viewPath+="_online";
        }
            //验证票据,进入我方系统主页
        ModelAndView mv = new ModelAndView(viewPath);
        String mtoken = CommonUtils.getStringValue(request.getParameter("mtoken"));
        mv.addObject("mtoken",mtoken);
        return mv;
    }

    /**
     * Uu1eqtCEXqB6wFWGi72wvo0JxY69gg19iPx6LbPGvexuHMeBOQgqN6tU1
     * Uu1eqtCEXqB6wFWGi72wvo0JxY69gg19iPx6LbPGvexuHMeBOQgqN6tU1/apw/ks
     *
     *
     * 3SCEBuM90Q7/LnMUy2CHYs1aDUJgwW112ktsccBh0PTisCYAS+mSzwZEuZCHc6KA
     * 3SCEBuM90Q7/LnMUy2CHYs1aDUJgwW112ktsccBh0PTisCYAS+mSzwZEuZCHc6KA
     *
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping("success2")
//    public ModelAndView loginSuccess2(HttpServletRequest request, HttpServletResponse response)
//    {
//        //验证票据,进入我方系统主页
//        ModelAndView mv = new ModelAndView("api/myHomeIndex2");
//        String mtoken = CommonUtils.getStringValue(request.getParameter("mtoken"));
//        mv.addObject("mtoken",mtoken);
//        return mv;
//    }

    /**
     * 登录错误接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("err")
    public ModelAndView loginErr(HttpServletRequest request, HttpServletResponse response)
    {
            return  new ModelAndView("api/err");
    }




}

package com.danbay.test_device;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by carter on 2017/6/2. Copyright © 2016 －2017 旦倍科技
 */
@Controller("com.danbay.test_device.IndexController")
@RequestMapping("/test_device/")
public class IndexController {

    @RequestMapping("/index.do")
    public ModelAndView index()
    {
        return  new ModelAndView("test_device/index");
    }


}

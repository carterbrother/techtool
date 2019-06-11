package com.carterbrother.techtool.webdevtool;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description 欢迎页面
 * @date 2019年02月19日 6:27 PM
 * @Copyright (c) carterbrother
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}

package com.danbay.core;

import com.danbay.utils.DateUtils;
import com.danbay.utils.PropertiesUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class SystemInitListener extends ContextLoaderListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);

        //插入本次启动的静态资源版本号,这样每次启动之后，不用客户端再刷新浏览器缓存；
        event.getServletContext().setAttribute("version", DateUtils.getTimestamp());
        //设置系统名称,页面如果有用到，直接通过表达式获取
        event.getServletContext().setAttribute("system_title", PropertiesUtils.GetValueByKey("system.title"));
        //设置系统的静态资源路径
        event.getServletContext().setAttribute("mch_id", PropertiesUtils.GetValueByKey("device.mch_id"));
        event.getServletContext().setAttribute("app_key", PropertiesUtils.GetValueByKey("device.app_key"));
        event.getServletContext().setAttribute("app_secret", PropertiesUtils.GetValueByKey("device.app_secret"));
        event.getServletContext().setAttribute("notify_url", PropertiesUtils.GetValueByKey("device.notify_url"));
        event.getServletContext().setAttribute("app_name", PropertiesUtils.GetValueByKey("device.app_name"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }
}
package com.danbay.test_filter;

import com.danbay.utils.PropertiesUtils;
import com.danbay.utils.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 * 对访问资源进行  登录判断   权限判断；
 */
public class LoginPowerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String contextPath = httpServletRequest.getServletPath();

        List<String> noFilterUrlList= StringUtils.split2List(PropertiesUtils.GetValueByKey("no.need.login.url")," ");

        Optional<String> firstMeetUrl = noFilterUrlList.stream().filter(item -> contextPath.contains(item)).findFirst();

        if(firstMeetUrl.isPresent())
        {//存在，表示不用过滤；
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //执行登录判断；
        boolean hasLogin = checkLogin(httpServletRequest, httpServletResponse);

        if(!hasLogin)
        {//没有登录过，跳转到登录页面；

            toLoginPage(httpServletResponse);
            return;
        }

        //进行权限判断；是否具有权限；
        boolean hasPower = checkPower(httpServletRequest,httpServletResponse,contextPath);

        if(!hasPower)
        {//用户没有操作改url的权限，跳转到没有权限页面；

            toNoPowerPage(httpServletResponse);
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }

    private boolean checkPower(HttpServletRequest request, HttpServletResponse response,String contextPath) {
        String power_key = contextPath;
        int user_id = UserPowerUtil.checkLogin(request,response).userID;
        return UserPowerUtil.isUserHavePower(user_id,power_key);
    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
        return UserPowerUtil.checkLogin(request,response).isLogin();
    }

    /**
     * 跳转到无权限的页面；
     * @param response
     */
    private void toNoPowerPage(HttpServletResponse response) {
        try {
            response.sendRedirect("/test_filter/noPower.do");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toLoginPage(HttpServletResponse response) {
        try {
            response.sendRedirect("/test_filter/login.do");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

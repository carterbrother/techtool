package com.danbay.test_filter;

import com.danbay.db.danbay_admin.bll.Admin_PowerListBll;
import com.danbay.db.danbay_admin.bll.Admin_UserBll;
import com.danbay.db.danbay_admin.model.Admin_UserModel;
import com.danbay.utils.*;
import com.danbay.web.BasePage;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 * 进行登录和权限判断的方法；
 */
public class UserPowerUtil {

    /**
     * 综合管理平台权限，这里进行权限配置
     */
    public static final int systemID = 3;


    /**
     * 检测用户是否登录，返回存储的用户登录对象
     */
    public static BasePage checkLogin(HttpServletRequest request, HttpServletResponse response)
    {
        BasePage page = new BasePage();
        page.checkLogin(false,request,response);
        return page;
    }

    /**
     * 转向到登录页面
     * @return
     */
    public static ModelAndView login(HttpServletRequest request)
    {
        String url =   new BasePage().getLoginPage() + "?frompage=" +  EncodingUtils.urlEncode( RequestUtils.getUrl(request,true,true));
        return new ModelAndView(new RedirectView(url));
    }


    /**
     * 判断用户是否具有特定权限
     * @param userID
     * @param powerKey
     * @return
     */
    public static boolean isUserHavePower(int userID, String powerKey)
    {
        if (userID == 0) return false;
        if (powerKey == "") return false;

        //判断用户是否具有通用权限
//        return  true; //为了方便测试，同意设置为有权限


        powerKey = powerKey.substring(0,powerKey.lastIndexOf("."));

        List<String> powerListString = StringUtils.split2List(powerKey,"/").stream().filter(item -> StringUtils.isNotEmpty(item)).collect(Collectors.toList());

        if(powerListString.size()<2)
        {//不是合法的权限字符串；
            return false;
        }


        String class_keywords = powerListString.get(0);
        String poweritem_keywords = powerListString.get(1);


        return  new Admin_PowerListBll().isUserHavePower(userID,class_keywords,poweritem_keywords,systemID);
    }


    public static boolean user_logout(HttpServletRequest request, HttpServletResponse response) {
        UserPowerUtil.checkLogin(request,response).logout(response);
        return true;
    }


    public static boolean check_login(HttpServletRequest request, HttpServletResponse response) {
        return UserPowerUtil.checkLogin(request,response).isLogin();
    }

    public static boolean user_login( String username,  String password,HttpServletRequest request, HttpServletResponse response) {
        Admin_UserBll userBll = new Admin_UserBll();
        Admin_UserModel user = userBll.getRecordInfo(username);


        if (user.getId() == 0 || user.isLock())
        {
            return false;
        }
        if (!SecurityUtils.EncryptMD5(password).equals(user.getUserPassword()))
        {
            return false;
        }

        // 得到来访用户IP
        String ip = IPUtils.getIp(request);

        // 设置最后登录时间
        userBll.setLoginInfo(user.getId(), ip);

        BasePage page = new BasePage();
        page.userID = user.getId();
        page.userName = username;
        page.nickName = user.getNickName();
        page.setInfo(response);
        return true;
    }

}

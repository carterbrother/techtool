package com.danbay.test_device.util;

import com.danbay.utils.CommonUtils;
import com.danbay.utils.PropertiesUtils;

/**
 * Created by carter on 2017/6/2. Copyright © 2016 －2017 旦倍科技
 */
public class ConfigUtil {

    public static String getMch_id()
    {
        return CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("device.mch_id"));
    }
    public static String getApp_key()
    {
        return CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("device.app_key"));
    }
    public static String getApp_secret()
    {
        return CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("device.app_secret"));
    }
    public static String getNotify_url()
    {
        return CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("device.notify_url"));
    }

    public static String getPlatform_url()
    {
        return CommonUtils.getStringValue(PropertiesUtils.GetValueByKey("device.platform_url"));
    }





}

package com.danbay.socket;

import com.danbay.constant.enu.socket.WordConstants;
import com.danbay.dbaccess.DataRow;
import com.danbay.dbaccess.DataTable;
import com.danbay.socket.mina.CoAPParam;
import com.danbay.socket.mina.MinaClient;
import com.danbay.utils.CommonUtils;
import com.danbay.utils.JsonUtils;
import com.danbay.utils.RequestUtils;
import com.danbay.utils.model.ResponseObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by carter on 2017/3/17. Copyright © 2016 －2017 旦倍科技
 */
@Controller("CloudCommandController")
@RequestMapping("/socket/cloud/command")
public class CloudCommandController {


    @RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("socket/cloud/index");

        DataTable dataTable = new DataTable();

        DataRow dataRow = new DataRow();
        dataRow.addColumn("deviceId","a92f404dd287434980fb6cf7cf1a9046");
        dataRow.addColumn("centerDeviceId","ab71d1b8ca762fc4e086fc85205cfaf7");
        dataTable.addRow(dataRow);



        DataRow dataRow2 = new DataRow();
        dataRow2.addColumn("deviceId","ca2f0c160943c891443af4a5f5225cc0");
        dataRow2.addColumn("centerDeviceId","5ff7d0134802d325dc282ac7cae3559a");
        dataTable.addRow(dataRow2);

        modelAndView.addObject("lockList",dataTable.rows);


        return modelAndView;
    }

    @RequestMapping("/ctrl/{command}.do")
    public void command(@PathVariable("command") String command, HttpServletRequest request, HttpServletResponse response)
    {
        String deviceID = CommonUtils.getStringValue(request.getParameter("DeviceID"));
        String fatherDeviceID = CommonUtils.getStringValue(request.getParameter("fatherDeviceID"));

        command = "ctrl/"+command;
        if(WordConstants.NEW_PWD.equals(command))
        {

            int type = CommonUtils.getIntValue(request.getParameter("type"));
            String psw = CommonUtils.getStringValue(request.getParameter("pwd"));
            String alias = CommonUtils.getStringValue(request.getParameter("alias"));

            CoAPParam coAPParam = new CoAPParam();
            coAPParam.setNew_pwd(command,type,alias,psw,fatherDeviceID,deviceID);
            new MinaClient().sendCommand(coAPParam);
        }else if(WordConstants.GET_ALIAS.equals(command)){
            CoAPParam coAPParam = new CoAPParam();
            coAPParam.setGet_alias(command,fatherDeviceID,deviceID);
            new MinaClient().sendCommand(coAPParam);
        }else if(WordConstants.EDIT_PWD.equals(command))
        {

            int type = CommonUtils.getIntValue(request.getParameter("type"));
            String psw = CommonUtils.getStringValue(request.getParameter("pwd"));
            String alias = CommonUtils.getStringValue(request.getParameter("alias"));

            CoAPParam coAPParam = new CoAPParam();
            coAPParam.setNew_pwd(command,type,alias,psw,fatherDeviceID,deviceID);
            new MinaClient().sendCommand(coAPParam);
        }else if(WordConstants.DELETE_PWD.equals(command))
        {

            int type = CommonUtils.getIntValue(request.getParameter("type"));
            String alias = CommonUtils.getStringValue(request.getParameter("alias"));

            CoAPParam coAPParam = new CoAPParam();
            coAPParam.setNew_pwd(command,type,alias,"",fatherDeviceID,deviceID);
            new MinaClient().sendCommand(coAPParam);
        }
        ResponseObject  responseObject = new ResponseObject();
        responseObject.setStatus("200");
        responseObject.setMessage("指令发送成功");
        RequestUtils.write(response, JsonUtils.getJson(responseObject));



    }


}

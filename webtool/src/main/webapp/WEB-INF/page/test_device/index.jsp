<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/2
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备控制平台接口测试页面</title>
</head>
<style type="text/css">
    .deviceImg1_1 {
        display: block;
        margin: 10px auto;
        width: 30px;
        height: 35px;
        background: url(http://120.77.236.134:8081/system/img/deviceImg.png) -6px -59px no-repeat;
    }

    .deviceImg2_1 {
        display: block;
        margin: 10px auto;
        width: 30px;
        height: 35px;
        background: url(http://120.77.236.134:8081/system/img/deviceImg.png) -55px -58px no-repeat;
    }
    .deviceImg3_1 {
        display: block;
        margin: 10px auto;
        width: 30px;
        height: 35px;
        background: url(http://120.77.236.134:8081/system/img/deviceImg.png) -105px -60px no-repeat;
    }

    .device {
        position: relative;
        display: table;
        float: left;
        width: 33%;
    }

    table td{border:1px solid #F00}

</style>
<body>
<h1>设备控制平台接口测试页面(卡特)</h1>
        <table style="border:1px solid #F00;width: 100%;">

            <thead>
            <tr>
                <td>设备类型</td>
                <td>图标</td>
                <td>接口说明(点开设备类型下的链接到具体接口的测试页面)</td>
                <td>文档地址</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td style="width: 5%"><a href="/test_device/gateway/index.do" >中控网关</a></td>
                <td style="width: 2%"> <div class="device"><span class="deviceImg1_1"></span></div></td>
                <td>包含中控的打开入网，重启中控，远程更新 接口</td>
                <td><a href="http://172.13.31.222/svn/公司项目/研发项目/4、接口文档/设备与云端接口文档/系统V1.5/设备与云端通讯-中控分册V1.1.docx">设备与云端通讯-中控分册V1.1.docx</a></td>
            </tr>
            <tr style="width: 100px;">
                <td><a href="/test_device/lock/index.do" >智能门锁</a></td>
                <td><div class="device"><span class="deviceImg2_1"></span></div></td>
                <td>包含门锁的增加密码，删除密码，修改密码，冻结解冻密码 ... 接口</td>
                <td><a href="http://172.13.31.222/svn/公司项目/研发项目/4、接口文档/设备与云端接口文档/系统V1.5/设备与云端通讯-门锁分册V1.0.docx">设备与云端通讯-门锁分册V1.0.docx</a></td>
            </tr>
            <tr>
                <td><a href="/test_device/wireless/index.do" >无线水电表</a></td>
                <td><div class="device" style="background-color: green;"><span class="deviceImg3_1"></span></div></td>
                <td>包含水电表的报数,读取日冻结,断电，供电 ... 接口</td>
                <td><a href="http://172.13.31.222/svn/公司项目/研发项目/4、接口文档/设备与云端接口文档/系统V1.5/设备与云端通讯-门锁分册V1.0.docx">设备与云端通讯-门锁分册V1.0.docx</a></td>
            </tr>
            <tr>
                <td><a href="/test_device/wire/index.do" >有线水电表</a></td>
                <td> <div class="device" style="background-color: red;"><span class="deviceImg3_1"></span></div></td>
                <td>包含水电表的读取日冻结数据，实操数据，拉合闸... 接口</td>
                <td><a href="http://172.13.31.222/svn/公司项目/研发项目/4、接口文档/设备与云端接口文档/系统V1.5/设备与云端通讯-门锁分册V1.0.docx">设备与云端通讯-门锁分册V1.0.docx</a></td>
            </tr>
            </tbody>
        </table>




</body>
</html>

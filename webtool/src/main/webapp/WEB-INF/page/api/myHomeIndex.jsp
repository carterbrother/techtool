<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的系统登录成功页面</title>
</head>
<body>
<h1>我的系统登录成功页面</h1>
<%--http://www.danbay.cn/system   http://localhost:8680/system  --%>
<form action="http://localhost:8680/system/deviceInfo/getDeviceIistAll" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;">
    <input type="submit" value="查询所有设备列表">
</form>
<hr>

<form action="http://localhost:8680/system/deviceInfo/getLockIist" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;">
    <input type="submit" value="查询门锁设备列表">
</form>
<hr>

<form action="http://localhost:8680/system/deviceInfo/getLockInfo" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><input type="submit" value="查询单个门锁设备信息（根据设备ID）">
</form>
<hr>


<form action="http://localhost:8680/system/deviceInfo/getLockPwdList" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><input type="submit" value="查询门锁密码列表（根据设备ID）">
</form>
<hr>


<form action="http://localhost:8680/system/deviceInfo/getEnergyDeviceIist" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="submit" value="查询能耗设备列表">
</form>

<form action="http://localhost:8680/system/deviceInfo/getEnergyDeviceInfo" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><input type="submit" value="查询单个能耗设备信息（根据设备ID）">
</form>
<hr>


<form action="http://localhost:8680/system/deviceInfo/getEnergyDailyReading" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><br>
    <input type="text" name="readTime" > 查看日期 查看日期 (格式 :yyyy-MM-dd)<br>
    <input type="submit" value="查询当日能耗设备示数">
</form>
<hr>


<hr><hr>
<form action="http://localhost:8680/system/deviceCtrl/lockPwd/addPwd" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><br>
    <input type="number" name="password" placeholder="密码 6位数字"><br>
    <select name="pwdType">
        <option value="0">临时密码</option>
        <option value="3" selected="selected">租客密码</option>
    </select>

    <input type="submit" value="门锁新增密码">
</form>



<hr>
<form action="http://localhost:8680/system/deviceCtrl/lockPwd/editPwd" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><br>
    <input type="number" name="password" placeholder="密码 6位数字"><br>
    <input type="number" name="pwdID" placeholder="密码 ID"><br>
    <select name="pwdType">
        <option value="2">管家密码</option>
        <option value="3" selected="selected">租客密码</option>
    </select>

    <input type="submit" value="门锁修改密码">
</form>


<hr>
<form action="http://localhost:8680/system/deviceCtrl/lockPwd/updatePwd" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><br>
    <%--<input type="number" name="password" placeholder="密码 6位数字"><br>--%>
    <input type="number" name="pwdID" placeholder="密码 ID"><br>
    <select name="pwdType">
        <option value="2">管家密码</option>
        <option value="3" selected="selected">租客密码</option>
    </select>
    <select name="updateType">
        <option value="0">冻结</option>
        <option value="1" selected="selected">启用</option>
    </select>

    <input type="submit" value="冻结或启用密码">
</form>



<hr>
<form action="http://localhost:8680/system/deviceCtrl/lockPwd/delPwd" method="post" target="_blank">
    <input type="text" name="mtoken" value="${mtoken}" style="width: 600px;"><br>
    <input type="text" name="deviceId" placeholder="设备 ID"><br>
    <input type="number" name="pwdID" placeholder="密码 ID"><br>
    <select name="pwdType">
        <option value="2">管家密码</option>
        <option value="3" selected="selected">租客密码</option>
    </select>

    <input type="submit" value="门锁删除密码">
</form>


</body>
</html>

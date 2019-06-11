<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<html>
<style>
    .button{ height: 21px;
        line-height: 21px;
        padding: 0 11px;
        background: #02bafa;
        border: 1px #26bbdb solid;
        border-radius: 3px;
        color: #fff;
        display: inline-block;
        text-decoration: none;
        font-size: 12px;
        outline: none;}
</style>
<body>
<h1>门锁列表</h1>
<table>
    <tr>
        <td>设备ID</td>
        <td>中控设备ID</td>
        <td>在线状态</td>
        <td>锁（云端->设备）操作</td>
        <td>锁（设备->云端）操作</td>
    </tr>
    <c:forEach items="${lockList}" var="lock">
        <tr>
            <td>${lock.getColumnValue('deviceID')}</td>
            <td>${lock.getColumnValue('centerdeviceID')}</td>
            <td>${lock.getColumnValue('state')}</td>
            <td>
                <a href="#" onclick="javascript:sel_show();" class="button">中控进入Zigbee入网模式</a>&nbsp;
                <a href="#" onclick="javascript:sel_show('new_pwd','${lock.getColumnValue('centerdeviceID')}','${lock.getColumnValue('deviceID')}');" class="button">新增密码</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show('edit_pwd','${lock.getColumnValue('centerdeviceID')}','${lock.getColumnValue('deviceID')}');" class="button">修改密码</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">删除密码</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">暂停/恢复密码</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">同步门锁密码</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">更新密码别名</a>&nbsp;
                <a href="#" onclick="javascript:sel_show('get_alias','${lock.getColumnValue('centerdeviceID')}','${lock.getColumnValue('deviceID')}');" class="button">获取用户密码别名</a>&nbsp;<br>
            </td>

            <td>
                <a href="#" onclick="javascript:sel_show();" class="button">设备注册</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">设备登录</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">设备登出</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">设备心跳</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">开门信息上报</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">报警信息上报</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">权限动态上报</a>&nbsp;
                <a href="#" onclick="javascript:sel_show();" class="button">定期数据同步</a>&nbsp;<br>
                <a href="#" onclick="javascript:sel_show();" class="button">更新别名结果上报</a>&nbsp;
            </td>
        </tr>
    </c:forEach>
</table>
<hr>
<div>
<div class="new_pwd" style="display: none;">
    <h3>老锁管家密码不能新增只能有1个  ， 新锁管家密码可以新增，允许5个</h3>
    密码类型：<select class="new_pwd_type">
        <%--<option value="1">管理员密码</option>--%>
        <option value="2">管家密码</option>
        <option value="3">房客密码</option>
        <%--<option value="4">感应卡</option>--%>
        <option value="0">临时密码</option>
    </select>
    密码：<input type="number" class="new_pwd_pwd" maxlength="6">
    密码别名： <input type="text" class="new_pwd_alias" maxlength="8">
    <input type="button" value="新增密码" class="button new_pwdButton"/>
</div>


    <div class="edit_pwd" style="display: none;">
        密码类型：<select class="edit_pwd_type">
        <option value="1">管理员密码</option>
        <option value="2">管家密码</option>
        <option value="3">房客密码</option>
    </select>
        密码：<input type="number" class="edit_pwd_pwd" maxlength="6">
        密码别名： <input type="text" class="edit_pwd_alias" maxlength="8"> 管理员密码别名：gly   管家密码别名：  gj   租客必须获取
        <input type="button" value="修改密码" class="button edit_pwdButton"/>
    </div>



    <div class="del_pwd" style="display: none;">
        密码类型：<select class="del_pwd_type">
        <%--<option value="1">管理员密码</option>--%>
        <option value="2">管家密码</option>
        <option value="3">房客密码</option>
        <option value="0">临时密码</option>
    </select>
        密码别名： <input type="text" class="del_pwd_alias" maxlength="8">  管家密码别名：  gj   租客,临时密码必须获取
        <input type="button" value="修改密码" class="button del_pwdButton"/>
    </div>


    <div class="get_alias" style="display: none;">
        <input type="button" value="获取密码别名" class="button get_aliasButton"/>
    </div>


</div>
<hr>

选择的中控：<span class="centerDeviceID"></span><br>
选择的设备：<span class="lockDeviceID"></span><br>
<span class="result"></span>




</body>
<script type="text/javascript" src="http://resource.danbay.cn/common/js/jquery-1.9.1.min.js"></script>
<script>

    function sel_show(divClass,centerDeviceId,deviceID) {
        $("."+divClass).show();
        $("."+divClass).siblings().hide();
        $(".centerDeviceID").empty().append(centerDeviceId);
        $(".lockDeviceID").empty().append(deviceID);
    }


    $(function () {

        $(".new_pwdButton").bind("click",function () {

            $.post("/socket/cloud/command/ctrl/new_pwd.do",{type:$(".new_pwd_type").val(),pwd:$(".new_pwd_pwd").val(),alias:$(".new_pwd_alias").val(),fatherDeviceID:$(".centerDeviceID").html(),DeviceID:$(".lockDeviceID").html()},function (data) {
                if(data.status=='200'){
                    $(".result").empty().append(data.message).css("color","green");
                }else
                {
                    $(".result").empty().append(data.message).css("color","red");
                }
            },'json');

        });

        $(".edit_pwdButton").bind("click",function () {

            $.post("/socket/cloud/command/ctrl/edit_pwd.do",{type:$(".edit_pwd_type").val(),pwd:$(".edit_pwd_pwd").val(),alias:$(".edit_pwd_alias").val(),fatherDeviceID:$(".centerDeviceID").html(),DeviceID:$(".lockDeviceID").html()},function (data) {
                if(data.status=='200'){
                    $(".result").empty().append(data.message).css("color","green");
                }else
                {
                    $(".result").empty().append(data.message).css("color","red");
                }
            },'json');

        });

        $(".del_pwdButton").bind("click",function () {

            $.post("/socket/cloud/command/ctrl/del_pwd.do",{type:$(".del_pwd_type").val(),alias:$(".del_pwd_alias").val(),fatherDeviceID:$(".centerDeviceID").html(),DeviceID:$(".lockDeviceID").html()},function (data) {
                if(data.status=='200'){
                    $(".result").empty().append(data.message).css("color","green");
                }else
                {
                    $(".result").empty().append(data.message).css("color","red");
                }
            },'json');

        });



        $(".get_aliasButton").bind("click",function () {

            $.post("/socket/cloud/command/ctrl/get_alias.do",{fatherDeviceID:$(".centerDeviceID").html(),DeviceID:$(".lockDeviceID").html()},function (data) {
                if(data.status=='200'){
                    $(".result").empty().append(data.message).css("color","green");
                }else
                {
                    $(".result").empty().append(data.message).css("color","red");
                }
            },'json');

        });


    });
</script>



</html>

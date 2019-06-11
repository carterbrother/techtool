<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="gateway" ng-controller="gatewayCtrl">
<head>
    <title>智能门锁-接口测试页面</title>
</head>
<script src="http://resource.danbay.cn/common/js/angular-1.2.29/angular.min.js"></script>
<style>
    table td{border:1px solid #F00}
</style>
<body>


<hr>
<h2><span style="color: green;">${applicationScope.getOrDefault("app_name","暂未设置")}</span>基础配置数据 ---智能门锁-接口测试页面</h2>
<table style="width: 100%;">
    <tr>
        <td><span>商户id:${applicationScope.getOrDefault("mch_id","暂未设置")}</span>&nbsp;&nbsp;&nbsp;<span>应用KEY:${applicationScope.getOrDefault("app_key","暂未设置")}</span>&nbsp;&nbsp;&nbsp;<span>应用秘钥:${applicationScope.getOrDefault("app_secret","暂未设置")}</span></td>
    </tr>
    <tr>
        <td><span>通知url:${applicationScope.getOrDefault("notify_url","暂未设置")}</span></td>
    </tr>
    <tr>
        <td><input  type="text"  ng-model="dev_id" style="width: 400px;" placeholder="设置的中控设备id" /> <input type="button" value="设置设备id" ng-click="setDeviceId()"></td> </tr>

    <tr> <td><input  type="text"  ng-model="lock_dev_id" style="width: 400px;" placeholder="设置的门锁设备id" /> <input type="button" value="设置的门锁设备id" ng-click="setLockDeviceId()"></td>
    </tr>
    <tr><td>接口返回状态码：<span >{{code}}</span></td></tr>
    <tr>  <td>接口返回消息：<span >{{message}}</span></td></tr>
    <tr>  <td>接口返回结果数据：<span >{{result}}</span></td></tr>

</table>
<h2>命令结果查询</h2>
<table style="width: 100%;">
    <tbody>
    <tr>
        <td>查询命令结果：</td>
        <td><input type="number" placeholder="请输入要查询的指令id即返回的token_id" ng-model="token_id"></td>
        <td> <input type="button" value="查询命令执行结果" ng-click="command_query()"></td>
    </tr>


    </tbody>
</table>
<hr>
<h2>接口测试</h2>
<table style="width: 100%;">

    <thead>
    <tr>
        <td>序号说明</td>
        <td>接口名称</td>
        <td>输入数据</td>
        <td>提交测试</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>接口：1</td>
        <td>新增密码，请根据密码别名查询设备恢复的access数据确认结果！</td>
        <td>请输入密码：<input type="number" placeholder="请输入密码，12位以内数字" ng-model="pwd"><br>
            请输入密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_psw"><br>
            选择密码类型：<select ng-model="type"><option value="2">管家密码</option><option value="3">房客密码</option><option value="0" selected="selected">临时密码</option></select></td>
        <td> <input type="button" value="新增密码" ng-click="new_pwd()"></td>
    </tr>

    <tr>
        <td>接口：2</td>
        <td>修改密码，请根据密码别名查询设备恢复的access数据确认结果！</td>
        <td>请输入密码：<input type="number" placeholder="请输入密码，12位以内数字" ng-model="pwd"><br>
            请输入密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_psw"><br>
            选择密码类型：<select ng-model="type"><option value="2">管家密码</option><option value="3">房客密码</option><option value="0" selected="selected">临时密码</option></select></td>
        <td><input type="button" value="修改密码" ng-click="edit_pwd()"></td>
    </tr>

    <tr>
        <td>接口：3</td>
        <td>删除密码，请根据密码别名查询设备恢复的access数据确认结果！</td>
        <td>
            请输入密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_psw"><br>
            选择密码类型：<select ng-model="type"><option value="2">管家密码</option><option value="3">房客密码</option><option value="0" selected="selected">临时密码</option></select></td>
        <td><input type="button" value="删除密码" ng-click="del_pwd()"></td>
    </tr>

    <tr >
        <td>接口：4</td>
        <td>暂停/恢复密码，请根据密码别名查询设备恢复的access数据确认结果！</td>
        <td>
            请输入密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_psw"><br>
            选择密码类型：<select ng-model="type"><option value="2">管家密码</option><option value="3">房客密码</option><option value="1" selected="selected">管理员密码</option></select>
            选择操作类型：<select ng-model="state"><option value="1">冻结</option><option value="0">恢复</option></select>
        </td>
        <td><input type="button" value="冻结/解冻密码" ng-click="stop_pwd()"></td>
    </tr>
    <tr style="font-style: oblique; color: blue;">
        <td>接口：5</td>
        <td>获取存储状态</td>
        <td>没实现</td>
        <td></td>
    </tr>

    <tr style="font-style: oblique; color: blue;">
        <td>接口：6</td>
        <td>获取电量信息</td>
        <td>没实现</td>
        <td></td>
    </tr>

    <tr style="font-style: oblique; color: blue;">
        <td>接口：7</td>
        <td>获取产品信息</td>
        <td>没实现</td>
        <td></td>
    </tr>

    <tr style="font-style: oblique; color: blue;">
        <td>接口：8</td>
        <td>4.8	同步门锁密码</td>
        <td>暂时用不上</td>
        <td></td>
    </tr>

    <tr>
        <td>接口：9</td>
        <td>4.9	更新密码别名</td>
        <td>
            请输入旧密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_old"><br>
            请输入新密码别名：<input type="number" placeholder="请输入密码别名，12位以内数字" ng-model="alias_new"><br>
            选择密码类型：<select ng-model="type"><option value="2">管家密码</option><option value="3">房客密码</option></select></td>
        <td><input type="button" value="4.9	更新密码别名" ng-click="update_alias()"></td>
    </tr>

    <%--<tr style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：10</td>--%>
        <%--<td>初始化门锁,也是通过查看权限动态上报信息</td>--%>
        <%--<td>没实现</td>--%>
        <%--<td></td>--%>
    <%--</tr>--%>

    <%--<tr  style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：11</td>--%>
        <%--<td>4.11	设置心跳周期</td>--%>
        <%--<td>--%>
            <%--没实现</td>--%>
        <%--<td></td>--%>
    <%--</tr>--%>

    <%--<tr  style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：12</td>--%>
        <%--<td>4.12	设置语音开关</td>--%>
        <%--<td>没实现</td>--%>
        <%--<td></td>--%>
    <%--</tr>--%>

    <%--<tr  style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：13</td>--%>
        <%--<td>4.13	调节门锁音量</td>--%>
        <%--<td>没实现</td>--%>
        <%--<td></td>--%>
    <%--</tr>--%>

    <%--<tr  style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：14</td>--%>
        <%--<td>4.14	设置报警开关</td>--%>
        <%--<td>没实现</td>--%>
        <%--<td></td>--%>
    <%--</tr>--%>

    <%--<tr  style="font-style: oblique; color: blue;">--%>
        <%--<td>接口：15</td>--%>
        <%--<td>4.15	设置门锁语言</td>--%>
        <%--<td>没实现</td>--%>
    <%--</tr>--%>

    <tr>
        <td>接口：16</td>
        <td>4.16	获取用户密码别名</td>
        <td></td>
        <td><input type="button" value="4.16	获取用户密码别名" ng-click="get_alias()"></td>
    </tr>



    </tbody>
</table>



</body>

<script type="text/javascript">
    (function (angular) {
        angular.module("gateway",[])
            .controller("gatewayCtrl",["$scope","$http",function ($scope,$http) {
                $scope.code='';
                $scope.message='';
                $scope.result='';
                $scope.dev_id='${sessionScope.getOrDefault("gateway_dev_id","fdf9388308dea4ffd41e8a3096bb2982")}';
                $scope.lock_dev_id='${sessionScope.getOrDefault("lock_dev_id","0ffb51df6590b993d3c5a3e1b6277619")}';

                $scope.setDeviceId=function () {
                    $http({method:"post",url:'/test_device/gateway/setDeviceId.do',params:{"dev_id":$scope.dev_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.dev_id =data.result;
                        });

                }
                $scope.setLockDeviceId=function () {
                    $http({method:"post",url:'/test_device/lock/setDeviceId.do',params:{"lock_dev_id":$scope.lock_dev_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.lock_dev_id =data.result;
                        });

                }

                $scope.new_pwd=function () {
                    $http({method:"post",url:'/test_device/lock/new_pwd.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id,"type":$scope.type,"pwd":$scope.pwd,"alias":$scope.alias_psw}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }

                $scope.edit_pwd=function () {
                    $http({method:"post",url:'/test_device/lock/edit_pwd.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id,"type":$scope.type,"pwd":$scope.pwd,"alias":$scope.alias_psw}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }


                $scope.del_pwd=function () {
                    $http({method:"post",url:'/test_device/lock/del_pwd.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id,"type":$scope.type,"alias":$scope.alias_psw}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }


                $scope.stop_pwd=function () {
                    $http({method:"post",url:'/test_device/lock/stop_pwd.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id,"type":$scope.type,"state":$scope.state,"alias":$scope.alias_psw}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }


                $scope.update_alias=function () {
                    $http({method:"post",url:'/test_device/lock/update_alias.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id,"type":$scope.type,"old_alias":$scope.alias_old,"new_alias":$scope.alias_new}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }

                $scope.get_alias=function () {
                    $http({method:"post",url:'/test_device/lock/get_alias.do',params:{"dev_id":$scope.lock_dev_id,"father_id":$scope.dev_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }







                    $scope.reboot=function () {
                    $http({method:"post",url:'/test_device/gateway/reboot.do',params:{"dev_id":$scope.dev_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }

                $scope.upgrade=function () {
                    $http({method:"post",url:'/test_device/gateway/upgrade.do',params:{"dev_id":$scope.dev_id,"url":$scope.url,"md5":$scope.md5,"force":$scope.force}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }


                $scope.command_query=function () {
                    $http({method:"post",url:'/test_device/gateway/command_query.do',params:{"dev_id":$scope.dev_id,"token_id":$scope.token_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.result =data.result;
                        });

                }




            }]);
    })(angular);
</script>
</html>

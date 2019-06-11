<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/2
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="gateway" ng-controller="gatewayCtrl">
<head>
    <title>中控网关-接口测试页面</title>
</head>
<script src="http://resource.danbay.cn/common/js/angular-1.2.29/angular.min.js"></script>
<style>
    table td{border:1px solid #F00}
</style>
<body>

<h1>中控网关-接口测试页面</h1>

<hr>
<h2><span style="color: green;">${applicationScope.getOrDefault("app_name","暂未设置")}</span>基础配置数据</h2>
<table style="width: 100%;">
    <tr>
        <td><span>商户id:${applicationScope.getOrDefault("mch_id","暂未设置")}</span>&nbsp;&nbsp;&nbsp;<span>应用KEY:${applicationScope.getOrDefault("app_key","暂未设置")}</span>&nbsp;&nbsp;&nbsp;<span>应用秘钥:${applicationScope.getOrDefault("app_secret","暂未设置")}</span></td>
    </tr>
    <tr>
        <td><span>通知url:${applicationScope.getOrDefault("notify_url","暂未设置")}</span></td>
    </tr>
    <tr>
        <td><input  type="text"  ng-model="dev_id" style="width: 400px;" placeholder="设置的中控设备id" /> <input type="button" value="设置设备id" ng-click="setDeviceId()"></td>
    </tr>
    <tr><td>接口返回状态码：<span >{{code}}</span></td></tr>
    <tr>  <td>接口返回消息：<span >{{message}}</span></td></tr>
    <tr>  <td>接口返回结果数据：<span >{{result}}</span></td></tr>

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
            <td>设备入网</td>
            <td><input type="text" placeholder="请输入要入网的mac地址" ng-model="mac_address"></td>
            <td> <input type="button" value="设备入网" ng-click="allow_ap()"></td>
        </tr>

        <tr>
            <td>接口：2</td>
            <td>重启中控</td>
            <td><%--<input type="text" placeholder="请输入中控的设备id">--%></td>
            <td> <input type="button" value="重启中控" ng-click="reboot()"></td>
        </tr>

        <tr>
            <td>接口：3</td>
            <td>远程更新</td>
            <td><input type="text" placeholder="固件的url" ng-model="url"><input type="text"  ng-model="md5"  placeholder="固件的md5">  选择是否强更:<select ng-model="force"><option value="0" selected="selected">不强更</option><option value="1">强更</option></select></td>
            <td> <input type="button" value="远程更新" ng-click="upgrade()"></td>
        </tr>
        </tbody>
    </table>
<h2>命令结果查询</h2>
<table style="width: 100%;">

    <thead>
    <tr>
        <td>接口名称</td>
        <td>输入数据</td>
        <td>提交测试</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>查询命令结果：</td>
        <td><input type="text" placeholder="请输入要查询的指令id即返回的token_id" ng-model="token_id"></td>
        <td> <input type="button" value="查询命令执行结果" ng-click="command_query()"></td>
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

                $scope.setDeviceId=function () {
                    $http({method:"post",url:'/test_device/gateway/setDeviceId.do',params:{"dev_id":$scope.dev_id}})
                        .success(function (data) {
                            $scope.code = data.status;
                            $scope.message=data.message;
                            $scope.dev_id =data.result;
                        });

                }

                $scope.allow_ap=function () {
                    $http({method:"post",url:'/test_device/gateway/allow_ap.do',params:{"mac_address":$scope.mac_address,"dev_id":$scope.dev_id}})
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

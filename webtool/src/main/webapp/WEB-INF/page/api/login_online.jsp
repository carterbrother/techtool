<%@ page import="com.danbay.utils.RndUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>蛋贝智能家居就API系统登录示例页面</title>
</head>
<body>
<%--   http://localhost:8680/system/connect   http://www.danbay.cn/system/connect --%>
        <div>
            <form action="http://www.danbay.cn/system/connect" method="post">
                <table>
                    <tr>
                        <td>用户名：</td><td><input type="text" name="mc_username">api商户接入的账号</td>
                    </tr>
                    <tr>
                        <td>密码：</td><td><input  name="mc_password" type="password">api商户接入的密码</td>
                    </tr>
                    <tr>
                        <td>回调地址：</td><td><input type="text" name="ticket_consume_url" value="http://<%=request.getServerName()+":"+request.getServerPort()%>/tool/api/test/success.do">登录ok之后跳转的页面</td>
                    </tr>
                    <tr>
                        <td>登录失败地址：</td><td><input type="text" name="return_url" value="http://www.danbay.cn">登录失败之后跳转的页面</td>
                    </tr>
                    <tr>
                        <td>随机数：</td><td><input type="text" name="random_code" value="<%=RndUtils.getRandNum(100000000)%>">登录的随机数</td>
                    </tr>
                    <tr>
                        <td></td><td><input type="submit" value="登录danbay api系统"></td>
                    </tr>
                </table>
            </form>
        </div>
</body>
</html>

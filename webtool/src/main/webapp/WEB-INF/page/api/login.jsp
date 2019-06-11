<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<%--   http://localhost:8680/system/connect   http://www.danbay.cn/system/connect --%>
        <div>
            <form action="http://localhost:8680/system/connect" method="post">
                <table>
                    <tr>
                        <td>用户名：</td><td><input type="text" name="mc_username"></td>
                    </tr>
                    <tr>
                        <td>密码：</td><td><input type="text" name="mc_password"></td>
                    </tr>
                    <tr>
                        <td>回调地址：</td><td><input type="text" name="ticket_consume_url" value="http://localhost:8980/api/test/success.do"></td>
                    </tr>
                    <tr>
                        <td>登录失败地址：</td><td><input type="text" name="return_url"></td>
                    </tr>
                    <tr>
                        <td>随机数：</td><td><input type="text" name="random_code"></td>
                    </tr>
                    <tr>
                        <td></td><td><input type="submit" value="登录danbay"></td>
                    </tr>
                </table>
            </form>
        </div>
</body>
</html>

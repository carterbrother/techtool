<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" session="false"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" >
    <title>蛋贝工程管理后台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="http://resource.danbay.cn/common/css/tip.css" type="text/css" />
    <link rel="stylesheet" href="http://resource.danbay.cn/admin/css/login.css" type="text/css" />
    <script type="text/javascript" src="http://resource.danbay.cn/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="http://resource.danbay.cn/common/js/default.js"></script>
</head>
<body  tabindex="1">
<div id="loginHeader">
    <div class="logoImg">
        <img src="http://resource.danbay.cn/common/images/logo.png" class="logoImg">
    </div>
    <span class="logoName">测试系统</span>
</div>
<div id="loginBox2">

    <div class="loginCont">
        <h3 class="logoTop">
            测试系统
        </h3>
        <div class="tab_login">
            <span class="login_text">登录</span>
            <%--<span class="login_mobilePhone">手机版</span>--%>
        </div>
        <div class="login_text_con">
            <form id="Form1" method="post" action="${currenturl}">
                <div class="username" id="username">
                    <span class="nameIcon"></span>
                    <input class="text" name="txtAccount" autocomplete="off" type="text"  title="请输入用户名" value="admin_danbay" >
                </div>
                <div class="password" id="password">
                    <span class="passIcon"></span>
                    <input class="text" name="txtPassword" autocomplete="off" type="password" title="请输入密码" value="abcabc">
                </div>
                <%--<div class="code" id="code">--%>
                    <%--<span class="codeIcon"></span>--%>
                    <%--<input class="text" name="verifyCode" id="txtCode" autocomplete="off"  class="text" MaxLength="4" title="请输入验证码" value="请输入验证码">--%>
                    <%--<span>--%>
                  <%--<img src="/verifyCode/verifyCodeAjax.do?t=0" id="validateimg" onclick="this.src=this.src.substring(0,this.src.indexOf('=')+1)+Math.random();return false;" title="看不清,点击更换验证码" border="0" />--%>
              <%--</span>--%>
                    <%--<span class="cliCode"><a title="看不清,点击更换验证码" id="changeimg" href="javascript:;">看不清，换一张</a></span>--%>
                <%--</div>--%>
                <div class="login" id="login">
                    <div class="button"><input type="submit" tabindex="4" name="submitButton" id="btnEnter"  class="btnEnter" value="登录"  /></div>
                    <%--<div class="button">--%>
                        <%--<input type="button" class="enrolment" value="注册" name="enrolmentButton">--%>
                    <%--</div>--%>
                </div>
                <div class="login_fot">
                    <p>Copyright &copy; 2016-2017 旦倍科技 粤ICP备16036034号</p>
                </div>

            </form>
        </div>
        <div class="login_mobilePhone_con active">
            <div class="erweima">
                <p>正在开发</p>
                <p>敬请期待</p>
            </div>
        </div>
    </div>
</div>

<div class="tanChu" id="tanChu">
    <div class="bgZheZhao" style="display: none"></div>
    <div class="settip_warp fail_style" style="display: none">
        <div class="tipCont">
            <div class="title">提示信息</div>
            <div class="settip">
                <div class="settip_info">
                    <span class="message">验证码有误或已失效</span>
                </div>
                <a class="button-cancel" id="button-cancel" href="javascript:void(0)"> <span id="close"> << 返回</span></a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function () {
        $('#changeimg').click(function(){
            var url = "/verifyCode/verifyCodeAjax.do?t=" + Math.random();
            $("#validateimg").attr("src", url);
        });

        $("#Form1 .text").on("focus",function(){
            var _this = this;
            palceHolderFocus(_this);
        })
        $("#Form1 .text").on("blur",function(){
            var _this = this;
            palceHolderBlur(_this);
        })




        $("#btnEnter").on("click",function(){
            var passcode = $("#passcode input").val();
            var username = $("#username input").val();
            var password = $("#password input").val();
            var code = $("#code input").val();
            if(passcode=="请输入通行码" || username=="请输入用户名" || password=="" || code=="请输入验证码"){
                $(".message").html("请输入登陆信息");
                $(".settip_warp").fadeIn();
                $(".bgZheZhao").fadeIn();
                return false;
            }
        });
        $("#button-cancel").on("click",function(){
            $(".settip_warp").fadeOut();
            $(".bgZheZhao").fadeOut();
        });

        $("#labelTxt").on("click",function(){
            $(this).hide();
            $("#password input").val("");
            $("#password input").focus();
        });
        $('span.login_text').on('click',function(){

            if($('div.login_text_con').hasClass('active')){
                $('div.login_text_con').removeClass('active')
                $('div.login_mobilePhone_con').addClass('active')
            }
        });
        $('span.login_mobilePhone').on('click',function(){
            if($('div.login_mobilePhone_con').hasClass('active')){
                $('div.login_mobilePhone_con').removeClass('active')
                $('div.login_text_con').addClass('active')
            }
        })
    });
</script>
</body>
</html>

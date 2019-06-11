<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<html>
<body>
<h1>webSocket测试页面</h1>

<textarea style="width: 400px;height: 200px;" id="sendText"></textarea><input type="button" value="发送消息" onclick="javascript:send();">
<hr>
<div style="width: 400px;height: 200px;" id="getText"></div><input type="button" value="清空消息" onclick="javascript:document.getElementById('getText').innerHTML=''">
</body>

<script type="text/javascript">
    var web_socket = null;

    if('WebSocket' in window)
    {
        web_socket = new WebSocket("ws://127.0.0.1:8080/wstest");
    }else
    {
        alert('该浏览器版本不支持webSocket');
    }

    web_socket.onopen=function () {
        document.getElementById("getText").innerHTML+='<br>'+'打开连接';
    }

    web_socket.onclose=function () {
        document.getElementById("getText").innerHTML+='<br/>'+'关闭连接';
    }

    web_socket.onerror=function () {
        document.getElementById("getText").innerHTML+='<br/>'+'连接错误';
    }

    web_socket.onmessage=function (event) {

        document.getElementById("getText").innerHTML+='<br/>'+event.data;

    }


    window.onbeforeunload = function () {
        web_socket.close();
    }


    function send() {
        var message = document.getElementById("sendText").value;
        web_socket.send(message);
    }



</script>

</html>

<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<html>
<body>

        <h1>允许设备入网</h1>
    <input type="text" name="ip"  class="ip" placeholder="网关的局域网IP">
    <input type="text" name="whiteMac"  class="whiteMac" placeholder="允许入网的设备MAC地址">
        <input type="button" class="alloc_ap" value="允许设备入网">

        <hr>

    <h1>查询网关入网列表</h1>
    <input type="button" class="device_discover" value="设备发现">



        <br>
        <br>
        <br>
        返回信息:<span class="message"></span>


        <table>
            <thead>
            <tr>
                <td>IP地址</td>
                <td>MAC地址</td>
                <td>产品型号</td>
                <td>版本号</td>
                <td>设备ID</td>
            </tr>
            </thead>
            <tbody class="responseBody">

            </tbody>
        </table>
</body>
<script
        src="https://code.jquery.com/jquery-2.2.4.min.js"
        integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
<script type="application/javascript">

    $(function () {

        $(".device_discover").bind("click",function () {


            $.getJSON('/app/project/device_discover.do?r=' + new Date().getTime(), function (data) {

                if(data.code == 200)
                {
                    $(".message").empty().html(data.data).css("color","red");


                    $(".responseBody").empty();
                    $(data.data).each(function (i,val) {
                        $(".responseBody").append("<tr><td>"+val.ip+"</td><td>"+val.mac+"</td><td>"+val.sn_id+"</td><td>"+val.ver_id+"</td><td>"+val.device_id+"</td></tr>");
                    });


                }else
                {
                    $(".message").empty().html(data.message).css("color","red");
                }

            });

        });

        $(".alloc_ap").bind("click",function () {


            $.getJSON('/app/project/alloc_ap.do?r=' + new Date().getTime(),{ip:$(".ip").val(), mac:$(".whiteMac").val()}, function (data) {

                if(data.code == 200)
                {
                    $(".message").empty().html(data.message).css("color","red");

                }else
                {
                    $(".message").empty().html(data.message).css("color","red");
                }

            });

        });


    });





</script>
</html>


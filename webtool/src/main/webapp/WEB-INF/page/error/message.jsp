<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/15
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<%@include file="/inc/header.jsp"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
<HEAD>
  <TITLE> <%=request.getAttribute("result")%></TITLE>
  <META http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script src="http://resource.danbay.cn/common/js/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
  <script src="http://resource.danbay.cn//common/js/plus/artDialog/jquery.artDialog.js?skin=default" type="text/javascript"></script>
  <script src="http://resource.danbay.cn//common/js/plus/artDialog/plugins/iframeTools.js" type="text/javascript"></script>
  <LINK href="http://resource.danbay.cn//common/css/tip.css" rel="stylesheet">
  <meta http-equiv="refresh" >
<HEAD>

<BODY class="${showCss}" >
<div class="settip_warp">
  <div class="tipCont">
    <div class="title">提示信息</div>
    <div class="settip">
      <div class="settip_info">
        <span class="${showColor} message">${showMessage}</span>
      </div>
      <a class="button-cancel" href=${redirectUrl }> <span><< 返回</span></a>
      ${showColor}
    </div>
  </div>
</div>
<script type="text/javascript">
  $(function () {
      <c:if test="${result eq true}">parent.location.reload(false);art.dialog.close();</c:if>
  })
</script>
<%@include file="/inc/footer.jsp"  %>

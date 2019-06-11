<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"  session="false"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${page_title}</title>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9"/>
  <% request.setAttribute("codeStaticPath",request.getContextPath()+"/static/code/");%>
  <link type="text/css" rel="stylesheet" href="${codeStaticPath}system.css"/>
  <link type="text/css" rel="stylesheet" href="${codeStaticPath}table_form.css"/>


  <script type="text/javascript" src="${codeStaticPath}jquery-1.9.1.min.js"></script>
  <script src="${codeStaticPath}common.js" language="javascript" type="text/javascript"></script>
  <script src="${codeStaticPath}jquery.artDialog.js?skin=default" type="text/javascript" language="javascript"></script>
  <script src="${codeStaticPath}iframeTools.js" type="text/javascript" language="javascript"></script>
  <script src="${codeStaticPath}vdate.js" language="javascript" type="text/javascript"></script>
  <script src="${codeStaticPath}checkbox.js" type="text/javascript" language="javascript"></script>
  <script src="${codeStaticPath}default.js" type="text/javascript" language="javascript"></script>
  <script src="${codeStaticPath}jquery.validation.min.js" type="text/javascript"></script>

  <script type="text/javascript" src="${codeStaticPath}leftMenu.js"></script>
</head>
<body>
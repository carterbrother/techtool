<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<%@include file="/inc/header.jsp"  %>
  <div class="pad-10">

    请选择文件打开： 打开之后，页面会显示文件所选文件的配置信息；

    <br>

    <c:forEach items="${files}" var="fileItem">
      <a href="/code/open.do?fileItem=${fileItem}">打开文件_${fileItem}</a><br>
    </c:forEach>


  </div>
<%@include file="/inc/footer.jsp"  %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"  isELIgnored="false"  %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<ul class="hidden">
    <c:forEach var="map" items="${tables}" varStatus="st">
        <li id="_MP${st.index}" class="sub_menu"><a href="javascript:_MP(${st.index},'/code/table/${map.key}.do','${map.value}');" hidefocus="true" style="outline:none;">${map.key}(${map.value})</a></li>
    </c:forEach>
</ul>
<script type="text/javascript" src="http://resource.danbay.cn/common/js/leftMenu.js"></script>

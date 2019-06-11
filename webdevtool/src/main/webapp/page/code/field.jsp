<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<%@include file="../error/header.jsp"  %>
  <div class="pad-10">
    <fieldset>
      <legend>表字段</legend>
      <div class="table-list">
        <table width="100%" cellspacing="0">
          <thead>
          <tr align="center">
            <th >字段名称</th>
            <th >字段备注</th>
            <th >字段类型</th>
            <th >字段长度</th>
            <th >默认值</th>
            <%--<th >控件类型</th>--%>
            <th width="42%">操作</th>
          </tr>
          </thead>

          <c:forEach items="${fieldList}" var="e">
            <tr align="center">
              <td>${e.getFieldName() } </td>
              <td>${e.getFieldComment() }</td>
              <td>${e.getFieldType() }</td>
              <td>${e.getFieldLength() }</td>
              <td>${e.getFieldDefaultValue() }</td>
              <%--<td>${e.getFormHtmlType() }</td>--%>
              <td><a class="button" href="/code/field/${tableName}/${e.getFieldName() }/save.do">编辑</a><a class="button" href="/code/field/${tableName}/${e.getFieldName() }/delete.do">删除</a></td>
            </tr>
          </c:forEach>
        </table>
      </div>
        <form id="Form1"  method="post" action="/code/table/${tableName}.do">
          <table width="100%" class="table-list" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="80" align="right" style="white-space: nowrap">字段名称： <input type="hidden"  name="txt_fieldName_pre"  maxlength="50" style="width: 200px;"  value="${selectfield.getFieldName()}" /></td>
              <td><input type="text"  name="txt_fieldName"  maxlength="50" style="width: 200px;"  value="${selectfield.getFieldName()}" /></td>
              <td width="80" align="right" style="white-space: nowrap">字段备注：</td>
              <td>
                <input type="text"  name="txt_fieldComment"  maxlength="50" style="width: 400px;" value="${selectfield.getFieldComment()}" /></td>
              <td width="80" align="right" style="white-space: nowrap">字段类型：</td>
              <td>
                <select name="sel_fieldType">
                  <option value="varchar" <c:if test="${selectfield.getFieldType() eq 'varchar'}">selected</c:if>> varchar</option>
                  <option value="bigint" <c:if test="${selectfield.getFieldType() eq 'bigint'}">selected</c:if>>bigint</option>
                  <option value="datetime"<c:if test="${selectfield.getFieldType() eq 'datetime'}">selected</c:if>> datetime</option>
                  <option value="tinyint"<c:if test="${selectfield.getFieldType() eq 'tinyint'}">selected</c:if>> tinyint</option>
                  <option value="date"<c:if test="${selectfield.getFieldType() eq 'date'}">selected</c:if>> date</option>
                  <option value="double"<c:if test="${selectfield.getFieldType() eq 'double'}">selected</c:if>> double</option>
                  <%--<option value="text"<c:if test="${selectfield.getFieldType() eq 'text'}">selected</c:if>> text</option>--%>
                  <option value="bit"<c:if test="${selectfield.getFieldType() eq 'bit'}">selected</c:if>> bit</option>
                  <option value="decimal"<c:if test="${selectfield.getFieldType() eq 'decimal'}">selected</c:if>> decimal</option>
                  <option value="blob"<c:if test="${selectfield.getFieldType() eq 'blob'}">selected</c:if>> blob</option>
                </select>

              </td>
              <td width="80" align="right" style="white-space: nowrap">字段长度：</td>
              <td>
                <input type="number"  name="txt_fieldLength"  maxlength="50" style="width: 50px;"/></td>
              <td width="80" align="right" style="white-space: nowrap">默认值：</td>
              <td>
                <input type="number"  name="txt_fieldDefaultValue"  maxlength="50" style="width: 100px;"/></td>
            </tr>
            <tr>
              <td align="right">&nbsp;</td>
              <td><input type="submit" value="提交" > |   <input type="button" onclick="javascript:editTable('${tableName}');" value="编辑表"/> |
                <a  href="/code/table/${tableName}/delete.do"  target="_parent">删除表</a></td>
            </tr>
          </table>
        </form>
        <div>
          <a class="button" onclick="generate('${tableName}','syc')">syc</a> |
          <a class="button" onclick="generate('${tableName}','sql')">sql</a> |<a class="button" onclick="generate('${tableName}','model')">model</a>|
          <a class="button" onclick="generate('${tableName}','dao')">dao</a> |<a class="button" onclick="generate('${tableName}','bll')">bll</a>|
          <a class="button" onclick="generate('${tableName}','controller')">controller</a> |<a class="button" onclick="generate('${tableName}','jsp')">listjsp</a>
        </div>
    </fieldset>
  </div>
<script type="text/javascript">
  function  generate(tableName,type) {
      var url = "/code/generate/"+tableName+"/"+type+".do?r=" + Math.random();
      art.dialog.open(url, { id: "tip", height: 900, width: 800, title: "生成"+type+"代码", lock: true }, false); //打开子窗体
  }
  function editTable(tableName) {
      var url = "/code/table/"+tableName+"/save.do?r=" + Math.random();
      art.dialog.open(url, { id: "tip", height: 360, width: 600, title: "编辑表", lock: true }, false); //打开子窗体

  }

</script>
<%@include file="../error/footer.jsp"  %>


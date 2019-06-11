<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<%@include file="/inc/header.jsp"  %>
  <div class="pad-10">
    <fieldset>
      <legend>${title}表</legend>
      <form id="Form1"  method="post" action="${currenturl}">
        <table width="100%" class="table-list" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="80" align="right" style="white-space: nowrap">表英文名称：</td>
            <td>
              <input type="hidden"  name="txt_tableName_pre"  maxlength="50" style="width: 200px;"  value="${table.getTableName()}" />
              <input type="text"  name="txt_tableName"  maxlength="50" style="width: 200px;"  value="${table.getTableName()}" />
              &nbsp;&nbsp;&nbsp;&nbsp;
              必须唯一，请使用unix命名风格</TD>
          </tr>
          <tr>
            <td width="80" align="right" style="white-space: nowrap">表备注：</td>
            <td>
              <input type="text"  name="txt_tableComment"  maxlength="50" style="width: 200px;"  value="${table.getTableComment()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              数据库备注</TD>
          </tr>

          <tr>
            <td align="right">&nbsp;</td>
            <td><input type="submit" value="提交" ></td>
          </tr>
        </table>
      </form>
    </fieldset>
  </div>
<%@include file="/inc/footer.jsp"  %>


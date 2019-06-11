<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"    %>
<%@include file="../error/header.jsp"  %>
  <div class="pad-10">
    <fieldset>
      <legend>${title}数据库</legend>
      <form id="Form1"  method="post" action="${currenturl}">
        <table width="100%" class="table-list" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="80" align="right" style="white-space: nowrap">数据库英文名称：</td>
            <td>
              <input type="hidden"  name="txt_databaseName_pre"  maxlength="50" style="width: 200px;" value="${database.getDatabaseName()}"/>
              <input type="text"  name="txt_databaseName"  maxlength="50" style="width: 200px;" value="${database.getDatabaseName()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              必须唯一，不容许重复</TD>
          </tr>
          <tr>
            <td width="80" align="right" style="white-space: nowrap">数据库备注：</td>
            <td>
              <input type="text"  name="txt_databaseComment"  maxlength="50" style="width: 200px;" value="${database.getDatabaseComment()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              数据库备注</TD>
          </tr>
          <tr>
            <td width="80" align="right" style="white-space: nowrap">创建者：</td>
            <td>
              <input type="text"  name="txt_databaseCreator"  maxlength="50" style="width: 200px;"  value="${database.getDatabaseCreator()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              数据库创建者</TD>
          </tr>
          <tr>
            <td width="80" align="right" style="white-space: nowrap">数据库相关代码包名：</td>
            <td>
              <input type="text"  name="txt_dbPackageName"  maxlength="50" style="width: 200px;"  value="${database.getDbPackageName()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              数据库相关代码包名</TD>
          </tr>
          <tr>
            <td width="80" align="right" style="white-space: nowrap">应用相关代码包名：</td>
            <td>
              <input type="text"  name="txt_appPackageName"  maxlength="50" style="width: 200px;" value="${database.getAppPackageName()}"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              应用相关代码包名</TD>
          </tr>

          <tr>
            <td align="right">&nbsp;</td>
            <td><input type="submit" value="提交" ></td>
          </tr>
        </table>
      </form>
    </fieldset>
  </div>
<%@include file="../error/footer.jsp"  %>


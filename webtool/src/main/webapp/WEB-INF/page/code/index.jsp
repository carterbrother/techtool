<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" session="false"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
  <title>代码生成主页</title>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <link type="text/css" rel="stylesheet" href="http://resource.danbay.cn/common/css/system.css" />
  <link href="http://resource.danbay.cn/admin/css/table_form.css" rel="stylesheet" type="text/css" />

  <script type="text/javascript" src="http://resource.danbay.cn/common/js/jquery/jquery-1.9.1.min.js"></script>
  <script type="text/javascript" src="http://resource.danbay.cn/common/js/plus/artDialog/jquery.artDialog.js?skin=default" ></script>
  <script type="text/javascript" src="http://resource.danbay.cn/common/js/plus/artDialog/plugins/iframeTools.js"  ></script>

  <script type="text/javascript">
    function createDatabase() {
      var url = "/code/database.do?r=" + Math.random();
      art.dialog.open(url, { id: "tip", height: 360, width: 600, title: "${title}数据库", lock: true }, false); //打开子窗体
    }
    function createTable() {
        var url = "/code/table/null/save.do?r=" + Math.random();
        art.dialog.open(url, { id: "tip", height: 360, width: 600, title: "创建表", lock: true }, false); //打开子窗体

    }

    function saveJson() {
        var url = "/code/saveJson.do?r=" + Math.random();
        art.dialog.open(url, { id: "tip", height: 360, width: 600, title: "保存结果", lock: true }, false); //打开子窗体

    }

    function openJson() {
        var url = "/code/openJson.do?r=" + Math.random();
        art.dialog.open(url, { id: "tip", height: 600, width: 600, title: "选择数据库文件打开", lock: true }, false); //打开子窗体

    }


    function code() {
        var url = "/code/generateCode.do?r=" + Math.random();
        art.dialog.open(url, { id: "tip", height: 600, width: 600, title: "生成文件", lock: true }, false); //打开子窗体

    }
//    function exit() {
//        var url = "/code/exit.do?r=" + Math.random();
//        window.location.href=url;
//    }

  </script>
</head>
<body>
<div class="header zonghe">
    <div class="log white cut_line">
      <a href="javascript:createDatabase()" class="button"    ${danbay_database.getDatabaseName() eq ''?'':'style="color:red;"'} dis >${title}数据库</a> &nbsp;<span>|</span>&nbsp; <a href="javascript:createTable()" class="button">创建表</a>
      &nbsp;<span>|</span> &nbsp; <a href="javascript:openJson()" class="button">打开数据库</a> &nbsp;<span>|</span>&nbsp; <a href="javascript:changePassword()" class="button">数据库中获取</a>
      &nbsp;<span>|</span> &nbsp; <a href="javascript:code()" class="button">生成代码</a> &nbsp;<span>|</span>&nbsp;<a href="javascript:saveJson()" class="button">保存文件</a>
      |</span>&nbsp;<a href="javascript:exit()" class="button">退出</a>
    </div>
    当前操作数据库：${danbay_database.getDatabaseName()} | 数据库备注：${danbay_database.getDatabaseComment()} | 创建者：${danbay_database.getDatabaseCreator()} | db项目包名：${danbay_database.getDbPackageName()} | app项目包名：${danbay_database.getAppPackageName()}
    </div>
</div>


<div id="content">
  <div class="col-left left_menu">
    <div class="leftMain leftMain8" id="leftMain" >

    </div>
    <a href="javascript:;" id="openClose" style="outline-style: none; outline-color: invert;
                outline-width: medium;" hidefocus="hidefocus" class="open" title="展开与关闭"><span class="hidden">
                    展开</span></a>
  </div>


  <div class="col-1 lf cat-menu" id="display_center_id" style="display: none" height="100%">
    <div class="content">
      <iframe name="center_frame" id="center_frame" src="" frameborder="false" scrolling="auto"
              style="border: none" width="100%" height="auto" allowtransparency="true"></iframe>
    </div>
  </div>
  <div class="col-auto mr8" id="contRight">
    <span class="view-left-btn" onclick="viewLeftScroll(-30)"></span>
    <span class="view-right-btn" onclick="viewLeftScroll(30)"></span>
    <div class="view">
      <div class="crumbs clear" style="padding:13px 0 0 0;" >
        <span class='span1' onclick=show(1) style='color:#4e94d6;font-size:12px;cursor:pointer;position:relative;border:1px solid #c2d1d8; padding:5px 16px 2px 10px;margin-right:-4px;background:#dbe8f8;border-radius: 4px 0 0 0;'>欢迎界面</span>

      </div>
    </div>
    <div class="col-1">
      <div class="content" style="position: relative; overflow: hidden;height: 788px">
        <iframe name="right" class="rightMain 1" id="rightMain" src="/index2.jsp" frameborder="false"
                scrolling="auto" style="position:absolute;left:0;top:0;border: none; margin-bottom: 30px" width="100%" height="auto"
                allowtransparency="true"></iframe>
        <div class="fav-nav">
        </div>
      </div>
    </div>
  </div>
</div>
<script src="http://resource.danbay.cn/common/js/change-default.js" type="text/javascript"></script>
<script src="http://resource.danbay.cn/common/js/select-backstage.js" type="text/javascript"></script>
<script type="text/javascript">
  $(function () {
    $(".leftMain").load("/code/tables.do");
  });
</script>

</body>
</html>

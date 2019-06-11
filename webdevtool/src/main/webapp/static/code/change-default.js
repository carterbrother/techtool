function palceHolderFocus(_this){
    if($(_this).attr("title").indexOf("密码")>0){
        $("#labelTxt").hide();
    }
    if($(_this).val() == $(_this).attr("title"))
    {
        $(_this).val("");
        $(_this).css("color","#000");
    }
}
function palceHolderBlur(_this){
    if($(_this).attr("title").indexOf("密码")>0){
        if($(_this).val() == ""){
            $("#labelTxt").show();
        }

    }
    if($(_this).val() == '') {
        $(_this).val($(_this).attr("title"));
        $(_this).css("color","#999");
    }
}

    //clientHeight-0; 空白值 iframe自适应高度
    function windowW() {
        if ($(window).width() < 980) {
          //  $('.header').css('width', 980 + 'px');
            $('#content').css('width', 980 + 'px');
            $('body').attr('scroll', 'yes');
            $('body').css('overflow', 'auto');
        }
    }
    windowW();
    $(window).resize(function () {
        if ($(window).width() < 980) {
            windowW();
        } else {
            $('.header').css('width', 'auto');
            $('#content').css('width', 'auto');
            //$('body').attr('scroll', 'no');
            //$('body').css('overflow', 'hidden');

        }
    });
    window.onresize = function () {
        var heights = document.documentElement.clientHeight - 150;
		$("#rightMain").height(heights);
		//document.getElementById('rightMain').height = heights;
        var openClose = $("#rightMain").height() + 39;
        $('#center_frame').height(openClose + 9);
        $("#openClose").height(openClose + 30);
    }
    window.onresize();
    $(function () {
        //默认载入左侧菜单
        //$("#leftMain").load("left_html/Left_Admin.html");
        //  $("#leftMain").load("left.html?m=admin&c=index&a=public_menu_left&menuid=10");
        $("#openClose").bind("click", function () {//展开与伸缩左边导航条
            if ($("#openClose").attr("class") == "open") {
                $(".left_menu").addClass("left_menu_on");
                $("#openClose").removeClass("open");
                $("#openClose").addClass("close");
                $(this).parents(".left_menu").siblings(".col-auto").css("margin-left",10);
                $(this).css("right","-10px");

            }
            else {
                $(".left_menu").removeClass("left_menu_on ");
                $("#openClose").addClass("open");
                $("#openClose").removeClass("close");
                $(this).parents(".left_menu").siblings(".col-auto").css("margin-left",205);
                $(this).css("right","3px");
            }
        });
                
    })

    function _M(menuid, targetUrl,menUrl) {
        $("#menuid").val(menuid);
        $("#bigid").val(menuid);
        $("#paneladd").html('<a class="panel-add" href="javascript:add_panel();"><em>添加</em></a>');
        if(!$("#_M"+menuid+"").attr("active")){
            $(".leftMain").css("display","none").attr("id","1");
            $(".leftMain8").after('<div class="leftMain leftMain'+menuid+'" id="leftMain" ></div>')
            $(".leftMain"+menuid+"").load(targetUrl);
            $("#_M"+menuid+"").attr("active","1");
        }else{
            $(".leftMain").css("display","none").attr("id","1");
            $(".leftMain"+menuid+"").css("display","block").attr("id","leftMain");
        }
        // $("#rightMain").attr('src', menUrl);
        $('.top_menu').removeClass("on");
        $('#_M' + menuid).addClass("on");
         $("#current_pos").html($('#_M' + menuid).attr("title"));
        //当点击顶部菜单后，隐藏中间的框架
        $('#display_center_id').css('display', 'none');
        //显示左侧菜单，当点击顶部时，展开左侧
        $(".left_menu").removeClass("left_menu_on");
        $("#openClose").removeClass("close");
        $("html").removeClass("on");
        $("#openClose").data('clicknum', 0);
        $("#current_pos").data('clicknum', 1);
    }
    function _MP(menuid, targetUrl, titleValue) {
        $("#current_pos").html(titleValue);
        $("#menuid").val(menuid);
        if(!$("#_MP"+menuid+"").attr("left")){ 
            var n =Math.ceil(Math.random()*10000);
            n = n+"";
            $(".rightMain").css({'z-index': '1'});
            $(".content").append("<iframe name='right' class='rightMain "+n+"' id='rightMain' src='"+targetUrl+"?t="+Math.random()+"' frameborder='false'scrolling='auto' style='z-index:10;position:absolute;left:0;top:0;border: none; margin-bottom: 30px; width:100%; height:788px;' allowtransparency='true'></iframe>");
            $(".crumbs span").css({'background': '#fff'});
            $(".crumbs").append("<span class='span"+n+"' onclick=show("+n+") style='color:#4e94d6;font-size:12px;cursor:pointer;position:relative;border:1px solid #c2d1d8;padding:5px 16px 2px 10px;background:#dbe8f8'>"+titleValue+"<strong onclick='hide("+n+","+menuid+")'style='position:absolute;right:1px;top:0px;color:#c2d1d8'>×</strong></span>")
            $("#_MP"+menuid+"").attr("left",""+n+"");
                // console.log(widthNum());
                // console.log($(".view").width());

            if(widthNum()>=$(".view").width()){
                $(".view-left-btn").css("display","block");
                $(".view-right-btn").css("display","block");
                // $(".view ").animate({'scrollLeft': $(".view ").scrollLeft()+$(".span"+n+"").outerWidth()+'px'}, 100)
                $(".view ").animate({'scrollLeft': widthNum()-$(".view").width()+'px'}, 100)
            }
        }else {
            show($("#_MP"+menuid+"").attr("left"));
            var number = $(".span"+$("#_MP"+menuid+"").attr("left")+"").index();
            var dis = 0;
            for (var i = 0; i < number+1; i++) {
               dis+= $(".crumbs span").eq(i).outerWidth();
            };
            if(dis>$(".view").width()){
                $(".view ").animate({'scrollLeft': dis-$(".view").width()+'px'}, 100)
            }else{
                $(".view ").animate({'scrollLeft': '0px'}, 100)
            }
            var showSrc = $("."+$("#_MP"+menuid+"").attr("left")+"");
            var lj = showSrc.attr("src");
            showSrc.attr("src",lj)
        }

        $('.sub_menu').removeClass("on fb blue");
        $('#_MP' + menuid).addClass("on fb blue");
        $("#current_pos").data('clicknum', 1);
    }
    //增加点击的iframe标签权重
     function show(n){
        $(".rightMain").css({
            'z-index': '1'
        });
        $("."+n+"").css({
            'z-index': '10'
        });
        $(".crumbs span").css({
           'background': '#fff'
        });
        $(".span"+n+"").css({
            'background':'#dbe8f8'
        });
    }
    //移除iframe和span标签
    function hide(n,num){
        setTimeout(function(){
            $(".crumbs span").eq($(".crumbs span").length-1).css({'background':'#dbe8f8'});
        }, 100)
        if($(".view ").scrollLeft()>0){
            
            $(".view ").animate({'scrollLeft': $(".view ").scrollLeft()-$(".span"+n+"").outerWidth()+'px'}, 100)
        }else{
            $(".view ").animate({'scrollLeft': '0px'}, 100)
        }
        $(".span"+n+"").remove();
        $("#_MP"+num+"").attr("left","");
        $("."+n+"").remove();
        if(widthNum()<=$(".view").width()){
            $(".view-left-btn").fadeOut();
            $(".view-right-btn").fadeOut();
        }else{
            $(".view ").animate({'scrollLeft': widthNum()-$(".view").width()+'px'}, 100)
        }
    }
    //获取所有标题的长度
    function widthNum() {
        var widthNum = 0;
        // $(".crumbs span").each(function() {
        //      widthNum += $(this)[0].offsetWidth+parseInt($(this).css("marginLeft"));
        // });

        for (var i = 0; i < $(".crumbs span").length; i++) {
            widthNum += $(".crumbs span").eq(i)[0].offsetWidth;
        };
        return widthNum;
    }
    //让标题栏向左或者向右滚动
    function viewLeftScroll(n) {
        var leftDis = $(".view ").scrollLeft()
        if(n==30&&(leftDis+$(".view").width())>widthNum()){
          $(".view ").animate({'scrollLeft': leftDis+'px'}, 100)  
        }else{
            $(".view ").animate({'scrollLeft': leftDis+n+'px'}, 100)
        }
    }
//首页点击到其它页面处理
function _mi(menuid, targetUrl, titleValue) {
	$(window.parent.$("#leftMain").load(targetUrl));
	   $('#current_pos', parent.document).html(titleValue);
	   $('.top_menu', parent.document).removeClass("on");
	   $('#_M' + menuid, parent.document).addClass("on");
	   
 }
 
 (function($){
	$.getUrlParam = function(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
	}
})(jQuery);
if($.getUrlParam('ulid')!=null && $.getUrlParam('ulid')!=undefined && $.getUrlParam('ulid')!="") {
	var ulid = $.getUrlParam('ulid');
	var liid = $.getUrlParam('liid');
	window.parent.$("#leftMain").find('ul:eq(0)').addClass("hidden");
	window.parent.$("#leftMain").find('ul:eq(' + (ulid - 1) + ')').removeClass("hidden");
	window.parent.$("#leftMain").find('#_MP'+ liid).addClass("on fb blue");
}


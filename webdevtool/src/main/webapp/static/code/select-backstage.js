(function(){
    //创建input节点并放到body中
    $("body").append('<input type="text" id="select_input" style="position:fixed; top:-100px;;height:1px;width:1px;">')
    var n =true;
    //下拉框头部显示页面头部标题
    $(".select dt").html($("title").html());
    //点击出现下拉框
    $(".select dt").click(function(){
      $("#select_input").off('blur')
      $(".select dd").slideToggle();
      if(n){
        $(this).css({
          "background-image": "url('down.png')"
        })
        n=false;
      $("#select_input").focus();
      }else {
        $(this).css({
          "background-image": "url('up.png')"
        })
        n=true;
      }
    })
    //鼠标在下拉框上时候让我们创造的input聚焦
    $(".select dt,.select dd").on('mouseover',  function(event) {
       $("#select_input").off('blur');
       $("#select_input").focus();
    });
    //鼠标离开下拉框 调用selectBlur函数
    $(".select dt,.select dd").on('mouseout',  function(event) {
      $("#select_input").blur(function(event) {
        selectBlur();
      });
    });
    //收起下拉框
    function selectBlur() {
        $(".select dt").css({
            "background-image": "url('up.png')"
          })
          $(".select dd").hide();
          $(".select-hland").slideUp();
          $(".select-mainland").slideUp();
          n=true;
    }
    //点击A标签 让下拉列表收起
    $(".select dl a").click(function(event) {
      /* Act on the event */
      selectBlur();
    });
    //点击大陆管理后台划出大陆管理后台列表
    $(".select-mainland-title").click(function(event) {
    /* Act on the event */
      $(".select-mainland").slideToggle();
      $(".select-hland").slideUp();
      $("#select_input").focus();
   });
    //点击香港管理后台划出香港管理后台列表
   $(".select-mainland-titlex").click(function(event) {
    /* Act on the event */
      $(".select-hland").slideToggle();
      $(".select-mainland").slideUp();
      $("#select_input").focus();
   });

  })();


$("#enlarge").click(
    function(){
        var containerbody = $("#enlarge-body");
        containerbody.toggleClass("container");
        containerbody.toggleClass("container-fluid");
    }
);

function warning(msg){
  $('#warning').text(msg);
  return false;
}
function toInt(value,defaultValue){
    if(value != undefined && parseInt(value)){
      return parseInt(value);
    }else{
      return defaultValue;
    }
}

function isMobile(){
  return (
        (navigator.platform.indexOf("iPhone") != -1) ||
        (navigator.platform.indexOf("iPod") != -1)   ||
        (navigator.platform.indexOf("iPad") != -1)   ||
        (navigator.platform.indexOf("Android") != -1)
    );
}

function admin_alert(msg, autoClose){
    var dom = $('<p>'+msg+'</p>').appendTo("body");
    dom.dialog({title:"系统提示："});
    if(autoClose){
        setTimeout(function(){
            dom.dialog('close');
        }, 1200);
    }
}

$(function(){
  $('.js-sync').click(function(event) {
    if(!confirm('立即生效变更，确定同步数据到线上么？')){
      return;
    }
    var target = $(event.target);
    var path = target.data('path');
    if(path){
      $.post('/zookeeper/update-status/',{
        path:path
      },function(data){
        if('true' == data){
          alert('同步成功');
        }else{
          alert('同步失败');
        }
      });
    }
  });

  if(isMobile()){
    $('.dropdown-toggle').dropdown();
    $('.dropdown-menu').click(function(event){
        event.stopPropagation();
    });
    $('.dropdown-submenu ul.dropdown-menu li a').on('touchstart', function(e) {
      e.preventDefault();
      window.location.href = $(this).attr('href');
    });
  }

  //通用上一页、下一页分页逻辑
  $(".pager-link").click(function(event){
      var target = $(event.target);
      $("input[name=page]").val(target.attr('data-id'));
      $('#search-form').submit();
  });

  $(".pagination li a").on('click',function(e){
      var a = $(e.target);
      var li=$(a.parent());
      if(li.hasClass('active')||li.hasClass('disabled')||!$.isNumeric(a.data('page')))return; //当前页就不要跳转了
      e.preventDefault();
      var pageid=$(a.parent().parent().parent()).data('pageid');//取得页面分页input的id
      $('#'+pageid).val(a.data('page'));
      $('#'+pageid).parent().submit();//提交表单
  });

  function wide_page(){
    var adminsales = $('div.admin-sales');
    adminsales.toggleClass('container').toggleClass('container-fluid');
    if(adminsales.hasClass('container')){
      $.removeCookie('yunhu_wide',{path:'/sales/'});
    }else{
      $.cookie('yunhu_wide', 'on', {expires: 365, path:'/sales/'});
    }
  }
  $(".js-resize").on('click', function(event) {
    wide_page();
  });
  $('body').keypress(function(event) {
    if(event.which == 63245){
      wide_page();
    }
  });

  if($.cookie('yunhu_wide') === 'on'){
    wide_page();
  }
});

function warning(msg){
  $('#warning').text(msg);
}

function removeimage(event){
  $(event.target).parent().remove();
}

$(function(){
  $('i.draggable').popover({"trigger":"hover",delay:{hide:1000},"content":"鼠标拖拽以调整顺序。"});
  
  //https://gist.github.com/Yavari/1891669
  $('#author').typeahead({
    source: function(typeahead,query){
      if($.trim(query).length<2)return;
      $.getJSON('/user/search/',{'nick':query},function(data){
        typeahead.process(data);
      });
    },
    onselect: function(obj){
      $('#uid').val(obj.uid);
    },
    items:10,
    matcher:function(item){return true},
    property:"nick"
  });

  $('.pager .icon-heart').click(function(){
    $('.pager .icon-heart').toggleClass("icon-blue")
    alert('一点都不好玩好吧？')
  });

    $('.icon-check').click(function(){
        $('.red').css("color","red") ;
    });


    $("ul.images").on("dblclick","li.img-show img",function(event){
    var src = $(event.target).attr('src');
    window.open(src.replace('_fs.','_b.'),'预览图');
  });
  $('.big-images').on('dblclick','.big-image',function(envent){
    window.open($(event.target).attr('src').replace('_fs.','_b.'),'预览图');
  });

  $('#btn-official').click(function(event) {
    //POA官方推荐服务
    var btn = $(this);
    var official = $(this).hasClass('icon-ok-circle')?0:1;
    var msg = official==0?'确定取消官方推荐？操作立即生效。':'确定设置官方推荐？操作立即生效。';
    if(!confirm(msg)){
      return;
    }
    btn.attr('disabled', 'disabled');
    $.post('/trip/poa/official/update/', {official:official,pid:btn.data('pid')}, function(data, textStatus, xhr) {
      if(data=='true'){
        btn.toggleClass('icon-ok-circle').toggleClass('btn-success');
        alert('操作成功');
      }else{
        alert('操作失败');
      }
      btn.removeAttr('disabled');
    });

  });

});

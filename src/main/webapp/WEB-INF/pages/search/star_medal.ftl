<#assign _title="达人勋章列表">
<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container">
  <div class="container-fluid">

    <div class="row-fluid">
    <#assign tab="star_medal" />
    <#include "/search/tabs.ftl" />

      <div class="span8">
        <div id="well" class="well hidden">
          <strong id="msg">${msg}</strong>
        </div>
        <div class="row-fluid">
          <form class="well form-inline" method="POST">
            <div class="input-prepend">
              <span class="add-on">用户UID</span>
              <input id="uid" name="uid" type="text" class="input-small" maxlength="10">
              <select id="medalType" class="input-medium">
                <option value="">请选择</option>
                <option value="1001">行程大师勋章</option>
              </select>
            </div>

            <button id="save" type="button" class="btn">授予勋章</button>
          </form>
        </div>

        <div class="row-fluid">
          <div class="page-header"><h4>达人勋章列表 &nbsp;<small>(${users?size})</small></h4></div>
          <table class="table table-striped table-bordered table-condensed">
            <thead>
            <tr>
              <th>用户UID</th><th>昵称</th><th>勋章</th><th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#list medals as medal>
            <tr>
              <td><a target="_blank" href="/search/user/?uid=${medal.uid}">${medal.uid} &nbsp;&nbsp;</a></td>
              <td><a target="_blank" href="http://www.shijiebang.com/u${medal.uid}/">${users["" + medal.uid].nick}</a></td>
              <td>${medal.medalType}</td>
              <td><a class="_delete" title="移除此用户商家勋章" uid="${medal.uid}" medal="${medal.medalType}">删除</a></td>
            </tr>
            </#list>
            </tbody>
          </table>
        </div>

      </div>
    </div>
  </div>
</div>

<script type="text/javascript">

  $(function(){

    $('#save').click(function(){
      var uid = $.trim($('#uid').val());
      var medalType = $.trim($('#medalType').val());
      if(parseInt(uid) && parseInt(medalType)){
        $.post('/star/medal/',
            { _method:'PUT',uid:uid,medalType:medalType },
            function(data){
          if('true' == data){
            window.location.href='';
          }else{
            alert('授予勋章失败');
          }
        });
      }
    });

    $('._delete').on('click',function(event){
      if(!confirm('确定移除此人的勋章吗？')){
        return;
      }
      var target = $(event.target);
      $.post('/star/medal/',
          {_method:'DELETE',uid:target.attr('uid'),medalType:target.attr('medal') }
          ,
          function(data){
            if('true' == data){
              target.parent().parent().remove();
            }else{
              alert('移除勋章失败');
            }
          });
    });
  });


</script>
<#include "commons/footer.ftl" />
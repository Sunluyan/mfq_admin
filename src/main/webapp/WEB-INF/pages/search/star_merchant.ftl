<#assign _title="商家达人列表">
<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container">
  <div class="container-fluid">

    <div class="row-fluid">
      <#assign tab="merchant-doyen" />
      <#include "/search/tabs.ftl" />

      <#assign signdesc={'MerchantDoyen':'商家达人','MerchandiseDoyen':'商品达人','TravelCounselor':'旅行顾问','Banger':'内部同事'}>
      <div class="span8">
        <div id="well" class="well hidden">
          <strong id="msg">${msg}</strong>
        </div>
        <div class="row-fluid">
          <form class="well form-inline" method="POST">
            <div class="input-prepend">
              <span class="add-on">用户UID</span>
              <input id="uid" name="uid" type="text" class="input-small" maxlength="10">
            </div>
            <select id="sign" name="sign" class="input-medium">
              <option value="MerchantDoyen" <#if sign=='MerchantDoyen'>SELECTED</#if>>商家达人</option>
              <option value="MerchandiseDoyen" <#if sign=='MerchandiseDoyen'>SELECTED</#if>>商品达人</option>
              <option value="CustomerService" <#if sign=='CustomerService'>SELECTED</#if>>客服人员</option>
              <option value="TravelCounselor" <#if sign=='TravelCounselor'>SELECTED</#if>>旅行顾问</option>
              <option value="Banger" ${(sign=='Banger')?string('SELECTED','')}>内部同事</option>
            </select>
            <button id="save" type="button" class="btn">添加用户标识</button>
            <button id="delete" type="button" class="btn">删除用户标识</button>
          </form>
        </div>

        <div class="row-fluid">
          <div class="page-header"><h4>${signdesc[sign]}列表 &nbsp;<small>(${users?size})</small></h4></div>
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th>用户UID</th><th>昵称</th><th>状态</th><th>操作</th>
              </tr>
            </thead>
            <tbody>
              <#list users as user>
              <tr>
                <td><a target="_blank" href="/search/user/?uid=${user.uid}">${user.uid} &nbsp;&nbsp;<img src="${user.icon}"></a></td>
                <td><a target="_blank" href="http://www.shijiebang.com/u${user.uid}/">${user.nick}</a></td>
                <td>${user.status}</td>
                <td></td>
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
      var sign = $('#sign').val();
      if($.isNumeric(uid)){
        $.post('/search/merchant-doyen/',{
          _method:'PUT',uid:uid,sign:sign
        },function(data){
          if('true' == data){
            window.location.href='/search/merchant-doyen/?sign='+sign;
          }else{
            alert('添加此用户标识失败');
          }
        });
      }
    });
    $('#delete').on('click',function(event){
      var uid = $.trim($('#uid').val());
      var sign = $('#sign').val();
      if($.isNumeric(uid)){
        if(!confirm('确定删除此用户标识么？')){
          return;
        }
        $.post('/search/merchant-doyen/',
          {_method:'DELETE',uid:uid,sign:sign
        },
        function(data){
          if('true' == data){
            window.location.href='/search/merchant-doyen/?sign='+sign;
          }else{
            alert('删除此用户标识失败');
          }
        });
      }

    });

    $('#sign').change(function(){
      var uid = $.trim($('#uid').val());
      var sign = $('#sign').val();
      if(!$.isNumeric(uid)){
        window.location.href='/search/merchant-doyen/?sign='+sign;
      }
    });

  });


</script>
<#include "commons/footer.ftl" />
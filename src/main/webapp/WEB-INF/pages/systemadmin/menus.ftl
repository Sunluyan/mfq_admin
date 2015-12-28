<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
  <div class="page-header">
    <h3>菜单列表
      <span class="pull-right">
        <a id="btn-create-submenu" data-toggle="modal" data-target="#modal-create" class="btn">创建子菜单</a>
        <#if id gt 0><a id="btn-create-divider" class="btn">创建分隔符</a></#if>
      </span>
    </h3>
  </div>
  <ul class="breadcrumb">
    <#list breadcrumbs as breadcrumb>
    <li><a href="/systemadmin/menus/?id=${breadcrumb.id}">${(breadcrumb.name)!"全部"}</a><span class="divider">/</span></li>
    </#list>
    <li class="active"><#if id==0>全部<#else>${menu.name}</#if></li>
  </ul>
  <#if menus?has_content>
  <table class="table table-striped table-bordered table-condensed counter">
    <thead>
      <tr><th>菜单ID</th><th>菜单名称</th><th>菜单URL</th><th>顺序</th><th>图标样式</th><td>是否启用</td><th>操作</th></tr>
    </thead>
    <tbody id="menus">
      <#list menus as menu>
      <tr class="table-item" data-id="${menu.id}">
        <td><#if menu.divider>${menu.id}<#else><a href="/systemadmin/menus/?id=${menu.id}">${menu.id}</a></#if></td>
        <td><#if menu.divider>#分隔符#<#else>${menu.name}</#if></td>
        <td><#if menu.divider><#else>${menu.url}</#if></td>
        <td>${menu.index}</td>
        <td>${menu.icon}</td>
        <td><#if menu.enabled>启用<#else>禁用</#if></td>
        <td><a data-id="${menu.id}" class="btn js_menu_delete">删除</a></td>
      </tr>
      </#list>
      <tr><td colspan="7"><span class="pull-right"><a class="btn" id="btn-save-order">保存菜单顺序</a></span></td></tr>
    </tbody>
  </table>
  </#if>

  <#if menu.id gt 0>
  <form id="form" class="well form-horizontal trade-add">
   <fieldset>

    <div class="control-group">
      <label class="control-label" for="">
        修改菜单
      </label>
      <div class="controls">
          <input type="hidden" name="pid" value="${(menu.pid)!}">
          <input type="hidden" name="id" value="${(id)!0}">
          <div class="input-prepend form-line">
            <span class="add-on">菜单名称</span>
            <input type="text" name="name" class="input-large" value="${(menu.name)!}">
          </div>
          <div class="input-prepend form-line">
            <span class="add-on">菜单链接</span>
            <input type="text" name="url" class="input-large" value="${(menu.url)!}">
          </div>
          <div class="input-prepend form-line">
            <span class="add-on">图标样式</span>
            <input type="text" name="icon" class="input-large" value="${(menu.icon)!}">
          </div>
          <label class="radio">
            <input type="radio" name="enabled" value="1" <#if menu.enabled>CHECKED</#if> >启用 (将启用其拥有的所有已启用子菜单)
          </label>
          <label class="radio">
            <input type="radio" name="enabled" value="0" <#if !menu.enabled>CHECKED</#if> >禁用 (将禁用其拥有的所有子菜单)
          </label>
      </div>
    </div>

    <div class="form-actions">
      <button type="button" id="btn-save" class="btn btn-primary">保存</button>
      <span id="warning" class="text-warning"></span>
    </div>
  </fieldset>
</form>
  </#if>





</div>
  <div class="modal fade" id="modal-create" style="display:none;">
    <div class="modal-header">
      <a class="close" data-dismiss="modal">x</a>
      <h3>创建子菜单</h3>
    </div>
    <div class="modal-body">
      <form id="form-create" class="well">
          <div class="controls">
            <input type="hidden" name="id" value="${(id)!0}">
            <div class="input-prepend form-line">
              <span class="add-on">菜单名称</span>
              <input id="create-name" type="text" name="name" class="input-large">
            </div>
            <div class="input-prepend form-line">
              <span class="add-on">菜单链接</span>
              <input id="create-url" type="text" name="url" class="input-large">
            </div>
          </div>
    </form>
  </div>
  <div class="modal-footer">
    <a class="btn" id="btn-create">创建</a>
  </div>
</div>
<script type="text/javascript">
  $(function(){
    $("#menus").sortable({
      connectWith: "#menus",
      items: ".table-item",
      axis: "y",
      cursor: "move"
    });

    $('.js_menu_delete').click(function(event) {
      if(!confirm('确定删除此菜单么？如果包含子菜单则无法删除。'))return;
      var id = $(event.target).data('id');
      $.post('/systemadmin/menus/',{_method:'DELETE',id:id},function(ret){
        if('true' == ret){
          alert('删除成功');
          window.location.href="";
        }else{
          alert('删除失败！请确认此菜单没有子菜单');
        }
      });
    });

    $('#btn-save').click(function(event) {
      var name = $.trim($('#form input[name=name]').val());
      var url = $.trim($('#form input[name=url]').val());
      var icon = $.trim($('#form input[name=icon]').val());
      var enabled = $('#form input[name=enabled]:checked').val();
      var data = {name:name,url:url,icon:icon,enabled:enabled,pid:${menu.pid},id:${menu.id},_method:"PUT"};
      $.post('/systemadmin/menus/',data,function(data){
        if("true" == data){
          alert('更新成功');
          window.location.href="";
        }else{
          alert('更新失败');
        }
      });
    });
    $('#btn-create').click(function(event) {
      var name = $.trim($('#create-name').val());
      var url = $.trim($('#create-url').val());
      if(name.length == 0){
        alert('名称不能为空');
        return;
      }
      var data = {name:name,url:url,pid:${menu.id},_method:"PUT"};
      $.post('/systemadmin/menus/',data,function(data){
        if("true" == data){
          window.location.href="";
        }else{
          alert('创建失败');
        }
      });
    });

    $('#btn-create-divider').click(function(event) {
      $.post('/systemadmin/menus/',{_method:"PUT",pid:${(menu.id)!0},divider:"true"},
        function(data){
          if("true" == data){
            alert('创建成功');
            window.location.href="";
          }else{
            alert('创建失败');
          }

      });
    });

    $('#btn-save-order').click(function(event) {
      var ids = $.map($('.table-item'), function(item, index) {
        return $(item).data('id');
      }).join(',');
      $.post('/systemadmin/menus/sort/',{ids:ids},function(data){
        if("true" == data){
          alert('保存成功');
        }else{
          alert('保存失败');
        }
      });
    });

  });
</script>

<#include "/commons/footer.ftl" />
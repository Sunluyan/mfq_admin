<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<div class="container" >
  <div class="row-fluid">
  <#if msg??>
    <div class="alert text-center">
      <a class="close" data-dismiss="alert">×</a>
    ${msg}
    </div>
  </#if>
  </div>

  <form class="form-horizontal" method="POST" action="/sysuser/permission_edit/" enctype="multipart/form-data">

    <fieldset>
      <legend>权限维护 </legend>
      <div >
        <p/>
      </div>

<#if id == 0>
          <input type="hidden" class="input-large" id="id" name="id" value="${id!}">
<#else>
      <div class="control-group">
        <label class="control-label" for="cname">ID</label>
        <div class="controls">
          <input type="text" class="input-large" id="id" name="id" value="${id!}">
          <span class="help-inline"><strong class="text-error">*</strong></span>
        </div>
      </div>
</#if>

      <div class="control-group">
        <label class="control-label" for="cname">URL</label>
        <div class="controls">
              <input type="text" id="url" name="url" value="${acl.url!}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="alias">NAME</label>
        <div class="controls">
	        <input type="text" id="name" name="name" value="${acl.name!}">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="alias">TYPE</label>
        <div class="controls">
          <select id='type' name="type">
            <option value="1" <#if acl.type == 1>selected</#if> >菜单</option>
            <option value="2" <#if acl.type == 2>selected</#if> >其它</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">父级ID</label>
        <div class="controls">
	        <input type="text" id="pid" name="pid" value="${acl.pid!}"></span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">排序</label>
        <div class="controls">
          <input type="text" id="index" name="index" value="${acl.index!}">
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">权限</label>
        <div class="controls">
        	<#list roleNames.keySet() as nkey>
	        	<input type="checkbox" name="permissionbox" value="${nkey}"
	        		<#if (roleValues.get(nkey)) == true>
	        		checked
               		</#if>
	          	> ${roleNames.get(nkey)}
          	</#list>
        </div>
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary" onclick="return docheck();">保 存</button>
        &nbsp;&nbsp;
        <button type="button" class="btn" onclick="history.back();">取消返回</button>
      </div>
    </fieldset>
  </form>

</div>


<script type="text/javascript">
  function docheck() {

    if($.trim($("#url").val()).length < 1){
      alert("URL太短");
      return false;
    }
    if($.trim($("#name").val()).length < 1){
      alert("名字不能为空");
      return false;
    }
	if($.trim($("#pid").val()).length < 1){
      alert("父级ID不能为空");
      return false;
    }
    if($.trim($("#index").val()).length < 1){
      alert("排序值不能为空");
      return false;
    }
    return true;
  }
</script>

<#include "commons/footer.ftl" />
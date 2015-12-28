<#assign _jss=['/static/js/plugin/jquery-ui.js','/static/js/plugin/bootstrap-typeahead.js?20130407']>
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container" style="max-width:600px;">
    <div class="row-fluid">
        <#if msg??>
        <div class="alert text-center">
            <a class="close" data-dismiss="alert">×</a>
            ${msg}
        </div>
        </#if>
    </div>

    <form class="form-horizontal" method="POST" action="/systemadmin/resource/">
        <input type="hidden" name="oldpath" value="${(resource.path)!}">
        <fieldset>
            <legend>资源(${(resource.pathdesc)!}, ${(resource.path)!})基本信息</legend>

          <div class="control-group">
                <label class="control-label" for="path">资源路径</label>
                <div class="controls">
                    <input type="text" class="input-large" id="path" name="path" value="${(resource.path)!''}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="pathdesc">资源描述</label>
                <div class="controls">
                    <input type="text" class="input-large" id="pathdesc" name="pathdesc" value="${(resource.pathdesc)!}" autocomplete="false">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">关联角色</label>
                <div class="controls">
                    <#list roles as role>
                      <label><#if role.rolename !='ROLE_ADMIN'><input name="rolename" type="checkbox" value="${role.rolename}" <#if checkedRoles?seq_contains(role.rolename)>CHECKED</#if> ></#if>${role.roledesc} (${role.rolename})</label>
                    </#list>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary" onclick="return docheck();">保存更改</button>
            </div>
        </fieldset>
    </form>

</div>


<script type="text/javascript">
    function docheck(){
        if($.trim($("#path").val()).length<2){
            alert("资源路径太搓");
            return false;
        }
        if($.trim($("#pathdesc").val()).length<2){
            alert("资源描述悲剧");
            return false;
        }

        return true;
    }

</script>

<#include "commons/footer.ftl" />
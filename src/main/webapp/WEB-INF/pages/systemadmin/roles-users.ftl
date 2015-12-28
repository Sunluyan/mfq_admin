<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
    <div class="page-header"><h4>${role.roledesc}角色(${role.rolename})的用户和资源</h4></div>
    <div class="container-fluid">
        <div class="row-fluid">
            <#if msg??>
            <div class="alert text-center">
                <a class="close" data-dismiss="alert">×</a>
                ${msg}
            </div>
            </#if>
        </div>
        <div class="row-fluid">
            <div class="span6">

                <form id="form-users" method="POST" action="/systemadmin/roles/${role.rolename}/">
                    <input type="hidden" name="users" value="true">
                <table class="table table-striped  table-condensed">
                    <thead>
                        <tr><th>选中</th><th>用户名称</th><th>中文名称</th></tr>
                    </thead>
                    <tbody>
                        <#list users as user>
                        <tr>
                            <td><label class="checkbox">
                                <input type="checkbox" name="usernames" value="${user.username}" <#if user.enabled>checked</#if>>
                            </label>
                            </td>
                            <td><a href="/systemadmin/users/${user.username}/">${user.username}</a></td>
                            <td>${user.cname}</td>
                        </tr>
                        </#list>
                        <tr>
                            <td colspan="3" style="text-align:center;">
                                <button id="btn-users" class="btn btn-medium btn-primary">更新用户</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                </form>
            </div>
            <div class="span6">
                <form id="form-resources" method="POST" action="/systemadmin/roles/${role.rolename}/">
                    <input type="hidden" name="resources" value="true">
                    <table class="table table-striped  table-condensed">
                        <thead>
                            <tr><th>选中</th><th>资源名称</th><th>资源描述</th></tr>
                        </thead>
                        <tbody>
                            <#list acls as acl>
                            <tr>
                                <td><label class="checkbox">
                                    <input type="checkbox" name="paths" value="${acl.path}" <#if acl.enable>checked</#if> <#if 'ROLE_ADMIN'==role.rolename>disabled="true"</#if>>
                                </label>
                                </td>
                                <td><a href="/systemadmin/resource/?path=${acl.path?url('utf8')}">${acl.path}</a></td>
                                <td>${acl.pathdesc}</td>
                            </tr>
                            </#list>
                            <tr>
                                <td colspan="3" style="text-align:center;">
                                    <#if 'ROLE_ADMIN' == role.rolename>
                                        <div class="well disabled">管理员无需授权</div>
                                    <#else>
                                        <button id="btn-resources" class="btn btn-medium btn-primary">更新资源</button>
                                    </#if>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </di>
        </div>
    </div>
</div>

<script type="text/javascript">
    $("#btn-users").click(function(){
        $("#form-users").submit();
    });
    $("#btn-resources").click(function(){
        $("#form-resources").submit();
    });
</script>

<#include "/commons/footer.ftl" />
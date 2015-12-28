<#assign _title="美分期管理后台-管理角色列表">
<#include "commons/header.ftl" />

<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container">

        <div class="row-fluid">


            <div>
                <legend>管理列表</legend>

                <table class="table table-bordered">

                    <tr>
                        <td>ID</td>
                        <td>角色名</td>
                        <td>角色描述</td>
                        <td>操作</td>
                    </tr>
                <#list roles as sysRole>
                    <tr>
                        <td>${sysRole.id}</td>
                        <td>${sysRole.rolename}</td>
                        <td>${sysRole.roledesc}</td>
                        <td><a href="javascript:void(0)" onclick="roleDel('${sysRole.id}','${sysRole.rolename}')">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/sysuser/role_edit/?id=${sysRole.id}" target="_blank">Edit</a></td>
                    </tr>

                </#list>
                </table>
            </div>




        </div>

    </div>
</div>


<script>
    function roleDel(rid, rolename){
        if(!confirm("确定要删除角色"+rolename+"吗？删除不可恢复，请谨慎操作！！！")){
            return;
        }
        window.open("/sysuser/role_delete/?id="+rid);
    }
</script>
<#include "/commons/footer.ftl" />
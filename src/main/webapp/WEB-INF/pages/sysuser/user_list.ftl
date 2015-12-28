<#assign _title="美分期管理后台-管理用户列表">
<#include "commons/header.ftl" />

<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />
<style type="text/css">
  .table-responsive{
    width:100%;
  }
  select{
    width:100px;
  }
</style>
<div class="container" id="enlarge-body">
    <div class="container ">
        <div class="row-fluid">
			<table width="100%" height="30" border="0" cellpadding="5" cellspacing="0" bgcolor="#000000">
  <tr>
    <td><span class="STYLE2">用户管理</span></td>
  </tr>
</table>
<br />

    
</div>
<br />
			
            <div>
                <legend>管理列表</legend>

                <table class="table table-bordered">

                    <tr>
                        <td>ID</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>手机号码</td>
                        <td>角色</td>
                        <td>状态</td>
                        <td>注册时间</td>
                        <td>操作</td>
                    </tr>
                <#list users as sysUser>
                    <tr>
                        <td>${sysUser.id}</td>
                        <td><a href="/user/info/?id=${sysUser.id}" target="_blank">${sysUser.username}</a></td>
                        <td>${sysUser.realname}</td>
                        <td>${sysUser.mobile}</td>
                        <td>
                        	<#list roles as role>
	                        	<#if sysUser.roleId == role.id>
                        		${role.rolename!}
                        		</#if>
                        	</#list>
                        </td>
                        <td>${sysUser.status}</td>
                        <td>${sysUser.created?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td><a href="javascript:void(0)" onclick="userDel('${sysUser.id}','${sysUser.username}')">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/sysuser/edit/?id=${sysUser.id}" target="_blank">Edit</a></td>
                    </tr>

                </#list>
                </table>
            </div>




        </div>

    </div>
</div>


<script>
    function userDel(uid, username){
        if(!confirm("确定要删除用户名为"+username+"的用户吗？")){
            return;
        }

        window.open("/sysuser/delete/?id="+uid);

    }
</script>
<#include "/commons/footer.ftl" />
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
    <div class="page-header"><h3>全部用户列表 (${users?size})</h3></div>
    <div class="container-fluid">

        <div class="row-fluid">
                <table class="table table-striped table-bordered table-condensed counter">
                    <thead>
                        <tr>
                          <th>用户名称</th><th>中文名</th><th>邮件</th><th>电话</th><th>世界邦uid</th>
                          <th>密码更新时间</th><th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list users as user>
                        <tr>
                            <td><a href="/systemadmin/users/${user.username}/">${user.username}</a></td>
                            <td>${user.cname}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td><#if user.uid gt 0><a href="/search/user-readonly/?uid=${user.uid}">${user.uid}</a></#if></td>
                            <td>${user.passwordUpdatedAt?datetime}</td>
                            <td><#if user.username!="admin">
                                <button class="btn" delete-url="/systemadmin/users/${user.username}/">删除</button>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
        </div>
        <div class="row-fluid">
            <div class="span8">
                <form class="well form-inline text-center" method="GET" >
                  <input id="username" name="username" type="text" class="input-medium" placeholder="用户名(3-16个英文字符)">
                  <button id="btn-create" type="btn" class="btn" >创建用户</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $("#btn-create").click(function(){
        var username = $.trim($("#username").val());
        if(username.length>3 && username.length<16){
            window.location.href="/systemadmin/users/"+$("#username").val()+"/";
        }else{
            alert("用户名长度为3-16个字符");
        }
        return false;
    });
    function delete_user(event){
        if(!confirm("确定删除用户么？")){
            return;
        }
        var target = $(event.target);
        var deleteurl = target.attr("delete-url");
        $.post(deleteurl,
            {
            method:"POST",_method:"DELETE"
            },function(data){
                if("true" == data){
                    target.parent().parent().remove();
                }else{
                    alert("删除用户失败");
                }
        });
    }
    $(":button[delete-url]").click(delete_user);
</script>

<#include "/commons/footer.ftl" />
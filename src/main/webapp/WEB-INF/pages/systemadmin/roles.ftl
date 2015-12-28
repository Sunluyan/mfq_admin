<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
    <div class="page-header"><h3>全部角色列表 (${roles?size})</h3></div>
    <div class="container-fluid">

        <div class="row-fluid">
            <div class="span8">
                <table class="table table-striped table-bordered table-condensed counter">
                    <thead>
                        <tr><th width="25%">角色名称</th><th>角色描述</th><th>操作</th></tr>
                    </thead>
                    <tbody id="record-list">
                        <#list roles as role>
                        <tr id="_${role.rolename}">
                            <td><a href="/systemadmin/roles/${role.rolename}/">${role.rolename}</a></td>
                            <td>${role.roledesc}</td>
                            <td><#if role.rolename!="ROLE_ADMIN"><button class="btn" data="${role.rolename}">删除</button></#if></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <form class="well form-inline" method="POST" action="#nogo">
                    <input name="_method" type="hidden" value="PUT">
                    <input id="rolename" name="rolename" type="text" class="input-large" placeholder="角色名称，例如ROLE_DEV">
                    <input id="roledesc" name="roledesc" type="text" class="input-medium" placeholder="角色描述，例如开发者">
                    <button id="btn-create" type="button" class="btn">创建角色</button>
                </form>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
    function delete_role(event){
        var btndelete=$(event.target);
        var rolename = btndelete.attr("data");
        if(confirm('确定删除角色 '+rolename+' 么？')){
            $.post("/systemadmin/roles/"+rolename+"/",
                {_method: "DELETE"},
                function(data){
                    if("true" == data){
                        btndelete.parent().parent().remove();
                    }else{
                        alert("删除失败!");
                    }
                }
                );

        }

    }
    function create_role(){
        $.post("/systemadmin/roles/",
               { _method: "PUT", rolename: $("#rolename").val(),roledesc:$("#roledesc").val() },
               function(data){
                    if("true" == data){
                        var rolename = $("#rolename").val();
                        var roledesc = $("#roledesc").val();
                        var record = '<tr><td><a href="/systemadmin/roles/'+rolename+'/">'+rolename+'</a></td><td>'+roledesc+'</td><td><button class="btn" data="'+rolename+'">删除</button></td></tr>';
                        $("#record-list").append(record);
                        $("#rolename").val("");
                        $("#roledesc").val("");
                        $(":button[data]").last().click(delete_role);
                    }else{
                        alert("添加失败!");
                    }
               }
        );
    }
    $("#btn-create").click(function(){
        create_role();
    });
    $(":button[data]").click(delete_role);
</script>

<#include "/commons/footer.ftl" />
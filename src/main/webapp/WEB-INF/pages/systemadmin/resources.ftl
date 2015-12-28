<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
    <div class="page-header"><h4>全部资源列表 (${resources?size})</h4></div>
    <div class="container-fluid">

        <div class="row-fluid">
            <div class="span8">
                <table class="table table-striped table-bordered table-condensed counter">
                    <thead>
                        <tr><th>资源路径</th><th>资源描述</th><th>操作</th></tr>
                    </thead>
                    <tbody id="record-list">
                        <#list resources as res>
                        <tr>
                            <td><a href="/systemadmin/resource/?path=${res.path?url('utf8')}">${res.path}</a></td>
                            <td>${res.pathdesc}</td>
                            <td><button class="btn" path="${res.path}">删除</button></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <form id="form-resource" class="well form-inline" method="POST" action="#nogo">
                    <input name="_method" type="hidden" value="PUT">
                    <input id="path" name="path" type="text" class="input-xlarge" placeholder="资源路径，例如'/systemadmin/users/'">
                    <input id="pathdesc" name="pathdesc" type="text" class="input-large" placeholder="资源描述,例如'用户管理'">
                    <button id="btn-create" type="button" class="btn">创建资源</button>
                </form>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
    function delete_resource(event){
        var btnobj = $(event.target);
        var path = btnobj.attr("path");
        if(confirm("确定删除资源么？这将可能导致某些角色或者用户无法访问资源，\n如果你不清楚请联系管理员！")
            && confirm("真的删除资源么？此操作不可恢复!")
            ){
            $.post("/systemadmin/resources/",
                {_method: "DELETE",path:path},
                function(data){
                    if("true" == data){
                        btnobj.parent().parent().remove();
                    }else{
                        alert("删除资源失败!刷新页面或者联系管理员!");
                    }
                }
            );
        }
    }
    function create_resource(path,pathdesc){
        $.post("/systemadmin/resources/",
               { _method: "PUT", path: path,pathdesc:pathdesc },
               function(data){
                    if("true" == data){
                        var record = '<tr><td>'+path+'</td><td>'+pathdesc+'</td><td><button class="btn" path="'+path+'">删除</button></td></tr>';
                        $("#record-list").append(record);
                        $("#path").val("");
                        $("#pathdesc").val("");
                        $(":button[path]").last().click(delete_resource);
                    }else{
                        alert("添加失败!");
                    }
               }
        );
    }
    $("#btn-create").click(function(){
        var path = $.trim($("#path").val());
        var pathdesc = $.trim($("#pathdesc").val());
        if(path.length == 0 || pathdesc.length == 0){
            return;
        }
        create_resource(path,pathdesc);
    });


    $(":button[path]").click(delete_resource);
</script>

<#include "/commons/footer.ftl" />
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
    <div class="page-header"><h3>${user.name}用户属于角色</h3></div>
    <div class="container-fluid">

        <div class="row-fluid">
            <div class="span8">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                        <tr><th>角色名称</th><th>角色描述</th></tr>
                    </thead>
                    <tbody>
                        <#list roles as role>
                        <tr>
                            <td><a href="/systemadmin/roles/${role.name}/">${role.name}</a></td>
                            <td>${role.desc}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>


<#include "/commons/footer.ftl" />
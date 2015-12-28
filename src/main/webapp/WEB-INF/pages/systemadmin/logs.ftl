<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />
<style type="text/css">
th{
    overflow: hidden;
    text-align: left;
    text-overflow: ellipsis;
    white-space: nowrap;
}
th:nth-child(1){width: 40px;}
th:nth-child(2){width: 102px;}
th:nth-child(3){width: 60px;}
th:nth-child(4){width: 115px;}
th:nth-child(5){width: 500px;}
th:nth-child(6){width: 40px;}
.pager-inline{margin: 0px 0px;float: right;display: inline;}
</style>

<div class="container-fluid">
    <div class="page-header"><h3>操作日志<small>用户操作/系统维护日志</small></h3></div>

    <form class="form-inline search-form" id="search-form" method="POST">
        <table class="table table-striped table-bordered table-condensed" style="font-size:10px;">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                    <th><input name="user" value="${user!}" type="text" class="input-small input-search-item" placeholder="操作人"></th>
                    <th><input name="type" value="${type!}" type="text" class="input-medium input-search-item" placeholder="类型"></th>
                    <th colspan="2">
                      <input name="startdate" value="${startdate!}" type="text" class="input-medium input-search-item" placeholder="开始日期(2012-10-15)">
                      <input name="enddate" value="${enddate!}" type="text" class="input-medium input-search-item" placeholder="结束日期(2012-11-24)">
                      <input name="page" type="hidden" value="1" />
                      <button type="submit" class="btn btn-primary">搜索</button>
                      <button type="button" id="reset" class="btn">清空</button>
                      <#if page gt 1 || hasnext>
                      <ul class="pager pager-small pager-inline">
                          <#if page gt 1><li><a class="page-link" data-id="${page-1}">前一页</a></li></#if>
                          <#if hasnext><li><a class="page-link" data-id="${page+1}">后一页</a></li></#if>
                      </ul>
                      </#if>
                    </th>
                </tr>
                <tr>
                    <th>ID</th>
                    <th>时间</th>
                    <th>操作人</th>
                    <th>类型</th>
                    <th class="text-wrap">内容</th>
                    <th>IP</th>
                </tr>
            </thead>
            <tbody>

                <#list logs as log>
                <tr>
                    <td>${log.id}</td>
                    <td>${log.createdAt?datetime}</td>
                    <td>${log.user}</td>
                    <td>${log.type}</td>
                    <td class="text-wrap">${log.description}</td>
                    <td>${log.ip}</td>
                </tr>
                </#list>

            </tbody>
        </table>
        </form>
    </div>

<script type="text/javascript">

    $(function(){
        $(".page-link").click(function(event){
            var target = $(event.target);
            $("input[name=page]").val(target.attr('data-id'));
            $('#search-form').submit();
        });
        $("#reset").click(function(){
            $('input[type=text]').each(function(i,x){$(x).val('');});
        });
    });

</script>

<#include "/commons/footer.ftl" />
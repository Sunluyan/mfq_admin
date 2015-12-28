<#assign _title="达人任务管理系统" />
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
<form class="well search-form" action="" method="GET" id="search-form">
  <span style="font-size:18px;margin:0px 20px;">
    <a href="/doyen/task/"><i class="icon-home" style="vertical-align:middle;"></i>任务管理</a>
  </span>
  <select id="form-status" name="status" class="input-medium">
      <option <#if RequestParameters.status==0>SELECTED</#if> value="0">正常</option>
      <option <#if RequestParameters.status==1>SELECTED</#if> value="1">下线</option>
  </select>
  <button type="submit" class="btn"><i class="icon-search"></i>搜索</button>
  <input name="page" type="hidden" value="1">
  <span class="pull-right">
    <div class="btn-group" style="display:inline;">
      <a class="btn" href="/doyen/task/add/">创建任务</a>
    </div>
    <ul class="pager pager-small pager-inline pull-right">
      <#if page gt 1 || hasnext>
        <#if page gt 1><li><a class="page-link" data-id="${page-1}">前一页 <i class="icon-backward"></i></a></li></#if>
        <#if hasnext><li><a class="page-link" data-id="${page+1}">后一页 <i class="icon-forward"></i></a></li></#if>
      </#if>
    </ul>
    </span>
</form>

<#if tasks??>
<table class="table table-striped table-bordered table-condensed" style="font-size:100%;">
  <thead>
    <tr>
      <th width="50px">编号</th>
      <th>名称</th>
      <th>状态</th>
      <th>截止时间</th>
      <th>任务详情</th>
      <th>任务动作</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
    <#list tasks as task>
    <tr>
      <td>${task.tid}</td>
      <td><a href="/doyen/task/add/?tid=${task.tid}">${(task.name)!}</a></td>
      <td>${task.status}</td>
      <td>${task.deadline?number_to_date}</td>
      <td>${task.url}</td>
      <td>${task.actionurl}</td>
      <td>${task.updatedAt?number_to_datetime}</td>
      <td></td>
    </tr>
    </#list>
  </tbody>
</table>
</#if>
</div>

<script type="text/javascript">
    $(".page-link").click(function(event){
        var target = $(event.target);
        $("input[name=page]").val(target.attr('data-id'));
        $('#search-form').submit();
    });
</script>

<#include "commons/footer.ftl" />
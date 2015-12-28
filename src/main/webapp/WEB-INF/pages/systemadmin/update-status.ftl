<#assign _title="刷新系统缓存">
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />


<div class="container">
<div class="row">
  <div class="span8">
    <h2>系统缓存列表</h2>
    <table class="table table-striped table-bordered table-hover">
      <thead>
        <tr>
          <th>路径</th>
          <th>最后更新时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <#list status?keys as path>
        <tr>
          <td>${path}</td>
          <td>${(status[path])?number_to_datetime}</td>
          <td><a class="btn pull-right js-sync" data-path="${path}">刷新缓存</a></td>
        </tr>
        </#list>
      </tbody>
    </table>
  </div>
  </div>
  <div class="row">
    <ul>
      <li>非专业人士，请不要操作，后果很严重！</li>
    </ul>
  </div>
</div>

<#include "/commons/footer.ftl" />
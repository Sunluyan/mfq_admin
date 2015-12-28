<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container-fluid">
  <div class="page-header">
    <h3>SQL语句查询
    </h3>
  </div>
  <form id="form" class="well form-horizontal trade-add" method="POST" action="/systemadmin/sql/">
      <select name="bizName" class="input-medium">
        <#list ['compass','tidy','admin','minitrack','biz_visa','biz_tour', 'biz_sales'] as bz>
          <option value="${bz}_readonly" ${(bizName == bz+'_readonly')?string('SELECTED','')}>${bz}</option>
        </#list>
      </select>
      <textarea name="sql" class="input-xxlarge">${sql!}</textarea>
      <select name="page" class="input-small">
        <#list [10,20,50] as limit>
          <option value="${limit}" <#if limit==page>SELECTED</#if>>${limit}</option>
        </#list>
      </select>
    <button class="btn btn-primary" type="sumit">查询</button>
  </form>

  <#if error>
    <span>SQL语句错误？？？</span>
    <div><pre>${error}</pre></div>
  </#if>
  <#if records?has_content>
  <table class="table table-striped table-bordered table-condensed">
    <thead>
      <tr><#list labels as label><th>${label}</th></#list></tr>
    </thead>
    <tbody>
      <#list records as record>
      <tr>
        <#list record as field><td><#if field?length gt 512><pre class="pre-scrollable">${field?html}</pre><#else>${field?html}</#if></td></#list>
      </tr>
      </#list>
    </tbody>
  </table>
  </#if>



<#include "/commons/footer.ftl" />
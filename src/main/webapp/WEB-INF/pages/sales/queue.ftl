<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='任务列表'>
<#assign toolbar="search">
<#assign tab1="clue">
<#assign tab2="cluelist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
    <#include "/sales/sales_clue_nav.ftl">

  <ul class="nav nav-pills">
    <li role="presentation" ${(team==1)?string('class="active"','')}><a href="/sales/admin/queue/?team=1">销售队列</a></li>
    <li role="presentation" ${(team==2)?string('class="active"','')}><a href="/sales/admin/queue/?team=2">销售支持队列</a></li>
    <li role="presentation" ${(team==0)?string('class="active"','')}><a href="/sales/admin/queue/?team=0">商机提醒队列</a></li>
  </ul>

<#if team gt 0>
		<table class="table table-striped table-bordered table-condensed vcenter">
		  <tr>
        <td>ID ${queues?size}</td>
        <td>权重</td>
        <td>SourceKey</td>
        <td>Mobile</td>
        <td>UID</td>
        <td>销售</td>
        <td>创建时间</td>
        <td>首次分配时间</td>
        <td>最近分配时间</td>
        <td>期望外呼时间</td>
        <td>SIGN</td>
      </tr>
      <#list queues as q>
        <tr>
          <td>${q.so.id}</td>
          <td>${q.weight}</td>
          <td>${q.so.sourceKey} ${q.so.sourceName}</td>
          <td>${q.so.mobile}</td>
          <td>${q.so.uid} ${q.so.name}</td>
          <td>${q.so.salesId}</td>
          <td>${q.so.createdAt?datetime}</td>
          <td><#if (q.so.firstAssignedAt)??>${q.so.firstAssignedAt?datetime}</#if></td>
          <td><#if (q.so.latestAssignedAt)??>${(q.so.latestAssignedAt)?datetime}</#if></td>
          <td><#if (q.so.expectedTime)??>${(q.so.expectedTime)?datetime}</#if></td>
          <td>${q.so.sign} <#if q.isGrabable(config)><span class="label label-success">可领取</span></#if></td>
        </tr>
      </#list>
    </table>
<#else>
    <table class="table table-striped table-bordered table-condensed vcenter">
      <tr>
        <td>ID ${queues?size}</td>
        <td>Mobile</td>
        <td>UID</td>
        <td>销售</td>
        <td>辅助销售</td>
        <td>商机</td>
        <td>创建时间</td>
        <td>期望外呼时间</td>
      </tr>
      <#list queues as q>
        <tr>
          <td>${q.id}</td>
          <td>${q.mobile}</td>
          <td>${q.uid} ${q.name}</td>
          <td>${q.salesId}</td>
          <td>${q.salesAssistId}</td>
          <td><a class="block" target="_blank" href="/sales/opportunity/detail/?oid=${q.opportunityId}">${q.opportunityId}</a></td>
          <td>${q.createdAt?datetime}</td>
          <td><#if (q.expectedTime)??>${(q.expectedTime)?datetime}</#if></td>
        </tr>
      </#list>
      <tr>
      	<td colspan="8">追单提醒</td>
      </tr>
      <#list reminds as r>
      <tr>
      	<td>${r.id}</td>
      	<td>${r.mobile}</td>
      	<td>${r.uid}</td>
      	<td>${r.salesId}</td>
      	<td>${r.salesAssistId}</td>
      	<td><a class="block" target="_blank" href="/sales/opportunity/detail/?oid=${r.refId}">${r.refId}</a></td>
      	<td>${r.createdAt?datetime}</td>
      	<td><#if r.remindDate??>${r.remindDate?datetime}</#if>${(r.type.value=11)?string('（手工提醒）', '（自动提醒）')}</td>
      </tr>	
      </#list>
    </table>
</#if>
    <ul>
      <li>SIGN:  2约定时间 4老用户 8领取 16重复入队列 32发送短信</li>
    </ul>
<#include "commons/footer.ftl">

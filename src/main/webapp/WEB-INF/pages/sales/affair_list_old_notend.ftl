<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='未处理完成的CRM旧事务-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="oldaffairlist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">
	
	<h3>未处理完成的CRM旧事务列表</h3>
	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th>事务名称</th>
				<th>商机名称</th>
				<th>用户UID</th>
				<th>事务状态</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<#list affairList as a>
			<tr>
				<td>${a.name}</td>
				<td>${a.opportunity.name}</td>
				<td>${a.opportunity.uid}</td>
				<td>${OrderStatus.findByValue(a.orderStatus).desc!''}</td>
				<td><a class="block" href="/sales/opportunity/detail/?oid=${a.opportunityId}&aid=${a.id}" target="_blank">查看详情</a></td>
			</tr>
			</#list>
		</tbody>
	</table>
	
	<form class="well well-sm search-form" action="/sales/affair/seaerchold/" style="display:none;" method="GET" id="search-form">
		<input class="form-control" id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	
	<div style="text-align:center;">共${count}条事务</div>
	<@pager cond.pageIndex pageCount />
</div>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='无事务的商机列表-${sysname}'>
<#assign toolbar="search">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	
	<h3>没有事务的商机列表</h3>
	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th>商机名称</th>
				<th>UID</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<#list oppList as opp>
			<tr>
				<td>${opp.name}</td>
				<td>${opp.uid}</td>
				<td><a class="block" href="/sales/opportunity/detail/?oid=${opp.id}" target="_blank">查看详情</a></td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

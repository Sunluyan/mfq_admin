<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='工作人员-${sysname}'>
<#assign toolbar="search">
<#assign tab1="setting">
<#assign tab2="outcallsetting">
<#assign tab3="salesuser">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_setting_nav.ftl">
	<#include "/sales/sales_outcallsetting_nav.ftl">

    <div class="container-option">
        <a class="btn btn-info btn-sm" href="/search/merchant-doyen/?sign=TravelCounselor">添加销售标志</a>
   	    <a class="btn btn-info btn-sm" href="/sales/admin/sales/new/">新增人员</a>
	</div>

	<table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
		<thead>
			<tr class="success">
        		<th>UID</th>
				<th>姓名</th>
				<th>工作容量</th>
				<th>部门/分组</th>
       		 	<th>座席号</th>
        		<th>最近领取时间</th>
        		<th>当日领取次数</th>
				<th>详情</th>
			</tr>
		</thead>
  		<tbody>
			<#list salesUserList as sales>
			<tr>
        		<td>${sales.id}</td>
				<td>${sales.username}/${sales.name}</td>
				<td>${sales.workVol}</td>
				<td>${(Team.findByValue(sales.team).desc)!''}<#if sales.subTeamName?? && sales.subTeamName != ''>/${sales.subTeamName}</#if></td>
		        <td>${sales.cno}</td>
		        <td>${sales.lastGrapAt?datetime}</td>
		        <td>${sales.todayGrapCount}</td>
				<td><a class="block" href="/sales/admin/sales/detail/?id=${sales.id}">详情</a></td>
			</tr>
    	</#list>
		</tbody>
	</table>
</div>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

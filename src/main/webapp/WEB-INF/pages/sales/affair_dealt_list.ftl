<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='已成单事务-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="affairdealt">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">

	<form class="form-inline well well-sm" action="/sales/affair/searchdealt/" method="GET">
		<div class="input-group">
	        <div class="input-group-addon">
	          <#assign queryTypeOptions=['', 'u', 'm', 'n']>
	    	  <#assign queryTypeValues=['智能查询', '用户UID', '手机号', '用户名']>
	          <select name="qt">
				<#list queryTypeOptions as opt>
				  <option value="${opt}" ${(opt=cond.queryType)?string('selected="selected"', '')}>${queryTypeValues[opt_index]}</option>
				</#list>
			  </select>
	        </div>
	        <input type="text" class="form-control" name="q" value="${cond.query}" size="20">
	    </div>
		<div class="input-group">
            <div class="input-group-addon">主/辅销售负责人：</div>
			<#assign asaNames=['主销售负责人', '辅销售负责人']>
			<#assign asaValues=[1, 2]>
			<select class="form-control" name="asa">
				<#list asaValues as a>
				<option value="${a}" ${(a=asa)?string('selected="selected"', '')}>${asaNames[a_index]}</option>
				</#list>
			</select>
        </div>
        <div class="input-group">
        	<div class="input-group-addon">商机受理时间：</div>
			<input class="form-control reset" type="text" name="cb" id="createdAtBegin" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="开始时间" size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="ce" id="createdAtEnd" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="截止时间" size="20">
		</div>
		<div class="input-group">    
			<div class="input-group-addon">事务类型：</div>
			<select class="form-control" name="at">
				<option value="">请选择</option>
				<#list AffairType.values() as ats>
				<option value="${ats.value}" ${(cond.affairType=ats.value)?string('selected="selected"', '')}>${ats.desc}</option>
				</#list>
			</select>
        </div>
        <div class="input-group">
            <button type="submit" class="btn btn-info btn-sm">查询</button>
        </div>
	</form>

	<table class="table table-striped table-bordered table-condensed vcenter table-yunhu">
		<thead>
			<tr class="success">
				<th>用户UID</th>
				<th>商机名称</th>
				<th>事务名称</th>
				<th>事务类型</th>
				<th>目的地</th>
				<th>出行时间</th>
				<th>事务状态</th>
				<th>最新备注信息</th>
				<th>商机受理时间</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<#list affairs as a>
			<tr>
				<td>${a.opportunity.uid}</td>
				<td>${a.opportunity.name}</td>
				<td>${a.name?html}</td>
				<td>${a.type.desc}</td>
				<td>
					<#if AffairType.PLAN_A=a.type>
						${planALocations['l_a_' + a.packId]!''}
					<#elseif AffairType.PLAN_B=a.type>
						${planBLocations['l_b_' + a.advisorId]!''}
					</#if>
				</td>
				<td>${a.goDate}</td>
				<td>${OrderStatus.findByValue(a.orderStatus).desc!''}</td>
				<td>${(affairOutcallDetails[a.id+''].comment)!''}</td>
				<td>${a.opportunity.createdAt?datetime}</td>
				<td><a class="block" href="/sales/opportunity/detail/?oid=${a.opportunity.id}&aid=${a.id}">查看详情</a></td>
			</tr>
			</#list>
		</tbody>
	</table>

	<form class="well well-sm search-form" action="/sales/affair/searchdealt/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input type="hidden" name="asa" value="${asa}">
		<input class="form-control" type="hidden" name="cb" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="ce" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="at" value="${cond.affairType}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条事务</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#createdAtBegin').datetimepicker();
		$('#createdAtEnd').datetimepicker();
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

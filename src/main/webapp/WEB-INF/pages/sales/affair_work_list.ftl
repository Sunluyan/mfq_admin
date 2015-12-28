<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='我的跟单工作台-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="affairworklist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">

	<form class="form-inline well well-sm" action="/sales/affair/searchwork/" method="GET">
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
			<div class="input-group-addon">最近更新时间：</div>
			<input class="form-control reset" type="text" name="ub" id="updatedAtBegin" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                value="<#if cond.updatedAtBegin gt 0>${cond.updatedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
                placeholder="开始时间"
                size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="ue" id="updatedAtEnd" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                value="<#if cond.updatedAtEnd gt 0>${cond.updatedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>"  
                placeholder="截止时间"
                size="20">
        </div>
        <div class="input-group">    
			<div class="input-group-addon">事务状态：</div>
			<select class="form-control" name="st">
				<option value="">请选择</option>
				<#list OrderStatus.values() as os>
				<#if os.following>
				<option value="${os.value}" ${(cond.orderStatusList?size=1 && os.value=cond.orderStatusList[0])?string('selected="selected"', '')}>${os.desc}</option>
				</#if>
				</#list>
			</select>
        </div>
        <div class="input-group">    
			<div class="input-group-addon">是否标星：</div>
			<select class="form-control" name="r">
				<option value="">请选择</option>
				<#list OrderRateInfo.values() as is>
				<option value="${is.value}" ${(is.value==cond.orderRate)?string('selected="selected"', '')}>${is.desc}</option>
				</#list>
			</select>
        </div>
        <div class="input-group">
			<div class="input-group-addon">出行时间：</div>
			<input class="form-control" type="text" name="d" size="10" value="${cond.goDate}">
            <div class="input-group-addon">（文本描述）</div>
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
			<div class="input-group-addon">事务类型：</div>
			<select class="form-control" name="at">
				<option value="">请选择</option>
				<#list AffairType.values() as ats>
				<option value="${ats.value}" ${(cond.affairType=ats.value)?string('selected="selected"', '')}>${ats.desc}</option>
				</#list>
			</select>
        </div>
       	<div class="input-group">
			<div class="input-group-addon">最新备注信息：</div>
			<input class="form-control" type="text" name="ct" size="20" value="${cond.comment}">
        </div>
        <div class="input-group">
            <button type="submit" class="btn btn-info btn-sm">查询</button>
        </div>
	</form>

	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th rowspan="2">用户UID</th>
				<th>商机</th>
				<th colspan="8">事务</th>
			</tr>
			<tr class="success">
				<th>商机名称</th>
				<th>事务名称</th>
				<th>事务类型</th>
				<th>是否标星</th>
				<th>目的地</th>
				<th>出行时间</th>
				<th>事务状态</th>
				<th>最新备注信息</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<#list affairs as a>
			<#assign orderRateNum=a.orderRate>
			<#if a.orderRate <= 3><#assign orderRateNum=3><#elseif a.orderRate gte 4><#assign orderRateNum=5></#if>
			<tr>
				<#if a.opportunity.uid!=preUid>
				<td rowspan="${countMap['uid' + a.opportunity.uid]}">${a.opportunity.uid}</td>
				<#assign preUid=a.opportunity.uid>
				</#if>
				<#if a.opportunity.id!=preOppId>
				<td rowspan="${countMap['opp' + a.opportunity.id]}">${a.opportunity.name}</td>
				<#assign preOppId=a.opportunity.id>
				</#if>
				<td>${a.name?html}</td>
				<td>${a.type.desc}</td>
				<td>
                    <#if orderRateNum?? && orderRateNum == 5><span class="glyphicon glyphicon-star"></span><#else>--</#if>
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
				<td><a class="block" href="/sales/opportunity/detail/?oid=${a.opportunity.id}&aid=${a.id}">查看详情</a></td>
			</tr>
			</#list>
		</tbody>
	</table>

	<form class="well well-sm search-form" action="/sales/affair/searchwork/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input type="hidden" name="ub" value="<#if cond.updatedAtBegin gt 0>${cond.updatedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="ue" value="<#if cond.updatedAtEnd gt 0>${cond.updatedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="st" value="${(cond.orderStatusList[0])!''}">
		<input type="hidden" name="r" value="${cond.orderRate}">
		<input type="hidden" name="d" value="${cond.goDate}">
		<input type="hidden" name="asa" value="${asa}">
		<input type="hidden" name="ct" value="${cond.comment}">
		<input type="hidden" name="at" value="${cond.affairType}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#updatedAtBegin').datetimepicker();
		$('#updatedAtEnd').datetimepicker();
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

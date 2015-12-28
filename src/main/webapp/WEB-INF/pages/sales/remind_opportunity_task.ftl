<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='商机外呼-${sysname}'>
<#assign toolbar="search">
<#assign tab1="remind">
<#assign tab2="task">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_remind_nav.ftl">
    
 	<div class="well well-sm bg-danger">
        今日成单情况：
        <span class="label label-bg label-success">成单：${affairOrderDayCount!0}</span>
        昨日成单情况：
        <span class="label label-bg label-success">成单：${affairOrderPreviousDayCount!0}</span>
    </div>
 
    <form class="form-inline well well-sm" id="searchForm" action="/sales/remind/opportunity/searchtask/" method="GET">
        <div class="input-group">
            <span class="input-group-addon">期望外呼时间</span>
			<input type="text" class="form-control reset" name="eb" id="expectedTimeBegin" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                placeholder="开始时间"
                value="<#if cond.expectedTimeBegin gt 0>${cond.expectedTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
                size="20">
            <span class="form-control reset">到</span>
			<input type="text" class="form-control reset" name="ee" id="expectedTimeEnd" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                placeholder="截止时间"
                value="<#if cond.expectedTimeEnd gt 0>${cond.expectedTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
                size="20">
		</div>
    
		<div class="input-group">
            <div class="input-group-addon" >是否标星：</div>
			<select class="form-control" name="r">
				<option value="">请选择</option>
				<#list OrderRateInfo.values() as is>
					<option value="${is.value}" ${(is.value=cond.orderRate)?string('selected="selected"', '')}>${is.desc}</option>
				</#list>
			</select>
		</div>
        <div class="input-group">
            <button type="submit" class="btn btn-info btn-sm">查询</button>
        </div>
    </form>
	
	<table class="table table-striped table-bordered table-condensed text-center vcenter table-yunhu">
  		<thead>
  			<tr class="success">
  				<th >用户UID</th>
  				<th >手机号</th>
  				<th >出行时间</th>
  				<th >方案类型</th>
  				<th >是否标星</th>
  				<th >最新外呼结论</th>
  				<th >期望外呼时间</th>
  				<th >商机详情</th>
  			</tr>
  		</thead>
  		<tbody>
			<#list taskInfoList as task>
			<#if affairs['a_'+task.latestAffairId].orderRate??>
			<#assign orderRateNum=affairs['a_'+task.latestAffairId].orderRate>
			<#if affairs['a_'+task.latestAffairId].orderRate <= 3><#assign orderRateNum=3><#elseif affairs['a_'+task.latestAffairId].orderRate gte 4><#assign orderRateNum=5></#if>
			</#if>
			<tr>
				<td><#if (task.uid > 0)>${task.uid}</#if></td>
				<td>${task.mobile}</td>
				<td>${affairs['a_'+task.latestAffairId].goDate!''}</td>
				<td>${affairs['a_'+task.latestAffairId].type.desc!''}</td>
				<td>
                    <#if orderRateNum?? && orderRateNum == 5><span class="glyphicon glyphicon-star"></span><#else>--</#if>
                </td>
				<td>
					<#if task.latestConclusion gt 0>${conclusions[task.latestConclusion+''].name!''}</#if>
					<#if task.latestConclusionValue gt 0>-${conclusions[task.latestConclusionValue+''].name!''}</#if>
				</td>
				<td><#if task.expectedTime??>${task.expectedTime?datetime}</#if></td>
				<td>
					<a class="link" href="/sales/opportunity/detail/?oid=${task.opportunityId}">查看</a>
				</td>
			</tr>
    		</#list>
		</tbody>
	</table>
	        
    <form class="well search-form" action="/sales/remind/opportunity/searchtask/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="eb" value="<#if cond.expectedTimeBegin gt 0>${cond.expectedTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="ed" value="<#if cond.expectedTimeEnd gt 0>${cond.expectedTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="r" value="${cond.orderRate}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#expectedTimeBegin').datetimepicker();
		$('#expectedTimeEnd').datetimepicker();
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

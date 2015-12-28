<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='我的跟单任务-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="worklist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">

	<form class="form-inline well well-sm" action="/sales/work/search/" method="GET">
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
			<div class="input-group-addon">最新备注时间：</div>
			<input class="form-control reset" type="text" name="rb" id="latestRemarkedAtBegin" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                value="<#if cond.latestRemarkedAtBegin gt 0>${cond.latestRemarkedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
                placeholder="开始时间"
                size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="re" id="latestRemarkedAtEnd" 
                data-date-format="yyyy-mm-dd hh:ii:ss" 
                value="<#if cond.latestRemarkedAtEnd gt 0>${cond.latestRemarkedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>"  
                placeholder="截止时间"
                size="20">
        </div> 
        <div class="input-group">    
			<div class="input-group-addon">事务状态：</div>
			<select class="form-control" name="st">
				<option value="">请选择</option>
				<#list OrderStatus.values() as os>
				<option value="${os.value}" ${(cond.orderStatusList?? && os.value=cond.orderStatusList[0])?string('selected="selected"', '')}>${os.desc}</option>
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
	
	<#if timeoutCount gt 0>
	<p class="alert alert-danger">您目前超时任务数为${timeoutCount}<#if timeoutCount gte timeoutCountUpper>，超时任务数>=${timeoutCountUpper}，不能抢新电话</#if></p>
	</#if>
	
	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th>任务分类</th>
				<th>所属商机</th>
				<th>所属事务</th>
				<th>事务类型</th>
				<th>是否标星</th>
				<th>事务状态</th>
				<th>期望完成时间</th>
				<th>超时时长</th>
				<th>最新备注</th>
				<th>详情</th>
			</tr>
		</thead>
		<tbody>
			<#list works as w>
			<#assign orderRateNum=w.affair.orderRate>
			<#if w.affair.orderRate <= 3><#assign orderRateNum=3><#elseif w.affair.orderRate gte 4><#assign orderRateNum=5></#if>
			<tr>
				<td>${(w.type.desc)!''}</td>
				<td>${w.opportunity.name}</td>
				<td>${w.affair.name}</td>
				<td>${w.affair.type.desc}</td>
				<td>
					<#if orderRateNum?? && orderRateNum == 5><span class="glyphicon glyphicon-star"></span><#else>--</#if>
				</td>
				<td>${OrderStatus.findByValue(w.affair.orderStatus).desc!''}</td>
				<td>
					<#assign timeoutflag=''>
					<#if (timeouts['flag_w_' + w.workId])??><#assign timeoutflag=timeouts['flag_w_' + w.workId]></#if>
					<span <#if 'r'==timeoutflag>class="label label-danger"<#elseif 'y'==timeoutflag>class="label label-warning"</#if>>
					${w.timeoutAt?datetime}
					</span>
				</td>
				<td>
					<#if (timeouts['t_w_' + w.workId])?? && timeouts['t_w_' + w.workId]?number gt 0>
					${DateTimeUtils.longToInterval((timeouts['t_w_'+w.workId]?number)*60*1000)}
					</#if>
				</td>
				<td>${(affairOutcallDetails[w.affair.id+''].comment)!''}</td>
				<td><a class="block" href="/sales/opportunity/detail/?oid=${w.opportunity.id}&aid=${w.affair.id}">查看详情</a></td>
			</tr>
			</#list>
		</tbody>
	</table>
	
	<form class="well well-sm search-form" action="/sales/work/search/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input class="form-control" type="hidden" name="rb" value="<#if cond.latestRemarkedAtBegin gt 0>${cond.latestRemarkedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="re" value="<#if cond.latestRemarkedAtEnd gt 0>${cond.latestRemarkedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="st" value="${(cond.orderStatusList[0])!''}">
		<input type="hidden" name="r" value="${cond.orderRate}">
		<input type="hidden" name="d" value="${cond.goDate}">
		<input type="hidden" name="ct" value="${cond.comment}">
		<input type="hidden" name="at" value="${cond.affairType}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#latestRemarkedAtBegin').datetimepicker();
		$('#latestRemarkedAtEnd').datetimepicker();
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

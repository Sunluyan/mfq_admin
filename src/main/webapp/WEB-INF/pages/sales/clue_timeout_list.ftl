<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='小帮手外呼超时列表-${sysname}'>
<#assign toolbar="search">
<#assign tab1="clue">
<#assign tab2="cluetimeoutlist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
    <#include "/sales/sales_clue_nav.ftl">

	<form class="form-inline well well-sm" action="/sales/admin/clue/searchtimeout/" id="cluesearchform" method="GET">
      <div class="input-group">
		<div class="input-group-addon">留电话时间：</div>
		<input type="text" class="form-control reset" name="cb" id="createdAtBegin" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
            placeholder="开始时间" 
            size="20">
        <span class="form-control reset" disabld>至</span>
		<input type="text" class="form-control reset" name="ce" id="createdAtEnd" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
            placeholder="截止时间" 
            size="20">
      </div>				
      <div class="input-group">
		<div class="input-group-addon">部门</div>
		<select class="form-control" name="t">
			<option value="">请选择</option>
			<#list Team.values() as t>
			<option value="${t.value}" ${(t.value=cond.team)?string('selected="selected"', '')}>${t.desc}</option>
			</#list>
		</select>
      </div>
      <div class="input-group">
        <div class="input-group-addon">留电话页</div>
		<select class="form-control" name="k">
			<option value="">全部小帮手</option>
			<#assign advisorKeys=['advisor', 'advisor_qunar', 'advisor_daoliuapp', 'advisor_weibocard']>
			<#assign advisorNames=['小帮手-重要', '小帮手-去哪儿', '	小帮手-导流APP', '小帮手-微博广告']>
			<#list advisorKeys as t>
			<option value="${t}" ${(t==cond.sourceKey)?string('selected="selected"', '')}>${advisorNames[t_index]}</option>
			</#list>
		</select>
      </div>
      <div class="input-group">
		<input type="submit" class="btn btn-info btn-sm"  value="查询">
	  </div>
	
	</form>
	
	<table class="table table-striped table-bordered table-condensed text-center">
		<thead>
			<tr class="success">
				<th>部门</th>
				<th>留电话页</th>
				<th>用户UID</th>
				<th>手机号</th>
				<th>留电话时间</th>
				<th>超时时长</th>
				<th>来源</th>
			</tr>
		</thead>
		<tbody>
			<#list tasks as t>
			<tr>
				<td><#if t.team??>${t.team.desc}</#if></td>
				<td>${t.sourceName}</td>
				<td>${t.uid}</td>
				<td>${t.mobile}</td>
				<td>${t.createdAt?datetime}</td>
				<#assign timeout=SalesUtils.calAdvisorOutcallTimeoutMillis(t.createdAt)>
				<td>${DateTimeUtils.longToInterval(SalesUtils.calAdvisorOutcallTimeoutMillis(t.createdAt))}</td>
				<td>
					<#include "sales/clue_source.ftl">
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
	
	<form class="well search-form well-sm" action="/sales/admin/clue/searchtimeout/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="cb" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="ce" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="t" value="${cond.team}">
		<input type="hidden" name="k" value="${cond.sourceKey}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
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

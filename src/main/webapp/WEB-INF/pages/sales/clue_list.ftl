<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='我的线索-${sysname}'>
<#assign toolbar="search">
<#assign tab1="clue">
<#assign tab2="cluelist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
    <#include "/sales/sales_clue_nav.ftl">
	<form class="form-inline well well-sm" action="/sales/clue/search/" method="GET">
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
        <div class="input-group-addon">最新外呼时间</div>
        <input type="text" class="form-control reset" name="lb" id="latestTimeBegin" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.latestTimeBegin gt 0>${cond.latestTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>"
            placeholder="开始时间" 
            size="20">
        <span class="form-control reset" disabld>至</span>
        <input type="text" class="form-control reset" name="le" id="latestTimeEnd" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.latestTimeEnd gt 0>${cond.latestTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>"  
            placeholder="截止时间" 
            size="20">
      </div>
      <div class="input-group">
          <div class="input-group-addon">留电话页</div>
          <select class="form-control" name="k">
            <option value="">请选择</option>
            <#list sourceTeams as t>
            <option value="${t.sourceKey}" ${(t.sourceKey==cond.sourceKey)?string('selected="selected"', '')}>${t.sourceName}</option>
            </#list>
          </select>
      </div>
      <div class="input-group">
        <div class="input-group-addon">最新外呼结论</div>
          <select class="form-control reset" name="c" onchange="javascript: sales.changeClueSearchConclusion(this);">
            <option value="">请选择</option>
            <#list ptypes as p>
            <option value="${p.val}" ${(cond.latestConclusion==p.val)?string('selected="selected"', '')}>${p.name}</option>
            </#list>
          </select>
          <select class="form-control reset" name="cv">
            <option value="">请选择</option>
            <#if cond.latestConclusion gt 0>
            <#list ctypes[''+cond.latestConclusion] as c>
            <option value="${c.val}" ${(cond.latestConclusionValue==c.val)?string('selected="selected"', '')}>${c.name}</option>
            </#list>
            </#if>
          </select>
      </div>
      <div class="input-group">
        <input type="submit" class="btn btn-info btn-sm" value="查询">
      </div>

	</form>

	<table class="table table-hover table-bordered table-condensed vcenter text-center table-yunhu">
		<thead>
			<tr class="success">
				<th>任务类型</th>
				<th>留电话页</th>
				<th>用户UID</th>
				<th>手机号</th>
				<th>最新外呼时间</th>
				<th>最新外呼结论</th>
				<th>来源</th>
				<th>线索详情</th>
			</tr>
		</thead>
		<tbody>
			<#list tasks as t>
			<tr>
				<td>${(t.taskType.desc)!''}</td>
				<td>${t.sourceName}</td>
				<td>${t.uid}</td>
				<td><span class="js-mobile-span text-info">${t.mobile}</span></td>
				<td><#if t.latestTime??>${t.latestTime?datetime}</#if></td>
				<td>
					<#if t.latestConclusion gt 0>${conclusions[t.latestConclusion+''].name!''}</#if>
					<#if t.latestConclusionValue gt 0>-${conclusions[t.latestConclusionValue+''].name!''}</#if>
				</td>
				<td>
					<#include "sales/clue_source.ftl">
				</td>
				<td>
					<a class="block" href="/sales/clue/detail/?taskInfoId=${t.id}">查看</a>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>

	<form class="well well-sm search-form" action="/sales/clue/search/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input type="hidden" name="k" value="${cond.sourceKey}">
		<input type="hidden" name="c" value="${cond.latestConclusion}">
		<input type="hidden" name="cv" value="${cond.latestConclusionValue}">
		<input type="hidden" name="lb" value="<#if cond.latestTimeBegin gt 0>${cond.latestTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="le" value="<#if cond.latestTimeEnd gt 0>${cond.latestTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#latestTimeBegin').datetimepicker();
		$('#latestTimeEnd').datetimepicker();
	    $('.js-mobile-span').addClass('glyphicon glyphicon-earphone').tooltip({
	        title: sales.requestCallHistoryEntries,
	        html: true,
			placement:'bottom'
	      });
		sales.conclusions = {<#list ptypes as p>"p${p.val}":[<#list ctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name?html}"}<#if c_index<ctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<ptypes?size-1>,</#if></#list>};
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='全部线索-${sysname}'>
<#assign toolbar="search">
<#assign tab1="clue">
<#assign tab2="cluelistall">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
    <#include "/sales/sales_clue_nav.ftl">

	<form class="form-inline well well-sm" action="/sales/admin/clue/searchall/" id="cluesearchform" method="GET">
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
		<div class="input-group-addon">首次分配时间：</div>
		<input type="text" class="form-control reset" name="fb" id="firstAssignedAtBegin" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.firstAssignedAtBegin gt 0>${cond.firstAssignedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
            placeholder="开始时间" 
            size="20">
        <span class="form-control reset" disabld>至</span>
		<input type="text" class="form-control reset" name="fe" id="firstAssignedAtEnd" 
            data-date-format="yyyy-mm-dd hh:ii:ss" 
            value="<#if cond.firstAssignedAtEnd gt 0>${cond.firstAssignedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" 
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
		<div class="input-group-addon">销售负责人（多个）：</div>
	    <div class="controls form-inline">
    		<div id="salesIdCheckbox"></div>
    		<span id="salesIdsDisplay"></span>
	    </div>
	    <input type="hidden" name="s" id="salesIds" value="<#list cond.salesIds as salesId>${salesId},</#list>" />
	  </div>
      <div class="input-group">
		<div class="input-group-addon">最新外呼时间：</div>
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
			<option value="-1" ${(cond.latestConclusion==-1)?string('selected="selected"', '')}>未外呼</option>
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
		<input type="button" class="btn btn-info btn-sm" onclick="javascript: search();" value="查询">
	  </div>
	  <div class="input-group">
	  	<input type="button" class="btn btn-info btn-sm" onclick="javascript: exportData();" value="导出">
	  </div>
	
	</form>
	
	<table class="table table-striped table-bordered table-condensed text-center">
		<thead>
			<tr class="success">
				<th>部门</th>
				<th>销售负责人</th>
				<th>留电话页</th>
				<th>用户UID</th>
				<th>手机号</th>
				<th>留电话时间</th>
				<th>首次分配时间</th>
				<th>最新外呼时间</th>
				<th>最新外呼结论</th>
				<th>来源</th>
				<th>线索详情</th>
			</tr>
		</thead>
		<tbody>
			<#list tasks as t>
			<tr>
				<td><#if t.team??>${t.team.desc}</#if></td>
				<td>
					${(salesUsers['u'+t.salesId].name)!''}
					<#--<button id="tm_btn_${t.id}" class="btn btn-info btn-sm" onclick="javascript: sales.toModifySales(${t.id});">修改</button>
					<button id="m_btn_${t.id}" class="btn btn-warning" style="display: none;" onclick="javascript: sales.modifySales(${t.id}, '/sales/admin/clue/searchall/');">修改</button>-->
				</td>
				<td>${t.sourceName}</td>
				<td>${t.uid}</td>
				<td>${t.mobile}</td>
				<td><#if t.createdAt??>${t.createdAt?datetime}</#if></td>
				<td><#if t.firstAssignedAt??>${t.firstAssignedAt?datetime}</#if></td>
				<td><#if t.latestTime??>${t.latestTime?datetime}</#if></td>
				<td>
					<#if t.latestConclusion gt 0>${conclusions[t.latestConclusion+''].name!''}</#if>
					<#if t.latestConclusionValue gt 0>-${conclusions[t.latestConclusionValue+''].name!''}</#if>
				</td>
				<td>
					<#include "sales/clue_source.ftl">
				</td>
				<td>
					<a class="link" href="/sales/clue/detail/?taskInfoId=${t.id}">查看</a>
				</td>
			</tr>
			</#list>
		</tbody>
	</table>
	
	<form class="well search-form well-sm" action="/sales/admin/clue/searchall/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input type="hidden" name="cb" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="ce" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="fb" value="<#if cond.firstAssignedAtBegin gt 0>${cond.firstAssignedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="fe" value="<#if cond.firstAssignedAtEnd gt 0>${cond.firstAssignedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="t" value="${cond.team}">
		<input type="hidden" name="s" value="<#list cond.salesIds as salesId>${salesId},</#list>">
		<input type="hidden" name="lb" value="<#if cond.latestTimeBegin gt 0>${cond.latestTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="le" value="<#if cond.latestTimeEnd gt 0>${cond.latestTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input type="hidden" name="k" value="${cond.sourceKey}">
		<input type="hidden" name="c" value="${cond.latestConclusion}">
		<input type="hidden" name="cv" value="${cond.latestConclusionValue}">
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#createdAtBegin').datetimepicker();
		$('#createdAtEnd').datetimepicker();
		$('#firstAssignedAtBegin').datetimepicker();
		$('#firstAssignedAtEnd').datetimepicker();
		$('#latestTimeBegin').datetimepicker();
		$('#latestTimeEnd').datetimepicker();
		//
		sales.salesUsers = [<#list salesUserList as sales>{"id":"${sales.id}", "name":"${sales.name}"}<#if sales_index<salesUserList?size-1>,</#if></#list>];
		sales.conclusions = {<#list ptypes as p>"p${p.val}":[<#list ctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name?html}"}<#if c_index<ctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<ptypes?size-1>,</#if></#list>};
		//
		var sales_sel = {
			salesList: '[<#list salesUserList as sales>{"id":${sales.id}, "username": "${sales.name}", "teamName": "${(Team.findByValue(sales.team).desc)!''}", "subTeamName": "${sales.subTeamName}"}<#if (sales_index<salesUserList?size-1)>,</#if></#list>]',
			selected: '<#list cond.salesIds as salesId>${salesId},</#list>',
			data: [],
			init: function() {
				var salesArr = eval(this.salesList);
				var selectedArr = this.selected.split(',');
				if (salesArr.length > 0) {
					for (var i = 0; i < salesArr.length; i++) {
						var isSelected = false;
						for (var j = 0; j < selectedArr.length; j++) {
							if (selectedArr[j] == salesArr[i].id) {
								isSelected = true;
								break;
							}
						}
						this.data.push({id: salesArr[i].id, label: salesArr[i].teamName + '-' + salesArr[i].subTeamName + '-' + salesArr[i].username, isChecked: isSelected});
					}
				}
			}
		};
		//
		sales_sel.init();
		//
		if (sales_sel.data.length > 0) {
			var display = '';
			for (var di = 0; di < sales_sel.data.length; di++) {
				display += sales_sel.data[di].isChecked ? sales_sel.data[di].label + ', ' : '';
			}
			if (display != '') {
				$("#salesIdsDisplay").html(display.substring(0, display.length - 2));
			}
		}
		//
		$("#salesIdCheckbox").dropdownCheckbox({
			data: sales_sel.data,
			title: "...",
		});
	});
	
	function search() {
		var selectedItems = $("#salesIdCheckbox").dropdownCheckbox("checked");
		var selectedIds = [];
		for (var i = 0; i < selectedItems.length; i++) {
			selectedIds.push(selectedItems[i].id);
		}
		$("#salesIds").val(selectedIds.join(','));
		$("#cluesearchform").submit();
	}
	
	function exportData() {
		$("#search-form").attr("action", "/sales/admin/clue/data/").submit();
		$("#search-form").attr("action", "/sales/admin/clue/searchall/")
		return false;
	}
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

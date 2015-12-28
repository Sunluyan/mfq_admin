<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='全部商机-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="affairlistall">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">

	<form class="form-inline well" action="/sales/admin/affair/searchall/" method="GET">
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
			<div class="input-group-addon">商机受理时间：</div>
			<input class="form-control reset" type="text" name="cb" id="createdAtBegin" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="开始时间" size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="ce" id="createdAtEnd" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="截止时间" size="20">
		</div>
		<div class="input-group">
			<div class="input-group-addon">最新备注时间：</div>
			<input class="form-control reset" type="text" name="rb" id="latestRemarkedAtBegin" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.latestRemarkedAtBegin gt 0>${cond.latestRemarkedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="开始时间" size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="re" id="latestRemarkedAtEnd" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.latestRemarkedAtEnd gt 0>${cond.latestRemarkedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="截止时间" size="20">
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
			<div class="input-group-addon">事务状态：</div>
			<select class="form-control" name="st">
				<option value="">请选择</option>
				<#list OrderStatus.values() as os>
				<option value="${os.value}" ${(cond.orderStatusList?? && os.value=cond.orderStatusList[0])?string('selected="selected"', '')}>${os.desc}</option>
				</#list>
			</select>
		</div>
		<div class="input-group">    
			<div class="input-group-addon">丢单原因：</div>
			<select class="form-control" name="lr">
				<#assign curLostReasonType=0>
				<option value="">请选择</option>
				<#list LostReasonType.values() as lrt>
				<#if lostReasonMap[lrt]?? && lostReasonMap[lrt]?size gt 0>
				<#if curLostReasonType != lrt>
				<option value="">--------------------------------</option>
				<#assign curLostReasonType=lrt.value>
				</#if>
				<#list lostReasonMap[lrt] as lr>
					<option value="${lr.value}" ${(lr.value==cond.lostReason)?string('selected="selected"', '')}>${lrt.desc}-${lr.desc}</option>
				</#list>
				</#if>
				</#list>
			</select>
        </div>
		<div class="input-group">
			<div class="input-group-addon">主销售负责人：</div>
			<select class="form-control" name="s">
				<option value="">请选择</option>
				<#list salesUserList as sales>
				<option value="${sales.id}" ${(sales.id=cond.salesId)?string('selected="selected"', '')}>${(Team.findByValue(sales.team).desc)!''}-${sales.subTeamName}-${sales.name}</option>
				</#list>
			</select>
		</div>
		<div class="input-group">
			<div class="input-group-addon">辅销售负责人：</div>
			<select class="form-control" name="as">
				<option value="">请选择</option>
				<#list salesUserList as sales>
				<option value="${sales.id}" ${(sales.id=cond.salesAssistId)?string('selected="selected"', '')}>${(Team.findByValue(sales.team).desc)!''}-${sales.subTeamName}-${sales.name}</option>
				</#list>
			</select>
		</div>
		<div class="input-group">
			<div class="input-group-addon">出行时间（文本描述）：</div>
			<input class="form-control reset" type="text" name="d" size="10" value="${cond.goDate}">
		</div>
		<div class="input-group">
            <label class="checkbox">
                <input type="checkbox" name="ic" value="1" ${(cond.includeChild=1)?string('checked="checked"', '')}> 含小孩
            </label>
        </div>
        <div class="input-group">
			<td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
		</div>
	</form>

	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th rowspan="2">用户UID</th>
				<th colspan="3">商机</th>
				<th colspan="9">事务</th>
			</tr>
			<tr class="success">
				<th>名称</th>
				<th>主销售负责人</th>
				<th>辅销售负责人</th>
				<th>事务名称</th>
				<th>事务类型</th>
				<th>是否标星</th>
				<th>目的地</th>
				<th>出行时间</th>
				<th>事务状态</th>
				<th>丢单原因</th>
				<th>最新备注信息</th>
				<th>事务详情</th>
				<#--
				<th>旅行方案状态</th>
				<th>订单详情</th>
				<th>订单金额</th>
				<th>付款金额</th>
				<th>详细行程状态</th>
				<th>用户手册寄送状态</th>
				-->
			</tr>
		</thead>
		<tbody>
			<#list affairs as a>
			<tr>
				<#if a.opportunity.uid!=preUid>
				<td rowspan="${countMap['uid' + a.opportunity.uid]}">${a.opportunity.uid}</td>
				<#assign preUid=a.opportunity.uid>
				</#if>
				<#if a.opportunity.id!=preOppId>
				<td rowspan="${countMap['opp' + a.opportunity.id]}">${a.opportunity.name?html}</td>
				<td rowspan="${countMap['opp' + a.opportunity.id]}">
					${(salesUsers['u'+a.opportunity.salesId].username)!''}/${(salesUsers['u'+a.opportunity.salesId].name)!''}
					<button class="btn btn-info btn-xs" onclick="javascript: sales.toModifyOpportunitySales(0, this);">修改</button>
					<button class="btn btn-warning btn-xs" style="display: none;" onclick="javascript: sales.modifyOpportunitySales(${a.opportunity.id}, 0, this);">修改</button>
				</td>
				<td rowspan="${countMap['opp' + a.opportunity.id]}">
					<#if salesUsers['u'+a.opportunity.salesAssistId]??>
					${(salesUsers['u'+a.opportunity.salesAssistId].username)!''}/${(salesUsers['u'+a.opportunity.salesAssistId].name)!''}
					</#if>
					<button class="btn btn-info btn-xs" onclick="javascript: sales.toModifyOpportunitySales(1, this);">修改</button>
					<button class="btn btn-warning btn-xs" style="display: none;" onclick="javascript: sales.modifyOpportunitySales(${a.opportunity.id}, 1, this);">修改</button>
				</td>
				<#assign preOppId=a.opportunity.id>
				</#if>
				<#include "/sales/affair_list_content.ftl">
			</tr>
			</#list>
		</tbody>
	</table>
	<form class="well search-form" action="/sales/admin/affair/searchall/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input class="form-control" type="hidden" name="cb" value="<#if cond.createdAtBegin gt 0>${cond.createdAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="ce" value="<#if cond.createdAtEnd gt 0>${cond.createdAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="st" value="${(cond.orderStatusList[0])!''}">
		<input class="form-control" type="hidden" name="rb" value="<#if cond.latestRemarkedAtBegin gt 0>${cond.latestRemarkedAtBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="re" value="<#if cond.latestRemarkedAtEnd gt 0>${cond.latestRemarkedAtEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="s" value="${cond.salesId}">
		<input class="form-control" type="hidden" name="as" value="${cond.salesAssistId}">
		<input class="form-control" type="hidden" name="d" value="${cond.goDate}">
		<input class="form-control" type="hidden" name="ic" value="${cond.includeChild}">
		<input class="form-control" type="hidden" name="at" value="${cond.affairType}">
		<input class="form-control" type="hidden" name="lr" value="${cond.lostReason}">
		<input class="form-control" id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>

	<div style="text-align:center;">共${count}条事务</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#createdAtBegin').datetimepicker();
		$('#createdAtEnd').datetimepicker();
		$('#latestRemarkedAtBegin').datetimepicker();
		$('#latestRemarkedAtEnd').datetimepicker();
		sales.salesUsers = [<#list salesUserList as sales>{"id":"${sales.id}", "name":"${sales.name}"}<#if sales_index<salesUserList?size-1>,</#if></#list>];
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

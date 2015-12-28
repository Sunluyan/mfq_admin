<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='我的成单-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="affairorder">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">

	<form class="form-inline well" action="/sales/affair/listorder/" method="GET">
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
			<div class="input-group-addon">事务类型：</div>
			<select class="form-control" name="at">
				<option value="">请选择</option>
				<#list AffairType.values() as ats>
				<option value="${ats.value}" ${(cond.affairType=ats.value)?string('selected="selected"', '')}>${ats.desc}</option>
				</#list>
			</select>
        </div>
		<div class="input-group">
			<div class="input-group-addon">付全款时间：</div>
			<input class="form-control reset" type="text" name="pb" id="payTimeBegin" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.payTimeBegin gt 0>${cond.payTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="开始时间" size="20">
			<div class="form-control reset">至</div>
			<input class="form-control reset" type="text" name="pe" id="payTimeEnd" data-date-format="yyyy-mm-dd hh:ii:ss" value="<#if cond.payTimeEnd gt 0>${cond.payTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" placeholder="截止时间" size="20">
		</div>
        <div class="input-group">
			<td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
		</div>
	</form>

	<table class="table table-striped table-bordered table-condensed vcenter text-center">
		<thead>
			<tr class="success">
				<th>用户UID</th>
				<th>商机名称</th>
				<th>事务名称</th>
				<th>事务类型</th>
				<th>目的地</th>
				<th>出行时间</th>
				<th>订单号</th>
				<th>订单金额</th>
				<th>付款金额</th>
				<th>付款状态</th>
				<th>付全款时间</th>
				<th>事务详情</th>
			</tr>
		</thead>
		<tbody>
			<#list affairOrderList as ao>
			<tr>
				<td>${ao.opportunity.uid}</td>
				<td>${ao.opportunity.name}</td>
				<td>${ao.affair.name}</td>
				<td>${ao.affair.type.desc}</td>
				<td>
					<#if AffairType.PLAN_A=ao.affair.type>
						${planALocations['l_a_' + ao.affair.packId]!''}
					<#elseif AffairType.PLAN_B=a.type>
						${planBLocations['l_b_' + ao.affair.advisorId]!''}
					</#if>
				</td>
				<td>${ao.affair.goDate}</td>
				<td><a class="link" href="http://tmall.shijiebang.com/order/userorder/detail?mid=${ao.orderId}" target="_blank">${ao.orderId}</a></td>
				<td>
					<#if (orderInfo['o_'+ao.orderId]['total_price'])??>${orderInfo['o_'+ao.orderId]['total_price']?string(',###.00')}元</#if>
				</td>
				<td>
					<#if orderInfo['o_'+ao.orderId]??>${OrderInfoMapper.getPaidMoney(orderInfo['o_'+ao.orderId])?string(',###.00')}元</#if>
				</td>
				<td>${(orderInfo['o_'+ao.orderId]['order_status_text'])!''}</td>
				<td>
					<#if (orderInfo['o_'+ao.orderId]['pay_time'])?? && orderInfo['o_'+ao.orderId]['pay_time']!='0'>
						${(orderInfo['o_'+ao.orderId]['pay_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
					</#if>
				</td>
				<td><a class="link" href="/sales/opportunity/detail/?oid=${ao.opportunity.id}&aid=${ao.id}">查看</a></td>
			</tr>
			</#list>
		</tbody>
	</table>
	<form class="well search-form" action="/sales/affair/listorder/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="qt" value="${cond.queryType}">
		<input type="hidden" name="q" value="${cond.query}">
		<input class="form-control" type="hidden" name="pb" value="<#if cond.payTimeBegin gt 0>${cond.payTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="pe" value="<#if cond.payTimeEnd gt 0>${cond.payTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
		<input class="form-control" type="hidden" name="at" value="${cond.affairType}">
		<input class="form-control" id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>

	<div style="text-align:center;">共${count}条成单</div>
	<@pager cond.pageIndex pageCount />
</div>

<script type="text/javascript">
	$(function() {
		$('#payTimeBegin').datetimepicker();
		$('#payTimeEnd').datetimepicker();
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

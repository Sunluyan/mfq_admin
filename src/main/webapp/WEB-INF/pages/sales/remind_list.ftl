<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title=(RemindType.ORDER.value==cond.remindTypeList[0])?string('新成单', (RemindType.BOOKING.value=cond.remindTypeList[0])?string('预定', '行程'))+'提醒-${sysname}'>
<#assign toolbar="search">
<#assign tab1="remind">
<#assign tab2="remindlist">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
    <#include "/sales/sales_remind_nav.ftl">

	<table class="table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
  		<thead>
  			<tr class="success">
  				<th width="80px">用户UID</th>
  				<th width="80px">订单号</th>
  				<th width="50px">联系人</th>
  				<th width="50px">手机号</th>
  				<th width="50px">订单金额（元）</th>
  				<#if RemindType.ORDER.value==cond.remindTypeList[0]>
  				<th width="80px">付款状态</th>
  				<th width="50px">订单状态</th>
  				<th width="50px">下单时间</th>
  				<th width="50px">关联方案</th>
  				</#if>
  				<#if RemindType.BOOKING.value==cond.remindTypeList[0] || RemindType.TRAVEL.value==cond.remindTypeList[0]>
  				<th width="80px">已付款金额（元）</th>
  				<th width="50px">付全款时间</th>
  				<th width="50px">预定状态</th>
  				<th width="50px">代订清单</th>
  				</#if>
  				<#if RemindType.TRAVEL.value==cond.remindTypeList[0]>
  				<th width="50px">详细行程状态</th>
  				<th width="50px">详细行程内容</th>
  				</#if>
  				<th width="50px">服务流程提醒</th>
  			</tr>
  		</thead>
  		<tbody>
			<#list remindList as remind>
			<tr>
				<td>${remind.uid}</td>
				<td><a class="link" href="http://tmall.shijiebang.com/order/userorder/detail?mid=${remind.orderId}" target="_blank">${remind.orderId}</a></td>
				<#if RemindType.ORDER.value==cond.remindTypeList[0]>
				<td>${orderInfo['o_'+remind.orderId]['contact']['lastname_cn']!''}${orderInfo['o_'+remind.orderId]['contact']['firstname_cn']!''}</td>
				<td>${orderInfo['o_'+remind.orderId]['contact']['mobile']!''}</td>
				<#else>
				<td>${orderInfo['o_'+remind.orderId]['contact_name']!''}</td>
				<td>${orderInfo['o_'+remind.orderId]['contact_mobile']!''}</td>
				</#if>
				<td>${(orderInfo['o_'+remind.orderId]['total_price']?string(',###.00'))!''}元</td>
				<#if RemindType.ORDER.value==cond.remindTypeList[0]>
				<td>
					<#if orderInfo['o_'+remind.orderId]['pay_time']!='0'>
					已付全款
					<#elseif orderInfo['o_'+remind.orderId]['deposit_time']!='0'>
					已付定金
					<#else>
					未付款
					</#if>
				</td>
				<td>${orderInfo['o_'+remind.orderId]['order_status_text']!''}</td>
				<td>
					<#if orderInfo['o_'+remind.orderId]['add_time']?? && orderInfo['o_'+remind.orderId]['add_time']!='0'>
					${(orderInfo['o_'+remind.orderId]['add_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
					</#if>
				</td>
				<td>
					<#if affairOrders?? && affairOrders[remind.orderId]??>
					已关联
					<#else>
					<a href="/sales/opportunity/todetail/?uid=${remind.uid}&refOrderId=${remind.orderId}" class="btn btn-default btn-sm" target="_blank">去关联</a>
					</#if>
				</td>
				</#if>
				<#if RemindType.BOOKING.value==cond.remindTypeList[0] || RemindType.TRAVEL.value==cond.remindTypeList[0]>
				<td>${OrderInfoMapper.getPaidMoney(orderInfo['o_'+remind.orderId])?string(',###.00')}元</td>
				<td>
					<#if orderInfo['o_'+remind.orderId]['pay_time']!='0'>
					${(orderInfo['o_'+remind.orderId]['pay_time']?number*1000)?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}
					</#if>
				</td>
				<td>
					<#if orderInfo['o_'+remind.orderId]['is_complete']='1'>
					预定完成
					<#else>
					未完成
					</#if>
				</td>
				<td>
					<#if orderDoneTrips[remind.orderId]??>
					<a href="http://www.shijiebang.com/u${orderDoneTrips[remind.orderId].uid}/trip-${orderDoneTrips[remind.orderId].tid}/order/" class="link" target="_blank">查看</a>
					</#if>
				</td>
				</#if>
				<#if RemindType.TRAVEL.value==cond.remindTypeList[0]>
				<td><#if orderDoneTrips[remind.orderId]??>已完成<#else>未完成</#if></td>
				<td>
					<#if orderDoneTrips[remind.orderId]??>
					<a href="http://www.shijiebang.com/u${orderDoneTrips[remind.orderId].uid}/trip-${orderDoneTrips[remind.orderId].tid}/" class="link" target="_blank">查看</a>
					</#if>
				</td>
				</#if>
				<td><button class="btn btn-default btn-sm" onclick="javascript: sales.updateRemindStatus(this, ${remind.id});">确认已提醒</button></td>
			</tr>
    		</#list>
		</tbody>
	</table>
	
	<form class="well search-form" action="/sales/remind/list/" style="display:none;" method="GET" id="search-form">
		<input type="hidden" name="type" value="${cond.remindTypeList[0]}" />
		<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
	</form>
	<div style="text-align:center;">共${count}条</div>
	<@pager cond.pageIndex pageCount />
</div>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

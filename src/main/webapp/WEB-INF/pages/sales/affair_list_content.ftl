<td>${a.name?html}</td>
<td>${a.type.desc}</td>
<#assign orderRateNum=a.orderRate>
<#if a.orderRate <= 3><#assign orderRateNum=3><#elseif a.orderRate gte 4><#assign orderRateNum=5></#if>
<td>
    <#if orderRateNum?? && orderRateNum == 5><span class="glyphicon glyphicon-star"></span><#else>--</#if>
</td>
<td>
	<#if AffairType.PLAN_A=a.type>
		${planALocations['l_a_' + a.packId]!''}
	<#elseif AffairType.PLAN_B=a.type>
		${planBLocations['l_b_' + a.advisorId]!''}
	</#if>
</td>
<td>${a.goDate}</td>
<td>${OrderStatus.findByValue(a.orderStatus).desc!''}</td>
<td><#if a.lostReason??>${a.lostReason.type.desc}-${a.lostReason.desc}</#if></td>
<td>${(affairOutcallDetails[a.id+''].comment)!''}</td>
<td><a class="block" style="white-space:nowrap" href="/sales/opportunity/detail/?oid=${a.opportunity.id}&aid=${a.id}">查看</a></td>
<#--去掉
<td>
	<#if AffairType.PLAN_A=a.type>
	--
	<#elseif AffairType.PLAN_B=a.type>
	${(a.tripStatus>=1)?string('已完成', '未完成')}
	</#if>
</td>
<td>
	<#if affairOrders[''+a.id]??>
	<#list affairOrders[''+a.id] as oid>
	<a class="link" href="http://tmall.shijiebang.com/order/userorder/detail?mid=${oid}" target="_blank">${oid}</a><br>
	</#list>
	</#if>
</td>
<td>
	<#if affairOrders[''+a.id]??>
	<#list affairOrders[''+a.id] as oid>
	<#if orderInfo['o_'+oid]??>${orderInfo['o_'+oid]['total_price']?string(',###.00')}元</#if><br>
	</#list>
	</#if>
</td>
<td>
	<#if affairOrders[''+a.id]??>
	<#list affairOrders[''+a.id] as oid>
	${OrderInfoMapper.getPaidMoney(orderInfo['o_'+oid])?string(',###.00')}元
	</#list>
	</#if>
</td>
<td>
	<#if AffairType.PLAN_A=a.type>
	<#if affairOrders[''+a.id]??>
	<#list affairOrders[''+a.id] as oid>
	<#if orderDoneTrips[oid]??>已完成<#else>未完成</#if><br>
	</#list>
	</#if>
	<#elseif AffairType.PLAN_B=a.type>
	${(a.tripStatus=2)?string('已完成', '未完成')}
	</#if>
</td>
<td>
	<#if affairOrders[''+a.id]??>
	<#list affairOrders[''+a.id] as oid>
	${OrderInfoMapper.getPostStatusInfo(orderInfo['o_'+oid]['post_status']!'0')}<br>
	</#list>
	</#if>
</td>
-->

<#if (t.refType='activity')>
	<#if (t.sourceKey='superseed')>
	<a class="link" href="/activity/superseed/detail/?id=${t.refId}" target="_blank" >H5用户需求收集器</a>
	<#else>
	<a class="link" href="/sales/taskinfo/activity/detail/?type=${t.sourceKey?substring(4)}&mobile=${t.mobile}" target="_blank" >来源</a>
	</#if>
</#if>
<#if (t.refType='advisor')>
<a class="link" href="<#if (t.refId!'') != ''>/advisor/${t.refId}/?ref=sales<#else>/advisor/newrequirements/?uid=${t.uid}&qmobile=${t.mobile}&trip=3</#if>" target="_blank" >小帮手</a>
</#if>

<#if (t.sourceKey='weibo_qingdingzhi')>
<a class="link" href="http://tmall.shijiebang.com/admin/weibo?id=${t.refId}" target="_blank" >微博轻应用</a>
</#if>
<#--reservate_t5是H5预定时的自动注册
<#if (t.sourceKey='reservate_t5')>
<a class="link" href="/reservation/${t.refId}/" target="_blank" >H5预定信息</a>
</#if>
-->
<#if (t.sourceKey='reservation_h5')>
<a class="link" href="/reservation/${t.refId}/" target="_blank" >H5预定信息</a>
</#if>
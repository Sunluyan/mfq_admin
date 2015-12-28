<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='商机详情-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">
<#include "/crm/crm_macro.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">
	
	<div class="panel panel-info form-horizontal panel-yunhu panel-user-info">
        <#include "sales/opportunity_detail_content.ftl">
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#outcallNextTime').datetimepicker();
		sales.taskInfoId = ${opportunityTaskInfo.id};
		sales.affairId = ${curAffairId};
		sales.opportunityId = ${curOpp.id};
		sales.conclusions = {<#list ptypes as p>"p${p.val}":[<#list ctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name?html}"}<#if c_index<ctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<ptypes?size-1>,</#if></#list>};
	});
</script>
<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

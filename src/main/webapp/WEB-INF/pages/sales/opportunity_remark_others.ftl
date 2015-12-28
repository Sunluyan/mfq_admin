<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='代他人备注-${sysname}'>
<#assign toolbar="search">
<#assign tab1="opportunity">
<#assign tab2="remarkothers">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">
<#include "/crm/crm_macro.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_opportunity_nav.ftl">
    
   	<form class="form-inline well well-sm" action="/sales/opportunity/searchothers/" method="GET">
    	<#assign options=['', 'u', 'm', 'n']>
    	<#assign values=['智能查询', '用户UID', '手机号', '用户名']>
    	<div class="input-group">
			<select name="t">
				<#list options as opt>
				<option value="${opt}" ${(opt=searchType)?string('selected="selected"', '')}>${values[opt_index]}</option>
				</#list>
			</select>
    	</div>
    	<div class="input-group">	
    		<input type="text" class="form-control" name="q" value="${queryCond!''}">
    	</div>
    	<div class="input-group">				
    		<input type="submit" class="btn btn-info btn-sm" value="查询">
    	</div>			
    </form>
    
    <#if curOpp??>
    <div class="panel panel-info form-horizontal panel-yunhu panel-user-info">
        <#include "sales/opportunity_detail_content.ftl">
	</div>
    </#if>
</div>

<script type="text/javascript">
	$(function() {
		$('#outcallNextTime').datetimepicker();
		sales.conclusions = {<#list ptypes as p>"p${p.val}":[<#list ctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name?html}"}<#if c_index<ctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<ptypes?size-1>,</#if></#list>};
		sales.taskInfoId = ${opportunityTaskInfo.id};
		sales.affairId = ${curAffairId};
		sales.opportunityId = ${curOpp.id};
		sales.searchType = '${searchType}';
		sales.queryCond = '${queryCond}';
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

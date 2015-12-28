<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='电话来源-${sysname}'>
<#assign toolbar="search">
<#assign tab1="setting">
<#assign tab2="sourceteam">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_setting_nav.ftl">
    
    <div style="text-align:right;">
        <a class="btn btn-info btn-sm" target="_blank" href="/dict/wordlist/?type=source">电话来源地址</a>
    </div>

	<table class="table table-striped table-bordered table-condensed vcenter form-inline text-center table-yunhu">
		<thead>
			<tr class="success">
        <th>ID (${sourceTeamList?size})</th>
				<th>source</th>
				<th>来源名称</th>
		        <th>初始权重</th>
		        <th>最大权重</th>
		        <th>最小权重</th>
		        <th>斜率w/h</th>
		        <th>期望min</th>
				<th>分组信息</th>
				<th>需求强弱</th>
				<th>操作</th>
			</tr>
		</thead>
  		<tbody>
			<#list sourceTeamList as st>
			<tr>
        <td>${(st.id gt 0)?string(st.id,'默认')}</td>
				<td>${st.sourceKey}</td>
				<td>${st.sourceName?html}</td>
		        <td>${st.weight}</td>
		        <td>${st.maxWeight}</td>
		        <td>${st.minWeight}</td>
		        <td>${st.k}</td>
		        <td>${st.texp}</td>
				<td>${Team.findByValue(st.team).desc}</td>
				<td>${st.isWeak()?string('弱', '强')}</td>
				<td><a target="_blank" href="/sales/admin/sourceteam/add/?sourceKey=${st.sourceKey}">修改</a></td>
			</tr>
    		</#list>
		</tbody>
	</table>
</div>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='工作人员-${sysname}'>
<#assign toolbar="search">
<#assign tab1="setting">
<#assign tab2="outcallsetting">
<#assign tab3="salesuser">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_setting_nav.ftl">
	<#include "/sales/sales_outcallsetting_nav.ftl">

    <p><strong><#if queryUser??>修改<#else>新增</#if>人员信息</strong></p>
	<table class="table table-striped table-bordered table-condensed vcenter form-inline table-yunhu">
  		<tbody>
			<tr>
				<td>用户名：</td>
				<td>
					<#if queryUser??>
					${queryUser.username} (${queryUser.id})
					<input type="hidden" class="form-control" id="username" value="${queryUser.username}">
					<#else>
					<input type="text" class="form-control" id="username">（必填 ）
					</#if>
				</td>
			</tr>
			<#if queryUser??>
			<tr>
				<td>姓名：</td>
				<td>${queryUser.name}</td>
			</tr>
			</#if>
			<tr>
				<td>工作容量：</td>
				<td><input type="number" class="form-control" id="workVol" value="${(queryUser.workVol)!''}" max="200" min="0">
        <#if queryUser??><br/><span>最后领取任务时间：${queryUser.lastGrapAt?datetime}</span>&nbsp;&nbsp;<span>当日领取次数：${queryUser.todayGrapCount}</span></#if>
        </td>
			</tr>
			<tr>
				<td>部门：</td>
				<td>
					<select class="form-control" id="team">
					<#list Team.values() as t>
						<option value="${t.value}" ${(queryUser?? && queryUser.team=t.value)?string('selected="selected"', '')}>${t.desc}</option>
					</#list>
					</select>
					二级分组名称：<input type="text" class="form-control" id="subTeamName" value="${(queryUser.subTeamName)!''}">（必填）
				</td>
			</tr>
      <tr>
        <td>400座席号：</td>
        <td><input type="text" class="form-control" id="cno" value="${(queryUser.cno)!''}"></td>
      </tr>
			<#if queryUser??>
			<tr>
				<td>在职状态：</td>
				<td>
					<select class="form-control" id="sign">
						<option value="1" ${(queryUser.sign=1)?string('selected="selected"', '')}>在职</option>
						<option value="0" ${(queryUser.sign=0)?string('selected="selected"', '')}>离职</option>
					</select>
				</td>
			</tr>
			</#if>
			<tr>
				<input type="hidden" id="salesId" value="${(queryUser.id)!''}">
				<td colspan="2" style="text-align: center;"><button class="btn btn-info btn-sm" onclick="javascript: sales.addOrUpdateSalesUser(this);">提交</button></td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	$(function() {
		sales.salesId = <#if queryUser??>${queryUser.id}<#else>0</#if>;
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

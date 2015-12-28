<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='线索详情-${sysname}'>
<#assign toolbar="search">
<#assign tab1="clue">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">

	<!-- 用户基本信息 -->
	<div class="panel panel-primary panel-yunhu">
		<div class="panel-heading">
			<strong>基本信息</strong>
		</div>
		<div class="panel-body">
            <table class="table table-bordered table-condensed vcenter">
			<#if clueTaskInfo.backupMobile??>
				<#assign backupMobile=clueTaskInfo.backupMobile?split(',')>
			</#if>
                <tr>
                    <td>
					<#if clueTaskInfo.uid==0>
                        <span>UID： [未注册]</span>
						<a class="btn btn-xs btn-primary pull-right" onclick="javascript: sales.regUser(${clueTaskInfo.mobile}, this);">代用户注册 <i class="icon icon-share"></i></a>
					<#else>
                        UID：${clueTaskInfo.uid}
					</#if>
					</td>
					<td>用户名：<#if clueTaskInfo.uid gt 0>${nick}</#if></td>
                    <td>手机号： <span class="js-mobile-span text-info">${clueTaskInfo.mobile}</span>
						<a class="btn btn-success btn-xs call-400-label" data-task-info-id="${clueTaskInfo.id}" data-phone-number="${clueTaskInfo.mobile}"><i class="icon icon-phone"></i> 拨打电话</a>
					</td>
                    <td>线索来源： <span class="js-sourcekey-span text-info glyphicon glyphicon-info-sign">${clueTaskInfo.sourceName}</span> <span><#assign t=clueTaskInfo><#include "sales/clue_source.ftl"></span></td>
                </tr>
                <tr>
                    <td colspan="2">线索生成时间： ${clueTaskInfo.createdAt?datetime?string('yyyy-MM-dd HH:mm')}</td>
                    <td>小帮手ID： <#if 'advisor'=clueTaskInfo.sourceKey>${clueTaskInfo.refId}<#else>无</#if></td>
                    <td>
					<#if (clueTaskInfo.opportunityIdCreated>0)>
                        商机： 已受理
						<a class="btn btn-primary btn-xs" href="/sales/opportunity/detail/?oid=${clueTaskInfo.opportunityIdCreated}" target="_blank">商机详情 <i class="icon icon-share"></i></a>
					<#else>
                        商机： 暂未受理
					</#if>

                    </td>
                </tr>
            </table>
		</div>
	</div>

	<!-- 当前外呼结论 & 用户信息 -->
	<div class="panel panel-primary form-horizontal panel-yunhu">
        <div class="panel-heading">
            <strong><#if !clueTaskInfo.over>外呼结论 & </#if>补充用户信息</strong>
			<button type="submit" class="btn btn-sm btn-info" onclick="javascript: <#if !clueTaskInfo.over>sales.addTaksInfoDetail(this, 'clue');<#else>sales.updateTaskInfo(this);</#if>">保 存</button>
        </div>
        <div class="panel-body">
        	<div class="form-group">
				<label class="control-label col-sm-2" for="name">用户称呼：</label>
				<div class="col-sm-2">
					<input type="text" class="form-control input-sm" id="name" value="${clueTaskInfo.name?html}" size="10">
				</div>
				<label class="control-label col-sm-2" for="promotion">推广来源： </label>
				<div class="col-sm-2">
					<#assign ppindex=0>
					<select class="form-control input-sm" id="promotion" onchange="javascript: sales.changePromotion(this);">
                    	<option value="">请选择</option>
						<#list pptypes as p>
                            <option value="${p.val}" ${(p.val=clueTaskInfo.promotion)?string('selected="selected"', '')}>${p.name}</option>
							<#if p.val=clueTaskInfo.promotion><#assign ppindex=p_index></#if>
						</#list>
                        </select>
                 </div>
                 <div class="col-sm-2">        
                    <select class="form-control input-sm" id="promotionValue">
                            <option value="">请选择</option>
							<#if pptypes?size gt 0>
								<#list pctypes[''+pptypes[ppindex].val] as c>
	                                <option value="${c.val}" ${(c.val=clueTaskInfo.promotionValue)?string('selected="selected"', '')}>${c.name}</option>
								</#list>
							</#if>
                 	</select>
                 </div> 
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="backupMobile1">备用电话：</label>
				<div class="col-sm-2">
					<div>
                        <input type="text" class="form-control input-sm col-sm-1" id="backupMobile1" value="${backupMobile[0]!''}" size="15">
                        <div class="input-group-btn" style="display: block;">
	                        <a class="btn btn-success btn-sm call-400-label" data-task-info-id="${clueTaskInfo.id}"
							   	<#if backupMobile[0]>data-phone-number="${backupMobile[0]!''}"<#else>disabled</#if>
								<i class="icon icon-phone"></i> 拨打电话</a>
						</div>	
					</div>
				</div>
			</div>
			
			<#if !clueTaskInfo.over>
			<#if latestOpportunity??>
            <div class="row">
                <div class="col-sm-2"></div>
			    <div class="alert alert-danger col-sm-8" style="margin-left:-85px;">
			    	【提醒】：<br>
			    	1.该用户上次商机为：${latestOpportunity.name} 创建时间：${latestOpportunity.createdAt?datetime} 销售负责人：${latestOppSalesNick}
			    	<#if latestOppOutcallDetail??>
			    	<br>
			    	2.该用户的最后商机备注时间：${latestOppOutcallDetail.createdAt?datetime}
			    	</#if>
			    </div>
            </div>
			</#if>
			<#if !isSales>
			<div class="form-group" id="salesTr" style="display: hidden;">
                <label class="control-label col-sm-2" for="oppSalesId">商机销售负责人： </label>
                <div class="col-sm-2" id="salesDiv">
                    <select id="oppSalesId" class="form-control input-sm">
						<#list salesTeamUsers as sales>
                            <option value="${sales.id}">${sales.name}</option>
						</#list>
                    </select>
                </div>
			</div>
			</#if>
			<div class="form-group">
				<label class="control-label col-sm-2" for="outcallConclusion">外呼结论： </label>
				<div class="col-sm-2">
                    <select class="form-control input-sm" id="outcallConclusion" onchange="javascript: sales.changeConclusionWithInfo(this);">
						<#list ptypes as p>
                            <option value="${p.val}">${p.name}</option>
						</#list>
                    </select>
				</div>
				<div class="col-sm-2">
                    <select class="form-control input-sm" id="outcallConclusionValue"  onchange="javascript: sales.changeConclusion(this);">
						<#if ptypes?size gt 0>
							<#list ctypes[''+ptypes[0].val] as c>
                                <option value="${c.val}">${c.name}</option>
							</#list>
						</#if>
                    </select>
				</div>
				<div class="col-sm-5">
                    <span id="conclusionInfo" class="text-danger"></span>
				</div>
			</div>
			<div class="form-group js-user-sms-content-div" style="display:none;">
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="outcallNextTime">外呼提醒： </label>
				<div class="col-sm-2">
                    <label><input type="checkbox" id="outcallNextRemind"> 设置下次外呼时间</label>
				</div>
				<div class="col-sm-2">
                    <input type="text" class="form-control input-sm" id="outcallNextTime"
                           data-date-format="yyyy-mm-dd hh:ii:ss" value="" size="15" placeholder="提醒时间">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="outcallComment">备注详情： </label>
				<div class="col-sm-4">
                    <textarea id="outcallComment" class="form-control input-sm" cols="80" rows="2"></textarea>
				</div>
			</div>
        	
        	</#if>
		</div>

    </div>
	
	<!-- 历史线索列表 -->
	<div class="panel panel-success panel-yunhu">
		<div class="panel-heading">
			<strong>历史线索列表</strong>
			<a class="btn btn-default btn-xs" data-toggle="collapse" href="#collapseOne" data-target="#uidTaskInfoListDiv">展开/折叠</a>
		</div>
		<div class="panel-body collapse" id="uidTaskInfoListDiv">
		    <table class="js-grab-tasks-list table table-striped table-bordered table-condensed vcenter text-center">
				<thead class="bg-success">
					<th>留电话页</th>
					<th>部门</th>
					<th>UID</th>
					<th>手机号</th>
					<th>留电话时间</th>
					<th>最新外呼时间</th>
					<th>最新外呼结论</th>
					<th>销售负责人</th>
					<th>来源</th>
					<th>线索详情</th>
					<th>操作</th>
				</thead>
				<tbody>
					<#if uidTaskInfoList?? && uidTaskInfoList?size gt 0>
						<#list uidTaskInfoList as t>
		                <tr>
		                    <td>${t.sourceName}</td>
		                    <td><#if t.team??>${t.team.desc}</#if></td>
		                    <td>${t.uid}</td>
		                    <td>${t.mobile}</td>
		                    <td><#if t.createdAt??>${t.createdAt?datetime}</#if></td>
		                    <td><#if t.latestTime??>${t.latestTime?datetime}</#if></td>
					        <td>
								<#if t.latestConclusion gt 0>${conclusions[t.latestConclusion+''].name!''}</#if>
								<#if t.latestConclusionValue gt 0>-${conclusions[t.latestConclusionValue+''].name!''}</#if>
							</td>
							<td>
								${(salesUsers['u'+t.salesId].name)!''}
							</td>
		                    <td>
		                    	<#include "sales/clue_source.ftl">
		                    </td>
					        <td>
								<a class="link" href="/sales/clue/detail/?taskInfoId=${t.id}" target="_blank">查看</a>
							</td>
							<td>
								<#if !(t.isOver() || SignUtil.isOutcallTaskTook(t)) && (salesUser.getTeam() == t.getTeam().getValue())>
								<a class="js-grab-button btn btn-sm btn-primary" data-task-id="${t.id}">抢</a>
								</#if>
							</td>
		                </tr>
						</#list>
					<#else>
						<tr><td colspan="11" class="text-center text-danger">无历史线索</td></tr>
					</#if>
				</tbody>
		    </table>
		</div>
	</div>

	<!-- 历史外呼详情 -->
	<div class="panel panel-success panel-yunhu">
		<div class="panel-heading">
			<strong>历史外呼结论</strong>
		</div>
		<#assign clueOutcallDetailList=outcallDetailList>
		<#include "sales/clue_detail_remark_content.ftl">
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#outcallNextTime').datetimepicker();
		sales.taskInfoId = ${clueTaskInfo.id};
		sales.isSales = ${isSales?string};//true:销售负责人，false:非销售负责人
		sales.promotions = {<#list pptypes as p>"p${p.val}":[<#list pctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name}"}<#if c_index<pctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<pptypes?size-1>,</#if></#list>};
		sales.conclusions = {<#list ptypes as p>"p${p.val}":[<#list ctypes[''+p.val] as c>{"val":"${c.val}", "name":"${c.name?html}", "regrab": ${SignUtil.isTypeValueRegrab(c)?string('1', '0')}, "opprel": ${SignUtil.isTypeValueOppRel(c)?string('1', '0')}, "userSms": ${SignUtil.isTypeValueUserSms(c)?string('1', '0')}, "smsContent": "${(c.smsContent!'')?html}"}<#if c_index<ctypes[''+p.val]?size-1>,</#if></#list>]<#if p_index<ptypes?size-1>,</#if></#list>};
		sales.changeInfo();

		$('.js-mobile-span').addClass('glyphicon glyphicon-earphone').tooltip({
    		title: sales.requestCallHistoryEntries,
    		html: true,
            placement:'bottom'
    	});
    	
    	$('.js-sourcekey-span').addClass('glyphicon').tooltip({
    		title: '${(sourceTeam.salesScript)!'无'}',
    		template: '<div class="tooltip" style="margin-top:5px;" role="tooltip"><div class="tooltip-inner" style="text-align:left;"></div></div>',
    		html: true,
            placement:'bottom'
    	});
	});
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">
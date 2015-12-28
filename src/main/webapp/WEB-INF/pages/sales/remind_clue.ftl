<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='线索外呼-${sysname}'>
<#assign toolbar="search">
<#assign tab1="remind">
<#assign tab2="clue">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales" ng-view>
    <#include "/sales/sales_remind_nav.ftl">

    <div class="well well-sm bg-danger">
        今日外呼情况：
        <span class="label label-success">已接通：${outcallDetailDayCountAll!0}</span>
        <span class="label label-info">已受理：${outcallDetailDayCountService!0}</span>
    </div>

    <div class="panel panel-primary panel-yunhu">
        <div class="panel-heading">
            <strong>我的线索</strong>
            <form class="form-inline" id="searchForm" action="/sales/remind/clue/search/" method="GET">
                <div class="input-group">
                    <div class="input-group-addon">期望外呼时间</div>
                    <input class="form-control input-sm" type="text" name="eb" id="expectedTimeBegin"
                           data-date-format="yyyy-mm-dd hh:ii:ss"
                           placeholder="开始时间"
                           value="<#if cond.expectedTimeBegin gt 0>${cond.expectedTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" size="20">
                    <div class="input-group-addon">至</div>
                    <input class="form-control input-sm" type="text" name="ee" id="expectedTimeEnd"
                           data-date-format="yyyy-mm-dd hh:ii:ss"
                           placeholder="截止时间"
                           value="<#if cond.expectedTimeEnd gt 0>${cond.expectedTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>" size="20">
                </div>
                <div class="input-group">
	                <select class="form-control input-sm" name="k">
	                    <option value="">留电话页</option>
	                    <#list sourceTeamList as sourceTeam>
	                    <option value="${sourceTeam.sourceKey}" <#if (sourceTeam.sourceKey == cond.sourceKey)>selected="selected"</#if>>${sourceTeam.sourceName}</option>
	                    </#list>
	                </select>
	            </div>
                <div class="input-group">
                	<button type="submit" class="btn btn-info btn-sm">查询</button>
                </div>	
            </form>
        </div>
        <div class="panel-body">
		<table class="table table-striped table-bordered table-condensed text-center vcenter table-yunhu">
  		<thead>
  			<tr class="success">
  				<#--<th width="80px">销售顾问</th>-->
  				<th width="80px">任务类型</th>
  				<th width="50px">留电话页</th>
  				<th width="50px">用户UID</th>
  				<th width="50px">手机号</th>
  				<th width="80px">期望外呼时间</th>
  				<th width="50px">最新外呼结论</th>
  				<th width="50px">操作</th>
  				<th width="50px">线索详情</th>
  			</tr>
  		</thead>
  		<tbody>
        <tr>
            <td>自己添加线索</td>
            <td><select class="form-control input-sm" id="addFormSourceKey">
            <#list sourceTeamList as sourceTeam>
                <option value="${sourceTeam.sourceKey}">${sourceTeam.sourceName}</option>
            </#list>
            </select></td>
            <td><input class="form-control input-sm" type="text" id="addFormUid" value="" disabled="disabled" placeholder="用户UID"></td>
            <td><input class="form-control input-sm" type="text" id="addFormMobile" value="" placeholder="手机号码"></td>
            <td><input class="form-control input-sm" type="text" id="addFormExceptedTime" data-date-format="yyyy-mm-dd hh:ii:ss" value="" placeholder="选择时间"></td>
            <td>-</td>
            <td>-</td>
            <td><button class="btn btn-info btn-xs" id="addFormBtn" onclick="javascript: sales.addTaskInfo(this); return false;">添加</button></td>
        </tr>
        <#if !taskInfoList?has_content>
            <tr>
                <td colspan="8" class="bg-warning text-center">
                        <#if emptyQuery>
                        <h4 class="text-success"><i class="icon icon-thumbs-up"></i>
                            我已经打完了所有外呼任务，销冠非我莫属！
                            <#--
                            <#if grabTasks?? && grabTasks?size gt 0>
                                去看看 <a class="link" href="#clue-queue">${grabTasks?size}个待领取线索</a> 吧！
                            <#else>
                                可以喝杯咖啡了！
                            </#if>
                            -->
                        </h4>
                        <#else>
                            <p class="text-danger">没有搜索到符合条件的线索，请改变/清除搜索条件再试试。</p>
                        </#if>
                </td>
            </tr>
        </#if>
			<#list taskInfoList as t>
			<tr>
				<#--<td>
					<button id="tm_btn_${t.id}" class="btn btn-info btn-sm" onclick="javascript: sales.toModifySales(${t.id});">修改</button>
					<button id="m_btn_${t.id}" class="btn btn-warning" style="display: none;" onclick="javascript: sales.modifySales(${t.id});">修改</button>
				</td>-->
				<td>${(t.taskType.desc)!''} ${t.id}</td>
				<td>${t.sourceName}</td>
				<td><#if (t.uid > 0)>${t.uid}</#if></td>
				<td><span class="js-mobile-span text-info">${t.mobile}</span></td>
				<td><#if t.expectedTime??>${t.expectedTime?datetime?string('yy-MM-dd HH:mm')}</#if></td>
				<td>
					<#if t.latestConclusion gt 0>${conclusions[t.latestConclusion+''].name!''}</#if>
					<#if t.latestConclusionValue gt 0>-${conclusions[t.latestConclusionValue+''].name!''}</#if>
				</td>
				<td>
					<#include "sales/clue_source.ftl">
				</td>
				<td>
					<a class="" href="/sales/clue/detail/?taskInfoId=${t.id}">查看</a>
				</td>
			</tr>
    		</#list>
		</tbody>
	</table>
        <div class="panel-footer">
 		<form class="well search-form" action="/sales/remind/clue/search/" style="display:none;" method="GET" id="search-form">
			<input tyep="hidden" name="k" value="${cond.sourceKey}" />
			<input type="hidden" name="eb" value="<#if cond.expectedTimeBegin gt 0>${cond.expectedTimeBegin?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
			<input type="hidden" name="ee" value="<#if cond.expectedTimeEnd gt 0>${cond.expectedTimeEnd?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}</#if>">
			<input id="page" name="page" type="hidden" value="${cond.pageIndex}">
		</form>
		<div style="text-align:center;">共${count!0}条</div>
		<@pager cond.pageIndex pageCount />
  		</div>
  	</div>
</div>
        
<div class="panel panel-info panel-yunhu">
    <div class="panel-heading">
        <strong>强抢线索</strong>
        <#--
        <span class="badge badge-warning"><#if grabTasks??>${grabTasks?size}<#else>0</#if></span>
        -->
        <div class="form-inline">
            <div class="input-group">
                <input class="form-control input-sm js-clue-search-inp" type="text"
                    placeholder="输入电话号码查询"
                >
            </div>
            <button type="button" class="btn btn-info btn-sm js-clue-search-btn">查询</button>
            <button type="button" class="btn btn-info btn-sm js-clue-search-reset hidden">关闭查询</button>
        </div>
    </div>
    <div class="panel-body">
  		<div class="js-clue-search-result js-grab-tasks-list clue-search-result hidden"></div>
  			<#--
  			<table class="js-grab-tasks-list js-clue-search-list table table-striped table-bordered table-condensed vcenter text-center table-yunhu">
		    	<thead>
		    		<tr class="success">
		    			<th width="50px">留电话页</th>
		    			<th>用户UID</th>
		    			<th>手机号</th>
		    			<th>期望外呼时间</th>
		          		<th width="50px">操作</th>
		        	</tr>
		      	</thead>
			  	<tbody>
			  		<#list grabTasks as gtask>
			  		<#assign t = gtask.so>
			  		<tr>
			    		<td>${t.sourceName}</td>
			    		<td>${t.uid}</td>
			    		<td>${t.mobile}</td>
			    		<td><#if t.expectedTime??>${t.expectedTime?datetime?string('yy-MM-dd HH:mm')}</#if></td>
			    		<td>
			      			<a class="js-grab-button btn btn-default btn-xs" data-task-id="${t.id}" href="javascript:void(0)">抢任务</a>
			    		</td>
			  		</tr>
			  		</#list>
			  	</tbody>
			</table>
			-->
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#expectedTimeBegin').datetimepicker();
		$('#expectedTimeEnd').datetimepicker();
		$('#addFormExceptedTime').datetimepicker();
		$('#addFormMobile').blur(sales.queryUserByMobile);
		$('.js-mobile-span').addClass('glyphicon glyphicon-earphone').tooltip({
    		title: sales.requestCallHistoryEntries,
    		html: true,
            placement:'bottom'
    	});
    	sales.salesUsers = [<#list salesUserList as sales>{"id":"${sales.id}", "name":"${sales.name}"}<#if sales_index<salesUserList?size-1>,</#if></#list>];
    });
</script>
<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">


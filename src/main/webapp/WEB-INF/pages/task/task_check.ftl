<#assign _title="B计划方案审核">
<#include "commons/header.ftl" />
<#assign toolbar="advisor" />
<#include "/commons/toolbar.ftl" />
<#include "/advisor/timemacro.ftl"/>

<style>
    .classR {
        background-color: #ff8080;
    }
    .classY {
        background-color: #ffff99;
    }
</style>

<#assign statusNameMap={'Creating':'创建中','Committed':'已提交','Confirmed':'确认','Offline':'下线','Illegal':'非法','Paid':'已购买','Deleted':'已删除','ReviewFailed':'审核不通过，达人未修改'}>


<div class="container-fluid" id="enlarge-body">
<div class="container-fluid">
<form class="form-horizontal form-inline">

    <fieldset>

        审核类型：
        <select name="category">
            <option <#if category==0>selected</#if> value="0">全部</option>
            <option <#if category==1>selected</#if> value="1">未审核</option>
            <option <#if category==2>selected</#if> value="2">审核已通过</option>
            <option <#if category==3>selected</#if> value="3">审核未通过</option>
        </select>
        <input type="text" class="input-small search-query" name="tid" <#if tid!=0>value="${tid}"</#if> placeholder="方案ID">
        <input type="text" class="input-small search-query" name="uid" <#if uid!=0> value="${uid}"</#if> placeholder="用户ID">
        <input type="text" class="input-small search-query" name="doyenUid" <#if doyenUid!=0>value="${doyenUid}"</#if> placeholder="达人ID">

        发布日期：<input type="text" class="input-medium search-query" name="cTime" value="${cTime}" placeholder="格式：2013-12-10">
        <input type="checkbox" name="nhnp" <#if nhnp>checked </#if>>显示未处理
        <input type="checkbox" name="nhp" <#if nhp>checked </#if>>显示已购买
        <button class="btn btn-primary" type="submit">搜索</button>

    </fieldset>





</form>

<div class="row-fluid">
    <legend>详细任务情况 (总数：${totalNum})</legend>




    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr >
            <th>行程ID</th>

            <th>任务名</th>
            <th>父任务id</th>
            <th>用户id</th>
            <th>达人用户名</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>审核时间</th>


            <th>方案状态</th>

            <th>审核状态</th>


            <th style="width: 220px">操作</th>

        </tr>
        </thead>
        <tbody>

        <#list tasks as task>
            <#assign  tripshell=tripShellMaps[task.tid+""]>
            <#assign  tripShellAudit=tripShellAuditMaps[task.tid+""]>
            <#assign  user=userMap[task.uid+""]>
            <#assign  title=taskMap[task.taskId+""].title>
            <#assign  statusSigns=statusMap[task.taskSId+""]>
            <#assign  firstD=task.firstDuration>
            <#assign  successD=task.duration>
            <#if task.firstDuration==0>
                <#assign  firstD=(now-task.createdAt)/1000>
            </#if>
            <#if task.duration==0>
                <#assign  successD=(now-task.createdAt)/1000>
            </#if>
        <tr >
            <td><#if task.tid==0>暂无<#else><#if status==4><a href="/trip/review/trip/${task.tid}/">${task.tid}</a><#else>${task.tid}</#if></#if></td>

            <td><a href="/advisor/task/?sign=99&&taskId=${task.taskId}"><#if title?contains("为")|| title?contains("：")>${title}<#else>为 ${user.nick} 设计${title}</#if></a></td>
            <td><a href="/advisor/pretask/?taskid=${task.taskId}" target="_blank">${task.taskId}</a></td>
            <td>${(userMap[task.uid+""].nick)!""}(${task.uid})</td>
            <td>${(userMap[task.doyenId+""].nick)!""}(${task.doyenId})<#if task.sign.value==1><i class="icon-star" title="内部达人"></i></#if></td>
            <td><#if tripshell?has_content>${(tripshell.createdAt?number_to_datetime)!}</#if></td>
            <td><#if tripshell?has_content>${(tripshell.updatedAt?number_to_datetime)!}</#if></td>
            <td><#if tripShellAudit?has_content>${(tripShellAudit.createdAt?number_to_datetime)!}</#if></td>




        <#--Start = 1 // 是否开始制作。实际就是tid是否为0的状态-->
        <#--Review = 2   //是否待审核-->
        <#--Paid = 3     //是否购买-->
        <#--Close=4  //  是否关闭-->
        <#--Return=5 //是否返程  ---如何判断呢？？-->
        <#--Change=6 //有未完成变更 ---当需求变化时运营设置的 ---审核通过则重置为0-->
        <#--ReviewSuccess = 7   //是否通过审核 行程是通过审核，方案是否提交给用户-->
            <td><#if tripshell?has_content>${(statusNameMap[(tripshell.status)])!}</#if><#if task.studio><i class="icon-home" title="工作室"></i></#if></td>


            <td>
                <#if (statusSigns["ReviewFail"])!false>
                <span class=" text-error"> 审核未通过  </span>
                <#elseif (statusSigns["ShellReviewSuccess"])!false>
                   <span class=" text-success">审核通过</span>
                <#else>
                    未审核
                </#if>
            </td>

            <td ><a href="" data-poload="/advisor/task/history/?taskSId=${task.taskSId}">动态</a>&nbsp;&nbsp;

                <a  href="/trip/shell/?tid=${task.tid}" target="_blank">审核方案</a>



            </td>

        </tr>

        </#list>
        </tbody>
    </table>
</div>

<ul class="pager">
<#if num!=1>
    <li>
        <a href="/advisor/task/check/?num=${num-1}&category=${category}&doyenUid=${doyenUid}&cTime=${cTime}&nhnp=${nhnp}&hp=${hp}">前一页</a>
    </li>
</#if>
<#if tasks?size==50>
    <li>
        <a href="/advisor/task/check/?num=${num+1}&category=${category}&doyenUid=${doyenUid}&cTime=${cTime}&nhnp=${nhnp}&hp=${hp}">后一页</a>
    </li>
</#if>
</ul>
</div>
</div>

<#include  "/task/task_js.ftl"/>
<#include "/commons/footer.ftl" />

<#assign _title="达人广场">
<#include "commons/header.ftl" />
<#assign toolbar="advisor" />
<#include "/commons/toolbar.ftl" />
<#include "/advisor/timemacro.ftl"/>


<div class="container-fluid" id="enlarge-body">
    <div class="container-fluid">

        <div class="row-fluid">

            <div>


                <form>
                    任务状态：
                    <select name="status">
                        <option value="99" <#if status==99>selected</#if>>全部</option>
                        <option value="1" <#if status==1>selected</#if>>未领取</option>
                        <option value="2" <#if status==2>selected</#if>>已被领取</option>
                        <option value="3" <#if status==3>selected</#if>>已结束</option>
                    </select>
                    任务类型：
                    <select name="type">
                        <option value="0" <#if type==0>selected</#if>>全部</option>
                        <option value="1" <#if type==1>selected</#if>>B计划任务</option>
                        <option value="2" <#if type==2>selected</#if>>A计划任务</option>
                    </select>
                    <input type="submit">
                </form>

            </div>




            <table class="table table-striped table-bordered table-condensed">
                <thead>
                <tr >
                    <th>任务</th>
                    <th>任务类型</th>
                    <th>需求用户</th>
                    <th>状态</th>
                    <th>生成任务时间</th>
                    <th>接受详情
                    <table style="width: 100%">
                        <tr>
                            <td>达人</td>
                            <td>接任务时间</td>
                            <td>任务状态</td>
                        </tr>
                    </table>
                    </th>
                    <th>领取剩余时间</th>
                    <th>截止剩余时间</th>
                    <th>备注</th>
                    <th>分配</th>
                    <th>操作</th>

                </tr>
                </thead>
                <tbody>

                <#list tasks as task>
                <#assign taskId=task.taskId>
                <#assign taskTitle=task.title>
                <#assign taskType=task.type.value>
                <#assign uid=task.uid>
                <#assign taskUser=userMap[uid+""]>
                <#assign shijieUserUrl='http://www.shijiebang.com/u'+uid+'/'>

                <tr>
                    <td title="${taskId}"><#if task.status!="WaitingApply"><a href="/advisor/task/?sign=99&&taskId=${taskId}" target="_blank">${taskTitle}</a><#else>${taskTitle}</#if></td>
                    <td>${(taskType==1)?string('B计划','A计划')}</td>
                    <td><a href="${shijieUserUrl}" target="_blank">${taskUser.nick}</a></td>

                    <td><#switch task.status>
                        <#case "WaitingApply">
                            <span class="mission-status__1 mission-btn">未领取</span>
                            <#break>
                        <#case "EndedApply">
                            <span class="mission-status__2 mission-btn">已领取</span>
                            <#break>
                        <#case "EndedTask">
                            <span class="mission-status__3 mission-btn">已结束</span>
                            <#break>

                    </#switch></td>
                    <td>${task.createdAt?number_to_datetime}</td>
                    <td>
                       <#assign taskSessions=taskSessionMap[taskId+""] />
                        <#--${taskSessions?size}-->
                       <#if taskSessions?is_sequence&&taskSessions?size gt 0>
                        <table class="table table-striped table-bordered table-condensed">
                            <#list taskSessions as taskSession>
                                <tr>
                                    <td>${taskSession.uid}</td>
                                    <td>${taskSession.createdAt?number_to_datetime}</td>
                                    <td>${taskSession.status}</td>
                                </tr>
                            </#list>

                        </table>
                       </#if>
                    </td>
                    <#if task.type.value==1>
                        <#assign h1=24>
                        <#assign h2=48>
                    <#else>
                        <#assign h1=8>
                        <#assign h2=16>
                    </#if>
                    <#assign ltimeLong= (task.createdAt+h1*3600*1000-now)>
                    <td><@ptime ltime=ltimeLong stime=(task.createdAt+h1*3600*1000)/></td>
                    <#assign otimeLong= (task.createdAt+h2*3600*1000-now)>
                    <td><@ptime ltime=otimeLong stime=(task.createdAt+h2*3600*1000)/></td>
                    <#assign notes=notesMap[task.tsid+""]>
                    <td><#if notes?if_exists><ol><#list notes as note>
                        <li>${note.content}</li>
                    </#list><ol><#else>无备注</#if></td>

                    <td><input class="input-mini" id="doyen_${task.taskId}" placeholder="达人ID" ><a class="btn" onclick="dis(${task.taskId},${task.tsid},${task.uid})">分配</a> </td>
                    <td style="width: 80px;">
                        <#if task.type==1><a href="/advisor/${task.tsid}/" target="_blank">需求</a>
                        <#else>
                            <a href="http://www.shijiebang.com/u${task.uid}/task-${task.taskId}/" target="_blank">需求</a>
                        </#if>
                        <#if status==1>&nbsp;<a href="#" onclick="terminal(${task.taskId})">废弃</a></#if>
                    </td>

                </tr>

                </#list>
                </tbody>
            </table>
        </div>

        <ul class="pager">
        <#if num!=1>
            <li>
                <a href="/advisor/pretask/?status=${status}&num=${num-1}&type=${type}">前一页</a>
            </li>
        </#if>
        <#if tasks?size==50>
            <li>
                <a href="/advisor/pretask/?status=${status}&num=${num+1}&type=${type}">后一页</a>
            </li>
        </#if>
        </ul>
    </div>
</div>

<script type="text/javascript">








    function dis(taskId,tsid,uid) {

        var doyenUid=$('#doyen_'+taskId).val()  ;

        if(doyenUid==0){
            alert("请填写达人的uid");
            return;
        }
        $.post(
                "/a/advisor/task/",
                {taskId: taskId,tsid:tsid,uid:uid ,doyenUid:doyenUid,op: "dis"},
                function (result) {
                    if(result=='true'){
                        alert('已分配');
                        window.location.href="/advisor/pretask/?status=${status}&num=${num}";

                    }else{
                        alert(result);
                    }
                }
        );
    }

    function terminal(taskId) {

        if(!confirm("确定要让这个任务废弃吗")){
            return;
        }
        $.post(
                "/advisor/task/",
                {taskId: taskId, op:"drop"},
                function (result) {
                    window.location.href="/advisor/pretask/?num=${num}&status=${status}";
                }
        );
    }


</script>
<#include "/commons/footer.ftl" />

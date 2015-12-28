<#assign _jss=['/static/js/plugin/jquery.tokeninput.js?20130520','/static/js/plugin/jquery-ui.js?1.10.2']>
<#assign _csss=['/static/css/plugin/token-input.css?20130520']>
<#assign _title="工作室相关统计">
<#include "commons/header.ftl" />
<#assign toolbar="advisor" />
<#include "/commons/toolbar.ftl" />
<#include "/advisor/timemacro.ftl"/>


<div class="container-fluid" id="enlarge-body">
    <div class="container-fluid">

        <div class="row-fluid">
            <form class="form-horizontal form-inline">

                <input type="text" <#if duid!=0> value="${duid}"</#if> placeholder="达人uid，不填为全部" name="duid"
                       class="input-medium">

                <input type="text" value="${start}" placeholder="任务发布时间" name="start" id="start" class="input-medium"> -

                <input type="text" value="${end}" placeholder="任务发布时间，不包含此此天" name="end" id="end" class="input-big">
                <input type="submit" value="查看统计">
            </form>
        </div>

    <#if allTaskIds??>
        <legend>工作室相关统计</legend>
        <table class="table table-striped table-bordered table-condensed">
            <thead>
                <#if duid==0>
                <td>总的任务数</td>
                <td>方案任务数</td>
                <td>方案任务是工作室的数</td>
                <td>领取的是工作室的数</td>
                <td>A行程任务数</td>
                <td>A行程发布是工作室的数</td>
                <td>A行程领取的是工作室的数</td>
                <td>B行程任务数</td>
                <#else>
                <td>领取的总任务数</td>
                <td>其中方案任务数</td>
                <td>这段时间放出的方案工作室</td>
                <td>其中方案任务是工作室的数</td>
                <td>A行程任务数</td>
                <td>这段时间放出的A行程工作室</td>
                <td>A行程任务数是工作室的数</td>
                <td>B行程任务数</td>

                </#if>
            </thead>
            <tr>
                <td>${allTaskIds?size}</td>
                <td>${bAllTaskIds?size}</td>
                <td>${bStudioTaskIds?size}</td>
                <td>${bAcceptStudioTaskNum}</td>
                <td>${aAllTaskIds?size}</td>
                <td>${aStudioTaskIds?size}</td>
                <td>${aAcceptStudioTaskNum}</td>
                <td>${allTaskIds?size-bAllTaskIds?size-aAllTaskIds?size}</td>

            </tr>
        </table>
    </#if>

    </div>
</div>

<script>
    $("#start").datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "-1w",
        changeMonth: true,
        numberOfMonths: 2,
        onClose: function (selectedDate) {
            $("#start").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#end").datepicker({
        dateFormat: "yy-mm-dd",
        changeMonth: true,
        numberOfMonths: 2,
        onClose: function (selectedDate) {
            $("#end").datepicker("option", "maxDate", selectedDate);
        }
    });
</script>

<#include "/commons/footer.ftl" />

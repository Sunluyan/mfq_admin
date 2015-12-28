<#assign _jss=['/static/js/plugin/jquery.tokeninput.js?20130520','/static/js/plugin/jquery-ui.js?1.10.2']>
<#assign _csss=['/static/css/plugin/token-input.css?20130520']>
<#assign _title="任务的一些统计数据情况">
<#include "commons/header.ftl" />
<#assign toolbar="advisor" />
<#include "/commons/toolbar.ftl" />
<#include "/advisor/timemacro.ftl"/>


<div class="container-fluid" id="enlarge-body" >
    <div class="container-fluid">

        <div class="row-fluid">
        <form class="form-horizontal form-inline">

            <input type="text" value="${start}" placeholder="开始时间" name="start" id="start" class="input-medium"> -
            <input type="text" value="${end}" placeholder="结束时间" name="end" id="end" class="input-medium">
             <input type="submit" value="查看统计">
        </form>
         </div>

    <#if applyTime ??>
        <legend>一些相关的数据统计</legend>
    <table class="table table-striped table-bordered table-condensed">
        <tr>
            <td>行程从任务发布到通过</td>
            <td>${(publishTime/60)?float}分钟</td>
        </tr>
        <tr>
            <td>行程从任务接受到通过</td>
            <td>${(applyTime/60)?float}分钟</td>
        </tr>
        <tr>
            <td>方案从任务领取到第一次提交给用户</td>
            <td>${(shellTime/60)?float}分钟</td>
        </tr>
    </table>
    </#if>
        <legend>壳审核不通过原因的数据统计</legend>

    <#if sortList??>
            <table class="table table-striped table-bordered table-condensed">
                <tr>
                    <td>原因</td>
                    <td>数量</td>
                </tr>
                <#list sortList as item>
                         <tr>
                             <td>${item["key"]}</td>
                             <td>${item["value"]}</td>
                         </tr>


                </#list>

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

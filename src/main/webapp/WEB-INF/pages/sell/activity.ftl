<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<style>
    .table th, .table td {
        text-align: center;
    }
</style>
<div class="container" id="enlarge-body">
    <div class="container">

        <form class="form-inline well" action="/sell/activity/" method="POST">
            <div class="form-group">
                <label for="proName">活动名称：</label>
                <input type="text" class="form-control" id="activityName" name="name" value="${name}"
                       size="20">
                <br/><br/>

                <label for="hosName">线上/线下：</label>
                <select id="online" name="isOnline" class="online select-group" style="width:100px;">

                    <option value="0" <#if isOnline == 0>selected</#if>>无</option>
                    <option value="1" <#if isOnline == 1>selected</#if>>线上</option>
                    <option value="2" <#if isOnline == 2>selected</#if>>线下</option>
                </select>


            </div>

            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
            </div>

            <input type="hidden" name="page" value="" class="page">
        </form>
        <script>


        </script>


        <div class="row-fluid">

            <div>

                <ul class="pager" style="float:right;">
                <#if page &gt; 1>
                    <li>
                        <a href="javascript:void(0)" class="prev">前一页</a>
                    </li>
                </#if>
                    <li>
                        <a href="javascript:void(0)" class="next">后一页</a>
                    </li>
                    <li><span>总数:</span></li>
                    <a href="/sell/activity/add/" target="_blank">增加活动</a>
                </ul>
            </div>
            <div>
                <table class="table table-striped table-bordered table-condensed" >
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>活动名称</th>
                        <th>类型</th>
                        <th>链接</th>
                        <th>有效日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list items as item>
                    <tr >
                        <td >${item.id}</td>
                        <td>${item.activityName}</td>
                        <td><#if item.type == 1>线上</#if><#if item.type == 2>线下</#if></td>
                        <td><a href="${item.link}" target="_blank"><#if item.link??>${item.link?substring(0,30)+"......"}</#if></a> </td>
                        <td>${item.beginAt?string("yyyy-MM-dd")} - ${item.endAt?string("yyyy-MM-dd")}</td>
                        <td>
                            <a class="delete">删除</a>
                            <a href="/sell/activity/add/?id=${item.id}">修改</a>

                        </td>
                    </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<#include "commons/footer.ftl" />

<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />



<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />
            <div class="span10">
                <form action="/question/" class="well form-inline" method="GET">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="qid">qid</option>

                    </select>
                    <input name="qid" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询</button>
                </form>
                <ul class="pager" style="float:left;">
                    <li>
                        <a class="<#if status='UNSOLVED'>btn-success  </#if> " href="/search/questions/?status=UNSOLVED">最新的未回答问题</a>
                    </li>
                    <li>
                        <a class="<#if status='ALL'>btn-success</#if> " href="/search/questions/?status=ALL">最新所有问题</a>
                    </li>
                    <li>
                        <a class="<#if status='rank'>btn-success</#if> " href="/search/questions/rank/">设置过rank值的问题</a>
                    </li>
                </ul>

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th width="100px">提问者id</th>
                        <th>问题标题</th>
                        <th>rank</th>
                        <th width="120px">提问时间</th>
                        <th >操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list questions as question>
                    <tr>
                        <td>
                            <a target="_blank" href="http://shijiebang.com/u${question.uid}/">${question.uid}</a>
                        </td>
                        <td>
                            <a target="_blank" href="http://shijiebang.com/question/${base62(question.qid)}/">${question.title}</a>
                        </td>
                        <td>${question.rank}</td>
                        <td>${question.createdAt?number_to_date}</td>
                        <td> <a target="_blank" href="/question/${question.qid}/">操作</a>                        </td>

                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<#include "commons/footer.ftl" />

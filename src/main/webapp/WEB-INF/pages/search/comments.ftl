<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />

        <div class="span10">
            <#if msg??>
            <div class="well">${msg}</div>
            </#if>
                <form class="well form-inline" action="/comment/" method="GET">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="cid">评论ID</option>
                    </select>
                    <input name="cid" type="text" class="input-medium search-query">
                    评论的发布人：<input name="uid" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询评论</button>
                </form>

                <ul class="pager" style="float:left;">
                    <li>
                        <a class="btn-success" href="/search/comments/">最新评论</a>
                    </li>
                </ul>
                <ul class="pager" style="float:right;">
                <#if num &gt; 1>
                    <li>
                        <a href="/search/comments/?page=${num-1}">前一页</a>
                    </li>
                </#if>
                    <li>
                        <a href="/search/comments/?page=${num+1}">后一页</a>
                    </li>
                    <li><span>评论总数:${total}</span></li>
                </ul>

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th width="100px">作者</th>
                        <th>评论类型</th>
                        <th>评论ID</th>
                        <th>内容</th>
                        <th>发布时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list comments as comment>
                    <tr>
                        <td>
                            <a target="_blank" href="http://shijiebang.com/u${comment.uid}/">
                            ${usersmap[ comment.uid?string ].nick}
                            </a>
                        </td>
                        <td>${comment.type}</td>
                        <td>${comment.cid}</td>
                        <td>
                            ${comment.comment}
                        </td>
                        <td>${comment.createdAt?number_to_datetime}</td>

                        <td> <a  href="/comment/${comment.cid}/?uid=${comment.uid}">操作</a>                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
        </div>
        </div>

    </div>
</div>


<#include "commons/footer.ftl" />
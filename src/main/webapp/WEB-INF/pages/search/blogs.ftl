<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />

<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />
            <div class="span10">
                <form action="/blog/" class="well form-inline" method="GET">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="bid">bid</option>
                    </select>
                    <input name="bid" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询</button>
                </form>

                <ul class="pager" style="float:left;">
                    <li>
                        <a class="<#if status = 'Normal' >btn-success</#if>" href="/search/blogs/?status=Normal">最新博文</a>
                        <a class="<#if status = 'Hot' >btn-success</#if>" href="/search/blogs/?status=Hot">最热博文</a>
                        <a class="<#if status = 'Deleted'>btn-success</#if>" href="/search/blogs/?status=Deleted">已删博文</a>
                    </li>
                </ul>
                <ul class="pager" style="float:right;">
                <#if num &gt; 1>
                    <li>
                        <a href="/search/blogs/?page=${num-1}&status=${status!}">前一页</a>
                    </li>
                </#if>
                    <li>
                        <a href="/search/blogs/?page=${num+1}&status=${status!}">后一页</a>
                    </li>
                    <li><span>总数:${total}</span></li>
                </ul>

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th width="100px">作者</th>
                        <th>标题</th>
                        <th>分类</th>
                        <th>标签</th>
                        <th>涉及地区</th>
                        <th>状态</th>
                        <th>发布时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list blogs as blog>
                    <tr>
                        <td>
                            <a target="_blank" href="http://shijiebang.com/u${blog.uid}/">
                            ${usersmap[ blog.uid?string ].nick}
                            </a>
                        </td>
                        <td>
                            <a target="_blank" href="http://shijiebang.com/u${blog.uid}/blog-${blog.bid}/">${blog.title}</a>
                            <#if status = 'Hot' >(${blog.likeNum}赞)(${blog.commentNum}评)</#if>
                        </td>
                        <td>
                            <#if blog.category=="Track">游记</#if>
                            <#if blog.category=="Showcase">晒货</#if>
                            <#if blog.category=="Experience">经验</#if>
                        </td>
                        <td>
                            <#list blog.tags as tag>
                                ${tag}
                            </#list>
                        </td>
                        <td>
                            <#list locationMap[blog.bid+""] as locationId>
                            	<#if cities[locationId+""]??>
                            		${cities[locationId+""].cname}
                            	</#if>
                            </#list>
                        </td>
                        <td>
                            <#if blog.status=="Normal">正常</#if>
                            <#if blog.status=="Draft">草稿</#if>
                            <#if blog.status=="Deleted">删除</#if>
                        </td>
                        <td>
                        	${blog.createdAt?number_to_datetime}
                        </td>

                        <td> <a  href="/blog/${blog.bid}/">操作</a>                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<#include "commons/footer.ftl" />

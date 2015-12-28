<#macro pager page totalpage pageid='page' pagecount=20>
<#-- 约束条件: 只能是form提交并且form里面有一个type=hidden，id=pageid的input -->
<!-- page=${page},totalpage=${totalpage} -->
<#if page gt 0 && totalpage gt 1 && page lte totalpage>
<#assign lower=page-pagecount/2,higher=page+pagecount/2>
<#if lower lt 1><#assign lower=1,higher=1+pagecount></#if>
<#if higher gt totalpage><#assign higher=totalpage,lower=totalpage-pagecount></#if>
<#if lower lt 1><#assign lower=1></#if>

<#if _bootstrap3>
<div class="text-center" data-pageid="${pageid}">
  <ul class="pagination">
<#else>
<div class="pagination pagination-centered" data-pageid="${pageid}">
  <ul>
</#if>
    <li <#if page==1>class="disabled"</#if>><a href data-page="1">&laquo;</a></li>
    <#list lower..higher?number as i>
      <li <#if page==i>class="active"</#if>><a href data-page="${i}">${i}</a></li>
    </#list>
    <li <#if page==totalpage>class="disabled"</#if>><a href data-page="${totalpage}">&raquo;</a></li>
  </ul>
</div>
</#if>
</#macro>
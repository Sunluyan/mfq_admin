<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container">

    <div class="row-fluid">

      <div>

        <ul class="pager" style="float:left;">
          <li>
            <a href="/sell/items/">商品管理</a>
          </li>
        </ul>
        <ul class="pager" style="float:right;">
        <#if page &gt; 1>
          <li>
            <a href="/sell/items/?page=${page-1}">前一页</a>
          </li>
        </#if>
          <li>
            <a href="/sell/items/?page=${page+1}">后一页</a>
          </li>
          <li><span>总数:${itemcount}</span></li>
          <a href="/sell/item/add/">增加商品</a>
        </ul>
	</div>
	<div>
        <table class="table table-striped table-bordered table-condensed">
          <thead>
          <tr>
            <th>ID</th>
            <th>产品名称</th>
            <th>分类</th>
            <th>医院</th>
            <th>价格</th>
            <th>有效日期</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <#list items as item>
          <tr>
            <td> ${item.id}  </td>
            <td> ${item.name} </td>
            <td>
            <#list classify as cs>
	            <#if ((item.tid))== ((cs.id))> ${cs.name} </#if>
            </#list>
            </td>
            <td>
            <#list hospitals as hs>
	            <#if ((item.hospitalId))== ((hs.id))> <a href="/hospital/view/?id=${item.hospitalId}">${hs.name}</a> </#if>
            </#list>
            </td>
            
            <td> ${item.price} </td>
            <td> ${item.dateStart?string("yyyy-MM-dd hh:mm:ss")} － ${item.dateEnd?string("yyyy-MM-dd hh:mm:ss")}</td>

            <td><a href="/sell/item/edit/?id=${item.id}">修改</a></td>
            <td><a href="/sell/item/delete/?id=${item.id}">删除</a></td>
          </tr>
          </#list>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>


<#include "commons/footer.ftl" />

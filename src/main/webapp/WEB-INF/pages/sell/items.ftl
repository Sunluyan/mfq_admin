<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container">
    
    <form class="form-inline well" action="/sell/items/" method="POST" >
      <div class="form-group">
          <label for="proName">产品名称：</label>
          <input type="text" class="form-control" id="proName" name="proname" value="${proname}" size="20">

          <br/><br/>  
          <label for="hosName">医院名称：</label>
          <input type="text" class="form-control" id="hosName" name="hosname" value="${hosname}" size="20">
          <br/><br/>
          <label for="hosName">排序方式：</label>
          <select id="orderby" name="orderby" class="orderby select-group" style="width:100px;">

              <option value="id desc" <#if orderby == "id desc">selected = selected</#if>   >无</option>
              <option value="price desc"   <#if orderby == "price desc">selected = selected</#if>   >价格</option>
              <option value="created_at desc"  <#if orderby == "created_at desc">selected = selected</#if>  >时间</option>
              <option value="tid desc"  <#if orderby == "tid desc">selected = selected</#if>   >分类</option>
          </select>
      </div>
      
      <div class="input-group">
        <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
      </div>

        <input type="hidden" name="page" value="${page}" class="page">
    </form>
        <script>

            $(function(){
                var $page = $(".page")

                var maxPage = Math.ceil(${itemcount}/50);

                if(maxPage <= $page.val()){
                    $(".next").hide()
                }
              $(".prev").click(function(){
                  $page.val(parseInt($page.val())-1);
                  $(".form-inline").submit();
              })
                $(".next").click(function(){
                  $page.val(parseInt($page.val())+1);
                  if(parseInt($page.val())>maxPage){
                      return false;
                  }
                    $(".form-inline").submit();
                })
            })
        </script>


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
            <a href="javascript:void(0)" class="prev">前一页</a>
          </li>
        </#if>
          <li>
            <a href="javascript:void(0)" class="next">后一页</a>
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

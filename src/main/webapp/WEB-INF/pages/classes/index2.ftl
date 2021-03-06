<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">

	  <div class="row-fluid">
	  <#if msg??>
	    <div class="alert text-center">
	      <a class="close" data-dismiss="alert">×</a>
	    ${msg}
	    </div>
	  </#if>
	  </div>
  
    <div class="container">
        
        <a data-toggle="modal" href="/classes/edit/?t=2&id=0&_=0.1596856986" data-target="#myModal">添加新项目</a>

    <div class="row-fluid">

	<div>
        <table class="table table-striped table-bordered table-condensed">
          <thead>
          <tr>
            <th>名称</th>
            <th>一级项目</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <#list data as item>
          <tr>
            <td>${item.name}</td>
            <td style="vertical-align: middle;"> ${item.desp} </td>
            <td style="vertical-align: middle;"><a href="javascript:editBanner(${item.id});" class="edit_btn">修改</a>|<a href="/classes/del/?t=2&id=${item.id}">删除</a></td>
          </tr>
          </#list>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" style="display: none;" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">项目</h4>
            </div>
            <div class="modal-body">

                

            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
  function editBanner(id){
  	$('#myModal').modal({remote:'/classes/edit/?t=2&id='+id+'&_='+Math.random()});
  }
</script>
<#include "commons/footer.ftl" />

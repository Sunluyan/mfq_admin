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
        
        <a data-toggle="modal" href="/home/banner/edit/" data-target="#myModal">添加新项目</a>

    <div class="row-fluid">

	<div>
        <table class="table table-striped table-bordered table-condensed">
          <thead>
          <tr>
            <th>图片预览</th>
            <th>名称</th>
            <th>类型</th>
            <th>图片链接</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <#list items as item>
          <tr>
            <td style="width:400px;"> <img src="${item.img!}" width="360" /></td>
            <td style="vertical-align: middle;"> ${item.name} </td>
            <td style="vertical-align: middle;"> ${item.pType.desc} </td>
            <td style="vertical-align: middle;"> ${item.url} </td>
            <td style="vertical-align: middle;"><a href="javascript:editBanner(${item.id});" class="edit_btn">修改</a>|<a href="/home/banner/del/?id=${item.id}">删除</a></td>
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
                <h4 class="modal-title" id="myModalLabel">Banner</h4>
            </div>
            <div class="modal-body">

                <form id="file_upload" name="file_upload" action="/home/banner/edit/" method="POST" enctype="multipart/form-data">
				  <input type="hidden" value="" name="bid" id="bid"/>
				  <div class="control-group">
					<label class="control-label" for="lname">上传图片</label>
	                <div class="controls">
	                    <input type="file" id="file_img" name="file_img">
	                    <p class="help-inline"><strong class="text-error">*</strong></p>
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">图片链接</label>
	                <div class="controls">
	                    <select id="b_url" name="is_url" style="float: left; margin-right:10px;">
	                        <option value="1">有链接</option>
	                    	<option value="0">无连接</option>
	                    	
	                    </select>
	                    <input type="text" class="input-large" id="burl" name="burl" value="${banner.img!}">
	                	<div style="clear:both;"></div>
	                </div>
				  </div>
				  
                    <div class="modal-footer">
                        <input type="submit" id="upload" class="btn btn-primary" value="确认"/>
                    </div>

                </form>

            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
  function editBanner(id){
  	$('#myModal').modal({remote:'/home/banner/edit/?id='+id});
  }
</script>
<#include "commons/footer.ftl" />

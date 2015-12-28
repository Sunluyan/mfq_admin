<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<!-- 配置文件 -->
<script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>

<div class="container" >
  <div class="row-fluid">
  <#if msg??>
    <div class="alert text-center">
      <a class="close" data-dismiss="alert">×</a>
    ${msg}
    </div>
  </#if>
  </div>

  <form class="form-horizontal" method="POST" action="/noti/send/" enctype="multipart/form-data">

    <input type="hidden" name="id" value="${item.id}">

    <fieldset>
      <legend>发送通知 </legend>
      <div >
        <p/>
      </div>

      <div class="control-group">
        <label class="control-label" for="alias">产品分类</label>
        <div class="controls">
          <select id='type' name="type">
            <option value="1" >公共</option>
            <option value="2" >个人</option>
          </select>
        </div>
      </div>
      
      <div class="control-group user">
        <label class="control-label" for="lname">用户手机号</label>
        <div class="controls">
          <input class="input-large" id="mobile" type="text" placeholder="手机号码" name="mobile">
        </div>
      </div>

	  <div class = "control-group">
	    <label class="control-label" for="lname">标题</label>
	    <div class="controls">
	    <input class="input-large" id="title" type="text" placeholder="标题" name="title">
	    </div>
	  </div>
      <div class="control-group">
        <label class="control-label" for="lname">内容</label>
        <div class="controls">
          <textarea class="form-control" style="width:400px" rows="2" id="desc" name="desc"></textarea>
        </div>
      </div>


	<div class="control-group">
        <label class="control-label" for="cname">图片</label>
        <div class="controls">
              <input type="file" id="img_file" name="img_file" value="">
              <p class="help-inline"><strong class="text-error"></strong>请上传95x95的png图片</p>
        </div>
      </div>
      
      <div class="form-actions">
        <button type="submit" class="btn btn-primary" onclick="return docheck();">保 存</button>
        &nbsp;&nbsp;
        <button type="button" class="btn" onclick="history.back();">取消返回</button>
      </div>
    </fieldset>
  </form>

</div>


<script type="text/javascript">
  $(document).ready(function(){
  	$("#type").change(function(){
  		var type = $('#type').val();
  		if(type==2){
  			$(".user").css("display","block");
  		}else{
  			$(".user").css("display","none");
  		}
  	});
  	$(".user").css("display","none");
  });
</script>

<#include "commons/footer.ftl" />
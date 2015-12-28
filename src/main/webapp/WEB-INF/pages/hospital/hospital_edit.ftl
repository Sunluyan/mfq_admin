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
<style>
	.hos-img{
		cursor:pointer;
		width:100px;
		height:33px;
		transition:all 0.5s;
	}
	.hos-img-hidden{
		display:none;
	}
</style>
<div class="container" >
  <div class="row-fluid">
  <#if msg??>
    <div class="alert text-center">
      <a class="close" data-dismiss="alert">×</a>
    ${msg}
    </div>
  </#if>
  </div>

  <form class="form-horizontal" method="POST" action="/hospital/edit/" enctype="multipart/form-data">

    <input type="hidden" name="id" value="${hospital.id}">

    <fieldset>
      <legend>医院信息  </legend>
      <div >
        <p/>
      </div>

      <div class="control-group">
        <label class="control-label" for="cname">医院名称</label>
        <div class="controls">
          <input type="text" class="input-large" id="name" name="name" value="${hospital.name!}">
          <span class="help-inline"><strong class="text-error">*</strong></span>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="cname">医院图片</label>
        <div class="controls">
              <input type="file" id="img_file" name="img_file" value="${hospital.img!}"><br><span class="help-inline"><strong class="text-error">${hospital.img!}
             <br> <img src="${hospital.img!}" class="hos-img" />
             
             <img src="${hospital.img!}" class="hos-img-hidden"/></strong>
              </span>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="alias">医院地址</label>
        <div class="controls">
          <input type="text" class="input-large" id="address" name="address" value="${hospital.address!}">
          <span class="help-inline"><strong class="text-error">*</strong></span>
        </div>
      </div>
	
	
	<div class="control-group">
		<label class="control-label" for="alias">所属区域</label>
		<div class="controls">
          <select id="s_province" name="s_province"></select>  
	    <select id="s_city" name="cityname" ></select>  
         <script class="resources library" src="/static/js/hospital/area.js" type="text/javascript"></script>
	    
	    <script type="text/javascript">_init_area();</script>
	    </div>
	    <div id="show"></div>
		
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
  function docheck() {

    if($.trim($("#name").val()).length < 1){
      alert("中文名太搓");
      return false;
    }
    if($.trim($("#address").val()).length < 1){
      alert("地址不能为空！！！");
      return false;
    }

    return true;
  }
  
  var isclick = false;
  $(".hos-img").click(function(){
  	if(isclick == false){
	  	var width = $(".hos-img-hidden").width();
	  	var height = $(".hos-img-hidden").height();
	  	$(this).css({
  			width:width,
  			height:height,
  		})
	  	isclick = true;
  	}else{
  		$(this).css({
  			width:"100px",
  			height:"33px",
  		})
  		isclick = false;
  	}
  })
  
  
  
</script>

<#include "commons/footer.ftl" />
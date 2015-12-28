<#include "commons/header.ftl" />

                <form id="file_upload" name="file_upload" action="/home/banner/edit/" method="POST" enctype="multipart/form-data">
				  <input type="hidden" value="${id}" name="bid" id="bid"/>
				  <div class="control-group">
					<label class="control-label" for="lname">上传图片</label>
	                <div class="controls">
	                    <input type="file" id="file_img" name="file_img" value="">
	                    <span class="help-inline"><strong class="text-error">${img!}</strong></span>
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">名称</label>
	                <div class="controls">
	                    <input type="text" id="name" name="name" value="${name}">
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">图片链接</label>
	                <div class="controls">
	                    <select id="b_url" name="is_url" style="float: left; margin-right:10px;">
	                    	<option value="1" <#if is_url>selected</#if> >有链接</option>
	                    	<option value="0" <#if !is_url>selected</#if> >无连接</option>
	                    </select>
	                    <input type="text" <#if !is_url>style="display:none;"</#if> class="input-large" id="burl" name="burl" value="${url!}">
	                	<div style="clear:both;"></div>
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">项目类型</label>
	                <div class="controls">
	                    <select name="type" id="type">
	                    	<option value="0" <#if ((type.id) == 0)>selected</#if> >默认</option>
	                    	<option value="2" <#if ((type.id) == 2)>selected</#if> >产品</option>
	                    	<option value="3" <#if ((type.id) == 3)>selected</#if> >活动</option>
	                    </select>
	                </div>
				  </div>
				  
				  <div class="control-group" id="p_id">
					<label class="control-label" for="lname">产品ID</label>
	                <div class="controls">
	                    <input type="text" id="pid" name="pid" value="${pid}">
	                </div>
				  </div>
				  
                    <div class="modal-footer">
                        <input type="submit" id="upload" class="btn btn-primary" onclick="return docheck();" value="确认"/>
                    </div>

                </form>

<script type="text/javascript">
function docheck(){

	var is_new = $("#bid").val();
	if(is_new == '' || parseInt(is_new) < 1){
		if($("#file_img").val()==''){
	        	alert("产品图片不能为空");
	        	return false;
	    }
	}
	var n = $("#name").val();
	if(n == ''){
		alert('名称不能为空！！');
		return false;
	}
    var isT = $("#b_url").val();
    if(parseInt(isT)==1){
    	if($("#burl").val() == ''){
    		alert("链接不能为空");
    		return false;
    	}
    	if($("#burl").val().length < 5){
    		alert("链接长度大于5");
    		return false;
    	}
    }
    return true;
}

//  		var t = $('#type').val();
//  		if(parseInt(t) == 1){
//  			$("#p_id").css("display","block");
//  		}else{
//  			$("#p_id").css("display","none");
//  		}
  		
  $(document).ready(function(){
  	
  	$("#b_url").change(function(){
  		var v = $('#b_url').val();
  		if(parseInt(v)==1){
  			$("#burl").css("display","block");
  		}else{
  			$("#burl").css("display","none");
  		}
  	});
  	
//  	$("#type").change(function(){
//  		  		
//  		var t = $('#type').val();
//  		if(parseInt(t) == 1){
//  			$("#p_id").css("display","block");
//  		}else{
//  			$("#p_id").css("display","none");
//  		}
//  	});
  	
  	
  	
  });
</script>
<#include "commons/header.ftl" />

                <form id="file_upload" name="file_upload" action="/classes/edit/" method="POST" enctype="multipart/form-data">
				  <input type="hidden" value="${id}" name="id" id="id"/>
				  <input type="file" name="file_img" style="display: none;"/>
				  <input type="hidden" value="2" name="t" id="t"/>
				  <div class="control-group">
					<label class="control-label" for="lname">名称</label>
	                <div class="controls">
	                    <input type="text" id="name" name="name" value="${name}">
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">选择部位</label>
	                <div class="controls">
	                    <select id="root_id" name="root_id">
	                    	<#list roots as root>
	                    	<option value="${root.id}" <#if (root_id) == (root.id)>selected</#if> >${root.name}</option>
	                    	</#list>
	                    </select>
	                	<div style="clear:both;"></div>
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
  	
  	$(":file").css('display','none');
  	
  });
</script>
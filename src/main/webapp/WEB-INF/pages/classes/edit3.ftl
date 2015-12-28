<#include "commons/header.ftl" />

                <form id="file_upload" name="file_upload" action="/classes/edit/" method="POST">
				  <input type="hidden" value="${id}" name="id" id="id"/>
				  <input type="hidden" value="3" name="t" id="t"/>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">名称</label>
	                <div class="controls">
	                    <input type="text" id="name" name="name" value="${name}">
	                </div>
				  </div>
				  
				  <div class="control-group">
					<label class="control-label" for="lname">排序</label>
	                <div class="controls">
	                    <input type="text" id="index" name="index" value="${index}">
	                </div>
				  </div>

                    <div class="modal-footer">
                        <input type="submit" id="upload" class="btn btn-primary" onclick="return docheck();" value="确认"/>
                    </div>

                </form>

<script type="text/javascript">
function docheck(){

	var n = $("#name").val();
	if(n == ''){
		alert('名称不能为空！！');
		return false;
	}
	
	if($.isNumeric($.trim($("#index").val())) == false){
            alert("排序必须为数字");
            return false;
    }
    return true;
}
</script>
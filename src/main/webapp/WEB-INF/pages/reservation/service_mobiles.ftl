<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/plugin/bootstrap-typeahead.js?20130407']>
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<style type="text/css">
  li.tag{
    padding-right: 20px !important;
    line-height: 28px;
  }
  .form-search{padding: 20px 40px;}
</style>

<div class="container">

  <form class="well form-search" action="">
    <fieldset>
      <legend>A计划预约后，发送短信情况配置</legend>
      <button type="button" id="add-image" class="btn">添加新的短信接收人</button>

      <ol class="connectedSortable tags" id="mobiles">
        <#list mobiles as mobileinfo>
        <li class="tag ui-state-default">
        	<div class="input-prepend" class="img">
	          	<span class="add-on">英文名</span>
            	<input name="ename" type="text" class="input-xxlarge" value="${mobileinfo.ename}">
            </div>
            <div class="input-prepend" class="img">
	          	<span class="add-on">手机号</span>
            	<input name="mobile" type="text" class="input-xxlarge" value="${mobileinfo.mobile}">
            </div>
            <br />

            <div class="input-prepend" class="img">
                <span class="add-on">短信内容包含联系方式:</span>
                <select name="privilege" >
                    ${mobileinfo.privilege}
                    <option value="true" <#if (mobileinfo.privilege!'true')=='true'>selected</#if>>包含</option>
                    <option value="false"  <#if (mobileinfo.privilege!'true')=='false'>selected</#if>>不包含</option>
                </select>

            </div>
            <a class="close block" data-dismiss="alert">×</a>
        </li>
        </#list>  
      </ol>

      <div class="form-actions">
        <button type="button" id="save" class="btn btn-primary">保存</button>
        <span id="warning" class="text-warning" style="margin-left:20px;"></span>
      </div>    
    </fieldset>
  </form>
</div>

<script type="text/javascript">

  $(function(){

    $("#roll").sortable({
      connectWith: "#roll",
      axis: "y",
      cursor: "move"
    });

    $("#add-image").click(addmoble);
    
    function addmoble(){
      var item = '<li class="tag ui-state-default" ">'
        	+'<div class="input-prepend" class="img">'
	          	+'<span class="add-on">英文名</span>'
            	+'<input name="ename" type="text" class="input-xxlarge" value="">'
            +'</div>'
            +'<div class="input-prepend" class="img">'
	          	+'<span class="add-on">手机号</span>'
            	+'<input name="mobile" type="text" class="input-xxlarge" value="">'
            +'</div>'
            +'<a class="close block" data-dismiss="alert">×</a>'
        +'</li>';
      $("#mobiles").append(item);
    }

    $('#save').click(function(){
    	value = new Array();
        isvaild = true
        $('#mobiles li').each(function(i,n){
			mobileinfo = {}
        	mobileinfo.ename = $(n).find("input[name=ename]")[0].value
        	mobileinfo.mobile = $(n).find("input[name=mobile]")[0].value
        	mobileinfo.privilege = $(n).find("select[name=privilege]")[0].value

        	var mobileregex=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;

			if(mobileregex.test(mobileinfo.mobile) == false)
			{
			    alert("请正确填写手机号码!");
			    isvaild = false
			    return;
			}
        	
        	value.push(mobileinfo);
        });
        
        if(isvaild == false){
        	return;
        }
        
        var data = {
          name:"reservation-mobiles",
          value:JSON.stringify(value),
          _method:"PUT"
        }
        $.post('/prop/store/',data,function(data){
          if('true' == data){
            alert("保存成功");
            window.location.href="";
          }else{
            alert("保存失败");
          }
        });
    });

  });

</script>

<#include "commons/footer.ftl" />
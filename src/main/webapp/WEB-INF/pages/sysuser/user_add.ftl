<#assign _title="创建用户" />
<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />

<div class="container" >
  <h2>创建用户</h2>
    <#if error??><div class="well text-important">${error}</div></#if>
    <div class="row-fluid">

        <form method="POST">
          <div class="control-group">
            <div>
              <div class="input-append">
                <input class="input-large" id="username" type="text" placeholder="用户名" name="username">
                <span class="add-on">用户名(2-20个常规字符)</span>
              </div>
              <span class="help-inline text-important" id="unamehelp"></span>
            </div>
            <div>
              <div class="input-append">
                <input class="input-large" id="realname" type="text" placeholder="真实姓名" name="realname">
                <span class="add-on">真实姓名(2-20个常规字符)</span>
              </div>
              <span class="help-inline text-important" id="rnamehelp"></span>
            </div>
            <div>
              <div class="input-append">
                <input class="input-large" id="password" type="password" placeholder="用户的密码" name="password">
                <span class="add-on">用户的密码</span>
              </div>
            </div>
            <div>
              <div class="input-append">
                <input class="input-large" id="mobile" type="text" placeholder="手机号码" name="mobile">
                <span class="add-on">真实手机号码</span>
              </div>
              <span class="help-inline text-important" id="mobilehelp"></span>
            </div>
            <div>
              <div class="input-append">
		          <select id='role' name="role">
		          		<#list roles as r>
                        	<option value="${r.id}">${r.rolename}</option>
                        </#list>
        		  </select>
	              <span class="add-on">用户角色</span>
              </div>
              <span class="help-inline text-important" id="rolehelp"></span>
            </div>
            
            <div class=" hospitals">
              <div class="input-append">
		          <select id='hospital' name="hospital">
		          		<#list hospitals as h>
                        	<option value="${h.id}">${h.name}</option>
                        </#list>
        		  </select>
	              <span class="add-on">医院</span>
              </div>
              <span class="help-inline text-important" id="rolehelp"></span>
            </div>
            
            <div>
              <div class="input-append">
		          <select id='status' name="status">
		          		<#list statuses as s>
                        	<option value="${s.getValue()}">${s.getName()}</option>
                        </#list>
        		  </select>
	              <span class="add-on">用户状态</span>
              </div>
            </div>
            <div>
	            <button type="submit" class="btn btn-primary" onclick="return docheck();">创建用户</button>
            </div>
          </div>
        </form>

      </div>
      <ul>
        <li>创建用户未激活，无标识，可以通过<a href="/search/user/">用户高级查询</a>进行操作(激活用户、修改邮箱、修改电话、修改简介等）</li>
      </ul>
</div>

<script type="text/javascript">
  
  function docheck() {
    if($.trim($("#role").val()) == 0){
        $("#rolehelp").text('公共仅用于权限限制，不能使用公共角色创建用户！');
        return false;
    }
    if($.trim($("#username").val()).length < 1){
	    $("#unamehelp").text('用户名不能为空');
        return false;
    }
    if($.trim($("#realname").val()).length < 1){
	    $("#rnamehelp").text('真实名字不能为空');
        return false;
    }
    if($.trim($("#password").val()).length < 1){
	    alert('密码不能为空');
        return false;
    }
    if($.trim($("#mobile").val()).length < 1){
	    $("#mobilehelp").text('手机号码不能为空');
        return false;
    }
    return true;
  }
  
  $(document).ready(function(){
  	$("#role").change(function(){
  		var role = $('#role option:selected').text();
  		if(role.indexOf("医院")>=0){
  			$(".hospitals").css("display","block");
  		}else{
  			$(".hospitals").css("display","none");
  		}
  	});
  	$(".hospitals").css("display","none");
  });
</script>
<#include "commons/footer.ftl" />
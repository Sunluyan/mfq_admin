<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container-fluid">
  <div class="page-header">
    <h3>解绑用户
    </h3>
  </div>
  <form id="form" class="well form-horizontal trade-add" method="POST" action="/wechat/unbunduser/">
  	 <span style="font-size:18px;margin:0px 10px;"><i style="vertical-align:middle;"></i>UID：</span>
	  <input id="uid" name="uid" value="${uid}" type="text">
	  
	  <span style="font-size:18px;margin:0px 10px;"><i style="vertical-align:middle;"></i>UUID：</span>
	  <input id="uuid" name="uuid" value="${uuid}" type="text">
    <button class="btn btn-primary" type="sumit">解绑</button>
  </form>

  <#if error>
    <span>后台异常</span>
    <div><pre>${error}</pre></div>
    <#else>
    <span>操作信息</span>
    <div><pre>${msg}</pre></div>
  </#if>

<#include "/commons/footer.ftl" />
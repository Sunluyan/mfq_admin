<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container-fluid">
  <div class="page-header">
    <h3>提示
    </h3>
  </div>
  <form id="form" class="well form-horizontal trade-add" method="POST" action="/wechat/send/invite/">
  	   <#if error>
	    <span>后台异常</span>
	    <div><pre>${error}</pre></div>
	    <#else>
	    <span>操作信息</span>
	    <div><pre>${msg}</pre></div>
	  </#if>
	  <input type="hidden" value="${openid}" name="openid"/>
	  <input type="hidden" value="${mobile}" name="mobile"/>
    <button class="btn btn-primary" type="submit">发送邀请码到用户微信</button>
  </form>



<#include "/commons/footer.ftl" />
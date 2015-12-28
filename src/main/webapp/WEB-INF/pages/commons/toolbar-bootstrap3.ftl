
<#macro show_menu m>
<#t><#if m.divider><li class="divider"></li><#else><li><a <#if m.url?has_content>href="${m.url}"</#if>>${m.name}<#if m.icon?has_content> <i class="${m.icon}"></i></#if></a></li></#if>
</#macro>
<div class="navbar navbar-inverse navbar-default navbar-fixed-top navbar-admin-web">
  <div class="navbar-header">
    <a class="navbar-brand" href="/" style="background: url(/static/img/logo-manage.png) no-repeat;display:block;height:20px;" title="超级系统管理后台"></a>
      <button type="button" class="btn btn-default navbar-btn navbar-btn-responsive" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon icon-bar"></span>
        <span class="icon icon-bar"></span>
        <span class="icon icon-bar"></span>
      </button>
  </div>
    <div class="container">

    <div class="nav-collapse collapse in">

        <#if _menu?has_content>
        <#list _menu.children as menu>
        <#t><ul class="nav navbar-nav">
          <#t><li class="dropdown">
          <#t>  <a class="dropdown-toggle" data-toggle="dropdown">
          <#t>    ${menu.name} <b class="caret"></b>
          <#t>  </a>
          <#t>  <ul class="dropdown-menu" <#if (menu.children[0].children)!?has_content>role="menu"</#if>>
          <#t>    <#list menu.children as submenu>
          <#t>    <#if submenu.children?has_content>
          <#t>    <li class="dropdown-submenu">
          <#t>      <a tabindex="-1">${submenu.name}</a>
          <#t>      <ul class="dropdown-menu"><#list submenu.children as submenu2><@show_menu m=submenu2 /></#list></ul>
          <#t>    </li>
          <#t>    <#else><@show_menu m=submenu /></#if>
          <#t>    </#list>
          <#t>  </ul>
          <#t></li>
        <#t></ul>
        </#list>
        </#if>

        <ul class="nav navbar-nav navbar-right">
          <li id="fat-menu" class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown">
              <i class="icon icon-user icon-white"></i> ${(_userdetail.user.cname)!'游客'} <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
              <#if _userdetail??><li><a href="/my/">个人信息</a></li>
              <li class="divider"></li>
              </#if>
              <li><a href="http://www.shijiebang.com/logout/">退出 <i class="icon icon-off"></i></a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>

<#if _bootstrap3>
<#include "commons/toolbar-bootstrap3.ftl">
<#else>
<#macro show_menu m>
<#if m.pid == 0><li class="divider"></li>
<#else><li><a <#if m.url?has_content>href="${m.url}"</#if>>${m.name}<#if m.icon?has_content> <i class="${m.icon}"></i></#if></a></li>
</#if>
</#macro>
<div class="navbar navbar-inverse navbar-fixed-top navbar-admin-web">
  <div class="navbar-inner">
    <div class="container">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="/" style="background: url(/static/img/logo-manage.png) no-repeat;display:block;height:20px;" title="超级系统管理后台"></a>

      <div class="nav-collapse collapse">

        <#if _menu?has_content>
        <#list _menu.children as menu>
        <ul class="nav">
          <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown">
              ${menu.name} <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" <#if (menu.children[0].children)!?has_content>role="menu"</#if>>
              <#list menu.children as submenu>
              <#if submenu.children?has_content>
              <li class="dropdown-submenu">
                <a tabindex="-1">${submenu.name}</a>
                <ul class="dropdown-menu">
                  <#list submenu.children as submenu2>
                  <@show_menu m=submenu2 />
                  </#list>
                </ul>
              </li>
              <#else>
              <@show_menu m=submenu />
              </#if>
              </#list>
            </ul>

          </li>
        </ul>
        </#list>
        </#if>

        <ul class="nav pull-right">
          <li id="fat-menu" class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown">
              <i class="icon-user icon-white"></i> ${(_userdetail.sysUser.username)!'游客'} <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
              <#if _userdetail??><li><a href="/my/">个人信息</a></li>
              <li class="divider"></li>
              </#if>
              <li><a href="http://c.5imfq.com/logout/">退出 <i class="icon-off"></i></a></li>
            </ul>
          </li>
        </ul>


      </div>

    </div>
  </div>
</div>
</#if>

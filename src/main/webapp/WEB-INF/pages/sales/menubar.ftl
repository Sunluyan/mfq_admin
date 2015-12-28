<#macro show_menu m>
    <#t><#if m.divider><li class="divider"></li><#else><li><a <#if m.url?has_content>href="${m.url}"</#if>>${m.name}<#if m.icon?has_content> <i class="${m.icon}"></i></#if></a></li></#if>
</#macro>
<!-- 销售系统顶部菜单导航 -->
<div class="navbar navbar-inverse navbar-default navbar-fixed-top navbar-admin-web">
    <div class="admin-sales container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/sales/">${sysname}<span style="font-size: 60%; color: gray; ">${sysversion}</span></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li ${(tab1=='remind')?string('class="active"','')}><a href="/sales/remind/clue/search/">我的提醒</a></li>
                <li ${(tab1=='clue')?string('class="active"','')}><a href="/sales/clue/search/">线索管理</a></li>
                <li ${(tab1=='opportunity')?string('class="active"','')}><a href="/sales/work/search/">商机管理</a></li>
                <li ${(tab1=='setting')?string('class="active"','')}><a href="/sales/admin/setting/typevalue/list/">基础设置</a></li>
            </ul>
            <#--
            <#if _menu?has_content>
                <#list _menu.children as menu>
                <#if menu.name?contains("小帮手")>
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
                </#if>
                </#list>
            </#if>
            -->
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
            <ul class="nav navbar-nav">
                <li>
   	                <form class="form-inline " action="/sales/opportunity/searchothers/" method="GET">
                    	<div class="form-group">
                            <label class="sr-only">UID/手机号/用户名</label>	
                    		<input type="text" placeholder="UID/手机号/用户名" class="form-control" name="q" value="${queryCond!''}">
                    	</div><button type="submit" class="btn btn-sm"><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
   	                <form class="form-inline " action="http://i.shijiebang.net/wiki/dosearchsite.action" target="_blank" method="GET">
                    	<div class="form-group">
                            <label class="sr-only">路线/政策/Wiki知识库</label>	
                    		<input type="text" placeholder="路线/政策/Wiki知识库" class="form-control" name="queryString" value="${queryCond!''}">
                            <input type="hidden" name="where" value="knowledge">
                    	</div><button type="submit" class="btn btn-sm"><span class="glyphicon glyphicon-search"></span></button>
                    </form>
                </li>
            </ul>
            <div class="nav navbar-nav navbar-right hidden">
                <span class="label label-primary">窗口标题闪烁:</span>
                <div class="btn-group yunhu-flash-title">
                    <button class="btn btn-xs navbar-btn btn-success btn-on">开</button>
                    <button class="btn btn-xs navbar-btn btn-danger btn-off">关</button>
                </div>
                <span class="label label-primary">接收新线索:</span>
                <div class="btn-group yunhu-call-switch">
                    <button class="btn btn-xs navbar-btn btn-success btn-on" disabled>开</button>
                    <button class="btn btn-xs navbar-btn btn-danger btn-off">关</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/ 销售系统顶部菜单导航 -->

<!-- HTML模板，供JavaScript处理 -->
<div class="sales-html-template hide">
    <div class="mobile-tooltip-loading">
        <span class="text-center"><i class="icon icon-spinner icon-spin"></i> 加载中...</span>
    </div>
    <div id="mobile-tooltip-template">
        <div class="mobile-tooltip-content table-responsive text-center" style="margin: auto auto">
            <p><br>找到<span class="mobile-tooltip-count">count</span>条线索外呼记录(最多显示最近5条)</p>
            <table class="table table-bordered vcenter mobile-tooltip-inner">
                <thead>
                <tr>
                    <th>留电话时间</th>
                    <th>留电话页</th>
                    <th>负责人</th>
                    <th>拨打情况</th>
                </tr>
                </thead>
                <tbody>
                <tr class="mobile-tooltip-record">
                    <td class="mobile-tooltip-create">yy-MM-dd HH:mm</td>
                    <td class="mobile-tooltip-source">source page</td>
                    <td class="mobile-tooltip-sales">sales name</td>
                    <td class="mobile-tooltip-conclusion">conclusion</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!--/ HTML模板，供JavaScript处理 -->


<i class="glyphicon glyphicon-resize-full js-resize" title="Ctrl+F10切换宽版"></i>

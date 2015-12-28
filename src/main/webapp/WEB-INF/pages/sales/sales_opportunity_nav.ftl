<ul class="nav nav-tabs">
    <li role="presentation" ${(tab2=='worklist')?string('class="active"','')}><a href="/sales/work/search/">我的跟单任务</a></li>
    <li role="presentation" ${(tab2=='affairlist')?string('class="active"','')}><a href="/sales/affair/search/">我的商机</a></li>
    <li role="presentation" ${(tab2=='affairorder')?string('class="active"','')}><a href="/sales/affair/listorder/">我的成单</a></li>
	<#--
    <li role="presentation" ${(tab2=='affairworklist')?string('class="active"','')}><a href="/sales/affair/searchwork/">我的跟单工作台</a></li>
        <li role="presentation" ${(tab2=='affairdealt')?string('class="active"','')}><a href="/sales/affair/searchdealt/">已成单事务</a></li>
    -->
	<#if salesUser.team == 3 || user.isAdmin>
    <li role="presentation" ${(tab2=='affairlistall')?string('class="active"','')}><a href="/sales/admin/affair/searchall/">全部商机</a></li>
	</#if>
	<#--
    <li role="presentation" ${(tab2=='remarkothers')?string('class="active"','')}><a href="/sales/opportunity/searchothers/">代他人备注</a></li>
    <li role="presentation" ${(tab2=='oldaffairlist')?string('class="active"','')}><a href="/sales/affair/seaerchold/">未处理完成的CRM旧事务（快处理吧）</a></li>
	-->
</ul>

<#if salesUser.team == 3 || user.isAdmin>
<ul class="nav nav-tabs">
    <li role="presentation" ${(tab2=='cluelist')?string('class="active"','')}><a href="/sales/clue/search/">我的线索</a></li>
    <li role="presentation" ${(tab2=='cluelistall')?string('class="active"','')}><a href="/sales/admin/clue/searchall/">全部线索</a></li>
    <li role="presentation" ${(tab2=='cluetimeoutlist')?string('class="active"','')}><a href="/sales/admin/clue/searchtimeout/">小帮手外呼超时列表</a></li>
<#--<li role="presentation" ${(tab2=='loglist')?string('class="active"','')}><a href="/sales/admin/log/search/">线索负责人变更列表</a></li>-->
</ul>
</#if>
<ul class="nav nav-tabs">
    <li role="presentation" ${(tab2=='clue')?string('class="active"','')}><a href="/sales/remind/clue/search/">线索外呼</a></li>
    <li role="presentation" ${(tab2=='opportunity')?string('class="active"','')}><a href="/sales/remind/opportunity/search/">追单提醒</a></li>
    <#--
    <li role="presentation" ${(tab2=='task')?string('class="active"','')}><a href="/sales/remind/opportunity/searchtask/">商机外呼</a></li>
    -->
    <li role="presentation" ${(tab2=='remindlist' && cond.remindTypeList[0]==1)?string('class="active"','')}><a href="/sales/remind/list/?type=1">新成单提醒</a></li>
    <#--
    <li role="presentation" ${(tab2=='remindlist' && cond.remindTypeList[0]==2)?string('class="active"','')}><a href="/sales/remind/list/?type=2">预定提醒</a></li>
    <li role="presentation" ${(tab2=='remindlist' && cond.remindTypeList[0]==3)?string('class="active"','')}><a href="/sales/remind/list/?type=3">行程提醒</a></li>
	-->
</ul>

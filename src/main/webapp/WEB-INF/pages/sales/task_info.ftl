<#if 'advisor'=source>
<#--p>留电话类型：小帮手</p-->
<table class="table task-info-table">
  <tr>
    <td>
        出行时间：${startDate!''}<br>
    </td> 
    <td>
    	<a class="link" href="/demand/?refId=${advisorId}" target="_blank">需求清单</a>
    </td>
  </tr> 
  <tr>
    <td>  
        目的地：${planBLocations['l_b_' + taskInfo.refId]}
    </td>
    <td>
        出行人数：${person!''}
    </td>
  </tr>
</table>
<#elseif 'h5'=source>
<#--p>留电话类型：H5</p-->
<div class="task-info-box">
    <div class="well">${h5Info}</div>
</div>
<#else>
<#--p>留电话类型：其他</p-->
<div class="task-info-box">
    <div class="well">留电话页：${taskInfo.sourceName!''}</div>
</div>
</#if>
<table class="table">
  <tr>
    <td>
        用户名：${nick!''}<br>
    </td> 
    <td>
        UID：${(taskInfo.uid>0)?string(taskInfo.uid+'', '')}
    </td>
  </tr> 
  <tr>
    <td>  
        手机号：${taskInfo.mobile}<br>
    </td>
    <td>
        留电话时间：${taskInfo.createdAt?string('yyyy-MM-dd HH:mm:ss')}<br>
    </td>
  </tr>
  <tr>
    <td>  
        上次外呼时间：<#if taskInfo.latestTime??>${taskInfo.latestTime?string('yyyy-MM-dd HH:mm:ss')}</#if><br>
    </td>
    <td>
        上次外呼结论：<#if taskInfo.latestConclusion gt 0>${conclusions[taskInfo.latestConclusion+''].name!''}</#if>
		   <#if taskInfo.latestConclusionValue gt 0>-${conclusions[taskInfo.latestConclusionValue+''].name!''}</#if><br>
    </td>
  </tr>

</table>


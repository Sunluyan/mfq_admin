
<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "commons/header.ftl" />
<#assign toolbar="auditor" />
<#include "commons/toolbar.ftl" />

<style type="text/css">
    table	{white-space:normal;word-wrap: break-word;word-break: break-all;table-layout:fixed;}
    .accordion-inner table td { padding:2px;line-height:auto;white-space:nowrap;overflow:hidden;}
    .inline li {line-height:auto;white-space: nowrap;overflow:hidden;}
</style>

<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
            <legend>
                邮件查询
            </legend>
		      <span class="pull-right">
			    <ul class="pager pager-small pager-inline pull-right">
                    <li>
                        <div class="input-append">
                            <form action="/mail/log/">
                                <input placeholder="部分内容" name="content" type="text" class="input-medium"></i></input>
                                <input placeholder="输入邮箱查询" name="to" type="text" class="input-medium"></i></input>
                                <button class="btn" type="submit">Search!</button>
                            </form>
                        </div>
                    </li>
                </ul>
		    </span>


            <table width="100%" class="table table-bordered">
                <tr>
                    <td >邮箱</td>
                    <td>内容</td>
                    <td>发送时间</td>

                </tr>
            <#list logs as log>
         <tr>
             <td>From : ${log.from}
               To: ${log.to}
             </td>
             <td>${log.content}</td>
             <td>${log.createdAt?number_to_datetime}</td>
         </tr>
            </#list>
            </table>
        </div>



<#if logs?has_content>
    <span class="pull-right">
			    <ul class="pager pager-small pager-inline pull-right">
                        <#if num gt 1><li><a href="/mail/log/?num=${num-1}"><i class="icon-backward"></i> 前一页</a></li> </#if>
                        <#if logs?size==50><li><a href="/mail/log/?num=${num+1}"><i class="icon-forward"></i> 后一页</a></li> </#if>

                </ul>
		    </span>
</#if>
    </div>
</div>
<#include "commons/footer.ftl" />


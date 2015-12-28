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
              	短信管理    <small> 【昨日消耗：${smscounts["TOTAL"]}】 </small>
              	<span class="pull-right">
	              	<p>
<#--
					  <small>信道信息：<#list smsconfig?keys as key>【${key}:<strong>${smsconfig["${key}"]}</strong>】</#list></small>
-->
					</p>
				</span>
			  </legend>
		      <span class="pull-right">
			    <ul class="pager pager-small pager-inline pull-right">
					<li>
						<div class="input-append">
							<form action="/smslogs/">
								<input placeholder="输入手机号查询" name="mobile" type="text" class="input-medium"></i></input>
								<button class="btn" type="submit">Search!</button>
							</form>
						</div>
					</li>
			    </ul>
		    </span>
		    
		    <div>
		        <ul class="breadcrumb">
		        <#if sendstatus==0 && sendtype==0>
		            <li class="active">全部</li>
		        <#else>
		            <li class="active"><a href="/smslogs/">全部</a></li>
		        </#if>
		        /
		        <#if sendtype==2>
		            <li class="active">定时发送</li>
		        <#else>
		            <li class="active"><a href="/smslogs/?sendtype=2">定时发送</a></li>
		        </#if>
		        /
		        <#if sendstatus==2>
		            <li class="active">发送失败</li>
		        <#else>
		            <li class="active"><a href="/smslogs/?sendstatus=2">发送失败</a></li>
		        </#if>
		       </ul>
		     </div>
		        <table width="100%" class="table table-bordered">
	              	<tr>
		         		<td width="50px">编号.</td>
		         		<td width="90px">手机号</td>
		         		<td width="70px">发送状态</td>
		         		<td width="60px">类型</td>
		         		<td >短信</td>
		         		<td width="80px">备注</td>
		         		<td width="40px">重发</td>
		         		<td width="60px">发送时间</td>
		         		<td width="60px">创建时间</td>
		         		<td width="60px">定时时间</td>
		         	</tr>
		         	<#list smslogs as smslog>
		         	<tr <#if smslog.sendStatus == "FAILED"> class="error"</#if> <#if smslog.sendStatus == "SUCCESS">class="success"</#if>>
		         		<td>${smslog.sid}</td>
		         		<td>${smslog.mobile}</td>
		         		<td>${smslog.sendStatus} <br/>
                          字数: ${smslog.content?length + 5 }
                        </td>
		         		<td>${smslog.provider}
                          <br/>
                        ${smslog.sendType}
                        </td>
		         		<td alt="${smslog.content}" title="${smslog.content}">
		         			<#if smslog.content?length gt 70 >
		         			 	${smslog.content?substring(0,70)}..
		         			<#else>	
		         				${smslog.content}【世界邦】
		         			</#if>
		         		</td>
		         		<td>${smslog.extra}</td>
		         		<td>${smslog.resendSid}</td>
		         		<td>
		         			<#if smslog.sentAt == 0>
		         				NULL
		         			<#else>
		         				${smslog.sentAt?number_to_datetime?string("yy-MM-dd HH:mm:ss")}
		         			</#if>
		         		</td>
		         		<td>${smslog.createdAt?number_to_datetime?string("yy-MM-dd HH:mm:ss")}</td>
		         		<td>
                          <#if smslog.sendType == 'TIMED'>
                            ${smslog.timedAt?number_to_datetime?string("yy-MM-dd HH:mm:ss")}
                          </#if>
                        </td>
		         	</tr>
		         	</#list>
		         </table>
              </div>
             <#if smslogs?has_content>
             <span class="pull-right">
			    <ul class="pager pager-small pager-inline pull-right">
			    	<#if page gt 1> 
			    		<li><a href="/smslogs/?page=${page - 1}"><i class="icon-backward"></i> 前一页</a></li>
			    	</#if>
			    	<#if smslogs?size == size>
			        	<li><a href="/smslogs/?page=${page + 1}">后一页 <i class="icon-forward"></i></a></li>
			        <#else>
			        	<li><a>后面没有了</a></li>
			        </#if>
			    </ul>
		    </span>
		    </#if>
            <ul>
              <li>昨日详细统计：<#list smscounts?keys as key>【${key}:<strong>${smscounts["${key}"]}</strong>】</#list></li>
           	  <hr>
			  <li>发送状态：FAILED(发送失败) , SUCCESS(供应商服务器返回成功) , PENDING(等待发送)</li>
			  <li>短信类型：NORMAL(普通短信) , RESEND(失败重发) , BATCH(批量短信) , VCODE(验证码短信) , TIMED(定时短信)</li>
			  <hr>
			  <li>提示：发送时间中的NULL 代表短信发送时间未知，仅出现在定时短信中</li>
			</ul>
    </div>
</div>

<#include "commons/footer.ftl" />

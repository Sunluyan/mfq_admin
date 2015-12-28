<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "commons/header.ftl" />
<#assign toolbar="auditor" />
<#include "commons/toolbar.ftl" />



<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">
	     
        <div class="row-fluid">
              <legend>预约管理 </legend>
              <a href="/reservation/mobile/config/">[短信接收人管理]</a><br/><br/>
		      <div>
		      <span class="pull-right">
			    <ul class="pager pager-small pager-inline pull-right">
			        <li><a href="/reservations/?page=${page + 1}">后一页 <i class="icon-forward"></i></a></li>
			    </ul>
		    </span>
		    
		    <div>
		        <ul class="breadcrumb">
		        <#if status==0>
		            <li class="active">全部</li>
		        <#else>
		            <li class="active"><a href="/reservations/?status=0">全部</a></li>
		        </#if>
		        /
		        <#if status==1>
		            <li class="active">等待处理</li>
		        <#else>
		            <li class="active"><a href="/reservations/?status=1">等待处理</a></li>
		        </#if>
		        /
		        <#if status==2>
		            <li class="active">已经处理</li>
		        <#else>
		            <li class="active"><a href="/reservations/?status=2">已经处理</a></li>
		        </#if>
		       </ul>
		     </div>
		        <table width="100%" class="table table-bordered">
	              	<tr>
		         		<td width="250px">报名时间</td>
		         		<td width="100px">用户姓名</td>
		         		<td width="130px">联系方式</td>
		         		<td width="130px">预约类型</td>
		         		<td width="200px">REF</td>
		         		<td width="50px">来源</td>
		         		<td width="100px">状态</td>
		         		<td width="100px">更新人</td>
		         		<td width="400px">备注</td>
		         		<td width="150px">操作</td>
		         	</tr>
		         	<#list reservations as reservation>
		         	<tr>
		         		<td>${reservation.createdAt?number_to_datetime}</td>
		         		<td>${reservation.userName}</td>
		         		<td>${reservation.mobile}</td>
		         		<td>${reservation.type}</td>
		         		<td>${reservation.ref}</td>
		         		<td>${reservation.source}</td>
		         		<td><p <#if reservation.status.value == 1> class="text-error"</#if>>${reservation.status}</p></td>
		         		<td>${reservation.updater}</td>
		         		<td>${reservation.extra}</td>
		         		<td>
		         			<#if reservation.status.value == 1>
		         				<p><a class="reservation-changestatus" username=${reservation.userName} rid=${reservation.rid} status=2>[已回电话]</a></p>
		         			</#if>
		         			<p><a href="#myModal${reservation.rid}" role="button" data-toggle="modal" rid=${reservation.rid} status=2>[添加备注]</a></p>
		         		</td>
						<!-- Modal -->
						<div id="myModal${reservation.rid}" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel${reservation.rid}" aria-hidden="true">
						  <div class="modal-header">
						    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						    <h3 id="myModalLabel${reservation.rid}">备注</h3>
						  </div>
						  <div class="modal-body">
						    <textarea rows=5 cols=140 style="width:95%;" id="reservation-note${reservation.rid}" >${reservation.extra}</textarea>
						  </div>
						  <div class="modal-footer">
						    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
						    <button rid=${reservation.rid} class="reservation-addnote btn btn-primary">保存</button>
						  </div>
						</div>
		         	</tr>
		         	</#list>
		         </table>
              </div>
              
    </div>
</div>
<script type="text/javascript">
    
    $('.reservation-addnote').click(
    	function(event){
    		var target = $(event.target);
	    	rid = target.attr("rid");
	    	content = $('#reservation-note'+rid).val();
	    	addnote(rid,content);
    	}
    );
    
    function addnote(rid,content){
    	$.post(
                "/reservation/" + rid + "/",
                {content:content,op:'note'}
        ).done(function() { 
        	window.location = "/reservations/"
         })
    };
    
    
	$('.reservation-changestatus').click(
    	function(event){
    		var target = $(event.target);
	    	rid = target.attr("rid");
	    	status = target.attr("status");
	    	username = target.attr("username");
    		if(confirm("确定要修改"+ username +"预约状态么？")){
	    		change_status(rid,status);
    		}
    	}
    );
    
    function change_status(rid,status){
    	$.post(
                "/reservation/" + rid + "/",
                {status:status}
        ).done(function() { 
        	window.location = "/reservations/"
         })
    };


    $( "#query-date,#start-query-date,#end-query-date" ).datepicker({
      dateFormat:"yy-mm-dd",
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 2,
    });
</script>
<#include "commons/footer.ftl" />

<#assign _title="美分期管理后台-订单管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<!--
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   -->
<style>
.form-inline{
	line-height:35px;
}

.success td{text-align: center;}
</style>

<div class="container" id="enlarge-body">
    <div class="container">

	<form id="form" class="form-inline well" action="/order/budget/" method="GET">
		<div class="input-group">
			<div class="input-group-addon">医院选择
				<select name="hid">
					<option value="0">全部</option>
					<#list hospitals as hospital>
					<option value="${hospital.id}" <#if 1 == ((status_id))>selected</#if>>${hospital.name}</option>
					</#list>
				</select>
			</div>

            <div class="input-group-addon">产品名称
                <input value="${pname}" name="pname" />
            </div>
	    </div>
		<div class="input-group">
			<div class="input-group-addon">搜索时间范围：
				<div class="input-append date form_datetime" id="date1" name="date1" data-date="${ob}" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="开始时间">
	    			<input class="span2" id="dateStart" name="ob" size="16" type="text" value="${ob}">
	    			<span class="add-on"><i class="icon-th"></i></span>
	    		</div>
				至
				<div class="input-append date form_datetime" id="date2" name="date2" data-date="${oe}" data-date-format="yyyy-mm-dd hh:ii:ss" placeholder="截止时间">
	    			<input class="span2" id="dateEnd" name="oe" size="16" type="text" value="${oe}">
	    			<span class="add-on"><i class="icon-th"></i></span>
	    		</div>
			</div>
		</div>
<#--		<div class="input-group">
			
			<div class="input-group-addon">订单状态：
				<select name="status">
					<option value="100">全部</option>
					<option value="1" <#if 1 == ((status_id))>selected</#if>>支付完成</option>
					<option value="2" <#if 2 == ((status_id))>selected</#if>>订单完成</option>
					<option value="8" <#if 8 == ((status_id))>selected</#if>>退款完成</option>
				</select>
			</div>
			
		</div> -->
		<input type="hidden" id="page" name="page" value="${page}">
        <div class="input-group">
			<td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
		</div>
	</form>

        <div class="row-fluid">

            <div>
                <legend>订单列表</legend>
                
                
                <!------------------------------------------start----------------------------------------------->
                <div class="panel-group" id="accordion">
                
                  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				      
				      	<table class="table table-condensed">
							     <tr class="success">
                        
								    <td style="width:50px;">用户ID</td>
			                        <td style="width:88px;">用户手机号</td>
			                        <td style="width:50px;">姓名</td>
			                        <td style="width:50px;">余额</td>
			                        <td style="width:50px;">订单总额</td>
			                        <td style="width:50px;">充值额</td>
			                        <td style="width:50px;">分期总额</td>
			                        <td style="width:50px;">分期剩额</td>
			                        <td style="width:50px;">订单量</td>
			                        <td style="width:115px;">充值数</td>
			                        <td style="width:100px;">还款数</td>
			                        
								  </tr>
							    </table>
							    
				      </h4>
				    </div>
				  </div>
                  <#list users as user>
                
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-parent="#accordion" style="text-decoration: none;" href="#collapse_${user.uid}">
				          	
				          	    <table class="table table-condensed">
							     <tr class="success">
								    <td style="width:50px;">${user.uid}</td>
			                        <td style="width:100px;">${user.mobile}</td>
			                        <td style="width:50px;">${user.name}</td>
			                        <td style="width:50px;">${user.blance}</td>
			                        <td style="width:50px;">${user.orderMoney}</td>
			                        <td style="width:50px;">${user.recharge}</td>
			                        <td style="width:50px;">${user.quotaAll}</td>
			                        <td style="width:50px;">${user.quotaLeft}</td>
			                        <td style="width:100px;">
			                        	总：${user.mn_count}，时间：${user.mn_count_at}
			                        </td>
			                        <td style="width:100px;">
			                            总：${user.cz_count}，时间：${user.cz_count_at}
			                        </td>
			                        <td style="width:120px;">
			                            总：${user.fl_count}，时间：${user.fl_count_at}
									</td>
								  </tr>
							    </table>
			
				        </a>
				      </h4>
				    </div>
				    <div id="collapse_${user.uid}" class="panel-collapse collapse">
				      <div class="panel-body">
				        
				           
						   	
						<table class="table table-bordered">
						
						<tr>
                    	<td>订单号</td>
                    	<td>订单类型</td>
                    	<td>支付号</td>
                    	<td>支付金额</td>
                    	<td>使用余额</td>
                    	<td>使用增额</td>
                    	<td>优惠券抵用</td>
                    	<td>产品名称</td>
                    	<td>银行Code</td>
                    	<td>支付状态</td>
                    	<td>支付时间</td>
                    	<td>医院名称</td>

                    	</tr>
                    	
						<#list user.orders as order>
                    	<tr>
                    	<td>${order.orderNo}</td>
                    	<td>${order.orderType}</td>
                    	<td>${order.tradeNo}</td>
                    	<td>${order.useAmount}</td>
                    	<td>${order.useBalance}</td>
                    	<td>${order.usePresent}</td>
                    	<td>${order.useCard}</td>
                    	<td>${order.pName}</td>
                    	<td>${order.bankCode}</td>
                    	<td>${order.payStatus}</td>
                    	<td>${order.callbackAt}</td>
                    	<td>${order.hospitalName}</td>

                    	</tr>
                    	 </#list>
                    	</table>
      
						  
				        
				      </div>
				    </div>
				  </div>
				  
				  </#list>
				 

				<!------------------------------------------end----------------------------------------------->
				
				<!--------------------以前代码注释----------------->
				
				<#--
                <table class="table table-bordered">
    
                    <tr>
                        <td>用户ID</td>
                        <td>用户手机号</td>
                        <td>用户姓名</td>
                        <td>用户剩余余额</td>
                        <td>订单总消费</td>
                        <td>充值额度</td>
                        <td>分期总额度</td>
                        <td>分期剩余额度</td>
                        <td>订单量</td>
                        <td>充值次数</td>
                        <td>还款单数</td>
                        <td>操作</td>
                    </tr>
                <#list users as user>
                    <tr>
                        <td>${user.uid}</td>
                        <td>${user.mobile}</td>
                        <td>${user.name}</td>
                        <td>${user.blance}</td>
                        <td>${user.orderMoney}</td>
                        <td>${user.recharge}</td>
                        <td>${user.quotaAll}</td>
                        <td>${user.quotaLeft}</td>
                        <td>
                        	总：${user.mn_count}，时间：${user.mn_count_at}
                        </td>
                        <td>
                            总：${user.cz_count}，时间：${user.cz_count_at}
                        </td>
                        <td>
                            总：${user.fl_count}，时间：${user.fl_count_at}
						</td>
                        <td>详情</td>
                    </tr>
                    
                    <tr>
                    <td>订单：</td>
                    <td colspan="11">
                    	
                    	<table>
                    	
                    	<tr>
                    	<td>订单号</td>
                    	<td>订单类型</td>
                    	<td>支付凭证</td>
                    	<td>支付金额</td>
                    	<td>余额支付</td>
                    	<td>额外</td>
                    	<td>优惠券</td>
                    	<td>产品名称</td>
                    	<td>银行代码</td>
                    	<td>支付状态</td>
                    	<td>支付时间</td>
                    	<td>医院名称</td>
                    	</tr>
                    	<#list user.orders as order>
                    	
                    	<tr>
                    	<td>${order.orderNo}</td>
                    	<td>${order.orderType}</td>
                    	<td>${order.tradeNo}</td>
                    	<td>${order.useAmount}</td>
                    	<td>${order.useBalance}</td>
                    	<td>${order.usePresent}</td>
                    	<td>${order.useCard}</td>
                    	<td>${order.pName}</td>
                    	<td>${order.bankCode}</td>
                    	<td>${order.payStatus}</td>
                    	<td>${order.callbackAt}</td>
                    	<td>${order.hospitalName}</td>
                    	
                 
                    	
                    	</tr>
                    	
                    	</#list>
                    	</table>
                    </td>
                    
                    </tr>

                </#list>
                </table>
                
                -->
            </div>
            <div>
             	<span class="pull-right">
			    	<ul class="pager pager-small pager-inline pull-right">
			    		<#if page gt 1> 
			    			<li><a href="javascript:void(0)" onclick="pageSubmit(${page - 1})"><i class="icon-backward"></i> 前一页</a></li>
			    		</#if>
			    			<li>${page}</li>
			    		
			        		<li><a href="javascript:void(0)" onclick="pageSubmit(${page + 1})">后一页 <i class="icon-forward"></i></a></li>
			        	
			        		<li>总${size}</li>
			        	
			   	 	</ul>
		    	</span>
		    </div>
        </div>

    </div>
</div>

<script>
    function pageSubmit(pageNo){
    	document.getElementById("page").value=pageNo;
		document.getElementById("form").submit();
    }
  $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>

<#include "./commons/footer.ftl" />

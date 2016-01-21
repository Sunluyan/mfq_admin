<#assign _title="美分期管理后台-订单管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />

<style>
.form-inline{
	line-height:35px;
}

</style>

<div class="container" id="enlarge-body">
    <div class="container">

	<form id="orderfrom" class="form-inline well" action="/order/list/" method="GET">
		<div class="input-group">
			<div class="input-group-addon">订&nbsp;&nbsp;单&nbsp;&nbsp;号：<input type="text" class="form-control" name="orderNo" value="${orderNo}" size="20"></div>
	    </div>
		<div class="input-group">
			<div class="input-group-addon">手机号码：<input type="text" class="form-control" name="mobile" value="${mobile}" size="20"></div>
	    </div>
		<div class="input-group">
			<div class="input-group-addon">安&nbsp;&nbsp;全&nbsp;&nbsp;码：<input type="text" class="form-control" name="securityCode" value="${securityCode}" size="20"></div>
	    </div>
	    <div class="input-group">    
			<div class="input-group-addon">订单状态：
				<select class="form-control" name="status">
                	<#list statusmap.keySet() as key>
						<option value="${key}" ${(status=key)?string('selected="selected"', '')}>${(statusmap.get(key)?length>0)?string(statusmap.get(key), '请选择')}</option>
            	    </#list>
				</select>
			</div>
        </div>
		<div class="input-group">
			<div class="input-group-addon">生单时间：
				<input class="form-control reset" type="text" name="ob" id="createdBegin" data-date-format="yyyy-mm-dd hh:ii:ss" value="${ob}" placeholder="开始时间" size="20">
				至
				<input class="form-control reset" type="text" name="oe" id="createdEnd" data-date-format="yyyy-mm-dd hh:ii:ss" value="${oe}" placeholder="截止时间" size="20">
			</div>
		</div>
		<input type="hidden" id="page" name="page" value="${page}">
        <div class="input-group">
			<td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
		</div>
	</form>

        <div class="row-fluid">

            <div>
                <legend>订单列表</legend>

                <table class="table table-bordered">
                    <tr>
                        <td>订单ID</td>
                        <td>订单号</td>
                        <td>产品名称</td>
						<td>医院名称</td>
                        <td>订单总额</td>
                        <td>订单状态</td>
                        <td>在线支付</td>
						<td>生单时间</td>
                        <td>订单类型</td>
                        <td>用户</td>
                        <td>操作</td>
                    </tr>
                <#list orders as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.orderNo}</td>
                        <td>
                        	<#list pmap?keys as key>
                        		<#if (pmap.get(key).id) == (order.pid)>
                        		${pmap.get(key).name}
                        		</#if>
	                        </#list>
                        </td>
                        <td>

							<#list pmap?keys as key>
								<#if (pmap.get(key).id) == (order.pid)>

									<#list hospitals as h>

									<#if (pmap.get(key).hospitalId)== (h.id)>
									    ${h.name}
									</#if>
									</#list>
								</#if>
							</#list>

                        </td>
                        <td>${order.price}</td>
                        <td>
                        	<#list statusmap? keys as key>
	                        	<#if key == order.status>
                        		${statusmap.get(key)!}
                        		</#if>
                        	</#list>
                        </td>
                        <td>${order.onlinePay}</td>
						<td>
						${order.createdAt?string("yyyy-MM-dd HH:mm:ss")}
						</td>
                        <td>
                        	<#if order.payType == 1>
								在线＋到院
							</#if>
							<#if order.payType == 2>
								分期付款
							</#if>
							<#if order.payType == 0>
								全额付款
							</#if>
                        </td>

                        <td>
                        	<a href="/user/list/detail/?uid=${order.uid}" target="_blank">
                        	<#list umap?keys as key>
                        		<#if key == order.uid>
                        		${umap.get(key)!}
                        		</#if>
	                        </#list>
	                        </a>
                        </td>
                        <td><a href="/order/view/?id=${order.id}" target="_blank">查看订单</a></td>
                    </tr>

                </#list>
                </table>
            </div>
            <div>
             	<span class="pull-right">
			    	<ul class="pager pager-small pager-inline pull-right">
			    		<#if page gt 1> 
			    			<li><a href="javascript:void(0)" onclick="pageSubmit(${page - 1})"><i class="icon-backward"></i> 前一页</a></li>
			    		</#if>
			    			<li>${page}</li>
			    		<#if page lt size>
			        		<li><a href="javascript:void(0)" onclick="pageSubmit(${page + 1})">后一页 <i class="icon-forward"></i></a></li>
			        	<#else>
			        		<li><a>后面没有了</a></li>
			        	</#if>
			   	 	</ul>
		    	</span>
		    </div>
        </div>

    </div>
</div>

<script>
    function pageSubmit(pageNo){
    	document.getElementById("page").value=pageNo;
		document.getElementById("orderfrom").submit();
    }
</script>

<#include "/commons/footer.ftl" />
<#assign _title="美分期管理后台-订单管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<style>
.form-inline{
	line-height:35px;
}
</style>

<div class="container" id="enlarge-body">
    <div class="container">

	<form id="form" class="form-inline well" action="/order/finance/" method="GET">
		<div class="input-group">
			<div class="input-group-addon">医&nbsp;&nbsp;院&nbsp;&nbsp;名：<input type="text" class="form-control" name="hospitalName" value="${hospitalName}" size="20"></div>
			<div class="input-group-addon">订&nbsp;&nbsp;单&nbsp;&nbsp;号：<input type="text" class="form-control" name="orderNo" value="${orderNo}" size="20"></div>
			<div class="input-group-addon">手机号码：<input type="text" class="form-control" name="mobile" value="${mobile}" size="20"></div>
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
		<div class="input-group">
			<div class="input-group-addon">支付类型：
				<select name="payApi">
					<option value=""></option>
					<#list payApis as api>
					<option value="${api.code}" <#if ((api.code))== ((pay_api))>selected</#if> >${api.pay}</option>
					</#list>
				</select>
			</div>
			<div class="input-group-addon">支付方式：
				<select name="payType">
					<option value=""></option>
					<#list pays as pay>
					<option value="${pay.id}" <#if ((pay.id))== ((pay_type))>selected</#if>>${pay}</option>
					</#list>
				</select>
			</div>
			
			<div class="input-group-addon">订单状态：
				<select name="status">
					<option value="100">全部</option>
					<option value="1" <#if 1 == ((status_id))>selected</#if>>支付完成</option>
					<option value="2" <#if 2 == ((status_id))>selected</#if>>订单完成</option>
					<option value="8" <#if 8 == ((status_id))>selected</#if>>退款完成</option>
				</select>
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
                        <td>用户ID</td>
                        <td>用户手机号</td>
                        <td>用户姓名</td>
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
		document.getElementById("form").submit();
    }
  $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script>

<#include "./commons/footer.ftl" />

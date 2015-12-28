<#assign _title="美分期管理后台-医院管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />

<style>
.form-inline{
	line-height:35px;
}
.area-choice{
	width:100px;		
}
</style>

<div class="container" id="enlarge-body">
    <div class="container">

	<form class="form-inline well" action="/hospital/list/" method="POST">
		<div class="input-group">
			<div class="input-group-addon">医院名称：<input type="text" class="form-control" name="hosname" value="${hosname}" size="20">
				<select class="area-choice" name="cityid" class="form-control">
					<option value="0" selected="selected">不限</option>
					<#list citys as city>
						<option value="${city.id}">${city.name}</option>
					</#list>
				</select>
			</div>
	    </div>
        <div class="input-group">
			<td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
		</div>
	</form>

        <div class="row-fluid">

            <div>
                <legend>订单列表</legend>

                <table class="table table-bordered">
                    <tr>
                        <td>ID</td>
                        <td>图片</td>
                        <td>医院名称</td>
                        <td>地址</td>
                        <td>所属地区</td>
                        <td>医院网址</td>
                        <td>最后更新</td>
                        <td>操作</td>
                    </tr>
                <#list hospitals as hospital>
                    <tr>
                        <td>${hospital.id}</td>
                        <td><img src="${hospital.img}" width="60" height="20" /></td>
                        <td>${hospital.name}</td>
                        <td>${hospital.address}</td>
                        <td>${hospital.cityName}</td>
                        <td><a href='http://i.5imfq.com/hospital/${hospital.id}'>预览</a></td>
                        <td>${hospital.updatedAt?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>
                        	<a href="/hospital/info/?id=${hospital.id}" target="_blank">查看</a>
                        	<a href="/hospital/edit/?id=${hospital.id}" target="_self">编辑</a>
                        </td>
                    </tr>

                </#list>
                </table>
            </div>
            
            <div>
             	<span class="pull-right">
			    	<ul class="pager pager-small pager-inline pull-right">
			    		<#if page gt 1> 
			    			<li><a href="/hospital/list/?page=${page - 1}"><i class="icon-backward"></i> 前一页</a></li>
			    		</#if>
			    			<li>${page}</li>
			    		<#if page lt size>
			        		<li><a href="/hospital/list/?page=${page + 1}">后一页 <i class="icon-forward"></i></a></li>
			        	<#else>
			        		<li><a>后面没有了</a></li>
			        	</#if>
			   	 	</ul>
		    	</span>
		    </div>
        </div>

    </div>
</div>
<#include "/commons/footer.ftl" />
<#assign _title="美分期管理后台-管理权限列表">
<#include "commons/header.ftl" />

<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container">

	<form class="form-inline well" name="permissionform" action="/sysuser/permission/" method="GET">
	    <div class="input-group">
			<div class="input-group-addon">链接类别：
				<select class="form-control" name="type">
					<option value="0" ${(type=0)?string('selected="selected"', '')}>所有</option>
                	<option value="1" ${(type=1)?string('selected="selected"', '')}>菜单</option>
                	<option value="2" ${(type=2)?string('selected="selected"', '')}>其它</option>
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
	        	<ul class="pager" style="float:left;">
    	    	  <li>
    	    	  	管理列表
        		  </li>
        		</ul>
                <ul class="pager" style="float:right;">
	  				<#if page &gt; 1>
		        	<li>
    	        		<a href="/sysuser/permission/?page=${page-1}">前一页</a>
					</li>
					<li><a href="/sysuser/permission/?page=${page+1}">后一页</a></li>
					</#if>
					<li><span>总数:${itemcount}</span></li>
						<a href="/sysuser/permission_add/">增加权限</a>
				</ul>
	        </div>
    	    <div>

                <table class="table table-bordered">

                    <tr>
                        <td>ID</td>
                        <td>URL</td>
                        <td>Name</td>
                        <td>Type</td>
                        <td>PID</td>
                        <td>排序</td>
                        <td>权限</td>
                        <td>操作</td>
                    </tr>
                <#list acls as acl>
                    <tr>
                        <td>${acl.id}</td>
                        <td>${acl.url}</td>
                        <td>${acl.name}</td>
                        <td>
                        <#if acl.type == 1>
                        	菜单
                        <#else>
                        	其它
                        </#if>
                        </td>
                        <td>${acl.pid}</td>
                        <td>${acl.index}</td>
                        <td>
                        	<#list permissions.keySet() as key>
	                        	<#if key == acl.id>
                        		${permissions.get(key)!}
                        		</#if>
                        	</#list>
		            	</td>
                        <td><a href="javascript:void(0)" onclick="pmsDel('${acl.id}','${acl.name}')">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/sysuser/permission_edit/?id=${acl.id}" target="_blank">Edit</a></td>
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
    function pmsDel(id, name){
        if(!confirm("确定要删除用户名为"+name+"的用户吗？")){
            return;
        }
        window.open("/sysuser/permission_delete/?id="+id);
    }
    
    function pageSubmit(pageNo){
    	document.getElementById("page").value=pageNo;
		document.permissionform.submit();
    }
</script>
<#include "/commons/footer.ftl" />
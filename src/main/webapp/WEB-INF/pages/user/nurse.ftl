<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<link rel="stylesheet" href="/static/css/user/peihu.css">
<script src="/static/js/user/peihu.js"></script>
	<div class="container">
		<h1 class="page-header">
			陪护人员列表
		</h1>
		<button class="add btn btn-success">新增</button>
		<table class="table table-bordered table-striped">
			<tr>
				<td>姓名</td>
				<td>性别</td>
				<td>电话</td>
				<td>工号</td>
				<td>身份证号</td>
				<td>现住址</td>
				<td>操作</td>
			</tr>
			<#list nurses as nurse>
				<tr>
					<td>${nurse.name}</td>
					<td><#if nurse.gender==0>未设置</#if><#if nurse.gender==1>男</#if><#if nurse.gender==2>女</#if></td>
					<td>${nurse.phone}</td>
					<td class="nurseNumber">${nurse.nurseNumber}</td>
					<td>${nurse.idCard}</td>
					<td>${nurse.address}</td>
					<td><a href="/user/nurse/edit/?id=${nurse.nurseNumber}" target="_blank">修改</a> / <a href="#" class="remove">删除</a></td>
				</tr>
			</#list>
			
		</table>
	</div>
	<div class="add-page">

					<span>姓  名 : </span>

					<input type="text" class="name"> <i>*</i>

					<span>性  别 : </span>

					<select class="sex">
						<option value="1">男</option>
						<option value="2">女</option>
					</select> <i>*</i>
					<br>
					<span>电  话 : </span>

					<input type="text" class="phone"> <i>*</i>

					<span>身份证号</span>

					<input type="text" class="code"> <i>*</i>

					<span>现住址</span>

					<input type="text" class="position"> <i>*</i>

					<button class="tijiao btn btn-primary">提交</button>

					<button class="quxiao btn btn-primary">取消</button>
		
		</div>
</body>
<script type="text/javascript" src="/static/js/user/jquery.dragable.js"></script>
<script type="text/javascript">
	$(".add-page").dragable();
</script>
<#include "commons/footer.ftl" />

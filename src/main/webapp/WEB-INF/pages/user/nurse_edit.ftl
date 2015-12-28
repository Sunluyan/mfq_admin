<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<link rel="stylesheet" href="/static/css/user/peihu-xiugai.css">
<script src="/static/js/user/peihu-xiugai.js"></script>
	<div class="container">
		<h3 class="page-header">
			基本信息修改
		</h3>
		<form action="#" class="form">
			<table class="table table-bordered table-striped">
			<tr>
				<td>姓名</td>
				<td>
					<input type="text" value="${nurse.name}" disabled> <span>*</span>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<input type="text" value="<#if nurse.gender==0>未设置</#if><#if nurse.gender==1>男</#if><#if nurse.gender==2>女</#if>" disabled>
				</td>
			</tr>
			<tr>
				<td>电话</td>
				<td>
					<input type="text" name="phone" value="${nurse.phone}">
				</td>
			</tr>
			<tr>
				<td>工号</td>
				<td>
					<input type="text" id="id" value="${nurse.nurseNumber}" disabled>
				</td>
			</tr>

			<tr>
				<td>身份证号</td>
				<td>
					<input type="text" name="idCard" value="${nurse.idCard}">
				</td>
			</tr>
			<tr>
				<td>现住址</td>
				<td>
					<input type="text" name="address" value="${nurse.address}">
				</td>
			</tr>
		</table>
		</form>
		<div>
			<button class="tijiao btn btn-primary">提交</button>
			<button class="quxiao btn btn-primary">取消</button>
		</div>
		<span>
			（修改成功后页面会自动关闭）
		</span>
	</div>
	<script type="text/javascript">
	$(".tijiao").click(function  () {
		var data = $(".form").serialize() ;
		$.post("/ajax?method=updateNurse&id="+$("#id").val()+"&"+data).done(function  (data) {
			if(data==1 || data == "1"){
				window.close()
			}else{
				alert("出现错误！请刷新重试")
			}
		})
	})
	$(".quxiao").click(function  () {
		window.close();
	})
	</script>

<#include "commons/footer.ftl" />

<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" /><html lang="en">
	<style>
		
		*{
			padding:0;
			margin:0;
			list-style:none;
		}
		
		/* .nav li:hover{
			background:lightblue;
		} */
		.now{
			height:50px;
			line-height:50px;
		}
		table{
			display:none;
		}
		table.current{
			display:table;
			line-height: 30px;
		}
		.box{
			height:;

		}
		.tongzhi{
			width:40%;
			height:40%;
			border-radius:10px;
			background:#f5f5f5;
			position:fixed;
			left:50%;
			top:50%;
			margin-left:-20%;
			margin-top:-10%;
			text-align:center;
			padding-top:5%;
			border:1px solid #ccc;
			display:none;
		}
		.tongzhi h3{
			margin-bottom:5%;
		}
		.tongzhi button.sure{
			margin-right:5%;
		}
		.container table .td01{
			width: 150px;
			font-weight: bold;
			text-align: right;
			padding-left: 40px;
		}
		.container table .td02{
			padding-left:50px;
			width: 1000px;
		}
		.container .button01{
			display: table;
			margin: 0 auto;
		}
		.container table .td03{
			width: 400px;
			font-weight: bold;
			text-align: center;
		}
		.container table .td04{
			width: 400px;
			text-align: center;
		}
	</style>
	
	
	
	<div class="container">
		<h3 class="page-header text-left">
			基本信息
		</h3>
				<table class="current table-striped">
					<tr>
						<td class="td01">姓名：</td>
						<td class="td02">${quota.realname}</td>
					</tr>
					<tr>
						<td class="td01">手机号：</td>
						<td class="td02"><#if user.mobile != null >${user.mobile}</#if>
						<#if user.mobile == null >${quota.contact}</#if>
						</td>
					</tr>
					<tr>
						<td class="td01">身份证号码：</td>
						<td class="td02">${quota.idCard}</td>
					</tr>
					<tr>
						<td class="td01">分期总额：</td>
						<td class="td02"><#if orderinfo.periodPay != 0 >${orderinfo.periodPay}</#if></td>

					</tr>
					<tr>
						<td class="td01">还款期数：</td>
						<td class="td02">${orderinfo.period}期</td>
					</tr>
					<tr>
						<td class="td01">每期还款额：</td>
						<td class="td02">${finance.newBalance}元</td>
					</tr>
					<tr>
						<td class="td01">申请日期：</td>
						<td class="td02">${finance.billAt?string('yyyy-MM-dd HH:mm:ss')}</td>
					</tr>
					<tr>
						<td class="td01">剩余日期：</td>
						<td class="td02">${finance.allPeriod-finance.curPeriod}月</td>
					</tr>
				</table>
				<script type="text/javascript">
				</script>
				<h3 class="page-header text-left">
				学籍/工作信息
				</h3>
				<table class="current table-striped">
					<tr>
						<td class="td01">用户类型：</td>
						<td class="td02"><#if quota.userType==1>学生党</#if><#if quota.userType==2>上班族</#if></td>
					</tr>
					<tr>
						<td class="td01">学校：</td>
						<td class="td02">${quota.school}</td>
					</tr>
					<tr>
						<td class="td01">入学时间：</td>
						<td class="td02">${quota.startschoolAt?string('yyyy-MM-dd HH:mm:ss')}</td>
					</tr>
					<tr>
						<td class="td01">备注：</td>
						<td class="td02"><#if quota.schoolRemark==null>无</#if><#if quota.schoolRemark!=null>${quota.schoolRemark}</#if></td>
					</tr>
				</table>

				<h3 class="page-header text-left">
				还款记录
				</h3>
				<table class="current table-striped">
				<tr>
						<td class="td03">还款日期</td>
						<td class="td03">还款额度</td>
						<td class="td03">支付方式</td>
						<td class="td03">还款期数</td>
					</tr>
				<#list payRecords as record>
					
					<tr>
						<td class="td04">${record.payAt?string('yyyy-MM-dd HH:mm:ss')}</td>
						<td class="td04">${record.amount+record.balance+record.present}</td>
						<td class="td04">${record.tpp}</td>
						<td class="td04">${record.payPeriod}</td>
					</tr>
				</#list>
					
					
					<!-- <tr>
						<td class="td04">2015.11.12</td>
						<td class="td04">1000</td>
						<td class="td04">微信支付</td>
						<td class="td04">3</td>
					</tr>
					<tr>
						<td class="td04">2015.10.12</td>
						<td class="td04">1000</td>
						<td class="td04">微信支付</td>
						<td class="td04">2</td>
					</tr>
					<tr>
						<td class="td04">2015.09.12</td>
						<td class="td04">1000</td>
						<td class="td04">微信支付</td>
						<td class="td04">1</td>
					</tr> -->
				</table>
				<br />
				<br />
	</div>
<#include "commons/footer.ftl" />
	
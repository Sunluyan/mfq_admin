<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<style>

    .dropdown-toggle {
        font-size: 14px;
    }

    .popover-title {
        margin-top: 40px;
    }

    .past {
        border-bottom: 1px dashed #ccc;
    }

    .past:last {
        border: none;
    }

    .past-content {
        color: #333;
        font-size: 14px;
        float: left;
    }

    .past-time, .past-author {
        color: #aaa;
        float: right;
        font-size: 13px;
    }
</style>

<link rel="stylesheet" href="/static/css/user/mianqian-information.css">
<script src="/static/js/user/mianqian-information.js"></script>

<div class="container">
    <h1 class="page-header text-left">面签用户详情</h1>
    <div class="jc-information span4">
        <table class="table table-bordered  table-condensed table table-striped">
            <tr>
                <td class="text-right" width="100">用户ID</td>
                <td class="text-left uid">${user.uid}</td>
            </tr>
            <tr>
                <td class="text-right">用户姓名</td>
                <td class="text-left">${user.realname}</td>
            </tr>

            <tr>
                <td class="text-right">用户性别</td>
                <td class="text-left">${user.gender}</td>
            </tr>
            <tr>
                <td class="text-right">出生日期</td>
                <td class="text-left">${user.birthday}</td>
            </tr>
            <tr>
                <td class="text-right">联系电话</td>
                <td class="text-left">${user.contact}</td>
            </tr>
            <tr>
                <td class="text-right">身份证号</td>
                <td class="text-left">${user.id_card}</td>
            </tr>
            <tr>
                <td class="text-right">籍贯</td>
                <td class="text-left">${user.origin}</td>
            </tr>
            <tr>
                <td class="text-right">现住址</td>
                <td class="text-left">${user.location}</td>
            </tr>
            <tr>
                <td class="text-right">用户类别</td>
                <td class="text-left">
                <#if user.user_type == 0>
                    不知道
                </#if>
                <#if user.user_type == 1>
                    学生党
                </#if>
                <#if user.user_type == 2>
                    上班族
                </#if>
                </td>
            </tr>
        </table>
    </div>
    <div class="jc-information span7">

        <h3 class="past" style="overflow:hidden;">
            <span class="past-content">asfdasdf</span> <span class="past-author"> by liuzhiugo</span><span
                class="past-time">2015-07-13 12:34:12 </span>
        </h3>
        <h3 class="past" style="overflow:hidden;">
            <span class="past-content">asfdasdf</span> <span class="past-author"> by liuzhiugo</span><span
                class="past-time">2015-07-13 12:34:12 </span>
        </h3>
        <textarea type="text" class="remark-new" style="width:97%;height:80px;"></textarea>
        <div>
            <button class="btn-primary add-remark" style="border-radius:15%;padding:3px 8px;float:right;">添加</button>
        </div>

        <script>
            $(".add-remark").click(function () {
                var newValue = $(".remark-new").val();
                //ajax发送到服务器
                var uid = $(".uid").html();
                var data = $(".remark-new").val();

                $.get("/ajax", {method: "addRemark", uid: uid, data: data}).done(function (data) {
                    insertRemark(data)
                })
            })
            var json = "${user.remark}";
            var index = 1;
            function insertRemark(json) {
                for (var i = 0; i < json.split("****").length - 1; i++) {
                    var p = $(".past").last();
                    var pClone = p.clone();
                    pClone.find(".past-content").html((index++) + "、" + json.split("****")[i].split("&&&&")[0]);
                    pClone.find(".past-time").html("-- " + json.split("****")[i].split("&&&&")[1] + "&nbsp;")
                    pClone.find(".past-author").html("by " + json.split("****")[i].split("&&&&")[2]);
                    pClone.show()
                    p.after(pClone)
                }
            }
            insertRemark(json)
        </script>
        </table>
    </div>
    <div style="clear:both;"></div>
<#if user.user_type == 1>
    <div class="student">
        <h3 class="page-header">
            学生用户学校详细信息
        </h3>
        <table class="table table-bordered table-striped">
            <tr>
                <td class="text-center">学院名称</td>
                <td class="text-center">学院地址</td>
                <td class="text-center">院系</td>
                <td class="text-center">专业</td>
                <td class="text-center">学号</td>
                <td class="text-center">学制</td>
                <td class="text-center">学校所在地</td>
                <td class="text-center">入学时间</td>
            </tr>

            <tr>
                <td class="text-center">${user.school}</td>
                <td class="text-center">${user.school_location}</td>
                <td class="text-center">${user.faculty}</td>
                <td class="text-center">${user.speciality}</td>
                <td class="text-center">${user.student_id}</td>
                <td class="text-center">${user.school_level}</td>
                <td class="text-center">${user.school_location_id}</td>
                <td class="text-center">${user.startschool_at}</td>
            </tr>

        </table>
    </div>

</#if>



<#if user.user_type == 2>
    <div class="worker">
        <h3 class="page-header">
            上班族用户工作信息
        </h3>
        <table class="table table-bordered table-striped">
            <tr>
                <td class="text-center">公司名称</td>

                <td class="text-center">月收入</td>
                <td class="text-center">社保号</td>
                <td class="text-center">工作年限</td>
                <td class="text-center">其他</td>
            </tr>
            <tr>
                <td class="text-center">
                    <input type="text">
                </td>

                <td class="text-center">
                    <select>
                        <option value="1">3500以下</option>
                        <option value="2">3500到5000</option>
                        <option value="3">5000到7000</option>
                        <option value="4">7000到10000</option>
                        <option value="4">10000到15000</option>
                        <option value="4">15000到20000</option>
                        <option value="4">20000到30000</option>
                        <option value="4">30000以上</option>

                    </select>
                </td>
                <td class="text-center">
                    <input type="text">
                </td>
                <td class="text-center">
                    <select>
                        <option value="1">1年以下</option>
                        <option value="2">2年</option>
                        <option value="3">3年</option>
                        <option value="4">4年</option>
                        <option value="4">5年</option>
                        <option value="4">6年</option>
                        <option value="4">7年</option>
                        <option value="4">8年</option>
                        <option value="4">9年</option>
                        <option value="4">10年以上</option>
                    </select>

                </td>
                <td class="text-center">
                    <input type="text">
                </td>
            </tr>
        </table>
    </div>
		<#if user.user_type == 1>
			<div class="student">
				<h3 class="page-header">
					学生用户学校详细信息
				</h3>
				<table class="table table-bordered table-striped">
					<tr>
						<td class="text-center">学院名称</td>
						<td class="text-center">学院地址</td>
						<td class="text-center">院系</td>
						<td class="text-center">专业</td>
						<td class="text-center">学号</td>
						<td class="text-center">学制</td>
						<td class="text-center">学校所在地</td>
						<td class="text-center">入学时间</td>
					</tr>

					<tr>
						<td class="text-center">${user.school}</td>
						<td class="text-center">${user.school_location}</td>
						<td class="text-center">${user.faculty}</td>
						<td class="text-center">${user.speciality}</td>
						<td class="text-center">${user.student_id}</td>
						<td class="text-center">${user.school_level}</td>
						<td class="text-center">${user.school_location_id}</td>
						<td class="text-center">${user.startschool_at}</td>
					</tr>

				</table>
			</div>
		<#elseif user.user_type ==2>
            <div class="worker">
                <h3 class="page-header">
                    上班族用户工作信息
                </h3>
                <table class="table table-bordered table-striped">
                    <tr>
                        <td class="text-center">公司名称</td>
                        <td class="text-center">部门/职位</td>
                        <td class="text-center">月收入</td>
                        <td class="text-center">工作年限</td>
                        <td class="text-center">其他</td>
                    </tr>
                    <tr>
                        <td class="text-center" >
                            <input type="text" value="${user.company}">
                        </td>
                        <td>${user.department}/${user.position}</td>
                        <td class="text-center">
						<#--<select >-->
							<#--<option value="0">${user.salary}</option>-->
						  <#--<option value="1">3500以下</option>  -->
						  <#--<option value="2">3500到5000</option>  -->
						  <#--<option value="3">5000到7000</option>  -->
						  <#--<option value="4">7000到10000</option> -->
						  <#--<option value="4">10000到15000</option>-->
						  <#--<option value="4">15000到20000</option>-->
						  <#--<option value="4">20000到30000</option>-->
						  <#--<option value="4">30000以上</option>-->
						 <#---->
						<#--</select>-->
						${user.salary}
                        </td>
                        <td class="text-center">
						<#--<select >  -->
						  <#--<option value = "1">1年以下</option>  -->
						  <#--<option value = "2">2年</option>  -->
						  <#--<option value = "3">3年</option>  -->
						  <#--<option value = "4">4年</option> -->
						  <#--<option value = "4">5年</option>-->
						  <#--<option value = "4">6年</option>-->
						  <#--<option value = "4">7年</option>-->
						  <#--<option value = "4">8年</option>-->
						  <#--<option value = "4">9年</option>-->
						  <#--<option value = "4">10年以上</option> -->
						<#--</select>-->
						${user.work_years}
                        </td>
                        <td class="text-center">
                            <input type="text" value="${user.work_remark}">
                        </td>
                    </tr>
                </table>
            </div>


		<#else >


            <div class="student">
                <h3 class="page-header">
                    学生用户学校详细信息
                </h3>
                <table class="table table-bordered table-striped">
                    <tr>
                        <td class="text-center">学院名称</td>
                        <td class="text-center">学院地址</td>
                        <td class="text-center">院系</td>
                        <td class="text-center">专业</td>
                        <td class="text-center">学号</td>
                        <td class="text-center">学制</td>
                        <td class="text-center">学校所在地</td>
                        <td class="text-center">入学时间</td>
                    </tr>

                    <tr>
                        <td class="text-center">${user.school}</td>
                        <td class="text-center">${user.school_location}</td>
                        <td class="text-center">${user.faculty}</td>
                        <td class="text-center">${user.speciality}</td>
                        <td class="text-center">${user.student_id}</td>
                        <td class="text-center">${user.school_level}</td>
                        <td class="text-center">${user.school_location_id}</td>
                        <td class="text-center">${user.startschool_at}</td>
                    </tr>

                </table>
            </div>


            <div class="worker">
                <h3 class="page-header">
                    上班族用户工作信息
                </h3>
                <table class="table table-bordered table-striped">
                    <tr>
                        <td class="text-center">公司名称</td>
                        <td class="text-center">部门/职位</td>
                        <td class="text-center">月收入</td>
                        <td class="text-center">工作年限</td>
                        <td class="text-center">其他</td>
                    </tr>
                    <tr>
                        <td class="text-center" >
                            <input type="text" value="${user.company}">
                        </td>
                        <td>${user.department}/${user.position}</td>
                        <td class="text-center">
						<#--<select >-->
							<#--<option value="0">${user.salary}</option>-->
						  <#--<option value="1">3500以下</option>  -->
						  <#--<option value="2">3500到5000</option>  -->
						  <#--<option value="3">5000到7000</option>  -->
						  <#--<option value="4">7000到10000</option> -->
						  <#--<option value="4">10000到15000</option>-->
						  <#--<option value="4">15000到20000</option>-->
						  <#--<option value="4">20000到30000</option>-->
						  <#--<option value="4">30000以上</option>-->
						 <#---->
						<#--</select>-->
						${user.salary}
                        </td>
                        <td class="text-center">
						<#--<select >  -->
						  <#--<option value = "1">1年以下</option>  -->
						  <#--<option value = "2">2年</option>  -->
						  <#--<option value = "3">3年</option>  -->
						  <#--<option value = "4">4年</option> -->
						  <#--<option value = "4">5年</option>-->
						  <#--<option value = "4">6年</option>-->
						  <#--<option value = "4">7年</option>-->
						  <#--<option value = "4">8年</option>-->
						  <#--<option value = "4">9年</option>-->
						  <#--<option value = "4">10年以上</option> -->
						<#--</select>-->
						${user.work_years}
                        </td>
                        <td class="text-center">
                            <input type="text" value="${user.work_remark}">
                        </td>
                    </tr>
                </table>
            </div>


</#if>

</div>
		</#if>


	</div>

<div class="answer container">
    <div class="child agree-1">
        <button class="agree btn  btn-info">
            同意批准
        </button>

    </div>
    <div class="child refuse-1 ">
        <button class="refuse btn btn-info">
            拒绝批准
        </button>

    </div>
</div>
<div class="refuse-information">
    <div>拒绝原因</div>
    <input type="text" class="reason">
    <div>审批人</div>
    <input type="text" value="" class="body" class="text-center">
    <br>
    <input type="submit" class="btn tijiao-2" value="提交">
    <input type="button" class="btn quxiao" value="取消">
</div>

<div class="shenpi-information">
    <div>审批额(元)</div>
    <input type="text" class="reason">
    <div>审批人</div>
    <input type="text" value="" class="body" class="text-center">
    <br>
    <input type="submit" class="btn  tijiao" value="提交">
    <input type="button" class="btn quxiao" value="取消">


</div>


<#include "commons/footer.ftl" />











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

    .group-span-filestyle{
        width:150px;
    }
    .form-control{
        width:160px;
    }
    .upload{
        float:right;
    }
</style>

<link rel="stylesheet" href="/static/css/user/mianqian-information.css">
<script src="/static/js/user/jquery.upload.js"></script>
<script src="/static/js/user/mianqian-information.js"></script>
<script src="/static/js/user/bootstrap-filestyle.min.js"></script>
<script src="/static/js/layer-v2.1/layer/layer.js"></script>

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


            <tr>
                <td class="text-right">用户状态</td>
                <td class="text-left">
                    <#list authStatus as auth>

                        <#if ((auth.id) == (user.auth_status))>
                            ${auth.desc}
                        </#if>
                    </#list>
                </td>
            </tr>
            <#if (user.auth_status) == -3>
                <tr>
                    <td class="text-right">拒绝原因</td>
                    <td class="text-left">
                        <#if user.user_type == 1>
                            ${user.school_remark}
                        <#else >
                            ${user.work_remark}
                        </#if>
                    </td>
                </tr>
            </#if>
            <tr>
                <td class="text-right">用户额度</td>
                <td class="text-left">
                总额:${user.quota_all} 剩余额度:${user.quota_left}
                </td>
            </tr>
        </table>
    </div>
    <div class="span7" style="border:1px solid #ccc;border-radius: 7px; padding:0 10px 10px 10px;">

        <h3 class="past" style="overflow:hidden;display: none;">
            <span class="past-content"></span> <span class="past-author"></span><span
                class="past-time"></span>
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
                    pClone.find(".past-author").html("at " + json.split("****")[i].split("&&&&")[2]);
                    pClone.show()
                    p.after(pClone)
                    pClone.show();
                    $(".remark-new").val("")
                }
            }
            insertRemark(json)
        </script>
        </table>
    </div>

    <div style="clear:both;"></div>
    <h3 class="page-header">
        用户面签资料上传
    </h3>

    <table class="table table-bordered table-striped">
        <tr>
            <td class="text-center">资料名称</td>
            <td class="text-center">预览</td>
            <td class="text-center">备注</td>
            <td class="text-center">上传</td>
            <td class="text-center">操作</td>
        </tr>
        <#list user.interview_info as info>

            <tr class="tr">
                <td class="text-center span1 desc">
                    ${info.desc}
                </td>
                <td class="span2 center">
                    <div>
                        <img src="${info.img}" alt="图片1"  class="img" style="width: 150px;cursor:pointer;">
                    </div>
                </td>
                <td class="span4">
                    <textarea style="width:300px;height:100px;" class="remark">${info.remark}</textarea>
                </td>
                <td class="text-center text-center">

                </td>
                <td>
                    <button href="javascript:void(0)" class="btn-danger delete" data="${info.id}">删除</button>
                </td>
            </tr>
        </#list>

        <tr class="last-tr">
            <td><input type="text" class="input desc"> </td>
            <td>
                <div>
                    <img src="#" alt="图片1"  class="img" style="width: 150px;cursor:pointer;">
                </div>
            </td>
            <td>
                <textarea style="width:300px;height:100px;" class="remark">${info.remark}</textarea>

            </td>
            <td>
                <form class="uploadForm" enctype="multipart/form-data" style="margin:0 auto;" target="frameFile" ">
                <input id="file" type="file" name="file" class="file filestyle" data-buttonBefore="true" data-buttonText="选择文件" style="display: block;"/>
                <button class="btn upload"  data="${info.id}" index="${info_index}">上传</button>
                <img src="/static/img/loading.gif" class="loading" width="20" height="20"
                     style="margin-top: 6px;margin-left: 5px; display:none;">
                </form>
            </td>
            <td><button class="btn-success add-tr" style="float: right;">添加</button></td>
        </tr>
    </table>

    <iframe id='frameFile' name='frameFile' style='display: none;'></iframe>
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
                <td class="text-center">
                    <input type="text" value="${user.company}">
                </td>
                <td>${user.department}/${user.position}</td>
                <td class="text-center">
                ${user.salary}
                </td>
                <td class="text-center">
                ${user.work_years}
                </td>
                <td class="text-center">
                    <input type="text" value="${user.work_remark}">
                </td>
            </tr>
        </table>
    </div>
<#else >
</#if>

</div>

</div>

<#if user.auth_status == 3>

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

</#if>
<form method="post" id="frm-refuse">
<div class="refuse-information">
    <div>拒绝原因</div>
    <input type="text" name="reason" class="reason">
    <input type="hidden" value="2" name="t"/>
    <input type="hidden" value="${user.uid}" name="uid"/>
    <input type="hidden" value="interViewChek" name="method" class="body" class="text-center">
    <br>
    <input type="button" class="btn tijiao-2" value="提交">
    <input type="button" class="btn quxiao" value="取消">
</div>
</form>

<form method="POST" id="frm-shenpi">

<div class="shenpi-information">
    <div>审批额(元)</div>
    <input type="number" name="reason" class="reason">
    <input type="hidden" value="1" name="t"/>
    <input type="hidden" value="${user.uid}" name="uid"/>
    <input type="hidden" value="interViewChek" name="method" class="text-center">
    <br>
    <input type="button" class="btn  tijiao" value="提交">
    <input type="button" class="btn quxiao" value="取消">
</div>
</form>


<#include "commons/footer.ftl" />











<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<style>
    .form-inline {
        line-height: 35px;
    }

    .area-choice {
        width: 100px;
    }

    .form-group {
        display: inline;
    }

    .tab {
        width: 100%;
        height: 40px;
        background: #f5f5f5;
        margin-bottom: 15px;
        border-top-right-radius: 20px;
        border-bottom-right-radius: 20px;
        /*1px solid #e3e3e3*/
        border: 1px solid #e3e3e3;
    }

    .prev, .next {
        width: 40px;
        height: 40px;
        background: #e3e3e3;
        float: right;
        line-height: 40px;
        text-align: center;
        font-size: 16px;
        cursor: pointer;
        color: white;
    }

    .next {
        border-top-right-radius: 50px;
        border-bottom-right-radius: 50px;
    }

    .prev {
        border-top-left-radius: 50px;
        border-bottom-left-radius: 50px;
        border-right: 1px solid #e3e3e3;
    }

    .next:hover, .prev:hover {
        background: #d3d3d3;
    }

    .disabled {
        background: #eee;
    }

    .disabled:hover {
        cursor: auto;
        background: #eee;
    }

    .table-pass, .table-out {
        display: none;
    }


</style>
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<div class="container" id="enlarge-body">
    <div class="container">

        <form class="form-inline well" action="#" method="GET">
            <div class="form-group">
                <label for="exampleInputName2">用户ID</label>
                <input type="text" class="form-control" id="uid" name="uid" size="20">
            </div>

            <div class="form-group">
                <label>用户手机号</label>
                <input type="text" class="form-control" id="phone" name="phone" size="20">
            </div>
            <div class="form-group">
                <label>身份证号</label>
                <input type="text" class="form-control" id="cardid" name="cardId" size="20">
            </div>
            <br>
            <div class="form-group">
                <label for="exampleInputName1">申请时间</label>
                <div class="input-append date" id="date1" name="date1" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimefrom" name="fromtime" size="16" type="text" value="">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                －
                <div class="input-append date" id="date2" name="date2" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimeto" name="totime" size="16" type="text" value="">
                    <span class="add-on"><i class="icon-th"></i></span>
                    <input type="text" name="page" class="realpage" style="display:none;" value="">
                </div>
            </div>
            <br>
            <div class="form-group">
                <label for="exampleInputName1">审核时间</label>
                <div class="input-append date" id="date1" name="date1" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="checktimefrom" name="fromtime" size="16" type="text" value="">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                －
                <div class="input-append date" id="date2" name="date2" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="checktimeto" name="totime" size="16" type="text" value="">
                    <span class="add-on"><i class="icon-th"></i></span>
                    <input type="text" name="page" class="realpage" style="display:none;" value="">
                </div>
            </div>


            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
            </div>
        </form>

        <div class="row-fluid">
            <ul class="nav nav-pills">
                <li role="presentation" class="active" index="1"><a href="#">待审核</a></li>
                <li role="presentation" index="2"><a href="#">已通过</a></li>
                <li role="presentation" index="3"><a href="#">未通过</a></li>
                <img src="/static/img/loading.gif" class="loading" width="20" height="20"
                     style="margin-top: 6px;margin-left: 5px; display:none;">
            </ul>
            <div>
                <legend>实名认证（共<span class="totalpage">1</span>页，当前第<span class="page">1</span>页，共<span
                        class="total">0</span>条）

                    <div class="next" onselectstart="return false;">></div>
                    <div class="prev" onselectstart="return false;"><</div>
                </legend>

                <!-- -------------------------三个table------------------------------- -->
                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>用户ID</td>
                        <td>申请时间</td>
                        <td>手机号</td>
                        <td>姓名</td>
                        <td>身份证号</td>
                        <td><select class="feedback-choiced" style="width:130px;">
                            <option value="">认证类型</option>
                            <option value="">无状态</option>
                            <option value="争取中">争取中</option>
                            <option value="待处理">待处理</option>
                            <option value="已放弃">已放弃</option>
                            <option value="重下单">重下单</option>
                            <option value="重复取消">重复取消</option>
                            <option value="已下单">已下单</option>
                        </select></td>
                        <td>认证反馈</td>
                        <td>操作</td>
                    </tr>

                    <tr class="tr-unsee" style="display:none;">
                        <td class="uid"></td>
                        <td class="applytime"></td>
                        <td class="phone"></td>
                        <td class="realname"></td>
                        <td class="cardid"></td>
                        <td class="feedback-type-td">
                            <select class="feedback-type" style="width:100px;">
                                <option value="无状态">无状态</option>
                                <option value="争取中">争取中</option>
                                <option value="待处理">待处理</option>
                                <option value="已放弃">已放弃</option>
                                <option value="重下单">重下单</option>
                                <option value="重复取消">重复取消</option>
                                <option value="已下单">已下单</option>
                            </select>
                        </td>
                        <td class="feed"><textarea type="text" class="form-control feedback-input" rows="3" style="width:220px;" /></textarea></td>
                        <td class="oparite"><a href="/user/certify/check/" target="_blank" data-id=''>详情</a></td>
                    </tr>
                </table>

                <table class="table table-bordered table-pass" index="2">
                    <tr>
                        <td>用户ID</td>
                        <td>申请时间</td>
                        <td>通过时间</td>
                        <td>手机号</td>
                        <td>姓名</td>
                        <td>身份证号</td>
                        <td><select class="feedback-choiced" style="width:130px;">
                            <option value="">认证类型</option>
                            <option value="">无状态</option>
                            <option value="争取中">争取中</option>
                            <option value="待处理">待处理</option>
                            <option value="已放弃">已放弃</option>
                            <option value="重下单">重下单</option>
                            <option value="重复取消">重复取消</option>
                            <option value="已下单">已下单</option>
                        </select></td>
                        <td>认证反馈</td>
                        <td>操作</td>
                    </tr>

                    <tr class="tr-pass" style="display:none;">
                        <td class="uid">5</td>
                        <td class="applytime"></td>
                        <td class="checktime"></td>
                        <td class="phone"></td>
                        <td class="realname"></td>
                        <td class="cardid"></td>
                        <td class="feedback-type-td">
                            <select class="feedback-type" style="width:100px;">
                                <option value="无状态">无状态</option>
                                <option value="争取中">争取中</option>
                                <option value="待处理">待处理</option>
                                <option value="已放弃">已放弃</option>
                                <option value="重下单">重下单</option>
                                <option value="重复取消">重复取消</option>
                                <option value="已下单">已下单</option>
                            </select>
                        </td>
                        <td class="feed"><textarea type="text" class="form-control feedback-input" rows="3" style="width:220px;" /></textarea></td>

                        <td class="oparite"><a href="/user/certify/check/" data-id='' target="_blank">详情</a></td>
                    </tr>
                </table>

                <table class="table table-bordered table-out" index="3">
                    <tr>
                        <td>用户ID</td>
                        <td>申请时间</td>
                        <td>手机号</td>
                        <td>姓名</td>
                        <td>身份证号</td>
                        <td><select class="feedback-choiced" style="width:130px;">
                            <option value="">认证类型</option>
                            <option value="">无状态</option>
                            <option value="争取中">争取中</option>
                            <option value="待处理">待处理</option>
                            <option value="已放弃">已放弃</option>
                            <option value="重下单">重下单</option>
                            <option value="重复取消">重复取消</option>
                            <option value="已下单">已下单</option>
                        </select></td>
                        <td>认证反馈</td>
                        <td>操作</td>
                    </tr>

                    <tr class="tr-out" style="display:none;">
                        <td class="uid">5</td>
                        <td class="applytime"></td>
                        <td class="phone"></td>
                        <td class="realname"></td>
                        <td class="cardid"></td>
                        <td class="feedback-type-td">
                            <select class="feedback-type" style="width:100px;">
                                <option value="无状态">无状态</option>
                                <option value="争取中">争取中</option>
                                <option value="待处理">待处理</option>
                                <option value="已放弃">已放弃</option>
                                <option value="重下单">重下单</option>
                                <option value="重复取消">重复取消</option>
                                <option value="已下单">已下单</option>
                            </select>
                        </td>
                        <td class="feed"><textarea type="text" class="form-control feedback-input" rows="3" style="width:220px;" /></textarea></td>

                        <td class="oparite"><a href="/user/certify/check/" data-id='' target="_blank">详情</a></td>
                    </tr>

                    <tr class="tr-remark" style="display:none;">
                        <td>失败原因</td>
                        <td colspan="7" class="remark">长得忒丑了</td>
                    </tr>


                </table>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    $('#date1').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
    $('#date2').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    var tables =
    {
        tableindex: 1,
        tableone: false,
        tabletwo: false,
        tablethree: false
    }
    $(document).on("click", "li[role='presentation']", function () {
        //去掉别人的tab效果，给自己加上。并把相应的table显示出来
        $(".table-bordered").hide();
        $("li[role='presentation']").removeClass("active")
        $(this).addClass("active")
        tables.tableindex = $(this).attr("index")
        $(".table-bordered[index='" + tables.tableindex + "']").show()

        if (tables.tableindex == 1) {
            getData(1)
        }
        else if (tables.tableindex == 2) {
            getData(1)
        }
        else if (tables.tableindex == 3) {
            getData(1)
        }
    })


    //传入页数获取数据
    function getData(page) {
        var uid = $("#uid").val();
        var phone = $("#phone").val()
        var cardid = $("#cardid").val()
        var applytimefrom = $("#applytimefrom").val()
        var applytimeto = $("#applytimeto").val()
        var checktimefrom = $("#checktimefrom").val()
        var checktimeto = $("#checktimeto").val()
        var type = tables.tableindex == 1 ? "unsee" : tables.tableindex == 2 ? "pass" : "out"
        var feedbackType = $(".feedback-choiced").eq(tables.tableindex-1).val();
        $.ajax({
            url: '/ajax',
            type: 'post',
            data: {
                method: "certifyData",
                page: page,
                uid: uid,
                type: type,
                phone: phone,
                cardid: cardid,
                applytimefrom: applytimefrom,
                applytimeto: applytimeto,
                checktimefrom: checktimefrom,
                checktimeto: checktimeto,
                feedbackType:feedbackType
            },
            dataType: 'json',
            success: function (json) {
                console.log(json)
                if (tables.tableindex == 1) {
                    tables.tableone = true;
                }
                if (tables.tableindex == 2) {
                    tables.tabletwo = true;
                }
                if (tables.tableindex == 3) {
                    tables.tablethree = true;
                }
                var count = json.data.count;

                //填入总条数
                if(json == null || json.data == null || json.data.data == null){
                    $(".tr-unsee-clone").remove();
                    $(".tr-pass-clone").remove()
                    $(".tr-out-clone").remove()
                    $(".tr-remark-clone").remove()
                    $(".total").html(count)
                    $(".totalpage").html(Math.ceil(count / 50))
                    return false;
                }
                $(".total").html(count)
                $(".totalpage").html(Math.ceil(count / 50))
                nextPrevCss()
                insertTable(json.data)
                canClick = true;

            }
        })
    }

    function nextPrevCss() {
        var page = parseInt($(".page").html())
        var totalpage = parseInt($(".totalpage").html())
        $(".next").removeClass("disabled")
        $(".prev").removeClass("disabled")
        if (page <= 1) {
            $(".prev").addClass("disabled")
        }
        if (page >= totalpage) {
            $(".next").addClass("disabled")
        }
    }


    //填充表格
    function insertTable(data) {
        var json = data.data;
        var feedbacks = data.feedback;
        if (tables.tableindex == 1) {
            $(".tr-unsee-clone").remove()

            for (var i = 0; i < json.length; i++) {
                var $tr = $(".tr-unsee").eq(0).clone(true).addClass("tr-unsee-clone");
                $tr.find(".uid").html(json[i].uid)
                $tr.find(".phone").html(json[i].mobile)
                $tr.find(".applytime").html(json[i].createtime)
                $tr.find(".realname").html(json[i].realname)
                $tr.find(".cardid").html(json[i].cardid)
                $tr.find(".oparite").children('a').attr("href", "/user/certify/check/?uid=" + json[i].uid);
                for (var j = 0; j < feedbacks.length ; j++){
                    if(json[i].uid == feedbacks[j].uid){
                        $tr.find(".feedback-input").val(feedbacks[j].feedback);
                        $tr.find("option[value='"+feedbacks[j].feedbackType+"']").attr("selected",true);
                    }
                }
                $tr.show()
                $(".table-unsee").append($tr);
            }
        }
        else if (tables.tableindex == 2) {

            $(".tr-pass-clone").remove()

            for (var i = 0; i < json.length; i++) {
                var $tr = $(".tr-pass").eq(0).clone(true).addClass("tr-pass-clone");
                $tr.find(".uid").html(json[i].uid)
                $tr.find(".phone").html(json[i].mobile)
                $tr.find(".applytime").html(json[i].createtime)
                $tr.find(".checktime").html(json[i].updatetime)
                $tr.find(".realname").html(json[i].realname)
                $tr.find(".cardid").html(json[i].cardid)
                $tr.find(".oparite").children('a').attr("href", "/user/certify/check/?uid=" + json[i].uid);
                for (var j = 0; j < feedbacks.length ; j++){
                    if(json[i].uid == feedbacks[j].uid){
                        $tr.find(".feedback-input").val(feedbacks[j].feedback);
                        $tr.find("option[value='"+feedbacks[j].feedbackType+"']").attr("selected",true);

                    }
                }
                $tr.show();
                $(".table-pass").append($tr);
            }
        }
        else if (tables.tableindex == 3) {
            var $trOut = $(".tr-out").eq(0).clone(true).addClass("tr-out-clone")
            var $trRemark = $(".tr-remark").clone(true).addClass("tr-remark-clone")
            $(".tr-out-clone").remove()
            $(".tr-remark-clone").remove()

            for (var i = 0; i < json.length; i++) {
                var $tr = $trOut.eq(0).clone(true);
                $tr.find(".uid").html(json[i].uid)
                $tr.find(".phone").html(json[i].mobile)
                $tr.find(".applytime").html(json[i].createtime)
                $tr.find(".checktime").html(json[i].updatetime)
                $tr.find(".realname").html(json[i].realname)
                $tr.find(".cardid").html(json[i].cardid)
                $tr.find(".oparite").children('a').attr("href", "/user/certify/check/?uid=" + json[i].uid);
                var $trmark = $trRemark.eq(0).clone(true)
                $trmark.find(".remark").html(json[i].remark)
                for (var j = 0; j < feedbacks.length ; j++){
                    if(json[i].uid == feedbacks[j].uid){
                        $tr.find(".feedback-input").val(feedbacks[j].feedback);
                        $tr.find("option[value='"+feedbacks[j].feedbackType+"']").attr("selected",true);
                    }
                }
                $tr.show()
                $trmark.show()
                $(".table-out").append($tr).append($trmark);
            }
        }
    }

    $(function () {
        getData(1)
    })


    var canClick = true;
    $(".next").click(function () {
        if (!canClick) {
            return false;
        }
        canClick = false
        var page = parseInt($(".page").html()) + 1
        if (page > parseInt($(".totalpage").html())) {
            canClick = true;
            return false;
        }
        $(".page").html(page)
        getData(page)
    })


    $(".prev").click(function () {
        if (!canClick) {
            return false;
        }
        canClick = false

        var page = parseInt($(".page").html()) - 1
        if (page < 1) {
            canClick = true;
            return false;
        }
        $(".page").html(page)
        getData(page)
    })


    $(".btn-sm").click(function () {
        getData(1);
        return false;
    })

    $(".loading").ajaxStart(function () {
        $(this).show();
    })
    $(".loading").ajaxStop(function () {
        $(this).hide();
    })

    $(".feedback-input").on("input webChange", function () {
        $(this).css("background-color", "pink");
    })
    $(".feedback-input").keypress(function (event) {
        var $obj = $(this);
        var uid = $obj.parent().parent().find(".uid").html();
        if (event.which == 13) {
            $.ajax({
                url: "/ajax",
                data: {
                    method: "editUserFeedback",
                    uid: uid,
                    feedback: $obj.val()
                },
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $obj.css("background-color", "white");
                        $obj.blur()
                    } else {
                        alert(data.msg)
                    }
                }
            })
            return false;
        }
    })

    $(".feedback-type").change(function(){
        var value = $(this).val();
        var uid = $(this).parent().parent().find(".uid").html();

        $.ajax({
            url: "/ajax",
            data: {
                method: "editUserFeedback",
                uid: uid,
                feedback_type:value
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data.code != 0){
                    alert(data.msg)
                }
            }
        })

    })



</script>
<#include "commons/footer.ftl" />

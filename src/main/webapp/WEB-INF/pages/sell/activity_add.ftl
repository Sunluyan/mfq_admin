<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script src="/static/js/user/bootstrap-filestyle.min.js"></script>
<script src="/static/js/user/jquery.upload.js"></script>

<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="//cdn.bootcss.com/typeahead.js/0.11.1/typeahead.jquery.min.js"></script>

<form class="form-horizontal" method="POST" action="/sell/activity/edit/" enctype="multipart/form-data">
    <div class="container">
    <#--<div class="row-fluid">-->
    <#--<div class="alert text-center">-->
    <#--<a class="close" data-dismiss="alert">×</a>-->
    <#--</div>-->
    <#--</div>-->

        <input type="hidden" name="id" value="${at.id}"/>

        <fieldset>
            <legend>活动信息</legend>
            <div>
                <p/>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">活动名称</label>
                <div class="controls">
                    <input type="text" maxlength="30" class="input-large" id="name" name="name"
                           value="${at.activityName}">
                    <p class="help-inline"><strong class="text-error">*</strong>30个字以内</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="aname">活动类型</label>
                <div class="controls">
                    <select class="type" name="type" style="width:80px;">
                        <option value="无">无</option>
                        <option value="online" <#if at.type == 1>selected</#if>>线上</option>
                        <option value="offline" <#if at.type == 2>selected</#if>>线下</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="nothing">是否结束</label>
                <div class="controls">
                    <select class="isEnd" name="isEnd" style="width:80px;">
                        <option value="0" <#if at.end == 0>selected</#if>>未结束</option>
                        <option value="1" <#if at.end == 1>selected</#if>>已结束</option>
                    </select>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label">活动有效日期</label>
                <div class="controls">
                    <div class="input-append date" id="date1" name="date1"
                         data-date="" data-date-format="yyyy-mm-dd hh:00:00">
                        <input class="span2" id="dateStart" name="begin" size="16" type="text"
                               value="${at.beginAt?string("yyyy-MM-dd HH:mm:ss")}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    －
                    <div class="input-append date" id="date2" name="date2"
                         data-date="" data-date-format="yyyy-mm-dd hh:00:00">
                        <input class="span2" id="dateEnd" name="end" size="16" type="text"
                               value="${at.endAt?string("yyyy-MM-dd HH:mm:ss")}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="imgSmall">列表图</label>
                <div class="controls">
                    <input type="file" name="imgSmall">
                    <span class="help-inline">
                        <a rel="popover" data-content="<img src='${at.imgSmall}'/>" href="${at.imgSmall}" target="_blank" class="text-error img-link">${at.imgSmall}</a>
                    </span>
                </div>
            </div>
        <#-- *************在线的部分*********** -->
            <div class="online" style="display:none;">

                <div class="control-group">
                    <label class="control-label" for="alias">大图</label>
                    <div class="controls">
                        <span class="help-inline">
                            <input type="file" name="imgBig">
                            <a rel="popover" data-content="<img src='${at.imgBig}'/>" href="${at.imgBig}" target="_blank" class="text-error img-link">${at.imgBig}</a>
                        </span>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="alias">选择产品</label>
                    <div class="controls">
                        <input type="text" name="pids" class="pids" placeholder="输入产品id,以逗号分开" value="${at.pids}">
                    </div>
                </div>

            </div>

        <#-- *************线下的部分*********** -->
            <div class="offline" style="display:none;">

                <div class="control-group">
                    <label class="control-label" for="link">网页链接</label>
                    <div class="controls">
                        <input type="text" value="${at.link}" name="link" id="link" class="input" style="width:300px;">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="place">活动地址</label>
                    <div class="controls">
                        <input type="text" value="${at.activityPlace}" name="place" id="place" class="input">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="time">活动时间</label>
                    <div class="controls">
                        <input type="text" value="${at.activityTime}" name="time" id="time" class="input">
                    </div>
                </div>

            </div>


            <div class="form-actions">
                <button type="submit" class="btn btn-primary" onclick="return docheck();">保 存</button>
                &nbsp;&nbsp;
                <button type="button" class="btn" onclick="history.back();">取消返回</button>
            </div>
        </fieldset>

    </div>
</form>


<script>

    $(".type").change(function () {
        if ($(this).val() == "online") {
            $(".offline").hide();
            $(".online").show();
        } else {
            $(".online").hide();
            $(".offline").show();
        }
    })
    var type = $(".type").val();
    if(type == "online"){
        $(".offline").hide();
        $(".online").show();
    }
    if(type == "offline"){
        $(".online").hide();
        $(".offline").show();
    }

</script>

<script type="text/javascript">

    function docheck() {
        if($("#name").val().length <1 || $("#name").val().length >30){
            alert("活动名称不能过短或过长")
            return false;
        }
        if($(".type").val() == "无"){
            alert("活动类型错误")
            return false;
        }
        if($("#dateStart").val().length < 3 || $("#dateEnd").val().length < 3){
            alert("日期错误")
            return false;
        }
        if($(".type").val() == "online"){
            if($(".pids").val() == ""){
                alert("选择产品不能为空")
                return false;
            }
        }else if($(".type").val() == "offline"){
            if($("#link").val().length < 3){
                alert("链接不能为空")
                return false;
            }
            if($("#place").val().length < 3){
                alert("活动地址不能为空")
                return false;
            }
            if($("#time").val().length < 3){
                alert("活动时间不能为空")
                return false;
            }
        }
        return true;
    }

    $('#date1').datetimepicker({
        language: 'zh-cn',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 1,
        forceParse: 0,
        maxView: 3
    });
    $('#date2').datetimepicker({
        language: 'zh-cn',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 1,
        forceParse: 0,
        maxView: 3
    });

    $(".img-link").hover(function () {
        $(".img-link").popover({
            html: true
        })
        $(this).popover("show")
    }, function () {
        $(this).popover("hide")
    })


</script>

<#include "commons/footer.ftl" />
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script src="/static/js/user/bootstrap-filestyle.min.js"></script>
<script src="/static/js/user/jquery.upload.js"></script>

<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>


<form class="form-horizontal" method="POST" action="/sell/activity/edit/" enctype="multipart/form-data">
    <div class="container">
        <div class="row-fluid">
        <#if msg??>
            <div class="alert text-center">
                <a class="close" data-dismiss="alert">×</a>
            ${msg}
            </div>
        </#if>
        </div>

        <input type="hidden" name="id" />
        <input type="hidden" name="t" />

        <fieldset>
            <legend>活动信息</legend>
            <div>
                <p/>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">活动名称</label>
                <div class="controls">
                    <input type="text" maxlength="30" class="input-large" id="name" name="name">
                    <p class="help-inline"><strong class="text-error">*</strong>30个字以内</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="aname">活动类型</label>
                <div class="controls">
                    <select class="type" name = "type">
                        <option value="无">无</option>
                        <option value="online">线上</option>
                        <option value="offline">线下</option>
                    </select>
                </div>
            </div>
            <div class="online" style="display:none;">
                <div class="control-group">
                    <label class="control-label" for="alias">产品分类</label>
                    <div class="controls">
                        <input type="text" class="input-large" id="fq_price" name="fq_price">
                    </div>
                </div>
            </div>
            <div class="offline" style="display:block;">
                <div class="control-group">
                    <label class="control-label" for="img-big">列表图</label>
                    <div class="controls">
                        <input id="file" type="file" name="file" class="file filestyle"  data-buttonText="选择文件"/>
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

    $(".type").change(function(){
        if($(this).val() == "online"){
            $(".offline").hide();
            $(".online").show();
        }else{
            $(".online").hide();
            $(".offline").show();
        }
    })
    $(document).ready(function () {



    });


</script>

<script type="text/javascript">

    function upload(){
        $.ajax({
            url: '/user/interview/uploadimg/',
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (img) {
                console.log(img);

            },
            error: function (returndata) {
                alert("失败啦")
            }
        });
    }
    function docheck() {

    }

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

    $(".img-link").hover(function () {
        $(".img-link").popover({
            html: true
        })

        $(".img-link").popover("show")
    }, function () {
        $(".img-link").popover("hide")
    })



</script>

<#include "commons/footer.ftl" />
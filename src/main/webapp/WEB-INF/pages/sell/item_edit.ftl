<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<form class="form-horizontal" method="POST" action="/sell/item/" enctype="multipart/form-data">
    <div class="container">
        <div class="row-fluid">
        <#if msg??>
            <div class="alert text-center">
                <a class="close" data-dismiss="alert">×</a>
            ${msg}
            </div>
        </#if>
        </div>

        <input type="hidden" name="id" value="${item.id}"/>
        <input type="hidden" name="t" value="${t}" id="t"/>

        <fieldset>
            <legend>商品信息</legend>
            <div>
                <p/>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">商品名称</label>
                <div class="controls">
                    <input type="text" maxlength="8" class="input-large" id="name" name="name" value="${item.name!}">
                    <p class="help-inline"><strong class="text-error">*</strong>8个字以内</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">产品图片</label>
                <div class="controls">
                    <input type="file" name="files" value="${item_img[0].img!}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${item_img[0].img!}'/>" href="${item_img[0].img!}"
                       target="_blank" class="text-error img-link">${item_img[0].img!}</a></span></br>

                    <input type="file" name="files" value="${item_img[1].img!}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${item_img[1].img!}'/>" href="${item_img[1].img!}"
                       target="_blank" class="text-error img-link">${item_img[1].img!}</a></span></br>

                    <input type="file" name="files" value="${item_img[2].img!}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${item_img[2].img!}'/>" href="${item_img[2].img!}"
                       target="_blank" class="text-error img-link">${item_img[2].img!}</a></span></br>

                    <input type="file" name="files" value="${item_img[3].img!}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${item_img[3].img!}'/>" href="${item_img[3].img!}"
                       target="_blank" class="text-error img-link">${item_img[3].img!}</a></span></br>

                    </button>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">美丽日记整容前后</label>
                <div class="controls">
                    <input type="file" id="file1" name="before" value="${before}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${before}'/>" href="${before}"
                       target="_blank" class="text-error img-link">${before}</a></span></br>
                    </button>

                    <input type="file" id="file1" name="after" value="${after}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${after}'/>" href="${after}"
                       target="_blank" class="text-error img-link">${after}</a></span></br>
                    </button>

                    <input type="file" id="file1" name="beautiful" value="${beautiful}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${beautiful}'/>" href="${beautiful}"
                       target="_blank" class="text-error img-link">${beautiful}</a></span></br>
                    </button>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">手术纪实</label>
                <div class="controls">
                    <input type="file" id="file1" name="surgery" value="${surgery}"><span class="help-inline">
                    <a rel="popover" data-content="<img src='${surgery}'/>" href="${surgery}"
                       target="_blank" class="text-error img-link">${surgery}</a></span></br>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="cname">产品详情图片</label>
                <div class="controls">
                        <#list details as detail>
                            <div class="detail-image">
                                <input type="file" id="file1" name="detail-images" value="${detail}"><span class="help-inline">
                                <a rel="popover" data-content="<img src='${detail}'/>" href="${detail}" target="_blank" class="text-error img-link">${detail}</a></span>
                                </br>
                            </div>
                        </#list>
                            <#if details == null || details.size() == 0>
                                <div class="detail-image">
                                    <input type="file" id="file1" name="detail-images" value=""><span class="help-inline">
                                <a rel="popover" data-content="<img src=''/>" href="" target="_blank" class="text-error img-link"></a></span>
                                    </br>
                                </div>
                            </#if>


                    <button class="btn add-detail-image">添加</button>

                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="cname">产品简介</label>
                <div class="controls">
                    <input type="text" name="desc" value="${detail.description}">
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="alias">产品分类</label>
                <div class="controls">
                    <input type="hidden" value="${rootId}" name="rootId" id="rid"/>
                    <input type="hidden" value="${classId}" name="classifyId" id="cid"/>
                    <select id='classify' name="classify">
                    </select>

                    <select id='classify2' name="classify2">
                        <option value="0">全部</option>
                    </select>
                    <select name="type2">
                        <option value="激光">激光</option>
                        <option value="针剂">针剂</option>
                        <option value="手术">手术</option>
                    </select>

                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="cname">产品类型</label>
                <div class="controls">
                    <select id='type' name="type">
                    <#list types as type>
                        <option value="${type.id}" <#if ((type.id))== ((item.type.id))>selected</#if>>

                            <#if type=='NORMAL'>普通产品</#if>
                            <#if type=='SPECIAL'>特价产品</#if>
                            <#if type=='SECKILLING'>秒杀产品</#if>

                        </option>
                    </#list>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="alias">所属医院</label>
                <div class="controls">
                    <select id='hospital' name="hospital">
                    <#list hospitals as hs>
                        <option value="${hs.id}" <#if ((hs.id))== ((hospitalId))>selected</#if>>${hs.name}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="alias">是否上线</label>
                <div class="controls">
                    <select id='is_online' name="is_online">
                        <option value="true" <#if true == ((item.online))>selected</#if>>上线</option>
                        <option value="false" <#if false == ((item.online))>selected</#if>>下线</option>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="alias">所在城市</label>
                <div class="controls">
                    <select id='city_id' name="city_id">
                        <option value="1" <#if 1== ((cityId))>selected</#if>>北京</option>
                        <option value="3" <#if 3== ((cityId))>selected</#if>>上海</option>
                        <option value="225" <#if 225== ((cityId))>selected</#if>>成都</option>
                        <option value="4" <#if 4== ((cityId))>selected</#if>>重庆</option>
                        <option value="256" <#if 256== ((cityId))>selected</#if>>三亚</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">团购价格</label>
                <div class="controls">
                    <input type="text" class="input-large" id="price" name="price" value="${item.price!}">
                    <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">分期价</label>
                <div class="controls">
                    <input type="text" class="input-large" id="fq_price" name="fq_price"
                           value="${fqs.get(0).periodPay}">
                    <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="lname">市场价</label>
                <div class="controls">
                    <input type="text" class="input-large" id="market_price" name="market_price"
                           value="${item.marketPrice!}">
                    <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">产品数量</label>
                <div class="controls">
                    <input type="text" class="input-large" id="total_num" name="total_num" value="${item.totalNum!}">
                    <p class="help-inline"><strong class="text-error">*</strong>产品数量只能为数字</p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">有效期</label>
                <div class="controls">
                    <div class="input-append date" id="date1" name="date1"
                         data-date="${item.dateStart?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
                        <input class="span2" id="dateStart" name="dateStart" size="16" type="text"
                               value="${item.dateStart?string("yyyy-MM-dd")!}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    －
                    <div class="input-append date" id="date2" name="date2"
                         data-date="${item.dateEnd?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
                        <input class="span2" id="dateEnd" name="dateEnd" size="16" type="text"
                               value="${item.dateEnd?string("yyyy-MM-dd")!}">
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    <p class="help-inline"><strong class="text-error">*</strong></p>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="alias">是否可分期</label>
                <div class="controls">
                    <select id='fq' name="fq">
                        <option value="1" <#if 1== ((item.fq))>selected</#if>>可分期</option>
                        <option value="0" <#if 0== ((item.fq))>selected</#if>>不可分期</option>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="alias">是否首页推荐</label>
                <div class="controls">
                    <select id='flag' name="flag">
                        <option value="0" <#if 0== (item.flag)>selected</#if>>不推荐</option>
                        <option value="1" <#if 1== (item.flag)>selected</#if>>推荐</option>
                    </select>
                </div>
            </div>

            <div class="control-group" data-toggle="collapse" data-target>
                <label class="control-label" for="alias">问答</label>
                <div class="controls">

                    <div class="accordion" id="accordion2">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                                    问答目录
                                </a>
                            </div>
                            <div id="collapseOne" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <div class="question-answer" >
                                        <textarea class="question"name="question" style="width:300px;"></textarea>
                                        <textarea class="answer" name="answer"style="width:300px;"></textarea>
                                        <button class="btn btn-danger delete-answer">删除</button>
                                        <br>
                                        <br>
                                    </div>
                                    <button class="add-question btn">添加问答</button>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>





            <div class="control-group">
                <label class="control-label" for="lname">注意事项</label>
                <div class="controls">
                    <textarea class="textarea" style="width:400px;" rows="5" name="warnings">${detail.attention}</textarea>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="lname">优惠策略</label>
                <div class="controls">
                    <textarea class="textarea" style="width:400px;" rows="5" name="preferential">${detail.preferential}</textarea>
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

    var roots = $("#classify");
    var le = $("#classify2");
    var cid = $("#cid").val();
    var rid = $("#rid").val();

    var data;

    $(document).ready(function () {

        $.get("/sell/classify/").done(function (json) {
            if (json.code != 0) {
                alert("请求出错!!!" + json.toString())
            }
            data = json.data;


            //roots.html('');
            for (var i = 0; i < data.length; i++) {
                root = data[i].classify;
                hml = createOption(root.id, root.name)

                if (data[i].id == rid) {
                    createOptions(data[i].items, le)

                } else if (cid == '' && i == 0) {

                    createOptions(data[i].items, le)
                }

                hml.appendTo(roots);

            }


            //设置默认选中


        });

        roots.change(function () {
            var rid = roots.val();

            for (var i = 0; i < data.length; i++) {
                if (data[i].id == rid) {
                    //le.html('')
                    createOptions(data[i].items, le)

                }
            }

        });


        function createOptions(data, elen) {
            elen.html('');
            createOption(0, '全部').appendTo(elen);
            for (var i = 0; i < data.length; i++) {
                html = createOption(data[i].id, data[i].name)
                html.appendTo(elen)
            }
        }

        function createOption(key, value) {
            var html = $("<option value='" + key + "'>" + value + "</option>");
            if (rid == key || cid == key) {
                html = $("<option value='" + key + "' selected>" + value + "</option>");
            }
            return html;
        }


    });


</script>

<script type="text/javascript">
    function docheck() {
        if ($("#t").val() == 0) {
            if ($("#file1").val() == '') {
                alert("产品图片不能为空");
                return false;
            }
        }
        if ($.trim($("#name").val()).length < 1) {
            alert("产品名称不能为空");
            return false;
        }
        if ($.isNumeric($.trim($("#price").val())) == false) {
            alert("价格必须是数字");
            return false;
        }
        if ($.isNumeric($.trim($("#market_price").val())) == false) {
            alert("价格必须是数字");
            return false;
        }
        if ($.isNumeric($.trim($("#total_num").val())) == false) {
            alert("产品数量必须是数字");
            return false;
        }
        if (parseInt($("#total_num").val()) < 1) {
            alert("产品数量须大于1");
            return false;
        }
        return true;
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

        $(this).popover("show")
    }, function () {
        $(this).popover("hide")
    })


    $(".add-detail-image").click(function(){
        var $detailImage = $(".detail-image").last().clone(true);
        $detailImage.find("input").attr("value","")
        $detailImage.find("a").attr("data-content","").attr("href","").html("")
        $(".detail-image").last().append($detailImage)
        return false;
    })
    $(".add-question").click(function(){
        var $question = $(".question-answer").last().clone(true);
        $question.find(".question").val("").find(".answer").val("")
        $(".question-answer").last().append($question);
        return false;
    })

    var ask = ${detail.ask};
    function answers(){
        var oldAnswer = $(".question-answer").last();
        oldAnswer.find(".question").val(ask[0].question)
        oldAnswer.find(".answer").val(ask[0].question)
        for(var i = 1;i<ask.length;i++){
            var answers = $(".question-answer").last().clone();
            answers.find(".question").val(ask[i].question)
            answers.find(".answer").val(ask[i].answer)
            answers.show();
            $(".question-answer").last().after(answers);
        }
    }
    answers()
    $(".delete-answer").click(function(){
        $(this).parent().remove();
        return false;
    })


</script>

<#include "commons/footer.ftl" />
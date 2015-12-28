<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />
<script type="text/javascript" src="/static/js/jquery.autocomplete.js"></script>

<style type="text/css">
    .select-label .lab {
        background-color: #E7E7E7;
        border: 0 none;
        border-radius: 3px 3px 3px 3px;
        color: #666666;
        cursor: pointer;
        display: inline-block;
        font-size: 12px;
        line-height: 16px;
        margin-bottom: 5px;
        margin-right: 2px;
        overflow: hidden;
        padding: 7px 4px;
        text-align: center;
        text-overflow: ellipsis;
        vertical-align: middle;
        white-space: nowrap;
        width: 62px;
    }
    .select-label .lab:hover {
        background: none repeat scroll 0 0 #D7D7D7;
        color: #666666;
        text-decoration: none;
        transition: background 0.3s ease-in-out 0s;
    }
    .select-label .lab.selected {
        background: url("//s2.sjbly.cn/img/lab.png") no-repeat scroll right bottom #008BFF;
        color: #FFFFFF;
    }
    .select-label .lab.selected:hover {
        background: url("//s2.sjbly.cn/img/lab.png") no-repeat scroll right bottom #008BFF;
    }
    .select-label .lab.hasblog, .select-label .lab.disabled, .select-label .lab[disabled] {
        border-color: #CCCCCC;
    }
</style>

<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />
            <div class="span10">
                <form class="well form-inline" method="GET">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="qid">qid</option>

                    </select>
                    <input name="qid" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询</button>
                </form>
                <legend>问题信息</legend>


                <fieldset>
                    <div class="control-group">
                        <label class="control-label">标题：<a
                                href="http://shijiebang.com/question/${base62(question.qid)}/" target="_blank">${question.title}</a>
                            <span id="btn-status" class="btn btn-danger" >${(question.status=="Normal")?string('删除','恢复')}</span>
                            <input id="status" value="${(question.status=="Normal")?string('Deleted','Normal')}" style="display: none"/>
                        </label>
                      <br>
                      <a href="/search/answers/?qid=${question.qid}">查看答案</a>
                    </div>

                    <div class="controls">
                        <input id="qid" value="${base62(question.qid)}" style="display: none"/>

                        <legend>按uid分配给达人</legend>
                        <input class="span2 search-query" id="doyenid" value="" name="doyenid" type="text" placeholder="达人数字ID" >
                        <button type="button" id="btn-doyen" class="btn">分配问题</button>
                        <span class="text-info">
                            &nbsp; 已分配给:
                            <#list doyens as doyen>
                                ${doyen.nick} (uid: ${doyen.uid} )
                            </#list>
                        </span>

                        <span id="doyenReturn"></span>
                        <legend>按国家分配给达人</legend>
                        <select id="doyen_locationId" class="input-medium">
                            <option value="0">选择分配的国家</option>
                            <option value="10000000000">美国</option>
                            <option value="30500000000">澳大利亚</option>
                            <option value="21100000000">泰国</option>
                            <option value="16300000000">法国</option>
                            <option value="15800000000">意大利</option>
                            <option value="20000000000">日本</option>
                            <option value="15600000000">德国</option>
                            <option value="16000000000">西班牙</option>
                            <option value="20400000000">柬埔寨</option>
                            <option value="20700000000">马来西亚</option>
                            <option value="30600000000">新西兰</option>
                            <option value="19000000000">希腊</option>
                            <option value="20200000000">越南</option>
                            <option value="17200000000">奥地利</option>
                            <option value="15100000000">瑞士</option>
                            <option value="19900000000">韩国</option>
                            <option value="10100000000">加拿大</option>
                            <option value="20900000000">新加坡</option>
                            <option value="21500000000">印度</option>
                            <option value="16800000000">俄罗斯</option>
                            <option value="15000000000">英国</option>
                            <option value="29800000000">南非共和国</option>
                            <option value="15300000000">荷兰</option>
                            <option value="21800000000">马尔代夫</option>
                            <option value="16100000000">葡萄牙</option>
                            <option value="17300000000">捷克</option>
                        </select>
                        <button type="button" id="btn-c-doyen" class="btn">分配问题</button>
                        <span class="text-info">
                            &nbsp; 已分配给:
                        <#list doyens as doyen>
                        ${doyen.nick} (uid: ${doyen.uid} )
                        </#list>
                        </span>

                        <span id="doyenlReturn"></span>

                        <legend>标记问题</legend>

                        <select id="sticker" name="sticker">
                            <option value="0">    </option>
                            <option value="1" <#if questioncharge.sticker=1 >selected="" </#if> >已分配</option>
                            <option value="2" <#if questioncharge.sticker=2 >selected="" </#if> >可推商品</option>
                            <option value="3" <#if questioncharge.sticker=3 >selected="" </#if> >1周后跟进</option>
                            <option value="4" <#if questioncharge.sticker=4 >selected="" </#if> >已推商品</option>
                        </select>
                        <button type="button" id="btn-sticker" class="btn">打标签</button>
                        <span id="StickerReturn"></span>

                        <legend>设置Rank</legend>

                        <input class="input-medium search-query" id="rank" value="${question.rank}" name="rank" type="text" placeholder="数字排序" >
                        <button type="button" id="btn-rank" class="btn">设置Rank</button>
                        <span id="return"></span>

                            <p></p>
                        <div class="row " style="border:solid 1px #efefef;padding: 20px;">
                      <legend>问题 TAGS (小写逗号分割)</legend>
                       <input type="hidden" name="locationId" id="locationId" value="${location.id}">
                     关联地区: <input class="input-large search-query" id="locationInput" value="${location.cname}"  type="text"  >
                     标签:<input class="input-large search-query" id="tags" value="${question.tags}" name="tags" type="text"  >
                      <button type="button" id="btn-tags" class="btn-warning">设置地区和标签</button>
                        <span class="text-info" style="color: red">请谨慎修改!!!</span>
                      <span id="TagsReturn"></span>
                        <div class="select-label">
                            <legend>主题</legend>
                            <span class="lab ${(question.tags?? && question.tags?contains('海岛'))?string('selected','')}">海岛</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('蜜月'))?string('selected','')}">蜜月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('购物'))?string('selected','')}">购物</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('美食'))?string('selected','')}">美食</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('摄影'))?string('selected','')}">摄影</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('历史'))?string('selected','')}">历史</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('亲子'))?string('selected','')}">亲子</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('带父母'))?string('selected','')}">带父母</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('艺术'))?string('selected','')}">艺术</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('湖泊'))?string('selected','')}">湖泊</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('徒步'))?string('selected','')}">徒步</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('骑行'))?string('selected','')}">骑行</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('温泉'))?string('selected','')}">温泉</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('赏花'))?string('selected','')}">赏花</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('动物'))?string('selected','')}">动物</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('医疗'))?string('selected','')}">医疗</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('潜水'))?string('selected','')}">潜水</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('滑雪'))?string('selected','')}">滑雪</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('游学'))?string('selected','')}">游学</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('体育赛事'))?string('selected','')}">体育赛事</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('海岸'))?string('selected','')}">海岸</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('城市'))?string('selected','')}">城市</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('小镇'))?string('selected','')}">小镇</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('建筑'))?string('selected','')}">建筑</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('探险'))?string('selected','')}">探险</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('体验'))?string('selected','')}">体验</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('高尔夫'))?string('selected','')}">高尔夫</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('狩猎'))?string('selected','')}">狩猎</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('垂钓'))?string('selected','')}">垂钓</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('第一次出游'))?string('selected','')}">第一次出游</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('深度出游'))?string('selected','')}">深度出游</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('名山'))?string('selected','')}">名山</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('河谷'))?string('selected','')}">河谷</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('国家公园'))?string('selected','')}">国家公园</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('军事'))?string('selected','')}">军事</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('展览'))?string('selected','')}">展览</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('人文'))?string('selected','')}">人文</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('宗教'))?string('selected','')}">宗教</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('自然风光'))?string('selected','')}">自然风光</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('名胜古迹'))?string('selected','')}">名胜古迹</span>
                            <legend>月份</legend>
                            <span class="lab ${(question.tags?? && question.tags?contains('一月'))?string('selected','')}">一月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('二月'))?string('selected','')}">二月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('三月'))?string('selected','')}">三月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('四月'))?string('selected','')}">四月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('五月'))?string('selected','')}">五月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('六月'))?string('selected','')}">六月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('七月'))?string('selected','')}">七月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('八月'))?string('selected','')}">八月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('九月'))?string('selected','')}">九月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('十月'))?string('selected','')}">十月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('十一月'))?string('selected','')}">十一月</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('十二月'))?string('selected','')}">十二月</span>
                            <legend>旅游要素</legend>
                            <span class="lab ${(question.tags?? && question.tags?contains('签证'))?string('selected','')}">签证</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('国际机票'))?string('selected','')}">国际机票</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('火车'))?string('selected','')}">火车</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('住宿'))?string('selected','')}">住宿</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('餐饮'))?string('selected','')}">餐饮</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('通讯'))?string('selected','')}">通讯</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('购物'))?string('selected','')}">购物</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('游览'))?string('selected','')}">游览</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('娱乐'))?string('selected','')}">娱乐</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('语言'))?string('selected','')}">语言</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('安全'))?string('selected','')}">安全</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('季节'))?string('selected','')}">季节</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('天气'))?string('selected','')}">天气</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('出入境'))?string('selected','')}">出入境</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('市内交通'))?string('selected','')}">市内交通</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('节日'))?string('selected','')}">节日</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('旅行贴士'))?string('selected','')}">旅行贴士</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('地图'))?string('selected','')}">地图</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('优惠信息'))?string('selected','')}">优惠信息</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('退税'))?string('selected','')}">退税</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('廉价机票'))?string('selected','')}">廉价机票</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('门票'))?string('selected','')}">门票</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('保险'))?string('selected','')}">保险</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('行程'))?string('selected','')}">行程</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('换汇'))?string('selected','')}">换汇</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('自驾'))?string('selected','')}">自驾</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('护照'))?string('selected','')}">护照</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('国际交通'))?string('selected','')}">国际交通</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('城际交通'))?string('selected','')}">城际交通</span>
                            <span class="lab ${(question.tags?? && question.tags?contains('市内交通'))?string('selected','')}">市内交通</span>

                        </div>
                           </div>
                    </div>


                </fieldset>
                <br />
                <legend>问题相关推荐</legend>
                <span id="resuccess" class=""></span>
                <form method="" class="well form-inline">

                    <fieldset>
                       右边内容： <textarea rows="8" class="input-xxlarge" id="text-right">
                         ${questionRecommend.right}
                        </textarea>
                        <br />
                        <br />
                        底部内容： <textarea rows="8"  class="input-xxlarge" id="text-bottom">
                    ${questionRecommend.bottom}
                    </textarea>
                    </fieldset>
                    <br />
                    <input  value="提交" class="btn btn-primary btn-large " id="btn-recommend">
                </form>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    $("#btn-rank").click(set_rank);
    $("#btn-status").click(set_status);
    $("#btn-recommend").click(set_recommend);
    $("#btn-doyen").click(set_doyen);
    $("#btn-c-doyen").click(set_doyen);
    $("#btn-sticker").click(set_sticker);
    $("#btn-tags").click(set_tags);

    function set_rank() {

        var qid = $("#qid").val();
        var rank = $("#rank").val();

        do_execute_rank(qid, rank);
    }
    function set_recommend() {

        var qid = $("#qid").val();
        var right = $("#text-right").val();
        var bottom = $("#text-bottom").val();
        do_execute_recommend(qid, right,bottom);
    }
    function set_status() {

        var qid = $("#qid").val();
        var status = $("#status").val();

        do_execute_status(qid, status);
    }

    function set_doyen() {

        var qid = $("#qid").val();
        var doyenid = $("#doyenid").val();

        do_execute_doyen(qid, doyenid);
    }
    function set_l_doyen() {

        var qid = $("#qid").val();
        var locationId = $("#doyen_locationId").val();

        do_execute_l_doyen(qid, locationId);
    }

    function set_sticker() {

        var qid = $("#qid").val();
        var sticker = $("#sticker").val();

        do_execute_sticker(qid, sticker);
    }

    function set_tags() {

      var qid = $("#qid").val();
      var tags = $("#tags").val();
      var locationId=$("#locationId").val();

      do_execute_tags(qid, tags,locationId);
    }


    function do_execute_rank(qid, rank) {

        $.post(
                "/question/rank/",
                {qid:qid, rank:rank},
                function () {
                    $("#return").html('<i class="icon-ok"></i> 设置成功');
                }
        );
    }
    function do_execute_status(qid, status) {

        $.post(
                "/question/" + qid + "/" + status + "/",

                function () {
                    if(status=='Normal')                                {
                        $("#status").attr("value","Deleted")  ;
                        $("#btn-status").html('删除');
                    } else{
                        $("#status").attr("value","Normal")  ;
                        $("#btn-status").html('恢复');
                    }

                }
        );
    }
    function do_execute_recommend(qid, right,bottom) {

        $.post(
                "/question/" + qid + "/recommend/",
                {qid:qid, right:right,bottom:bottom},
                function () {

                    $("#resuccess").html('<i class="icon-ok"></i>更改成功');
                }
        );
    }

    function do_execute_doyen(qid, doyenid) {

        $.post(
                "/question/" + qid +"/doyen/",
                {qid:qid, doyenid:doyenid},
                function () {
                    $("#doyenReturn").html('<i class="icon-ok"></i> 设置成功');
                }
        );
    }
    function do_execute_l_doyen(qid, locationId) {

        $.post(
                "/question/" + qid +"/doyen/",
                {qid:qid, locationId:locationId},
                function () {
                    $("#doyenlReturn").html('<i class="icon-ok"></i> 设置成功');
                }
        );
    }

    function do_execute_sticker(qid, sticker) {

        $.post(
                "/question/" + qid +"/sticker/",
                {qid:qid, sticker:sticker},
                function () {
                    $("#StickerReturn").html('<i class="icon-ok"></i> 设置成功');
                }
        );
    }

    function do_execute_tags(qid, tags,locationId) {

      $.post(
          "/question/tags/",
          {qid:qid, tags:tags,locationId:locationId},
          function () {
            $("#TagsReturn").html('<i class="icon-ok"></i> 设置成功');
          }
      );
    }



    $('#locationInput').autocomplete({
        serviceUrl: '/a/location/suggest/',
        paramName: "input",
        minChars: 2,
        onSelect: function (suggestion) {
            $("#locationId").val(suggestion.data);
        }
    });

    var inputDom = $("#tags");
    var tagsBox = $(".select-label");
    tagsBox.click(function (event) {
        if (/^(span)$/i.test(event.target.tagName)) {
            var target = $(event.target);
            target.toggleClass("selected");
            var result = target.text().trim();

            if (target.hasClass("selected")) {
                var arr = inputDom.val().trim().split(",");
                arr[arr.length] = result;
                inputDom.val(arr.join(","));

            } else {
                var arr = inputDom.val().trim().split(",");
                var actualArr = [];


                for (var i = 0; i < arr.length; i++) {
                    if (arr[i] != result) {
                        actualArr[actualArr.length] = arr[i];
                    }

                }
                console.log(actualArr)

                inputDom.val(actualArr.join(","));
            }

        }
    });

</script>
<#include "commons/footer.ftl" />
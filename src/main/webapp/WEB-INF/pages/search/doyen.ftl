<#include "commons/header.ftl" />
<#assign toolbar="doyen" />
<#include "commons/toolbar.ftl" />

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
<script type="text/javascript" src="/static/js/jquery.autocomplete.js"></script>

<div class="container">
<h3>达人设置 <a class="btn pull-right js-sync" data-path="/xpower/status/doyen-cache"
            title="添加达人或者设置达人的行程国家后需要立即生效，请点击此操作。">同步到线上</a>
</h3>

<div class="container-fluid">

<div class="row-fluid">
<#assign tab="${item}" />
<#include "/prop/tabs.ftl" />


<div class="span10">
<form class="well form-inline">
    <input id="userId" type="text" class="input-small" placeholder="用户id">

    <select id="locationId" class="input-medium">
        <option value="">请选择</option>
        <option value="0">预备达人</option>
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
        <option value="18500000000">芬兰</option>
        <option value="21300000000">尼泊尔</option>
        <option value="18700000000">丹麦</option>
        <option value="19200000000">土耳其</option>
        <option value="21200000000">不丹</option>
        <option value="18400000000">挪威</option>
        <option value="18300000000">瑞典</option>
        <option value="10200000000">墨西哥</option>
        <option value="21700000000">斯里兰卡</option>
        <option value="24300000000">阿拉伯联合酋长国</option>
        <option value="31700000000">斐济</option>
        <option value="16200000000">安道尔公国</option>
        <option value="14900000000">爱尔兰</option>
        <option value="17100000000">波兰</option>
        <option value="17600000000">斯洛文尼亚</option>
        <option value="23500000000">以色列</option>
        <option value="19400000000">马耳他</option>
        <option value="-1">其他国家</option>
    </select>

    <input id="mylocationname" type="text" class="input-medium" placeholder="输入国家">
    <input type="hidden" id="mylocationid">

    <span id="btn-add-doyen" class="btn btn-primary">添加达人</span>
    <span id="btn-remove-doyen" class="btn">删除达人</span>
</form>


<form class="well form-inline">
    <input id="userId2" type="text" class="input-small" placeholder="用户id">
    <input id="countryNum" type="text" class="input-large" placeholder="去过的国家数">
    <input id="cityNum" type="text" class="input-large" placeholder="去过的城市数">
    <span id="btn-add-num" class="btn btn-primary">设置数目</span>

</form>


<div>
<#if msg??>
    <div class="well">${msg}</div>
</#if>
    <form class="well form-inline" method="post" action="/search/doyen/">
        <select id="type-select" name="type" class="select input-medium">
            <option value="uid">uid</option>
            <option value="nick">用户名(nick)</option>
            <option value="country">国家</option>
            <option value="status">达人状态(现只支持预备达人)</option>
        </select>
        <input name="value" type="text" class="input-medium search-query">
        <button type="submit" class="btn">查询</button>
    </form>

</div>

<div>
    <form class="well form-inline">
        <fieldset>
            <legend>设置达人制作行程的国家权限</legend>
            <input id="tripUid" name="tripUid" onfocus="focusTripUid(this)" onblur="blurTripUid(this)"
                   class="input-medium" value="" placeholder="①达人UID">
            <a href="javascript:void(0)">②Check.........</a>
            <input id="tripLocations" name="tripLocations" type="hidden" value="" disabled="disabled">
            <button id="btn-add-tripLocations" type="button" class="btn btn-primary">③设置权限</button>
            <div id="tripLocationsDiv" class="select-label">
            </div>
        </fieldset>
    </form>
</div>


<div class="row-fluid">
    <div class="page-header" style="margin:10px 0 18px;"><h4 title="达人总数 ${doyens?size}">达人列表&nbsp;&nbsp;<span
            style="color:white;">达人总数 ${doyen_count!}</span></h4></div>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th width="20px;">uid</th>
            <th>用户名</th>
            <th>达人所属地</th>
            <th>国家权限</th>
        <#--<th>职业</th>-->
        <#--<th>一些数目</th>-->
            <th>rank</th>
        <#--<th>个人介绍bid</th>-->
            <th>是否內部达人</th>
            <th>角色设置<i class="info icon-info-sign" data-info="现在只用于一些统计数据使用"></i></th>
        </tr>
        </thead>
        <tbody>
        <#list doyens as doyen>
        <tr>
            <#--<a name="anchor${user.uid}"></a>-->


            <td><a href="http://shijiebang.com/u${doyen.uid}/">${doyen.uid}</a></td>
            <#assign user=userMap[doyen.uid+""]>
            <td>${user.nick}</td>
            <td>
                <#list doyen.locationIds as locationId>
                    <#if locationId==0><font color="red">预备达人</font>，
                    <#else>
                    ${lgetCname((locationId)!0)}
                    </#if>
                </#list>
            </td>
            <td>
                <#list doyen.tripLocationIdList as locationId>
                ${lgetCname(locationId)}
                    <#if locationId_has_next>
                        ,
                    </#if>
                </#list>
            </td>
        <#--<td>-->
        <#--<input id="career_${doyen.uid}" type="text" value="${user.career}" class="input-small" placeholder="职业">-->
        <#--<span onclick="setCareer(${doyen.uid})" class="btn btn-primary">设置</span>-->
        <#--</td>-->
        <#--<td>国家数：${userAwardMap[doyen.uid+""].countryNum}&nbsp;城市数：${userAwardMap[doyen.uid+""].cityNum}</td>-->
            <td>
                <input id="rank_${doyen.uid}" type="text" value="${doyen.rank}" class="input-small" placeholder="rank">

                <span onclick="setRank(${doyen.uid})" class="btn btn-primary">设置</span>
            </td>
        <#--<td>-->
        <#--<#assign bid=userAwardMap[doyen.uid+""].profileBid>-->
        <#--<input id="profile_bid_${doyen.uid}" type="text" value="${(bid==0)?string('',bid)}" class="input-small" placeholder="博文 id">-->

        <#--<span onclick="setProfileBid(${doyen.uid})" class="btn btn-primary">设置</span>-->
        <#--</td>-->
            <td style="width: 55px;">
                <#if user.innerDoyen>
                    <a href="#" onclick="setInnerDoyen(${user.uid},false)">取消为内部达人</a>
                <#else>
                    <a href="#" onclick="setInnerDoyen(${user.uid},true)">设置为内部达人</a></#if>
            </td>
            <td>
                <a name="anchor${user.uid}" />

                <#assign signs=signMap[user.uid+""]>
                <#assign isSet=false isSetStudio=false isSetAPlan=false isSetBPlan=false isSetBPlanPre=false isSetCPlan=false isSetFullTime=false>
                <a href="javascript:void(0)" class="sign" data-uid='${user.uid}'>
                    <#if signs['FullTime']><span class="btn-success">全职达人</span>
                        &nbsp; <#assign isSet=true isSetFullTime=true></#if>
                    <#if signs['Studio']><span class="btn-success">工作室达人</span>
                        &nbsp; <#assign isSet=true isSetStudio=true></#if>
                    <#if signs['APlan']><span class="btn-inverse">A计划达人</span>
                        &nbsp;<#assign isSet=true isSetAPlan=true></#if>
                    <#if signs['BPlan']> <span class="btn-info">B计划达人</span>
                        &nbsp;<#assign isSet=true isSetBPlan=true></#if>
                    <#if signs['BPlanPre']> <span class="btn-primary">B计划预备达人</span>
                        &nbsp;<#assign isSet=true isSetBPlanPre=true></#if>
                    <#if signs['CPlan']><span class="btn-warning">C计划达人</span>
                        &nbsp;<#assign isSet=true isSetCPlan=true></#if>
                    <#if !isSet>
                        设置
                    </#if>

                </a>

                <div class="modal hide fade " id="m${user.uid}">

                    <div class="modal-header">
                        <a class="close" data-dismiss="modal">×</a>

                        <h3>设置达人角色</h3>
                    </div>
                    <div class="modal-body">
                        <form action="/doyen/sign/" method="post">
                            <input type="hidden" name="uid" value="${user.uid}">
                            <input type="hidden" name="num" value="${num}">
                            <input type="hidden" name="anchor" value="anchor${user.uid}">


                            <div class="control-group">
                                <label class="control-label">设置达人《${user.nick}》角色</label>

                                <div class="controls">
                                    <input type="checkbox" name="sign" value="6"
                                           <#if isSetFullTime>checked</#if> >全职达人<br>
                                    <input type="checkbox" name="sign" value="1"
                                           <#if isSetStudio>checked</#if> >工作室达人<br>
                                    <input type="checkbox" name="sign" value="2"
                                           <#if isSetAPlan>checked</#if> >A计划达人<br>
                                    <input type="checkbox" name="sign" value="3"
                                           <#if isSetBPlan>checked</#if> >B计划达人<br>
                                    <input type="checkbox" name="sign" value="4" <#if isSetBPlanPre>checked</#if> >B计划预备达人<br>
                                    <input type="checkbox" name="sign" value="5"
                                           <#if isSetCPlan>checked</#if> >C计划达人<br>
                                </div>
                            </div>
                            <input type="submit" value="更改">

                        </form>

                    </div>
                    <div class="modal-footer">
                        <a href="#" data-dismiss="modal" class="btn">关闭</a>
                    </div>
                </div>
            </td>

        </tr>

        </#list>
        </tbody>
    </table>

</div>
<#if type=='all'>
<ul class="pager">
    <#if num!=1>
        <li>
            <a href="/search/doyen/?num=${num-1}">前一页</a>
        </li>
    </#if>
    <#if doyens?size==50>
        <li>
            <a href="/search/doyen/?num=${num+1}">后一页</a>
        </li>
    </#if>
</ul>
</#if>
</div>
</div>
</div>

<script type="text/javascript">

var europe = [
    {"lid": 15800000000, "name": "意大利"},
    {"lid": 16300000000, "name": "法国"},
    {"lid": 15600000000, "name": "德国"},
    {"lid": 15000000000, "name": "英国"},
    {"lid": 15100000000, "name": "瑞士"},
    {"lid": 19000000000, "name": "希腊"},
    {"lid": 16000000000, "name": "西班牙"},
    {"lid": 16100000000, "name": "葡萄牙"},
    {"lid": 15300000000, "name": "荷兰"},
    {"lid": 17500000000, "name": "匈牙利"},
    {"lid": 17300000000, "name": "捷克"},
    {"lid": 15400000000, "name": "比利时"},
    {"lid": 17200000000, "name": "奥地利"},
    {"lid": 18400000000, "name": "挪威"},
    {"lid": 18300000000, "name": "瑞典"},
    {"lid": 18700000000, "name": "丹麦"},
    {"lid": 16800000000, "name": "俄罗斯"},
    {"lid": 19200000000, "name": "土耳其"},
    {"lid": 15700000000, "name": "摩纳哥"},
    {"lid": 15900000000, "name": "梵蒂冈"},
    {"lid": 16200000000, "name": "安道尔公国"},
    {"lid": 18500000000, "name": "芬兰"},
    {"lid": 18600000000, "name": "冰岛"},
    {"lid": 15500000000, "name": "卢森堡"},
    {"lid": 15200000000, "name": "列支敦士登"},
    {"lid": 17600000000, "name": "斯洛文尼亚"},
    {"lid": 17100000000, "name": "波兰"},
    {"lid": 14900000000, "name": "爱尔兰"},
    {"lid": 19400000000, "name": "马耳他"}
];
var northAmerica = [
    {"lid": 10000000000, "name": "美国"},
    {"lid": 10100000000, "name": "加拿大"},
    {"lid": 10200000000, "name": "墨西哥"}
];
var oceania = [
    {"lid": 30500000000, "name": "澳大利亚"},
    {"lid": 30600000000, "name": "新西兰"},
    {"lid": 31700000000, "name": "斐济"}
];
var asia = [
    {"lid": 21800000000, "name": "马尔代夫"},
    {"lid": 21300000000, "name": "尼泊尔"},
    {"lid": 21000000000, "name": "印度尼西亚"},
    {"lid": 21200000000, "name": "不丹"},
    {"lid": 21100000000, "name": "泰国"},
    {"lid": 20400000000, "name": "柬埔寨"},
    {"lid": 20000000000, "name": "日本"},
    {"lid": 20700000000, "name": "马来西亚"},
    {"lid": 20200000000, "name": "越南"},
    {"lid": 19900000000, "name": "韩国"},
    {"lid": 20900000000, "name": "新加坡"},
    {"lid": 21500000000, "name": "印度"},
    {"lid": 24300000000, "name": "阿拉伯联合酋长国"},
    {"lid": 21700000000, "name": "斯里兰卡"},
    {"lid": 19700000000, "name": "中国"},
    {"lid": 20100000000, "name": "菲律宾"},
    {"lid": 23500000000, "name": "以色列"}
];
var africa = [
    {"lid": 25800000000, "name": "肯尼亚"},
    {"lid": 29800000000, "name": "南非共和国"},
    {"lid": 30200000000, "name": "毛里求斯"}
];
<#-- -->
$("#btn-add-doyen").click(addDoyen);
$("#btn-remove-doyen").click(delDoyen);
$("#btn-add-num").click(setNum);

$("#btn-add-tripLocations").click(function () {
    var locations = $("#tripLocations").val();
    var uid = $("#tripUid").val();

    $("#btn-add-tripLocations").attr("disabled", "disabled");

    var data = {
        locations: locations, uid: uid
    };

    $.ajax({
        url: '/trip/doyen/locations/',
        data: data,
        type: 'POST',
        dataType: 'json',
        success: function (ret) {
            if (ret.code == 0) {
                alert('保存成功！');
                window.location.href = '/search/doyen/';
            } else {
                alert('保存失败');
            }
            $("#btn-add-tripLocations").removeAttr("disabled");
        },
        error: function () {
            alert('操作失败!联系管理员!');
            $("#btn-add-tripLocations").removeAttr("disabled");
        }
    });
});

var tempTripUid = '';
function focusTripUid(src) {
    tempTripUid = $(src).val();
}

var inputDom = $("#tripLocations");
var tagsBox = $(".select-label");
tagsBox.click(function (event) {
    if (/^(span)$/i.test(event.target.tagName)) {
        var target = $(event.target);
        target.toggleClass("selected");
//        var result = target.text().trim();
        var result = target.data('lid');

        if (target.hasClass("selected")) {
            var arr = [];
            if (inputDom.val() != '') {
                arr = inputDom.val().trim().split(",");
            }

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

function blurTripUid(src) {
    if ($(src).val()) {
        var tripUid = $(src).val();

        if (tripUid != tempTripUid) {
            var locations = '';

            var data = {uid: tripUid};
            $.ajax({
                url: '/trip/doyen/locations/',
                data: data,
                type: 'GET',
                dataType: 'json',
                success: function (ret) {
                    if (ret.code == 0) {
                        locations = ret.data.locations;
                        $("#tripLocations").val(locations);

                        var locationHtml = [];

                        locationHtml.push(getTripLocationHtml('欧洲', locations, europe));
                        locationHtml.push('<br/>');
                        locationHtml.push(getTripLocationHtml('北美洲', locations, northAmerica));
                        locationHtml.push('<br/>');
                        locationHtml.push(getTripLocationHtml('大洋洲', locations, oceania));
                        locationHtml.push('<br/>');
                        locationHtml.push(getTripLocationHtml('亚洲', locations, asia));
                        locationHtml.push('<br/>');
                        locationHtml.push(getTripLocationHtml('非洲', locations, africa));

                        $("#tripLocationsDiv").html(locationHtml.join(''));
                    }
                },
                error: function () {
                }
            });
        }
    } else {
        $("#tripLocations").val('');
        $("#tripLocationsDiv").html('');
    }

}

function getTripLocationHtml(title, doyenLocations, locations) {
    var html = [];
    for (var i = 0; i < locations.length; i++) {
        var loc = locations[i];
        if (doyenLocations.indexOf(loc.lid) != -1) {
            html.push('<span class="lab selected" data-lid="' + loc.lid + '">' + loc.name + '</span>');
        } else {
            html.push('<span class="lab" data-lid="' + loc.lid + '">' + loc.name + '</span>');
        }
    }
    return html.join('');
}


function delDoyen(event) {

    var locationId = $("#mylocationid").val();
    var uid = $("#userId").val();

    if (locationId == "" || uid == "") {
        alert("没填啊");
        return;
    }

    $.post("/doyen/", {
        _method: "DELETE", uid: uid, locationId: locationId
    }, function (data) {
        window.location = "/search/doyen/"
    });
}
function addDoyen(event) {
    var locationId = $("#mylocationid").val();
    var uid = $("#userId").val();

    if (locationId == "" || uid == "") {
        alert("没填啊");
        return;
    }


    $.post("/doyen/", {
        uid: uid, locationId: locationId
    }, function (data) {

        window.location = "/search/doyen/"

    });

}
function setCareer(uid) {
    var cid = "#career_" + uid;
    var career = $(cid).val();
    $.post("/career/", {
        uid: uid, career: career
    }, function (data) {
        alert("设置成功")
    });

}

function setInnerDoyen(uid, setOrNo) {
    $.post("/doyen/inner/", {
        uid: uid, inner: setOrNo
    }, function (data) {
        if (setOrNo) {
            alert("设置成功")
            window.location = "/search/doyen/"
        } else {
            alert("取消成功")
            window.location = "/search/doyen/"
        }
    });

}
function setRank(uid) {
    var cid = "#rank_" + uid;
    var rank = $(cid).val();
    $.post("/doyen/rank/", {
        uid: uid, rank: rank
    }, function (data) {
        alert("设置成功")
    });

}
function setProfileBid(uid) {
    var cid = "#profile_bid_" + uid;
    var bid = $(cid).val();
    $.post("/doyen/profilebid/", {
        uid: uid, bid: bid
    }, function (data) {
        alert("设置成功")
    });

}

function setNum(event) {
    var countryNum = $("#countryNum").val();
    var cityNum = $("#cityNum").val();
    var uid = $("#userId2").val();
    $.post("/doyen/num/", {
        uid: uid, countryNum: countryNum, cityNum: cityNum
    }, function (data) {
        window.location = "/search/doyen/"
    });

}

function addCountry(countryId) {
    console.log($('#tripLocations').length);
    var tripLocations = $('#tripLocations');
    //1,2,3,4,5
    var tripLocationArray = [];
    if (tripLocations.val() != '') {
        tripLocationArray = tripLocationArray.concat(tripLocations.val().split(','));
    }
    if ($.inArray(countryId + '', tripLocationArray) == -1) {
        tripLocationArray.push(countryId + '');
    }
    tripLocations.val(tripLocationArray.join(','));
}

$('#locationId').change(function () {
            var curloc = $('#locationId').val();
            if (curloc == -1) {
                $('#mylocationname').val("");
                $('mylocationid').val(0);
            } else {
                $('#mylocationname').val($("#locationId").find("option:selected").text());
                $('#mylocationid').val(curloc);
            }

        }
);

$('#mylocationname').change(function () {
    $('#mylocationid').val("");
}).autocomplete({
            serviceUrl: '/a/location/suggest/?level=1',
            paramName: "input",
            minChars: 2,
            onSelect: function (suggestion) {
                $('#mylocationid').val(suggestion.data);
            }
        });

$(".sign").each(function () {
    var _this = $(this);
    $(this).click(function () {
        var uid = _this.data("uid");
        $("#m" + uid).modal({
            backdrop: true,
            keyboard: true,
            show: true
        })

    });

});


</script>
<#include "commons/footer.ftl" />


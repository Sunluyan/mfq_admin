<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/plugin/bootstrap-typeahead.js?20130407']>



<#include "commons/header.ftl" />
<#assign toolbar="search" />
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
<div class="container">
  <div class="container-fluid">

    <div class="row-fluid">
      <#assign tab="${item}" />
      <#include "/search/tabs.ftl" />
      <div class="span10">
        <form class="well form-inline" action="/blog/" method="GET">
          <select id="type-select" name="field" class="select input-medium">
            <option value="bid">bid</option>
          </select>
          <input name="bid" type="text" class="input-medium search-query">
          <button type="submit" class="btn">查询</button>
        </form>

        <div class="well">
          <legend>博文信息</legend>
          <fieldset>
            <input id="bid" value="${blog.bid}" type="hidden">
            <input id="uid" value="${blog.uid}" type="hidden">
            <div class="control-group">
              <label class="control-label">标题：
                <a href="http://shijiebang.com/u${blog.uid}/blog-${blog.bid}/" target="_blank">${blog.title}</a>
                <span id="btn-status" class="btn btn-danger" >${(blog.status=="Normal")?string('删除','恢复')}</span>
                <input id="status" value="${(blog.status=="Normal")?string('Deleted','Normal')}" type="hidden"/>
                <span id="btn-index" class="btn btn-danger" >点我加分（推荐到首页）</span>
              </label>

              <input id="blog-rank" value="${blog.rank}" type="input"/>
              <span id="btn-rank-update" class="btn" >修改评分</span>
              <br>注:默认分为0分，默认情况下按照赞数排序,评分将影响H5推荐博文的排序
            </div>
          </fieldset>
        </div>

        <div class="control-group">
          <legend><label class="control-label" for="city">涉及地区 <i class="icon-star icon-blue"></i></label> </legend>
          <div class="controls" id="cityControls">
            <input type="text" id="city" class="input-xxlarge" autocomplete="off" placeholder="请输城市名，可以有多个"><i class="icon-info-sign draggable"></i>
            <button type="button" id="btn-locationIds" class="btn-warning">保存设置</button>
            <ul id="cities" class="tags inline">
              <#list cities as city>
              <li class="tag ui-state-default data-cid-index" data-cid="${city.id!}">
                ${city.word!}
                <a class="close block" data-dismiss="alert">×</a>
              </li>
              </#list>
            </ul>
          </div>
          <div id="locationReturn" class="alert alert-error hide">
            <a class="close" data-dismiss="alert">×</a>                                                                                      `
            <strong></strong>
          </div>
        </div>


        <div class="control-group well">
          <legend><label class="control-label" for="city">涉及poi <i class="icon-star icon-blue"></i></label> </legend>
          <div class="controls" id="poiControls">
            <input type="text" id="poi" class="input-xxlarge" autocomplete="off" placeholder="请输poi名，可以有多个"><i class="icon-info-sign draggable"></i>
            <button type="button" id="btn-poiIds" class="btn-warning">保存设置</button>
            <ul id="pois" class="tags inline">
              <#list pois as poi>
              <li class="tag ui-state-default data-cid-index" data-pid="${poi.id!}">
                ${poi.word!}
                <a class="close block" data-dismiss="alert">×</a>
              </li>
              </#list>
            </ul>
          </div>
          <div id="poiReturn" class="alert alert-error hide">
            <a class="close" data-dismiss="alert">×</a>
            <strong></strong>
          </div>
        </div>

        <div class="control-group well" style="border:solid 1px #efefef;padding: 20px;">
          <h2>问题标签设置功能</h2>
          <div class="select-label">
            <legend>主题</legend>
            <#list ['海岛','蜜月','购物','美食','摄影','历史','亲子','带父母','艺术','湖泊','徒步','骑行','温泉','赏花','动物','医疗','潜水','滑雪','游学','体育赛事','海岸','城市','小镇','建筑','探险','体验','高尔夫','狩猎','垂钓','第一次出游','深度出游','名山','河谷','国家公园','军事','展览','人文','宗教','自然风光','名胜古迹'] as t>
              <span class="lab ${(blog.tags?contains(t))?string('selected','')}">${t}</span>
            </#list>
            <legend>月份</legend>
            <#list ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'] as t>
              <span class="lab ${(blog.tags?contains(t))?string('selected','')}">${t}</span>
            </#list>
            <legend>旅游要素</legend>
            <#list ['签证','国际机票','火车','住宿','餐饮','通讯','购物','游览','娱乐','语言','安全','季节','天气','出入境','市内交通','节日','旅行贴士','地图','优惠信息','退税','廉价机票','门票','保险','行程','换汇','自驾','护照','国际交通','城际交通','市内交通'] as t>
              <span class="lab ${(blog.tags?contains(t))?string('selected','')}">${t}</span>
            </#list>

          </div>
           <div class="control-group">
              <input class="input-xxlarge search-query" id="tags" value="${blog.tags}" name="tags" type="text"  >
              <button type="button" id="btn-tags" class="btn-warning">设置标签</button>
              <br/>
              <span class="text-info" style="color: red">请谨慎修改!!!亲，除了点标签，你还可以手动修改,用半角逗号分隔即可</span>
              <div id="TagsReturn" class="alert alert-error hide">
                <a class="close" data-dismiss="alert">×</a>
                <strong></strong>
              </div>
          </div>
        </div>

        <#assign markers={"垃圾":-1,"非精品":0,"拍的好":1,"写得好":2,"超实用":3,"晒得好":4,"精华":5,"美食家":6,"潮爆了":7}>
        <div class="control-group form-inline well">
          <legend>精品设置-现在是<span id="marker_html"></span> </legend>
          <#list markers?keys as k>
          <label class="radio"><input type="radio" name="marker" value="${markers[k]}" ${(blog.marker==markers[k])?string("checked","")} >${k}</label>
          </#list>
          <button type="button" id="btn-marker" class="btn-warning">设置</button>
          <div class="text-important">垃圾文章不被搜索列表推荐（但是依然可以单独访问，如果要彻底删除文章，点击此页上面删除文章</div>
          <div id="marker-return" class="text-important" >
          </div>
        </div>
      </div>
  </div>

</div>

<script type="text/javascript">

  $("#btn-tags").click(
    function(){
      var bid = $("#bid").val();
      var uid=$("#uid").val();
      var tags=$.trim($("#tags").val());
      $.post("/"+uid+"/blog/"+bid+"/tags/",
        {"tags":tags},
        function (result) {
          if(result=='true') {
            $("#TagsReturn strong").first().html("更新标签成功")
          }else{
            $("#TagsReturn strong").first().html("更新标签失败")
          }
          $('#TagsReturn').show()
        }
        );
    }
    );



  $("#btn-status").click(set_status);

  function set_status() {

    var bid = $("#bid").val();
    var status = $("#status").val();

    do_execute_status(bid, status);
  }

  function do_execute_status(bid, status) {

    $.post(
      "/blog/" + bid + "/" + status + "/",

      function () {
        if(status=='Normal') {
          $("#status").attr("value","Deleted")  ;
          $("#btn-status").html('删除');
        } else{
          $("#status").attr("value","Normal")  ;
          $("#btn-status").html('恢复');
        }
      }
      );
  }






  (function (window, $, undefined) {


//      var city = $('#city');
//      var cityId = $('#cityId');
//      var cityControls = $('#cityControls');
//
//
//      var poi = $('#poi');
//      var poiId = $('#poiId');
//      var poiControls = $('#poiControls');
//
//
//
//
//
//

$('#city').typeahead({
  source: function (typeahead, query) {
    if($.trim(query).length<2)return;
    $.getJSON('/trip/location/suggest/', {'q': query,"level":0}, function (data) {
      typeahead.process(data);
    });
  },
          onselect: function (obj) { //obj.id,obj.value
            $('#cities').append('<li class="tag ui-state-default data-cid-index" data-cid="' + obj.id + '">'
              + obj.word + '<a class="close block" data-dismiss="alert">×</a></li>');
              //拼city数据
              $('#city').val("");

            },
            items:10,
            matcher:function(item){return true},
            property: "word"
          });

$('#poi').typeahead({
  source: function (typeahead, query) {
    if($.trim(query).length<2)return;
    $.getJSON('/trip/poi/suggest/', {'q': query}, function (data) {
      typeahead.process(data);
    });
  },
          onselect: function (obj) { //obj.id,obj.value
            $('#pois').append('<li class="tag ui-state-default data-cid-index" data-pid="' + obj.poiid + '">'
              + obj.value + '<a class="close block" data-dismiss="alert">×</a></li>');
              //拼city数据
              $('#poi').val("");

            },
            matcher:function(item){return true},
            items:10,
            property:"value"
          });




})(window, jQuery);

function getId(spans, key) {
  return spans.map(function () {
    return $(this).attr(key);
  }).get().join(',');
}


$("#btn-locationIds").click(
  function () {
    var bid = $("#bid").val();
    var uid = $("#uid").val();
    var cityIdStr = getId($('.data-cid-index'), 'data-cid');
    $.post("/" + uid + "/blog/" + bid + "/location/",
      {"locationIdStr": cityIdStr},
      function (result) {
        if (result == 'true') {
          $("#locationReturn strong").first().html("更新涉及地区成功")
        } else {
          $("#locationReturn strong").first().html("更新涉及地区失败")
        }
        $('#locationReturn').show()
      }
      );
  }
  );

$("#btn-poiIds").click(
  function () {
    var bid = $("#bid").val();
    var uid = $("#uid").val();
    var pidIdStr = getId($('.data-cid-index'), 'data-pid');
    $.post("/" + uid + "/blog/" + bid + "/poi/",
      {"poiIdStr": pidIdStr},
      function (result) {
        if (result == 'true') {
          $("#poiReturn strong").first().html("更新涉及poi成功")
        } else {
          $("#poiReturn strong").first().html("更新涉及poi失败")
        }
        $('#poiReturn').show()
      }
      );
  }
  );
$("#btn-marker").click(
  function () {
    var bid = $("#bid").val();
    var uid = $("#uid").val();
    var marker=$('input[name="marker"]:checked').val();
    $.post("/" + uid + "/blog/" + bid + "/marker/",
      {"marker": marker},
      function (result) {
        if (result == 'true') {
          $("#marker-return").html("更新精品成功")
          $("#marker_html").html("精品")
        } else {
          $("#marker-return").html("更新精品失败")
          $("#marker_html").html("非精品")
        }
      }
      );
  }
  );

$("#btn-index").click(

  function () {
    if(!confirm("确定因为推荐到首页加分吗？")){
      return;
    }
    var bid = $("#bid").val();
    var uid = $("#uid").val();
    $.post("/" + uid + "/blog/" + bid + "/index/",
      function (result) {

      }
      );
  }
  );

$("#btn-rank-update").click(

  function () {
    if(!confirm("确定修改博文的评分吗？")){
      return;
    }
    var bid = $("#bid").val();
    var uid = $("#uid").val();
    var rank = $("#blog-rank").val();
    $.post("/" + uid + "/blog/" + bid + "/rank/",
      {rank:rank},
      function (result) {

      }
      );
  }
  );


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

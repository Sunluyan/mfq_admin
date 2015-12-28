<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />

<script type="text/javascript" src="/static/js/plugin/jquery-ui.js"></script>

<#--
<script type="text/javascript" src="/static/js/jquery.autocomplete.js"></script>
-->

<div class="container">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />

        <div class="span10">
            <form class="well form-inline" method="POST">
                <input id="base62-src" type="text" class="input-medium" placeholder="原文">
                <select id="base62-type" name="field" class="select input-medium">
                    <option value="1">decode()</option>
                    <option value="0">encode()</option>
                </select>
                <input id="base62-dest" type="text" class="input-medium search-query">
                <button id="btn-base62" type="button" class="btn">转换</button>
            </form>

            <div class="well">
<#--
              <div class="input-prepend row-fluid">
                <span class="add-on">地区查询</span>
                <input id="locationname" type="text" class="input-xlarge" placeholder="请输入地区名称">
                <span id="locationid" class="input-medium uneditable-input"></span>
              </div>
-->
              <div class="input-prepend row-fluid">
                <span class="add-on">地区反查</span>
                <input id="rlocationid" type="text" class="input-medium" placeholder="请输入地区ID">
                <button id="btn-location" type="button" class="btn btn-primary">查询</button>
                <span id="rlocationname" class="input-xxlarge uneditable-input"></span>
              </div>

                <br/>
              <br/>
              <div class="input-prepend row-fluid">
                <span class="add-on">地区POI查询</span>
                <input id="poiname" type="text" class="input-meidium" placeholder="请输入地区POI名称">
                <span id="poishow" class="input-large uneditable-input"></span>
                <span id="poiid" class="input-medium uneditable-input"></span>
                <span id="poilocationid" class="input-small uneditable-input"></span>
              </div>

            </div>

        </div>

    </div>
</div>

<script type="text/javascript">
    $("#btn-base62").click(function(){
        var src = $("#base62-src").val();
        var type = $("#base62-type").val();
        var url = type == "0" ? "/search/utility/base62encode/":"/search/utility/base62decode/";
        url = url + src +"/";
        $.get(url,{},function(data){
            $("#base62-dest").val(data);
        });
    });

    $('#locationname').change(function(){
      $('#locationid').html("");
    }).autocomplete({
      serviceUrl: '/a/location/suggest/',
      paramName: "input",
      minChars: 2,
      onSelect: function (suggestion) {
        $('#locationid').html( suggestion.data);
      }
    });


    function querylocation(event){
      var locationId = $("#rlocationid").val();

      if(locationId == "" || locationId == "0" ){
        alert("请先输入地区ID");
        return;
      }

      $.post(
          "/a/location/byid/",
          {lid:locationId },
          function(data){
            $("#rlocationname").html(data)
          }
      );
    }


    $("#btn-location").click(querylocation);


    $('#poiname').autocomplete({
      source: function(request, response) {
        $.ajax({
          url: "http://is.shijiebang.com/poi/suggest/",
          dataType: "jsonp",
          data: {
            q : request.term,
            datatype: '101,102,103,104',
            count : 10
          },
          success: function( data ) {
            response( $.map(  data.data, function( item ) {
              return {
                value: item.longlocation,
                word: item.longlocation,
                id: item.id,
                locationid: item.location,
                longword: item.longlocation
              }
            }));
          }
        });
      },
      minLength: 2,
      select: function(event,ui){
        item = ui.item;
        $('#poilocationid').html(ui.item.id);
        $('#poiid').html(ui.item.locationid);
        $('#poishow').html(ui.item.longword);
        return false;
      }
    });




</script>

<#include "commons/footer.ftl" />
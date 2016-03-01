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
    <ul class="nav nav-pills">
        <li role="presentation" class="active" index="1"><a href="#">订单</a></li>
        <li role="presentation" index="2"><a href="#">充值</a></li>
        <img src="/static/img/loading.gif" class="loading" width="20" height="20"
             style="margin-top: 6px;margin-left: 5px; display:none;">
    </ul>
    <div class="container">


        <div class="row-fluid">

            <div  id="context">

                <legend>数据加载中...</legend>


            </div>
        </div>

    </div>
</div>
<script type="text/javascript">

    var tables =
    {
        tableindex: 1,
        tableone: false,
        tabletwo: false,
        tablethree: false
    }

    $(document).on("click", "li[role='presentation']", function () {
        //去掉别人的tab效果，给自己加上。并把相应的table显示出来   1
        $(".table-bordered").hide();
        $("li[role='presentation']").removeClass("active")
        $(this).addClass("active")
        tables.tableindex = $(this).attr("index")
        $(".table-bordered[index='" + tables.tableindex + "']").show()
		var cx = $("#context");
        if (tables.tableindex == 1) {
            cx.load("/order/budget/data/?c=1");
        }
        else if (tables.tableindex == 2) {
            cx.load("/order/budget/data/?c=2");
        }

    })

    $(function () {
        $("#context").load("/order/budget/data/?c=1")

    })



    $(".loading").ajaxStart(function () {
        $(this).show();
    })
    $(".loading").ajaxStop(function () {
        $(this).hide();
    })



</script>
<#include "commons/footer.ftl" />


<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<style>
.left{
    width:45%;
    min-width: 400px;
    height:250px;
    margin-right: 50px;
    margin-bottom: 50px;
    float:left;
}

.right{
    width:50%;
    min-width: 400px;
    height:250px;
    margin-bottom: 50px;
    float:left;
    margin-left: -40px;
}

.left-child {
    width: 45%;
    height: 50%;
    margin: 10px;
    border: 1px solid #888;
    float: left;
}

.right-child{
    width:45%;
    height:107%;
    float:left;
    margin:10px;
    border:1px solid #888;
}

h4{
    padding: 5px 15px;
    font-weight: normal;
}

strong{
    display: block;
    text-align: right;
    padding-right: 20px;
    font-size: 20px;
    margin-top: 50px;
}

h2{
    text-align: center;
    margin-top: 50px;
    font-size: 44px;
}

</style>

<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<div class="container" id="enlarge-body">

    <div>
        <h3>现在是<span class="time-now"></span></h3>
        <hr>

        <form action="/home/" method="post">
        <div class="controls">
            <div class="input-append date" id="date1" name="begin"
                 data-date="${begin!}" data-date-format="yyyy-mm-dd">
                <input class="span2" id="dateStart" name="begin" size="16" type="text"
                       value="${begin!}">
                <span class="add-on"><i class="icon-th"></i></span>
            </div>
            －
            <div class="input-append date" id="date2" name="end"
                 data-date="${end!}" data-date-format="yyyy-mm-dd">
                <input class="span2" id="dateEnd" name="end" size="16" type="text"
                       value="${end!}">
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            <input type="submit" class="btn btn-primary" value="查询"/>
        </div>




        </form>

    <div class="left">
        <div class="left-child">
            <h4>昨日新增用户(设备激活):</h4>
            <strong class="new-user">${a}</strong>
        </div>
        <div class="left-child">
            <h4>昨日新增激活用户(注册)：</h4>
            <strong class="new-user">${b}</strong>
        </div>
        <div class="left-child">
            <h4>昨日下单量:</h4>
            <strong class="new-user">${c}</strong>
        </div>
        <div class="left-child">
            <h4>昨日成交量:</h4>
            <strong class="new-user">${d}</strong>
        </div>
    </div>

    <div class="right">
        <div class="right-child">
            <h4>当前合作医院:</h4>
            <h2>
                ${e} <span style="font-size: 14px;font-style: normal;">  家</span>
            </h2>
        </div>
        <div class="right-child">
            <h4>当前上线产品:</h4>
            <h2>
                ${f} <span style="font-size: 14px;font-style: normal;">  项</span>
            </h2>
        </div>
    </div>

</div>

</div>

<script type="text/javascript" language="javascript">

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

    function show_cur_times(){
        var date_time = new Date();
        var week;
        switch (date_time.getDay()){
            case 1: week="星期一"; break;
            case 2: week="星期二"; break;
            case 3: week="星期三"; break;
            case 4: week="星期四"; break;
            case 5: week="星期五"; break;
            case 6: week="星期六"; break;
            default:week="星期天"; break;
        }
        var year = date_time.getFullYear();
        if(year<10){
            year="0"+year;
        }
        var month = date_time.getMonth()+1;
        if(month<10){
            month="0"+month;
        }
        var day = date_time.getDate();
        if(day<10){
            day="0"+day;
        }
        var hours =date_time.getHours();
        if(hours<10){
            hours="0"+hours;
        }
        var minutes =date_time.getMinutes();
        if(minutes<10){
            minutes="0"+minutes;
        }
        var seconds=date_time.getSeconds();
        if(seconds<10){
            seconds="0"+seconds;
        }
        var date_str = year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds+" "+week;
        $(".time-now").html(date_str);
    }

    //设置1秒调用一次show_cur_times函数
    setInterval("show_cur_times()",1000);
    show_cur_times();
</script>

<#include "commons/footer.ftl" />

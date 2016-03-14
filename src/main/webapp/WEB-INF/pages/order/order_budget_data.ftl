

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

            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
            </div>
        </form>

        <div class="row-fluid">


            <div  id="context">


                <#if msg != null && msg != ''>${msg}</#if>
                <legend>订单列表（共<span class="totalpage">1</span>页，当前第<span class="page">1</span>页，共<span
                        class="total">0</span>条）

                    <div class="next" onselectstart="return false;">></div>
                    <div class="prev" onselectstart="return false;"><</div>
                </legend>

                <!-- -------------------------三个table------------------------------- -->
                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>订单号</td>
                        <td>用户ID</td>
                        <td>姓名</td>
                        <td>手机号</td>
                        <td>验证码</td>
                        <td>订单金额</td>
						<td>订单付款</td>
						<td>保险</td>
                        <td>订单状态</td>
                        <td>操作</td>
                    </tr>

                    <#list orders as order>

                        <tr class="tr-unsee">
                            <td>${order.orderNo}</td>
                            <td>${order.uid}</td>
                            <td>${order.realname}</td>
                            <td>${order.mobile}</td>
                            <td>${order.code}</td>
                            <td>${order.orderMoney}</td>
                            <td>${order.onlinePay}</td>
                            <td>保险</td>
                            <td>${order.orderStatus}</td>
                            <td class="oparite"><a href="/user/certify/check/" target="_blank" data-id=''>详情</a></td>
                        </tr>

                    </#list>
                </table>


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


    })


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
//        getData(page)
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


</script>




        <form class="form-inline well" id="form1" action="#" method="GET">

            <div class="form-group">
                <label>用户手机号</label>
                <input type="text" class="form-control" id="mobile" name="mobile" value="${mobile}" size="20">
            </div>
            <div class="form-group">
                <label>姓名</label>
                <input type="text" class="form-control" id="uname" name="uname" value="${uname}" size="20">
            </div>

            <div class="form-group">
                <label>订单号</label>
                <input type="text" class="form-control" id="orderNo" name="orderNo" value="${orderNo}" size="20">
            </div>

            <br>
            <div class="form-group">
                <label for="exampleInputName1">充值时间</label>
                <div class="input-append date" id="date1" name="date1" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimefrom" name="ob" size="16" style="width: 85%" type="text" value="${ob}">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                －
                <div class="input-append date" id="date2" name="date2" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimeto" name="oe" size="16" style="width: 85%" type="text" value="${oe}">
                    <span class="add-on"><i class="icon-th"></i></span>

                </div>
                <input type="hidden" name="page" class="realpage" id="page" value="${page}">
            </div>
            <br>

            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="button" id="sub"  class="btn btn-info btn-sm" value="查询">
            </div>
        </form>

        <div class="row-fluid">


            <div  id="context">


                <#if msg != null && msg != ''>${msg}</#if>
                <legend>订单列表（当前第${page}页）

                    <div class="next" id="npage" onselectstart="return false;">></div>
                    <div class="prev" id="ppage" onselectstart="return false;"><</div>
                </legend>

                <!-- -------------------------三个table------------------------------- -->
                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>订单号</td>
                        <td>用户ID</td>
                        <td>姓名</td>
                        <td>手机号</td>
                        <td>订单金额</td>
                        <td>状态</td>
                        <td>时间</td>
                        <td>操作</td>
                    </tr>

                    <#list pays as pay>

                        <tr class="tr-unsee">
                            <td>${pay.orderNo}</td>
                            <td>${pay.uid}</td>
                            <td>${pay.username}</td>
                            <td>${pay.mobile}</td>
                            <td>${pay.amount}</td>
                            <td>
                                <#if pay.status = 1>
                                未支付
                                    <#elseif pay.status = 2>
                                支付完成
                                </#if>
                            <td>
                            ${pay.callbackAt?string("yyyy-MM-dd")!}
                            </td>
                            <td class="oparite">
                                <#--<a href="/user/certify/check/" target="_blank" data-id=''>详情</a>-->
                            </td>
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



    $('#npage').click(function(){
        p = parseInt($("#page").val());
        if(p<0){
            return;
        }
        $("#page").val(p+1);
        var form_data = $('#form1').serialize();
        $("#context").load("/order/budget/data/?c=2",form_data)
    });

    $('#ppage').click(function(){
        p = parseInt($("#page").val());
        if(p<2){
            return;
        }
        $("#page").val(parseInt(p)-1);
        var form_data = $('#form1').serialize();
        $("#context").load("/order/budget/data/?c=2",form_data)
    });

    $("#sub").click(function(){
        var form_data = $('#form1').serialize();
        $("#context").load("/order/budget/data/?c=2&p=t",form_data)
    });

</script>


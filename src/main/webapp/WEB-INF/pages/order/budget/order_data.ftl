
        <form class="form-inline well" method="post" action="/order/budget/data/?c=1" id="form1">
            <div class="form-group">
                <label for="exampleInputName2">用户名</label>
                <input type="text" class="form-control" id="uname" name="uname" value="${uname}" size="20">
            </div>

            <div class="form-group">
                <label>用户手机号</label>
                <input type="text" class="form-control" id="mobile" name="mobile" value="${mobile}" size="20">
            </div>
            <div class="form-group">
                <label>订单号</label>
                <input type="text" class="form-control" id="orderNo" name="orderNo" value="${orderNo}" size="20">
            </div>
            <div class="form-group">
                <label>产品名</label>
                <input type="text" class="form-control" id="pname" name="pname" value="${pname}" size="20">
            </div>
            <div class="form-group">
                <label>状态</label>
                <select name="status">
                    <option value="0">所有</option>
                    <#list order_status as s>
                        <option value="${s.id}" <#if (status == (s.id))>selected</#if>>${s.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label>医院</label>
                <select name="hid">
                    <option value="0">所有</option>
                    <#list hospitals as h>
                        <option value="${h.id!}" <#if (hid == (h.id))>selected</#if>>${h.name!}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label>验证码</label>
                <input type="text" class="form-control" id="code" name="code" value="${code}" size="20">
            </div>
            <br>
            <div class="form-group">
                <label for="exampleInputName1">时间</label>
                <div class="input-append date" id="date1" name="date1" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimefrom" name="ob" size="16" style="width: 85%" type="text" value="${ob}">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                －
                <div class="input-append date" id="date2" name="date2" data-date-format="yyyy-mm-dd">
                    <input class="span2" id="applytimeto" name="oe" size="16" style="width: 85%" type="text" value="${oe}">
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                <input type="hidden" name="page" id="page" class="realpage"  value="#{page!}">
            </div>
            <br>

            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="button" id="sub" class="btn btn-info btn-sm" value="查询">
            </div>
        </form>

        <div class="row-fluid">


            <div  id="context">


                <#if msg != null && msg != ''>${msg}</#if>
                <legend>订单列表(当前页数#{page})
                    <input type="hidden" value="#{page}" id="page"/>
                    <div class="next" onselectstart="return false;">></div>
                    <div class="prev" onselectstart="return false;"><</div>
                </legend>

                <!-- -------------------------三个table------------------------------- -->
                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>订单号</td>
                        <td>产品名</td>
                        <td>UID</td>
                        <td>姓名</td>
                        <td>手机号</td>
                        <td>医院</td>
                        <td>验证码</td>
                        <td>订单金额</td>
						<td>订单付款</td>
						<td>保险</td>
                        <td>下单时间</td>
                        <td>订单状态</td>
                        <td>订单类型</td>

                    </tr>

                    <#list orders as order>

                        <tr class="tr-unsee">
                            <td><a href="/order/finance/detail/?order=${order.orderNo}"> ${order.orderNo} </a></td>
                            <td>${order.pName}</td>
                            <td>${order.uid}</td>
                            <td>${order.realname}</td>
                            <td><a href="/user/finance/?uid=${order.uid}"> ${order.mobile}</a></td>
                            <td>${order.hospitalName}</td>
                            <td>${order.code}</td>
                            <td>${order.orderMoney}</td>
                            <td>${order.onlinePay}</td>
                            <td>${order.policyStatus}</td>
                            <td>${order.createdAt?string("yyyy-MM-dd")!}</td>
                            <td>${order.orderStatus}</td>
                            <td>
                            <#if order.orderType = 0>
                            全款
                                <#elseif order.orderType =2>
                               分期
                            </#if></td>

                        </tr>

                    </#list>
                </table>


            </div>
        </div>

<script type="text/javascript">
    $('.next').click(function(){
        p = parseInt($("#page").val());
        if(p<0){
            return;
        }
        $("#page").val(p+1);
        var form_data = $('#form1').serialize();
        $("#context").load("/order/budget/data/?c=1",form_data)
    });


    $('.prev').click(function(){
        p = parseInt($("#page").val());
        if(p<2){
            return;
        }
        $("#page").val(parseInt(p)-1);
        var form_data = $('#form1').serialize();
        $("#context").load("/order/budget/data/?c=1",form_data)
    });

    $('#sub').click(function(){
        $("#context").load("/order/budget/data/?c=1&p=t",$('#form1').serialize())
    })

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




</script>


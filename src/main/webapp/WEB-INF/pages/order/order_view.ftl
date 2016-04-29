<#assign _title="美分期管理后台-订单管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />
<style>
    .form-inline {
        line-height: 35px;
    }

    .bg-success {
        color: #FFF;
        background-color: #337AB7;
        padding: 15px;
    }

    .bg-danger {
        color: #FFF;
        background-color: #CE4844;;
        padding: 15px;
    }
</style>

<div class="container" id="enlarge-body">
<#if msg != null>
    <#if success>
        <p class="bg-success">${msg}</p>
    <#else>
        <p class="bg-danger">${msg}</p>
    </#if>
</#if>
    <div class="container">
        <h3>${realname}</h3>
        <form class="form-inline well" action="/order/view/" method="POST">
            <input type="hidden" value="${order.orderNo}" name="orderNo"/>
            <div class="input-group">
                <div class="input-group-addon">订&nbsp;&nbsp;单&nbsp;&nbsp;号：${order.orderNo}</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">订单总价：${order.price}元</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">下单用户：${realname}</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">手机号码：${mobile}</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">备用电话：${contact}</div>
            </div>

            <div class="input-group">
                <div class="input-group-addon">验&nbsp;&nbsp;证&nbsp;&nbsp;码：${order.securityCode}</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">订单状态：
                ${statusname}
                <#if hasright=1 && order.status = 1>
                    &nbsp;&nbsp;<input type="submit" class="btn btn-info btn-sm" value="确认使用">
                </#if>
                <#if hasright=1 && order.status = 0>
                    &nbsp;&nbsp;<input type="button" data-toggle="modal" data-target="#myModal"
                                       class="btn btn-info btn-sm" value="用户已付款">
                </#if>
                </div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">在线支付：${order.onlinePay}元</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">到院支付：${order.hospitalPay}元</div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">优惠券码：${order.couponNum}</div>
            </div>

            <div class="input-group">
                <div class="input-group-addon">生单时间：${createdAt}
                </div>
            </div>

            <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close"
                                    data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                确认用户付款
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div>
                                &nbsp;&nbsp;&nbsp;&nbsp;<label for="payNo">流水号:</label>

                                <input type="text" class="input" id="payNo" name="payNo" placeholder="流水号">
                            </div>
                            <div>
                                <label for="tpp">付款方式:</label>
                                <select name="tpp" id="tpp">
                                    <option value="POS">POS</option>
                                    <option value="HOSPITAL">院方</option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-primary">
                                提交更改
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>


        </form>


    </div>
</div>



<#if u_price == 1 || u_price == 2>
<div class="container">
    <div class="input-group">
        <div class="input-group-addon">
            <a href="#myModal" role="button" class="btn" data-toggle="modal">修改订单价格</a>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#up_btn").click(function () {
            $("#up_from").serialize;
            $.ajax({
                url: "/order/update/",
                data: $("#up_from").serialize(),
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        location.reload();
                    } else {
                        alert("return is " + data.msg + ",code is " + data.code)
                    }
                }
            })
        })
    });
</script>


<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">修改订单价格</h3>
    </div>

    <div class="modal-body">
        <form id="up_from" method="post">
            <#if status == 1>
                <p>订单价格超出部分,暂时不做处理,手动退款...</p>
                <input type="hidden" name="order_no" value="${order.orderNo}"/>
                <input type="number" name="add_price" placeholder="订单还需支付 +value"/>
            <#else >
                <input type="hidden" name="order_no" value="${order.orderNo}"/>
                <input type="number" name="add_price" placeholder="订单价格..."/>
            </#if>
        </form>

    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
        <button class="btn btn-primary" id="up_btn">确定</button>
    </div>
</div>

</#if>


<#include "/commons/footer.ftl" />
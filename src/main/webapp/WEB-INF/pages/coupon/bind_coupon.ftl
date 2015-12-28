<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />
<style type="text/css">
    select {
        width: 100px;
    }

    input.span2 {
        width: 200px;
    }
</style>

<div>
    <div style="margin-left: 100px;width: 1000px;">
        <h3>绑定优惠券</h3>

        <div>
            <form class="form-inline" id="qform" action="/coupon/" method="get">
                <input id="uid" name="uid" type="hidden" value="${uid}"/>
                <select id="qt" name="qt">
                    <option value="0" <#if qt == 0>selected="selected" </#if>>智能查询</option>
                    <option value="1" <#if qt == 1>selected="selected" </#if>>UID</option>
                    <option value="2" <#if qt == 2>selected="selected" </#if>>昵称</option>
                    <option value="3" <#if qt == 3>selected="selected" </#if>>邮箱</option>
                    <option value="4" <#if qt == 4>selected="selected" </#if>>手机</option>
                </select>

                <div class="input-append">
                    <input class="span2" name="qkey" id="qkey" type="text" value="${qkey}">
                    <button class="btn" id="query" type="button">Search</button>
                </div>
            </form>

            <div id="coupons_data">
                <#if uid??>
                <table id="couponsList" class="table table-bordered">
                    <colgroup>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="18%"/>
                        <col width="22%"/>
                        <col width="10%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tr>
                        <th>名称</th>
                        <th>券号</th>
                        <th>使用规则</th>
                        <th>有效期</th>
                        <th>状态</th>
                        <th>使用说明</th>
                    </tr>
                      <#if (uid > 0) && couponList?has_content>
                        <#list couponList as coupon>
                        <tr>
                            <td>${coupon.card_name}</td>
                            <td>${coupon.card_num}</td>
                            <td>${coupon.card_rule_text}</td>
                            <td>${((coupon.card_start_time?number) * 1000)?number_to_datetime?string("yyyy年MM月dd日 HH:mm:ss")} 到 ${((coupon.card_end_time?number) * 1000)?number_to_datetime?string("yyyy年MM月dd日 HH:mm:ss")}</td>
                            <td>${coupon.card_status_text}</td>
                            <td>${coupon.card_remark}</td>
                        </tr>
                        </#list>
                      <#elseif uid == 0>
                        <tr>
                            <td colspan="6">该用户不存在</td>
                        </tr>
                      <#else>
                        <tr>
                            <td colspan="6">当前用户尚未绑定优惠券</td>
                        </tr>
                      </#if>
                    </#if>
                </table>
                <#if uid?? && (uid > 0)>
                <form class="form-inline">
                    <input type="text" id="new_coupons" value=""/>
                    <button class="btn btn-primary" id="bind" type="button">绑定新优惠券</button>
                    (绑定后不可撤销，请仔细检查核对后再进行操作)
                </form>
                </#if>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var coupons = (function() {
        var elements = {
            'qt' : $('#qt'),
            'qkey' : $('#qkey'),
            'new_coupons' : $('#new_coupons'),
            'query' : $('#query'),
            'bind' : $('#bind'),
            'couponsList' : $('#couponsList'),
            'couponsData' : $('#coupons_data'),
            'qform' : document.getElementById('qform'),
            'uid' : $('#uid')
        },
        param = {};
        var reqOptions = {
            type:'post',
            url:'/a/coupon/',
            data:param,
            dataType:'json'
        };

        function couponsCheck() {
            var newCoupon = $.trim(elements.new_coupons.val());
            if (newCoupon.length != 16) {
                alert('请输入正确的优惠券号码！');
                return false;
            }
            param.newCoupon = newCoupon;
            reqOptions.success = bindResultPro;
            return true;
        }

        function reset() {
            if (!elements.couponsData.hasClass('hide')) {
                elements.couponsData.addClass('hide');
            }
            elements.couponsList.find("tr:gt(0)").remove();
            elements.couponsList.append("<tr><td colspan='6'>当前用户尚未绑定优惠券</td></tr>");
            elements.new_coupons.val('');
            elements.qkey.val('');
            elements.uid.val('');
            param = {};
        }

        function refreshCoupons(couponsList) {
            if (couponsList instanceof Array) {
                var data = [];
                for (var i = 0 ; i < couponsList.length ; i++) {
                    data.push('<tr>');
                    data.push('<td>' + couponsList[i]['card_type_text'] + '</td>');
                    data.push('<td>' + couponsList[i]['card_num'] + '</td>');
                    data.push('<td>' + couponsList[i]['card_rule_text'] + '</td>');
                    data.push('<td>开始时间: ' + couponsList[i]['card_start_time'] + ' 结束时间: ' + couponsList[i]['card_end_time'] + '</td>');
                    data.push('<td>' + couponsList[i]['card_status_text'] + '</td>');
                    data.push('<td>' + couponsList[i]['card_remark'] + '</td>');
                    data.push('</tr>');
                }
                elements.couponsList.find("tr:gt(0)").remove();
                if (couponsList.length > 0) {
                    elements.couponsList.append(data.join(''));
                } else {
                    elements.couponsList.append("<tr><td colspan='6'>当前用户尚未绑定优惠券</td></tr>");
                }
            }
        }

        function queryResultPro(data) {
            if (data.code == '0') {
                if (data.data != undefined && data.data.list != undefined) {
                    refreshCoupons(data.data.list);
                    elements.couponsData.removeClass('hide');
                    elements.couponsData.addClass('show');
                    uid = data.data.uid;
                }
            } else {
                alert(data.msg);
            }
        }

        function bindResultPro(data) {
            if (data.code == 0) {
                alert('优惠券添加成功！');
                location.href = location.href;
            } else {
                alert('优惠券不可用！');
            }
        }

        function req(e) {
            var target = $(e.target);
            param.type = target.attr('id');
            var flag = false;
            if (param.type == 'bind') {
                flag = couponsCheck();
            }

            if (!flag)
              return ;

            var uid = $.trim(elements.uid.val());
            if (uid == '') {
                return ;
            }
            param.uid = uid;
            $.ajax(reqOptions);
        }

        elements.query.click(function() {
            var qkey = $.trim(elements.qkey.val());
            if (qkey.length <= 0) {
                alert('请输入查询关键字');
                return ;
            }
            elements.qform.submit();
        });
        elements.bind.click(req);
        elements.qt.change(function() {
            if (this.value == 0) {
                reset();
            }
            elements.uid.val('');
        });
        elements.qkey.keyup(function() {
            if ($.trim(this.value) == '') {
                reset();
                elements.uid.val('');
            }
        });
    })();
</script>
<#include "commons/footer.ftl" />
<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
  
  <link rel="stylesheet" type="text/css" href="/static/css/user/user_edit_style.css">
  
<table width="100%" height="30" border="0" cellpadding="5" cellspacing="0" bgcolor="#000000">
  <tr>
    <td><span class="STYLE2">用户管理</span></td>
  </tr>
</table>
<br />
<div id="enlarge-body">
  <legend>用户详细信息</legend>
  <br />
</div>
　<br />
<div class="tit" id="jbxx_tit">基本信息</div>
<div id="jbxx">

<!-- 
quota.id , u.nick , u.mobile , quota.auth_status , quota.realname , u.gender
quota.contact , quota.id_card , quota.origin , quota.location , quota.idcard_reverse , 
quota.idcard_front 

 -->
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="10">
    <tr>
      <td width="96" height="30" class="bold">用户ID</td>
      <td width="263" height="30">&lt;${user.uid}&gt;</td>
      <td width="96" height="30" class="bold">用户昵称</td>
      <td width="529" height="30">&lt;${user.nick}&gt;</td>
    </tr>
    <tr>
      <td width="96" height="30" class="bold">注册手机号</td>
      <td height="30">&lt;${user.mobile}&gt;</td>
      <td width="96" height="30" class="bold">邀请人手机号</td>
      <td height="30">&lt;inviter&gt;</td>
    </tr>
  </table>
</div>
<div class="tit" id="smrz_tit" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="8%" align="center" onclick="smrz.style.display=''; "><span class="STYLE4">状态：
      <#if user.auth_status==1 >
        已认证
      </#if>
       <#if user.auth_status!=1 >
        未认证
      </#if>

      </span></td>
      <td class="tit" width="89%" align="center" onclick="smrz.style.display=''; ">实名认证　　</td>
      <td width="3%" align="center" valign="middle" onclick="smrz.style.display='none'"><span class="STYLE4">隐藏</span></td>
    </tr>
  </table>
</div>
<div id="smrz">
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="10">
    <tr>
      <td width="94" height="30" class="bold">姓名</td>
      <td width="229" height="30">&lt;${user.realname}&gt;</td>
      <td width="48" height="30" class="bold">性别</td>
      <td width="332" height="30">&lt;
      <#if user.gender == 1 >
        男
      </#if>
      <#if user.gender == 0 >
        未设置
      </#if>
      <#if user.gender == 2 >
        女
      </#if>

      &gt;</td>
      <td width="78" height="30" class="bold">应急电话</td>
      <td width="199">&lt;${user.contact}&gt;</td>
    </tr>
    <tr>
      <td width="94" height="30" class="bold">身份证号</td>
      <td height="30" colspan="5">&lt;${user.id_card}&gt;</td>
    </tr>
    <tr>
      <td width="94" height="30" class="bold">现住地址</td>
      <td height="30" colspan="5">&lt;${user.location}&gt;</td>
    </tr>
    <tr>
      <td width="94" height="30" class="bold">籍贯地址</td>
      <td height="30" colspan="5">&lt;${user.origin}&gt;</td>
    </tr>

  </table>
</div>

<div class="tit" id="pays_tit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="8%" align="center" class="STYLE4" onclick="ali.style.display=''; "></td>
            <td width="89%" align="center" onclick="pays.style.display=''; ">支付信息</td>
            <td width="3%" align="center" valign="middle" onclick="pays.style.display='none'; "><span class="STYLE4">隐藏</span></td>
        </tr>
    </table>
</div>
<div id="pays" style="width: 84%; margin: 0 auto;">
    <h5>充值</h5>
    <table  class="table table-bordered">

        <tr>
            <td>订单号</td>
            <td>支付单号</td>
            <td>充值金额</td>
            <td>充值时间</td>
            <td>支付方式</td>
            <td>支付银行</td>
            <td>支付状态</td>
        </tr>
    <#list pays as pay>
        <tr>
            <td>${pay.orderNo}</td>
            <td>${pay.tradeNo}</td>
            <td>${pay.amount}</td>
            <td>${(pay.callbackAt?string("yyyy-MM-dd"))!}</td>
            <td>${pay.tpp}</td>
            <td>${pay.bankCode}</td>
            <td>
            <#if (pay.status == 1)> 未支付</#if>
                <#if (pay.status == 2)> 支付完成</#if>
                <#if (pay.status == 3)> 取消支付</#if>
            </td>

        </tr>

    </#list>
    </table>
    <h5>订单支付</h5>
    <div style="overflow: auto;">
    <table  class="table table-bordered" style="width: 1800px;">

        <tr>
            <td>订单号</td>
            <td>支付单号</td>
            <td>支付金额</td>
            <td>余额支付</td>
            <td>优惠券(金额)</td>
            <td>支付时间</td>
            <td>支付方式</td>
            <td>支付银行</td>
            <td>支付状态</td>
            <td>产品名称</td>
            <td>产品总额</td>
            <td>订单状态</td>
            <td>产品类型</td>
            <td>悟空保</td>
            <td>医院</td>
        </tr>
    <#list mnPays as pay>
        <tr>
            <td <#if (pay.status == 0)>style="color:#f0f0f0;" </#if>>${pay.orderNo!}</td>
            <td <#if (pay.status == 0)>style="color:#f0f0f0;" </#if>>${pay.tradeNo!}</td>
            <td <#if (pay.status == 0)>style="color:#f0f0f0;" </#if>>${pay.payAmount!}</td>
            <td <#if (pay.status == 0)>style="color:#f0f0f0;" </#if>>${pay.payBalance!}</td>
            <td>${pay.payCoupon!}</td>
            <td>${pay.payDate!}</td>
            <td>${pay.payType!}</td>
            <td>${pay.payBlank!}</td>
            <td>${pay.payStatus!}</td>
            <td>${pay.productName!}</td>
            <td>${pay.productMoney!}</td>
            <td>${pay.orderStatus!}</td>
            <td>${pay.productType!}</td>
            <td>${pay.orderPolicy!}</td>
            <td>${pay.hospital}</td>

        </tr>

    </#list>
    </table>
    </div>
</div>

<div class="tit" id="orders_tit">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="8%" align="center" class="STYLE4" onclick="ali.style.display=''; "></td>
            <td width="89%" align="center" onclick="orders.style.display=''; ">订单信息</td>
            <td width="3%" align="center" valign="middle" onclick="orders.style.display='none'; "><span class="STYLE4">隐藏</span></td>
        </tr>
    </table>
</div>
<div id="orders" style="width: 84%; margin: 0 auto;">

    <table  class="table table-bordered">

        <tr>
            <td>订单号</td>
            <td>订单价格</td>
            <td>产品名</td>
            <td>医院名</td>
            <td>订单状态</td>
            <td>保险</td>
            <td>优惠券</td>
            <td>创建时间</td>
        </tr>
    <#list orders as order>

        <tr <#if (order.status == 1)>style="background-color: #00a0e9;" </#if>>
            <td>${order.orderNo}</td>
            <td>${order.price}</td>
            <td>${order.productName}</td>
            <td><${order.hospital}/td>
            <td>${order.orderStatus}</td>
            <td>${order.policyStatus}</td>
            <td>${order.couponNum}</td>
            <td>${order.createTime}</td>

        </tr>

    </#list>
    </table>

</div>




<#include "commons/footer.ftl" />











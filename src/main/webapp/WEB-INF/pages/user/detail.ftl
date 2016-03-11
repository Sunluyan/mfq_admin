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
    <tr>
      <td class="bold">身份证信息</td>
      <td colspan="5" align="center"><table width="90%" height="100%" border="0" align="left" cellpadding="3" cellspacing="0">
        <tr>
          <td align="left"><img src="${user.idcard_front}" width="400" height="200" alt="" /></td>
          <td align="left"><img src="${user.idcard_reverse}" width="400" height="200" alt="" /></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>
<div class="tit" id="ali_tit">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="8%" align="center" class="STYLE4" onclick="ali.style.display=''; "></td>
      <td width="89%" align="center" onclick="ali.style.display=''; ">用户账户</td>
      <td width="3%" align="center" valign="middle" onclick="ali.style.display='none'; "><span class="STYLE4">隐藏</span></td>
    </tr>
  </table>
</div>
<div id="ali">
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="10">
    <tr>
      <td width="104" height="30" class="bold">用户余额</td>
      <td width="884" height="30">${quota.balance}</td>
    </tr>
    <tr>
      <td width="104" height="30" class="bold">用户总信用额度</td>
      <td height="30">${quota.quota_all}</td>
    </tr>
      <tr>
          <td width="104" height="30" class="bold">用户剩余额度</td>
          <td height="30">${quota.quota_left}</td>
      </tr>
  </table>
</div>
</br>
</br>

<div class="container">
<div class="row-fluid">


    <div  id="context">


    <#if msg != null && msg != ''>${msg}</#if>

        <!-- -------------------------三个table------------------------------- -->
        <div class="row-fluid">


            <div  id="context">


            <#if msg != null && msg != ''>${msg}</#if>

              <h3>订单.</h3>
                <!-- -------------------------三个table------------------------------- -->
                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>订单号</td>
                        <td>产品名</td>
                        <td>医院</td>
                        <td>安全码</td>
                        <td>订单金额</td>
                        <td>订单付款</td>
                        <td>保险</td>
                        <td>下单时间</td>
                        <td>订单状态</td>
                        <td>订单类型</td>

                    </tr>

                <#list orders as order>

                    <tr class="tr-unsee">
                        <td>${order.orderNo}</td>
                        <td>${order.pName}</td>
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


      <h3>支付 </h3>

        <table class="table table-bordered table-unsee" index="1">
            <tr>
                <td>订单号</td>
                <td>用户ID</td>
                <td>姓名</td>
                <td>手机号</td>
                <td>订单金额</td>
                <td>状态</td>
                <td>时间</td>

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
                </td>
                <td>
                ${pay.callbackAt?string("yyyy-MM-dd")!}
                </td>

            </tr>

        </#list>
        </table>


    </div>
</div>
</div>
  
  
<#include "commons/footer.ftl" />











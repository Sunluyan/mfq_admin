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
      <td width="8%" align="center" class="STYLE4" onclick="ali.style.display=''; ">状态：未通过</td>
      <td width="89%" align="center" onclick="ali.style.display=''; ">支付宝信息</td>
      <td width="3%" align="center" valign="middle" onclick="ali.style.display='none'; "><span class="STYLE4">隐藏</span></td>
    </tr>
  </table>
</div>
<div id="ali">
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="10">
    <tr>
      <td width="104" height="30" class="bold">支付宝账号</td>
      <td width="884" height="30">&lt;name&gt;</td>
    </tr>
    <tr>
      <td width="104" height="30" class="bold">芝麻信用积分</td>
      <td height="30">&lt;id_number&gt;</td>
    </tr>
  </table>
</div>
</br>
</br>
  
  
  
<#include "commons/footer.ftl" />











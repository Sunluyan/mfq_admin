<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />



<div class="container">
  <div class="container-fluid">

    <div class="row-fluid">

      <div class="span12">
        <div id="well" class="well hidden">
          <strong id="msg">${msg}</strong>
        </div>
        <form class="form-horizontal" action="#" method="POST">
          <fieldset>
            <legend>用户信息</legend>
            <div class="control-group">
              <label class="control-label">UID</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${user.uid}</span>
                <input id="uid" type="hidden" value="${user.uid}">
                <#if power><a id="autologin" class="" title="注意！盗取别人帐号是不合法的行为。">自动登录</a></#if>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">用户标识</label>
              <div class="controls">
                <#if userSign['MailStatus']><span class="btn-success">邮箱已激活</span> &nbsp;</#if>
                <#if userSign['AutoRegister']><span class="btn-inverse">自动注册用户</span> &nbsp;</#if>
                <#if userSign['DoyenStatus']> <span class="btn-info" >旅行达人</span> &nbsp;</#if>
                <#if userSign['Merchant']> <span class="btn-primary" >商家用户</span> &nbsp;</#if>
                <#if userSign['MerchantDoyen']><span class="btn-warning" >商家达人</span> &nbsp;</#if>
                <#if userSign['MerchandiseDoyen']><span class="btn-warning" >商品达人</span> &nbsp;</#if>
                <#if userSign['CustomerService']><span class="btn-warning" >客服人员</span> &nbsp;</#if>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">用户状态</label>
              <div class="controls">
                <input id="user-status" type="hidden" value="${user.status.value}">
                <span id="user-status-msg" class="input-xlarge uneditable-input"></span>
                <#if power><div>
                <span id="btn-delete-user" class="btn" data-status="">删除用户</span>
                <span id="btn-active-user" class="btn" data-status="">激活用户</span>
                </div></#if>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">用户名/昵称</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${user.nick}</span>
                <a id="to-user" target="_blank" href="http://www.shijiebang.com/u${user.uid}/"><i class="icon-share"></i> 访问此用户</a>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">真实姓名</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${user.realname}</span>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">头像</label>
              <div class="controls">
                <#assign url='http://s2.sjbly.cn/img/avt_180.png' />
                <#if (user.pic)?has_content><#assign url=user.pic /></#if>
                <img id="user-pic" src="${url}">
                <#if power>
                <#if (user.pic)?has_content>
                <span id="btn-delete-pic" class="btn"><i class="icon-remove"></i>删除头像</span>
                </#if>
                </#if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">邮箱</label>
              <div class="controls">
                <#if user.email?has_content&&user.email?length!=32>
                  <#if power><input id="update-email-value" class="input-xlarge " value="${user.email}"><span class="btn" id="delete-email">删除邮箱</span>   <span class="btn" id="update-email">修改邮箱</span>
                  <#else><span class="input-xlarge uneditable-input">${user.email[0..6]}****</span>
                  </#if>
                 <#else>
                     <#if power><input id="update-email-value" class="input-xlarge " value=""><span class="btn" id="update-email">修改邮箱</span>  </#if>
                </#if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">电话</label>
              <div class="controls">
                <#if power><input class="input-xlarge" <#if user.mobile?length!=32>value="${user.mobile}"</#if>  id="update-mobile-value" >
                <#if user.mobile?length!=32><span class="btn" id="delete-mobile">删除手机号</span>
                <#if userSign["NewMobile"] >
                    <span class="btn" id="verity-mobile">手机号通过验证</span>
                </#if>
                </#if>
                    <span class="btn" id="update-mobile">修改手机号</span>
                <#elseif user.mobile?has_content&&user.mobile?length!=32><span class="input-xlarge uneditable-input">${user.mobile[0..6]}****</span></#if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">生日</label>
              <div class="controls">
                <#if user.birthday gt 0><span class="input-xlarge uneditable-input">${user.birthday}</span></#if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">创建时间</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${user.createdAt?number_to_datetime}</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">注册IP</label>
              <div class="controls">
                <#if userlogin.regIp?has_content><a target="_blank" href="http://wap.ip138.com/ip_search.asp?ip=${userlogin.regIp}">${userlogin.regIp}</a></#if>
                <span>来源：${(userlogin.source)!}</span>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">修改设置时间</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${user.updatedAt?number_to_datetime}</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">最后登录时间</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${userlogin.lastLoginAt?number_to_datetime}</span>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label">最后登录IP</label>
              <div class="controls">
                <span class="input-xlarge uneditable-input">${userlogin.lastLoginIp!}</span>
              </div>
            </div>
            <#if power>
            <div class="control-group">
              <label class="control-label">密码</label>
              <div class="controls">
                <input id="password" name="password" type="text" id="input-xlarge inputWarning">
                <span id="btn-password" class="btn">修改密码</span>
              </div>
            </div>
            </#if>

            <div class="control-group">
              <label class="control-label">简介</label>
              <div class="controls">
                <textarea id="intro" class="input-xlarge" style="height:120px;">${user.intro}</textarea>
                <#if power><span id="btn-intro" class="btn">修改简介</span></#if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">第三方登录</label>
              <div class="controls">
                <#if passportOauths??>
                <#list passportOauths as po>
                <span class="btn h-${po.uuid}">
                  <#if po.siteType.value=1>
                  <a target="_blank" href="http://weibo.com/${po.uuid}">新浪微博</a>     <#if power><i class="icon-remove" onclick="removeBound(${user.uid},'${po.uuid}')"></i></#if>
                  <#elseif po.siteType.value=3>
                  <a target="_blank" href="http://www.renren.com/${po.uuid}">人人网</a>         <#if power><i class="icon-remove" onclick="removeBound(${user.uid},'${po.uuid}')"></i></#if>
                  <#elseif po.siteType.value=5>
                  <a target="_blank" href="http://www.douban.com/people/${po.uuid}/">豆瓣网</a>          <#if power><i class="icon-remove" onclick="removeBound(${user.uid},'${po.uuid}')"></i></#if>
                  <#elseif po.siteType.value=6>
                  <a target="_blank" href="http://www.baidu.com/p/${po.uuid}/">百度</a>           <#if power><i class="icon-remove" onclick="removeBound(${user.uid},'${po.uuid}')"></i></#if>
                  <#else>
                  ${po.siteType.name()}        <#if power><i class="icon-remove" onclick="removeBound(${user.uid},'${po.uuid}')"></i></#if>
                  </#if>
                </span>
                </#list>
                </#if>
              </div>
            </div>

          </fieldset>
        </form>

      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/static/js/search/user.js"></script>
<#if power>
<script>
  function removeBound(uid,uuid){
    if(!confirm("确定解除绑定吗？")){
      return;
    }

    $.post("/user/passwordOauth/", {
      _method: "DELETE",
      uid: uid,
      uuid: uuid
    }, function(data) {
      if("true" == data) {
        $(".h-"+uuid).hide();
      } else {
        alert("操作失败。刷新重新操作吧。");
      }
    });
  }

  $("#btn-intro").click(function (){
   var intro=$("#intro").val();
   var uid=$("#uid").val();
   $.post("/user/intro/", {
    _method: "POST",
    uid: uid,
    intro: intro
  }, function(data) {
    if("true" == data) {
     alert("更新成功");
   } else {
    alert("操作失败。刷新重新操作吧。");
  }
});

 })

  function deleteUserEmailOrMobile(item){
    var secret = prompt("输入此功能的超级密码: ","");
    if(secret){
      var data = {uid:${user.uid},secret:secret,_method:"DELETE"};
      data[item] = "true";
      $.post("/search/user/deletefield/",data,function(data){
        if("true" == data){
          location.href="/search/user/?uid=${user.uid}";
        }else{
          alert("密码错误或者系统错误。");
        }
      });
    }
  }

  function updateUserEmailOrMobile(item){
      var secret = prompt("输入此功能的超级密码: ","");
      var filed ;
      var value  ;
      if(item=='email'){
          filed='email';
          value=$("#update-email-value").val();
      }else{
          filed='mobile';
          value=$("#update-mobile-value").val();
      }
      if(secret){
          var data = {uid:${user.uid},secret:secret,filed:filed,value:value};

          $.post("/search/user/updatefield/",data,function(data){
              if("true" == data){
                  location.href="/search/user/?uid=${user.uid}";
              }else{
                  alert(data);
              }
          });
      }
  }

  $(function(){
    $('#autologin').click(function(){
      var secret = prompt("输入此功能的超级密码: ", "");
      if(secret){
        var data = {uid:${user.uid},secret:secret};
        $.post("/search/user/autologin/",data,function(data){
          if("true" == data){
            location.href=$('#to-user').attr('href');
          }else{
            alert("密码错误或者暂时无法登录");
          }
        });
      }
    });

      function verityMobile(){
          if(!confirm("确定要验证通过吗？")){
              return;
          }

              var data = {uid:${user.uid},_method:"POST"};
              $.post("/user/verity/mobile/",data,function(data){
                  if("true" == data){
                      location.href="/search/user/?uid=${user.uid}";
                  }else{
                      alert("密码错误或者系统错误。");
                  }
              });
      }
    $('#delete-email').click(function(){deleteUserEmailOrMobile('email')});
    $('#delete-mobile').click(function(){deleteUserEmailOrMobile('mobile')});
    $('#verity-mobile').click(function(){verityMobile()});

      $('#update-email').click(function(){updateUserEmailOrMobile('email')});
      $('#update-mobile').click(function(){updateUserEmailOrMobile('mobile')});
  });

</script>
</#if>

<#include "commons/footer.ftl" />
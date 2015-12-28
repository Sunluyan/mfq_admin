<#assign _title='登录'>
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container">
  <form id="login" class="well form-inline horizontal-center" action="/xlogin/" method="POST">
    <label for="user">用户名</label>
    <input id="user" name="user" type="text" class="input-small" placeholder="请输入用户名" value="">
    <span class="login-clean"></span>
    <label for="password" class="login-password">密  码</label>
    <input id="password" name="password" type="password" class="input-small" value="">
    <input name="_r" type="hidden" value="${_r!}">
    <span class="login-clean"></span>
    <button type="submit" class="btn btn-primary login-btn">登录</button>
  </form>
</div>

<#include "commons/footer.ftl" />
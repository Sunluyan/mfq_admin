<#assign _title="版本信息">
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container">
  <div class="container-fluid">

    <div class="hero-unit">
      <h2>${_siteName}</h2>
      <p>
        <ul>
          <li>版本: 1.1xx</li>
          <li>原始作者: Ady Liu (imxylz@gmail.com)</li>
        </ul>
      </p>
    </div>
  </div>
  <div class="container-fluid">
    <ul>
      <li>v1.112 2015/03/09: 支持SSO登录(www.shijiebang.com)</li>
      <li>v1.111 2014/06/18: 支持30天未修改密码提醒</li>
      <li>v1.110 2013/12/23: 支持多后台的权限系统同步（角色、用户、资源同步）</li>
      <li>v1.109 2013/12/20: 修正未登录授权提醒问题；登录跳转到上次退出的页面</li>
      <li>v1.108 2013/11/25: 权限系统内核重写（支持跨机器会话保持）</li>
      <li>v1.107 2013/10/16: 菜单采用可配置方式 ↑</li>
      <li>v1.106 2013/08/23: 支持移动设备浏览(登录、菜单、动画等),升级jquery/bootstrap等</li>
      <li>v1.105 2013/08/19: 底部js(admin.js)限制未登录用户查看</li>
      <li>v1.104 2013/08/15: 底部增加支持浏览器提醒</li>
      <li>v1.103 2013/07/16: 底部显示当前运行的服务器IP地址</li>
      <li>v1.102 2013/07/01: 更换静态资源的目录，调整footer样式。</li>
      <li>v1.101 2013/06/26: 默认创建用户指定ROLE_USER角色，一些细微调整</li>
      <li>v1.100 2013/06/14: 自动登录网站用户，更换HTTP客户端</li>
      <li>v1.0 2013/04/24: 跳跃版本上线</li>
      <li>好久未更新了</li>
      <li>v0.9 2012/12/12: 正式版本发布</li>
      <li>v0.4 2012/12/10: 测试版公网上线</li>
      <li>v0.3 2012/11/26: 完善用户与角色系统（增删改列）</li>
      <li>v0.2 2012/11/25: 增加权限系统（认证、授权等）</li>
      <li>v0.1 2012/11/13: 初始版本</li>
    </ul>
  </div>
</div>

<#include "/commons/footer.ftl" />
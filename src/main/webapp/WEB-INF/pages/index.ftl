<#assign _title='首页'>
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container">
  <div class="alert alert-block">
    <a class="close" data-dismiss="alert">×</a>
    <h4 class="alert-heading">${(quotation.author)!"无名氏"}:</h4>
    <a href="/quotation/">${(quotation.quotation)!"钓鱼岛是中国的！"}</a>
  </div>

  <div class="page-header"><h1>${_userdetail.user.cname}<small>，欢迎访问${_siteName}。</small></h1>
 </div>
</div>
<#include "commons/footer.ftl" />
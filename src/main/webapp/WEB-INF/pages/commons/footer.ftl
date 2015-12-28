<#if _userdetail.user?? && _userdetail.user.uid lt 1>
<div id="user-warning" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>绑定UID提醒（SSO）</h3>
  </div>
  <div class="modal-body">
    <p>为了统一全站登录系统（单点登录），您当前登录的系统需要绑定5imfq.com网站用户UID，请联系管理员@yongshan.xing进行操作。
    <p>很烦吧，赶快绑定用户吧，只需要1分钟...</p>
  </div>
</div>
</#if>

<hr class="footer-line">
<div class="container">
    <footer class="footer">
        <p class="pull-left">&copy;2015 <a href="http://www.5imfq.com" target="_blank">美分期@${(_userdetail.user.uid)!}</a>
        <span class="version">v1.112 2015/03/09 ${(_rhost)!}</span>
        </p>
        <p class="pull-right">
          <a href="#">返回顶部</a>
        </p>
        <div class="explorer">本网站支持浏览器：Chrome/Firefox/Safari
        <#if !(_server!"")?ends_with('c.5imfq.com')> | 应该使用<a href="http://c.5imfq.com">c.5imfq.com</a>来访问此网站</a></#if>
        </div>
    </footer>
</div>
<#if _userdetail??>
<script type="text/javascript" src="/static/js/admin.js"></script>
</#if>
<script>
$(function(){
    $('i.info').each(function(){

        $(this).popover({"trigger":"hover",delay:{hide:1000},"content":$(this).data('info')});
    })

  $('#user-warning').modal({
    keyboard: false
  });

});
</script>
</body>
</html>
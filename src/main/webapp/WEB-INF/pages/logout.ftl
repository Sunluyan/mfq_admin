<#assign _title='退出'>
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div style="text-align:center;">退出成功！3秒后系统会自动跳转到首页，也可<a href="http://c.5imfq.com">点击本处</a>直接跳</div>

<script>  
function jumpurl(){  
  location='http://c.5imfq.com';
}  
setTimeout('jumpurl()',3000);  
</script> 

<#include "commons/footer.ftl" />
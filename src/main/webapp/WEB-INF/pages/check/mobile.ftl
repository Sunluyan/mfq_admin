<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
  <form action="/mobile/check" method="post" class="well form-inline">
    <h4>批量验证手机号</h4>

    手机号<br>
    小写逗号分割或者一行一个 <br/>
    <textarea name="mobiles" class="input-xxlarge" rows="10"></textarea>
    <br/>
    <br/>
    <input value="查询" type="submit" class="btn btn-large btn-primary" id="btn-submit"/>
    <br />
    <div class="alert-success">${message}</div>
    <br />
    <#list map.keySet() as key>
		mobile:${key}, status:${map.get(key)}<br />
    </#list>
    
  </form>
</div>

<#include "commons/footer.ftl" />
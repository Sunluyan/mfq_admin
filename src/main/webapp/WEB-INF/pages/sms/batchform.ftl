<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
  <form action="/batchsms/" method="post" class="well form-inline">
    <h4>批量发送短信接口</h4>

    <div class="alert-error">${error}</div>
    <div class="alert-success">${message}</div>

    手机号<br>
    1. 小写逗号分割或者一行一个 <br/>
    2. 发送短信的内容请事先报批! 请联系Felix <br/>
    3. <span style="color: red">不要短时间内发送同样内容的短信, 会被封号!!!!! </span>如果不懂请联系Felix <br/>
    <br/>
    <textarea name="mobiles" class="input-xxlarge" rows="10">${mobiles}</textarea>

    <br/>
    <br/>
    <br/>
    <br/>

    短信内容：<br/><textarea name="content" class="input-xxlarge">${content}</textarea>
      <br/>
    <br/>
    <input type="checkbox" name="over">短信超过65字请选择
    <br/><br/>
    <input value="发送" type="submit" class="btn btn-large btn-primary" id="btn-submit"/>
  </form>
</div>


<#include "commons/footer.ftl" />
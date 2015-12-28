<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
    <form action="/sms/send/all/" method="post" class="well form-inline">
        <h4>发送短信接口</h4>
        <div class="alert-error">${error}</div>
        <div class="alert-success">${message}</div>
        要发超过65字<input type="checkbox" name="over">
        <br />
        <br />
        <br />
        <br />
        内容：<textarea name="content" class="input-xxlarge">${content}</textarea>
        <br />
        <input value="发送" type="submit" class="btn btn-large btn-primary" id="btn-submit"/>
    </form>
</div>


<#include "commons/footer.ftl" />
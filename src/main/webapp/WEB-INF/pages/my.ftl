<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container" style="">
    <div class="row-fluid">
        <#if msg??||errmsg??>
        <div class="alert text-center">
            <a class="close" data-dismiss="alert">×</a>
            ${msg!errmsg}
        </div>
        </#if>
    </div>
    <form class="form-horizontal" method="POST" action="">
        <fieldset>
            <legend>${user.cname!user.username}基本信息</legend>
           <div class="control-group">
                <label class="control-label">用户名</label>
                <div class="controls">
                    <span class="input uneditable-input">${user.username}</span>
                    <input type="hidden" class="input-large" name="name" value="${user.username}">
                </div>
            </div>
          <div class="control-group">
                <label class="control-label" for="cname">中文名</label>
                <div class="controls">
                    <input type="text" class="input-large" name="cname" value="${user.realname}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="email">邮件地址</label>
                <div class="controls">
                    <span class="input uneditable-input">${user.email}</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="phone">电话号码</label>
                <div class="controls">
                    <input type="text" class="input-large" name="phone" value="${user.mobile}"></input>
                </div>
            </div>
            <#--
            <div class="control-group">
                <label class="control-label">角色列表</label>
                <div class="controls">
                    <span class="input uneditable-input" style="width:auto">
                        <#list roles as role>${role.roledesc} &nbsp;</#list>
                    </span>
                </div>
            </div>

            -->


            <div class="control-group">
                <label class="control-label" for="opassword">原始密码</label>
                <div class="controls">
                    <input type="password" class="input-large" id="opassword" name="opassword" placeholder="留空不修改密码">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="password">新密码</label>
                <div class="controls">
                    <input type="password" class="input-large" id="password" name="password" placeholder="留空不修改密码">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="cpassword">确认密码</label>
                <div class="controls">
                    <input type="password" class="input-large" id="cpassword" name="cpassword">
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">保存更改</button>
            </div>
        </fieldset>
    </form>
    <ul>
      <li>修改用户名、邮件地址、电话号码、角色等需要联系管理员</li>
      <li>修改密码请前往 <a href="http://c.5imfq.com/setting/password/">修改密码</a></li>
    </ul>
</div>




<#include "commons/footer.ftl" />
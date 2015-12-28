<#assign _jss=['/static/js/plugin/jquery-ui.js','/static/js/plugin/bootstrap-typeahead.js?20130407']>
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container" style="max-width:480px;">
    <div class="row-fluid">
        <#if msg??>
        <div class="alert text-center">
            <a class="close" data-dismiss="alert">×</a>
            ${msg}
        </div>
        </#if>
    </div>

    <form class="form-horizontal" method="POST" action="">
        <#if _method??>
            <input type="hidden" name="_method" value="PUT">
        </#if>
        <fieldset>
            <legend>${(user.cname)!username}基本信息</legend>
           <div class="control-group">
                <label class="control-label">用户名</label>
                <div class="controls">
                    <span class="input uneditable-input">${(user.username)!username}</span>
                </div>
            </div>
          <div class="control-group">
                <label class="control-label" for="cname">中文名</label>
                <div class="controls">
                    <input type="text" class="input-large" id="cname" name="cname" value="${(user.cname)!''}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="email">邮件地址</label>
                <div class="controls">
                    <input type="text" class="input-large" id="email" name="email" value="${(user.email)!}" autocomplete="false">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="phone">电话号码</label>
                <div class="controls">
                    <input type="text" class="input-large" id="phone" name="phone" value="${(user.phone)!'18600000000'}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">角色列表</label>
                <div class="controls">
                    <span class="input uneditable-input" style="width:auto">
                    <#if roles??><#list roles as role><a target="_blank" href="/systemadmin/roles/${role.rolename}/">${role.roledesc}</a> &nbsp;</#list>
                    <#else>普通用户
                    </#if>
                    </span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">密码</label>
                <div class="controls">
                    <input type="password" class="input-large" id="password" name="password" placeholder="<#if _method??>密码不能为空<#else>留空不修改密码</#if>">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="password">对应世界邦用户</label>
                <div class="controls">
                    <input type="text" class="input-large" id="sjbNick" placeholder="世界邦用户的昵称" value="${(sjbUser.nick)!}">
                    <input type="hidden" class="input-large" id="uid" name="uid" placeholder="世界邦用户的uid" value="${(user.uid)!}">
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary" onclick="return docheck();">保存更改</button>
            </div>
        </fieldset>
    </form>
    <ul>
      <li>每个用户必须绑定一个世界邦主站的uid，否则不允许登录</li>
    </ul>
</div>


<script type="text/javascript">
    function docheck(){
        <#if _method??>
            var password = $.trim($("#password").val());
            if(password.length < 5){
                alert("密码长度必须大于5");
                return false;
            }
        </#if>
        if($.trim($("#cname").val()).length<2){
            alert("中文名太搓");
            return false;
        }
        if($.trim($("#email").val()).length<2){
            alert("邮件地址悲剧");
            return false;
        }
        if($.trim($("#phone").val()).length<8){
            alert("电话号码难道不愿填么？");
            return false;
        }

        if($.trim($("#uid").val())==0){
            alert("现在必须绑定一个世界邦用户了");
            return false;
        }

        return true;
    }

    $('#sjbNick').typeahead({
        source: function(typeahead,query){
            if($.trim(query).length<2)return;
            $.getJSON('/user/search/',{'nick':query},function(data){
                typeahead.process(data);
            });
        },
        onselect: function(obj){
            $('#uid').val(obj.uid);
        },
        items:10,
        matcher:function(item){return true},
        property:"nick"
    });




</script>

<#include "commons/footer.ftl" />
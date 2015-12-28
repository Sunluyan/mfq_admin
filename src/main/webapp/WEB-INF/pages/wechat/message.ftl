<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container-fluid">
  <div class="container">

        <div class="row-fluid">

			<div>
			<form class="form-horizontal" method="POST" action="/wechat/gen/invite/">
				<input type="text" name="mobile" />
				<input type="hidden" value="${openid}" name="openid"/>
				<input type="submit" class="btn btn-primary" value="生成邀请码"/>
			</form>
			</div>
	
            <div>
                <legend>微信用户列表</legend>

                <table class="table table-bordered">

                    <tr>
                        <td>ID</td>
						<td>msgType</td>
                        <td>content</td>
                        <td>createAt</td>
                        <td>操作</td>
                    </tr>
                <#list msgs as msg>
                    <tr>
                        <td>${msg.id}</td>
                        <#if msg.msgType=='text'>
                        <td>文本</td>
                        <td>${msg.content}</td>
                        <#elseif msg.msgType=='image'>
                        <td>图片</td>
                        <td><img src="${msg.picUrl}"/></td>
                        </#if>
                        
                        <td>${msg.created?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td><a href="javascript:void(0)" onclick="userDel('${msg.id}')">Messages</a></td>
                    </tr>

                </#list>
                </table>
            </div>




        </div>

    </div>
  </div>
<script>
    function userDel(uid){
        window.open("/wechat/msglist/?openid="+uid);

    }
</script>
<#include "/commons/footer.ftl" />
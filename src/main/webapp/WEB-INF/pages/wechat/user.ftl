<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />

<div class="container-fluid">
  <div class="container">

        <div class="row-fluid">


            <div>
                <legend>微信用户列表</legend>

                <table class="table table-bordered">

                    <tr>
                        <td>ID</td>
                        <td>OPENID</td>
               
                        <td>消息条数</td>
                        <td>操作</td>
                    </tr>
                <#list users as user>
                    <tr>
                        <td>0</td>
                        <td><a href="/user/info/?id=${user.id}" target="_blank">${user}</a></td>
                        <td>${user.count}</td>
                        <td><a href="javascript:void(0)" onclick="userDel('${user}')">Messages</a></td>
                    </tr>

                </#list>
                </table>
            </div>




        </div>

    </div>
  </div>
<script>
    function userDel(uid){
        window.open("/wechat/message/list/?openid="+uid);

    }
</script>
<#include "/commons/footer.ftl" />
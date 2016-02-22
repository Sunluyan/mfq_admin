<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<link rel="stylesheet" type="text/css" href="/static/css/user/check_certify.css">
<style>
    .dropdown-toggle{
        font-size: 14px;
    }
    .popover-title{
        margin-top: 40px;
    }
</style>

<div class="popover-title container">用户实名认证审核页</div>
<table width="96%" border="0" align="center" cellpadding="3" cellspacing="2" class="bk" style="position:absolute; z-index:1;">
  <tr>
    <td width="100" align="right" class="bold">用户ID</td>
    <td class="neirong uid" >${user.uid}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">用户名</td>
    <td class="neirong">${user.nick}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">手机号</td>
    <td class="neirong">${user.mobile}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">姓名</td>
    <td class="neirong">${user.realname}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">身份证号码</td>
    <td class="neirong">${user.id_card}</td>
  </tr>
  <tr>
      <td width="100" align="right" class="bold">备注</td>
      <td class="neirong"><input type="text" class="form-control input-lg input-remark" value="${user.remark}" /></td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">籍贯地址</td>
    <td class="neirong">${user.origin}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">学校地址</td>
    <td class="neirong">${user.location}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">紧急联系电话<img src="http://7xowbr.com2.z0.glb.qiniucdn.com/${user.idcard_front}" width="200" height="78" class="img-responsive"></td>
    <td class="neirong">${user.contact}</td>
  </tr>
  <tr>
    <td width="100" align="right" style="font-weight:bold;padding-right:40px">身份证正面</td>
    <td>
        <a href="#"><img src="http://7xowbr.com2.z0.glb.qiniucdn.com/${user.idcard_front}" width="200" height="78" class="img-responsive"></a>

    </td>
  </tr>
  <tr>
    <td width="100" align="right" style="font-weight:bold;padding-right:40px">身份证背面</td>
    <td><a href="#"><img src="http://7xowbr.com2.z0.glb.qiniucdn.com/${user.idcard_reverse}" width="200" height="78" class="img-responsive"></a></td>
  </tr>
  <tr>
    <td height="90" colspan="2" align="center" valign="middle">
  <script type="text/javascript"> 
  function docheck() 
  { 
    if(confirm("确定通过实名审核？")){
      var uid = $(".uid").html();
      var remark = $(".remark").val()
      $.get("/ajax",{method:"certifyStatus",uid:uid,remark:remark,status:2}).done(function  (data) {
       if(data==1){
        window.close()
       }else if(data == 0){
        alert("该用户已处于通过状态")
       }else{
        alert("删除失败！请重试")
       }
      })
    }
    }

    function reject() 
  { 
      var uid = $(".uid").html();
      var remark = $(".remark").val()
      $.get("/ajax",{method:"certifyStatus",uid:uid,remark:remark,status:-2}).done(function  (data) {
       if(data==1){
        window.close()
       }else if(data == 0){
        alert("该用户已处于驳回状态")
       }else{
        alert("删除失败！请重试")
       }
    })
    }
    </script>
    <input name="button" type="submit" id="button" value="通过审核" onClick="docheck()" > 
    <input name="button2" type="submit" id="button2" value="不通过审核" onClick="windows.style.display='';">
  </td>
  </tr>
</table>

  <table width="99%" height="99%" border="0" cellpadding="0" cellspacing="0" id="windows" style="display:none; z-index:100; position:absolute;" >
    <tr>
      <td align="center" valign="middle">
      <table width="300" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="center" valign="middle" class="down_line">提示</td>
      </tr>
      <tr>
        <td height="150" align="center" valign="middle" class="down_line"><p>请输入未通过审核原因：<br>
        <br>
          <label>
          <textarea name="textarea" rows="3" class="remark"></textarea>
          </label>
        </p>
        <p>
        <input name="button3" type="button" id="button3" onClick="reject()" value="确定，并短信/私信通知TA" >
  　
  <input name="button22" type="button" id="button22" value="取消" onClick="windows.style.display='none';">
        </p></td>
      </tr>
      </table>
    </td>
    </tr>
    <script type="text/javascript">
        $(".input-remark").on("input webChange", function () {
            $(this).css("background-color", "pink");
        })
        $(".input-remark").keypress(function (event) {
            var $obj = $(this);
            var uid = $obj.parent().parent().parent().find(".uid").html();
            if (event.which == 13) {
                $.ajax({
                    url: "/ajax",
                    data: {
                        method: "editUserFeedback",
                        uid: uid,
                        remark: $obj.val()
                    },
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            $obj.css("background-color", "white");
                            $obj.blur()
                        } else {
                            alert(data.msg)
                        }
                    }
                })
                return false;
            }
        })
    </script>
</table>



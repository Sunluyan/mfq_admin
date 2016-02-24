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
    .past{
        border-bottom: 1px dashed #ccc;
    }
    .past:last{
        border:none;
    }
    .past-content{
        color:#333;
        font-size: 14px;
        float:left;
    }
    .past-time,.past-author{
        color:#aaa;
        float:right;
        font-size: 13px;
    }

</style>

<div class="popover-title container">用户实名认证审核页</div>
<table width="50%" border="0" align="center" cellpadding="3" cellspacing="2" class="bk" style="float:left;">
  <tr>
    <td width="100" align="right" class="bold">用户ID</td>
    <td class="neirong" ><a class="uid" href = "http://t.5imfq.com:8080/user/list/detail/?uid=${user.uid}">${user.uid}</a></td>
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
    <td width="100" align="right" class="bold">籍贯地址</td>
    <td class="neirong">${user.origin}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">学校地址</td>
    <td class="neirong">${user.location}</td>
  </tr>
  <tr>
    <td width="100" align="right" class="bold">紧急联系电话</td>
    <td class="neirong">${user.contact}</td>
  </tr>
  <tr>
    <td width="100" align="right" style="font-weight:bold;padding-right:40px">身份证正面</td>
    <td>
        <#if user.idcard_front == "">
            无
        </#if>
        <#if user.idcard_front != "">
            <img src="http://7xowbr.com2.z0.glb.qiniucdn.com/${user.idcard_front}" width="200" height="78" class="img-responsive">
        </#if>
    </td>
  </tr>
  <tr>
    <td width="100" align="right" style="font-weight:bold;padding-right:40px">身份证背面</td>
    <td>
    <#if user.idcard_reverse == "">
        无
    </#if>
    <#if user.idcard_reverse != "">
        <img src="http://7xowbr.com2.z0.glb.qiniucdn.com/${user.idcard_reverse}" width="200" height="78" class="img-responsive">
    </#if></td>
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
<#--
数据组织方式:
xxx&&&&xxx&&&&xx****asd&&&&sadf&&&&
-->
<table style="background:white;border:1px solid #ccc;border-radius: 20px;float:left;width: 43%;"   align="center" class="remark-table">
    <tr >
        <td style="padding:5px 10px;">
            <p class="past" style="overflow:hidden;">
                <span class="past-content"></span> <span class="past-author"></span><span class="past-time"></span>
            </p>
            <textarea type="text" class="remark-new" style="width:97%;height:50px;"></textarea>
            <div>
                <button class="btn-primary add-remark" style="border-radius:15%;padding:3px 8px;float:right;">添加</button>
            </div>
        </td>
    </tr>
    <script>
        $(".add-remark").click(function(){
            var newValue = $(".remark-new").val();
            //ajax发送到服务器
            var uid = $(".uid").html();
            var data = $(".remark-new").val();

            $.get("/ajax",{method:"addRemark",uid:uid,data:data}).done(function(data){
                insertRemark(data)
            })
        })
        var json = "${user.remark}";
        var index = 1;
        function insertRemark(json){
            for(var i = 0;i<json.split("****").length-1;i++){
                var p = $(".past").last();
                var pClone = p.clone();
                pClone.find(".past-content").html((index++)+"、"+json.split("****")[i].split("&&&&")[0]);
                pClone.find(".past-time").html("-- "+json.split("****")[i].split("&&&&")[1]+"&nbsp;")
                pClone.find(".past-author").html("by "+json.split("****")[i].split("&&&&")[2]);
                pClone.show()
                p.after(pClone)
            }
        }
        insertRemark(json)
    </script>
</table>



  <table width="99%" height="99%" border="0" cellpadding="0" cellspacing="0" id="windows" style="display:none; z-index:100; position:absolute;top:0px;" >
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
</table>



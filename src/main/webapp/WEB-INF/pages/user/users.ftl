<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />
<style>
.form-inline{
  line-height:35px;
}
.area-choice{
  width:100px;    
}

.form-group{
  display:inline;
}
.tab{
  width:100%;
  height:40px;
  background:#f5f5f5;
  margin-bottom: 15px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  /*1px solid #e3e3e3*/
  border:1px solid #e3e3e3;
}
.prev,.next{
  width:40px;
  height:40px;
  background:#e3e3e3;
  float:right;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  cursor:pointer;
  color:white;
}
.next{
  border-top-right-radius: 50px;
  border-bottom-right-radius: 50px;
}
.prev{
  border-top-left-radius: 50px;
  border-bottom-left-radius: 50px;
  border-right: 1px solid #e3e3e3;
}
.next:hover,.prev:hover{
  background:#d3d3d3;
}
.disabled{
  background:#eee;
}
.disabled:hover{
  cursor:auto;
  background:#eee;
}


</style>
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<div class="container" id="enlarge-body">
    <div class="container">
	
  <form class="form-inline well" action="/user/list/" method="GET">
    <div class="form-group">
        <label for="exampleInputName2">用户名</label>
        <input type="text" class="form-control" id="" name="username"  size="20" value="${query.username}">
    </div>
    <div class="form-group">
        <label >真实姓名</label>
        <input type="text" class="form-control" id="" name="realname"  size="20" value="${query.realname}">
    </div>
    <div class="form-group">
        <label >手机号码</label>
        <input type="text" class="form-control" id="" name="phone"  size="20" value="${query.phone}">
    </div>
    <div class="form-group">
        <label >邀请人ID</label>
        <input type="text" class="form-control" id="" name="inviteId"  size="20" value="${query.inviteid}">
    </div>

    <div class="form-group">
        <label>支付宝</label>
        <input type="text" class="form-control" id="" name="alipay"  size="20" value="${query.alipay}">
    </div>
    <div class="form-group">
        <label>实名认证</label>
        <select name="isReal">
          <option value="">请选择</option>
          <option value="0" <#if query.status == 0>selected</#if>  >未递交实名认证</option>
          <option value="1" <#if query.status == 1>selected</#if>  >已通过网络实名认证</option>
          <option value="2" <#if query.status == 2>selected</#if>  >已通过人工实名认证</option>
          <option value="3" <#if query.status == 3>selected</#if>  >已递交面签申请</option>
          <option value="4" <#if query.status == 4>selected</#if>  >已通过面签</option>
          <option value="-1" <#if query.status == -1>selected</#if>  >未通过网络实名认证</option>
          <option value="-2" <#if query.status == -2>selected</#if>  >未通过人工实名认证</option>
          <option value="-3" <#if query.status == -3>selected</#if>  >未通过面签</option>
          
        </select>
    </div>
    <br>
    <div class="form-group">
        <label for="exampleInputName1">注册时间</label>
        <div class="input-append date" id="date1" name="date1"  data-date-format="yyyy-mm-dd">
            <input class="span2" id="dateStart" name="fromtime" size="16" type="text" value="${query.fromtime}">
            <span class="add-on"><i class="icon-th"></i></span>
        </div>
        －
        <div class="input-append date" id="date2" name="date2"  data-date-format="yyyy-mm-dd">
            <input class="span2" id="dateEnd" name="totime" size="16" type="text"  value="${query.totime}">
            <span class="add-on"><i class="icon-th"></i></span>
            <input type="text" name="page" class="realpage" style="display:none;" value="${page}">
        </div>
        
    </div>
    

    <div class="input-group">
      <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
    </div>
        
  </form>

        <div class="row-fluid">

            <div>
                <legend>用户列表（共<span class="total">${total}</span>页，当前第<span class="page">${page}</span>/${total}）
                
                <div class="next">></div>
                <div class="prev"><</div>
                  </legend>

                
                <table class="table table-bordered">
                    <tr>
                        <td>ID</td>
                        <td>手机号码</td>
                        <td>用户名</td>
                        <td>真实姓名</td>
                        <td>实名认证</td>
                        <td>性别</td>
                        <td>面签状态</td>
                        <td>已支付订单</td>
                        <td>未支付订单</td>
                        <td>已取消订单</td>
                        <td>注册时间</td>
                        <td>邀请人ID</td>
                        <td>操作</td>
                    </tr>
                    
                    <#list users as user>
                    <tr>
                        <td>${user.uid}</td>
                        <td>${user.mobile}</td>
                        <td>${user.nick}</td>
                        <td>${user.realname}</td>
                        <td>${user.status}</td>
                        <td>${user.gender}</td>
                        <td>无</td>
                        <td>${user.alreadypay}</td>
                        <td>${user.notpay}</td>
                        <td>${user.cancel}</td>
                        <td>${user.createdAt}</td>
                        <td>${user.invite_code}</td>
                        <td><a href="/user/list/detail/?uid=${user.uid}" data-id='${user.uid}'>详情</a>/<a href="#" data-id='${user.uid}'>编辑</a></td>
                    </tr>
                    </#list>
                </table>
            </div>
            
        </div>

    </div>
</div>
<script type="text/javascript">
  $('#date1').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
    $('#date2').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    


    var clearPage = true;
    $(".btn-sm").click(function  (e) {
      if(clearPage)$(".realpage").val(1);
    })

    

    $(function  () {
      var page = parseInt($(".realpage").val())
      var total = parseInt($(".total").html())

      $(".next").click(function(){
        var page = parseInt($(".realpage").val())
        var total = parseInt($(".total").html())
        if(page<total){
          $(".realpage").val(page+1);
          clearPage = false;
          $(".btn-sm").click()
        }
        
      })

      $(".prev").click(function(){
        if(page>1){
          $(".realpage").val(page-1);
          clearPage = false;
          $(".btn-sm").click()
        }
      })

      if(page<=1){
        $(".prev").addClass("disabled")
      }else if(page>=total){
        $(".next").addClass("disabled")
      }

    })

</script>
<#include "commons/footer.ftl" />











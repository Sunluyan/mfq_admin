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
  cursor:pointer;
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
	
    <form class="form-inline well" action="#" method="POST">
      <div class="form-group">
          <label for="exampleInputName2">用户ID</label>
          <input type="text" class="form-control" id="uid" name="uid"  size="20">
      </div>
      <div class="form-group">
          <label for="exampleInputName2">真实姓名</label>
          <input type="text" class="form-control" id="username" name="username"  size="20">
      </div>

      <br>

      <div class="form-group">
          <label >用户手机号</label>
          <input type="text" class="form-control" id="phone" name="phone"  size="20">
      </div>

      <div class="form-group">
          <label >身份证号</label>
          <input type="text" class="form-control" id="cardid" name="cardId"  size="20">
      </div>
      <br>
      <div class="form-group">
          <label for="exampleInputName1">申请时间</label>
          <div class="input-append date" id="date1" name="date1"  data-date-format="yyyy-mm-dd">
              <input id="applytimefrom" name="fromtime" size="16" type="text" value="">
              <span class="add-on"><i class="icon-th"></i></span>
          </div>
          －
          <div class="input-append date" id="date2" name="date2"  data-date-format="yyyy-mm-dd">
              <input id="applytimeto" name="totime" size="16" type="text"  value="">
              <span class="add-on"><i class="icon-th"></i></span>
              <input type="text" name="page" class="realpage" style="display:none;" value="">
          </div>
      </div>
      <br>
      
      <div class="input-group">
        <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
      </div>
    </form>
  	
        <div class="row-fluid">
          <ul class="nav nav-pills">
            <li role="presentation" class="active" index="0"><a href="#">三日内需还款</a></li>
            <li role="presentation" index="1"><a href="#">还款中</a></li>
            <li role="presentation" index="-1"><a href="#">已完成还款</a></li>
            <li role="presentation" index="2"><a href="#">过期未还款</a></li>
            <img src="/static/img/loading.gif" class="loading" width="20" height="20" style="margin-top: 6px;margin-left: 5px; display:none;">
          </ul>
            <div>
                <legend>分期用户列表（共<span class="totalpage">1</span>页，当前第<span class="page">1</span>页，共<span class="total">0</span>条）
                
                <div class="next" onselectstart= "return false;">></div>
                <div class="prev disabled" onselectstart= "return false;"><</div>
                  </legend>


                <table class="table table-bordered table-unsee" index="1">
                    <tr>
                        <td>ID</td>
                        <td>姓名</td>
                        <td>手机号</td>
                        <td>用户类型</td>
                        <td>应还款日期</td>
                        <td>分期总额</td>
                        <td>还款期数</td>
                        <td>每期还款额</td>
                        <td>申请日期</td>
                        <td>结束日期</td>
                        <td>操作</td>
                    </tr>
                    
                    <tr class="targetTr" style="display:none;">
                        <td class="uid">id</td>
                        <td class="username">username</td>
                        <td class="phone">phone</td>
                        <td class="usertype">usertype</td>
                        <td class="returnDate">returnDate</td>
                        <td class="allMoney">allMoney</td>
                        <td class="countTime">countTime</td>
                        <td class="everyTimeMoney">everyTimeMoney</td>
                        <td class="applytime">applytime</td>
                        <td class="endTime">endTime</td>
                        <td class="oparite"><a href="#" target="_blank" data-id='' class="detail">详情</a></td>
                    </tr>
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
    
    var tableIndex = 1;

    $(document).on("click","li[role='presentation']",function  () {
      //去掉别人的tab效果，给自己加上。并把相应的table显示出来
      $("li[role='presentation']").removeClass("active")
      $(this).addClass("active")
      index = $(this).attr("index")

      getData(1)

    })


    //传入页数获取数据
    function getData (page) {
      var uid = $("#uid").val();
      var phone = $("#phone").val()
      var idcard = $("#cardid").val()
      var realname = $("#realname").val()
      var username = $("#username").val()
      var applytimefrom = $("#applytimefrom").val()
      var applytimeto = $("#applytimeto").val()
      var type = parseInt($(".active").attr("index"))

      $.ajax({
        url:'/ajax',
        type:'post',
        data:{
          method:"ajaxFinanceList",
          page:page,
          username:username,
          uid:uid,
          type:type,
          phone:phone,
          idcard:idcard,
          applytimefrom:applytimefrom,
          applytimeto:applytimeto
        },
        dataType:'json',
        success:function (json) {
          insertTable(json.data)
        }
      })
    }

    function nextPrevCss () {
      var page = parseInt($(".page").html())
      var totalpage = parseInt($(".totalpage").html())
      $(".next").removeClass("disabled")
      $(".prev").removeClass("disabled")
      if(page<=1){
        $(".prev").addClass("disabled")
      }
      if(page>=totalpage){
        $(".next").addClass("disabled")
      }

    }


/**
<td class="uid">id</td>
                        <td class="username">username</td>
                        <td class="phone">phone</td>
                        <td class="usertype">usertype</td>
                        <td class="returnDate">returnDate</td>
                        <td class="allMoney">allMoney</td>
                        <td class="countTime">countTime</td>
                        <td class="everyTimeMoney">everyTimeMoney</td>
                        <td class="applytime">applytime</td>
                        <td class="endTime">endTime</td>
                        <td class="oparite"><a href="#" target="_blank" data-id='' class="detail">详情</a></td>

{uid=201, user_type=0, price=-1.00, mobile=, bill_at=2015-09-07 12:30:17.0, due_at=2015-09-07 12:30:44.0, all_period=24, new_balance=195.00, id=1, realname=}

1、完善搜索  OK
2、完善分页  
3、开始个人详细信息
*/ 

    //填充表格
    function insertTable (data) {
        $(".tr").remove()
        for(var i = 0;i<data.length;i++){
          var $tr = $(".targetTr").eq(0).clone(true).addClass("tr");
          $tr.find(".uid").html(data[i].uid)
          $tr.find(".username").html(data[i].realname==''?'未设置':data[i].realname)
          $tr.find(".phone").html(data[i].mobile)
          $tr.find(".usertype").html(data[i].user_type=="0"?"未设置":data[i].user_type=="1"?"学生党":"上班族")
          $tr.find(".returnDate").html(data[i].due_at)
          $tr.find(".allMoney").html(data[i].period*data[i].period_pay)
          var period = data[i].period=="0"?data[i].all_period:data[i].period;
          $tr.find(".countTime").html(period)

          $tr.find(".everyTimeMoney").html(data[i].period_pay)
          var billAt = new Date(data[i].bill_at);
          $tr.find(".applytime").html(billAt.getFullYear()+"-"+(billAt.getMonth()+1)+"-"+billAt.getDate()+" "+billAt.getHours()+":"+billAt.getMinutes()+":"+billAt.getSeconds())
          var endTime = new Date(data[i].bill_at)
          endTime.setMonth(endTime.getMonth()+13)
          $tr.find(".endTime").html(endTime.getFullYear()+"-"+(endTime.getMonth()+1)+"-"+endTime.getDate()+" "+endTime.getHours()+":"+endTime.getMinutes()+":"+endTime.getSeconds())
          $tr.find(".detail").attr("href","/order/finance/detail/?id="+data[i].id)
          $tr.show()
          $(".table-bordered").append($tr)
        }
    } 

    $(function (){
        getData(1)
        getCount()
    })

    function getCount () {
      var uid = $("#uid").val();
      var phone = $("#phone").val()
      var idcard = $("#cardid").val()
      var realname = $("#realname").val()
      var username = $("#username").val()
      var applytimefrom = $("#applytimefrom").val()
      var applytimeto = $("#applytimeto").val()
      var type = parseInt($(".active").attr("index"))
      var count = "yes"
      $.ajax({
        url:'/ajax',
        type:'post',
        data:{
          method:"ajaxFinanceList",
          username:username,
          uid:uid,
          type:type,
          phone:phone,
          idcard:idcard,
          applytimefrom:applytimefrom,
          applytimeto:applytimeto,
          count:count
        },
        dataType:'json',
        success:function (json) {
          console.log(json.data[0].count)
          $(".total").html(json.data[0].count)

          $(".totalpage").html(Math.ceil(parseInt(json.data[0].count)/50))
        }
      })
    }


    var canClick = true;
    $(".next").click(function() {
      if(!canClick){
        return false;
      }
      canClick = false
      var page = parseInt($(".page").html())+1
      if(page > parseInt($(".totalpage").html())){
        canClick = true;
        return false;
      }
      $(".page").html(page)
      getData(page)
    })


    $(".prev").click(function  () {
      if(!canClick){
        return false;
      }
      canClick = false

      var page = parseInt($(".page").html())-1
      if(page < 1){
        canClick = true;
        return false;
      }
      $(".page").html(page)
      getData(page)
    })

    
    $(".btn-sm").click(function  () {
      getData(1);
      return false;
    })

    $(".loading").ajaxStart(function  () {
      $(this).show();
    })
    $(".loading").ajaxStop(function  () {
      $(this).hide();
    })

</script>
<#include "commons/footer.ftl" />


<#assign _title="美分期管理后台-订单管理列表">
<#include "commons/header.ftl" />
<#assign toolbar="crm" />
<#include "commons/toolbar.ftl" />
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<style>
.form-inline{
	line-height:35px;
}
.form-group{
  display:inline;
}
</style>

<div class="container" id="enlarge-body">
    <div class="container">

	<form class="form-inline well" action="#" method="GET">
    <div class="form-group">
        <label for="exampleInputName2">用户ID</label>
        <input type="text" class="form-control" id="" name="username"  size="20" value="${query.username}">
    </div>
    <div class="form-group">
        <label >手机号</label>
        <input type="text" class="form-control" id="" name="realname"  size="20" value="${query.realname}">
    </div>
    <div class="form-group">
        <label >身份证</label>
        <input type="text" class="form-control" id="" name="phone"  size="20" value="${query.phone}">
    </div>
    <br>
    <div class="form-group">
        <label>医院名</label>
        <input type="text" class="form-control" id="" name="alipay"  size="20" value="${query.alipay}">
    </div>
    <div class="form-group">
        <label>状态</label>
        <select name="isReal">
          <option value="">请选择</option>
          
          <option value="true">正常</option>
          <option value="false">过期</option>
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
                <legend>保单列表</legend>

                <table class="table table-bordered">
                    <tr>
                        <td>用户ID</td>
                        <td>姓名</td>
                        <td>性别</td>
                        <td>手机号</td>
                        <td>身份证</td>
                        <td>所保项目</td>
                        <td>相关医院</td>
                        <td>提交时间</td>
                        <td>状态</td>
                        <td>操作</td>
                    </tr>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    
                </table>
            </div>
            
        </div>

    </div>
</div>

<script>

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
</script>

<#include "/commons/footer.ftl" />
<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />
<link href="/static/bootstrap-3.3.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="/static/bootstrap-3.3.4/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>

<div class="container" >
  <div class="row-fluid">
  <#if msg??>
    <div class="alert text-center">
      <a class="close" data-dismiss="alert">×</a>
    ${msg}
    </div>
  </#if>
  </div>

  <form class="form-horizontal" method="POST" action="/sell/item/">

    <input type="hidden" name="id" value="${item.id}">

    <fieldset>
      <legend>商品信息  </legend>
      <div >
        <p/>
      </div>

      <div class="control-group">
        <label class="control-label" for="cname">商品名称</label>
        <div class="controls">
          <input type="text" class="input-large" id="name" name="name" value="${item.name!}">
          <span class="help-inline"><strong class="text-error">*</strong></span>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="alias">产品类型</label>
        <div class="controls">
          <select id='classify' name="classify">
          <#list classify as cs>
            <option value="${cs.id}" <#if ((cs.id))== ((classId))>selected</#if> >${cs.name}</option>
          </#list>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="alias">所属医院</label>
        <div class="controls">
          <select id='hospital' name="hospital">
          <#list hospitals as hs>
            <option value="${hs.id}" <#if ((hs.id))== ((hospitalId))>selected</#if> >${hs.name}</option>
          </#list>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="alias">是否上线</label>
        <div class="controls">
          <select id='is_online' name="is_online">
            <option value="true" <#if true == ((item.online))>selected</#if> >上线</option>
            <option value="false" <#if false == ((item.online))>selected</#if> >下线</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="alias">所在城市</label>
        <div class="controls">
          <select id='city_id' name="city_id">
            <option value="1" <#if 1== ((cityId))>selected</#if> >北京</option>
            <option value="3" <#if 3== ((cityId))>selected</#if> >上海</option>
            <option value="225" <#if 225== ((cityId))>selected</#if> >成都</option>
          </select>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">价格</label>
        <div class="controls">
          <input type="text" class="input-large" id="price" name="price" value="${item.price!}">
          <p class="help-inline"><strong class="text-error">*</strong>价格只能为数字</p>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">在线支付</label>
        <div class="controls">
          <input type="text" class="input-large" id="online_pay" name="online_pay" value="${item.onlinePay!}">
          <p class="help-inline"><strong class="text-error">*</strong>在线支付只能为数字</p>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">到院支付</label>
        <div class="controls">
          <input type="text" class="input-large" id="hospital_pay" name="hospital_pay" value="${item.hospitalPay!}">
          <p class="help-inline"><strong class="text-error">*</strong>到院支付只能为数字</p>
        </div>
      </div>
      
      <div class="control-group">
        <label class="control-label" for="lname">有效期</label>
        <div class="controls">
      		<div class="input-append date" id="dateStart" name="dateStart" data-date="${item.dateStart?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
    			<input class="span2" size="16" type="text" value="${item.dateStart?string("yyyy-MM-dd")!}">
    			<span class="add-on"><i class="icon-th"></i></span>
    		</div>
    		－
    		<div class="input-append date" id="dateEnd" name="dateEnd" data-date="${item.dateEnd?string("yyyy-MM-dd")!}" data-date-format="yyyy-mm-dd">
    			<input class="span2" size="16" type="text" value="${item.dateEnd?string("yyyy-MM-dd")!}">
    			<span class="add-on"><i class="icon-th"></i></span>
    		</div>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="lname">消费流程</label>
        <div class="controls">
          <input type="text" class="input-large" id="consume_step" name="consume_step" value="${detail.consumeStep!}">
          <span class="help-inline"><strong class="text-error">**</strong></span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">如何预约</label>
        <div class="controls">
          <input type="text" class="input-large" id="reserve" name="reserve" value="${detail.reserve!}">
          <span class="help-inline"><strong class="text-error">**</strong></span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">特殊说明</label>
        <div class="controls">
          <input type="text" class="input-large" id="special_note" name="special_note" value="${detail.specialNote!}">
          <span class="help-inline"><strong class="text-error">**</strong></span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="lname">详细内容：</label>
      </div>
      <div class="control-group" style="margin-left:100px;">
		  <link href="/static/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
          <!-- 加载编辑器的容器 -->
		  <div id="myEditor" name="myEditor"></div>
		  <!-- 配置文件 -->
		  <script type="text/javascript" src="/static/umeditor/umeditor.config.js"></script>
		  <!-- 编辑器源码文件 -->
		  <script type="text/javascript" src="/static/umeditor/umeditor.min.js"></script>
		  <!-- 实例化编辑器 -->
		  <script type="text/javascript">
		  	var um = UM.getEditor('myEditor', {
		  		initialContent:'${detail.body!}',
		  		initialFrameWidth:800, //初始化宽度
		  		initialFrameHeight:280 //初始化高度
		  	});
		  </script>
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary" onclick="return docheck();">保 存</button>
        &nbsp;&nbsp;
        <button type="button" class="btn" onclick="history.back();">取消返回</button>
      </div>
    </fieldset>
  </form>

</div>


<script type="text/javascript">
  function docheck() {

    if($.trim($("#name").val()).length < 1){
      alert("中文名太搓");
      return false;
    }
    if($.isNumeric($.trim($("#price").val())) == false){
      alert("价格必须是数字");
      return false;
    }
    if($.isNumeric($.trim($("#online_pay").val())) == false){
      alert("在线支付必须是数字");
      return false;
    }
    if($.isNumeric($.trim($("#hospital_pay").val())) == false){
      alert("到院支付必须是数字");
      return false;
    }
    if($.trim($("#consume_step").val()).length < 1){
      alert("消费流程内容不能为空");
      return false;
    }
	if($.trim($("#reserve").val()).length < 1){
      alert("如何预约内容不能为空");
      return false;
    }
    if($.trim($("#special_note").val()).length < 1){
      alert("特殊说明内容不能为空");
      return false;
    }
    return true;
  }
  
  $('#dateStart').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
  });
  $('#dateEnd').datetimepicker({
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
<#include "commons/footer.ftl" />
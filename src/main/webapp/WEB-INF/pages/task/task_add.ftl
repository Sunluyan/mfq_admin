<#assign _title="创建/修改达人任务" />
<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">

  <form class="well form-horizontal trade-add">
   <fieldset>
   <legend>创建/修改达人任务 ${(task.tid)!}</legend>
    <div class="control-group">
      <label class="control-label" for="name">
        任务名称 *
      </label>
      <div class="controls form-inline">
        <input id="name" name="name" type="text" class="input-xxlarge" value="${(task.name)!}">
        <input name="tid" type="hidden" value="${(task.tid)!0}">
        <span class="help-inline">任务名称，32个字符以内。</span>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="status">
        任务状态 *
      </label>
      <div class="controls form-inline">
        <span></span>
        <label class="radio input-small"><input type="radio" name="status" value="0" <#if !task?? || task.status.value==0>CHECKED</#if> >正 常</label>
        <label class="radio input-small"><input type="radio" name="status" value="1" <#if task??&&task.status.value==1>CHECKED</#if> >下 线</label>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="deadline">
        截止时间 *
      </label>
      <div class="controls form-inline">
        <input id="deadline" type="text" class="input-medium" value="${(task.deadline?number_to_date)!}">
        <span class="help-inline">任务截止时间，仅作展示使用，不决定任务是否下线。</span>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="url">
        任务链接 *
      </label>
      <div class="controls form-inline">
        <input id="url" type="text" class="input-xxlarge" value="${(task.url)!}">
        <span class="help-inline">任务详情的链接地址。</span>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="actionurl">
        任务动作 *
      </label>
      <div class="controls form-inline">
        <input id="actionurl" type="text" class="input-xxlarge" value="${(task.actionurl)!}">
        <span class="help-inline">任务动作的链接地址。</span>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="summary">
        任务简介 *
      </label>
      <div class="controls form-inline">
        <textarea id="summary" class="input-xxlarge" style="height:120px;">${(task.summary)!}</textarea>
        <span class="help-inline">任务的简单描述，256个字符以内。</span>
      </div>
    </div>

    <#if task??>
    <div class="control-group">
      <label class="control-label">
        维护信息
      </label>
      <div class="controls form-inline">
        <div class="help-inline">创建者: ${(task.creator)!} ${(task.createdAt?number_to_datetime)!}</div>
        <div class="help-inline">最后修改者: ${(task.updater)!} ${(task.updatedAt?number_to_datetime)!}</div>
      </div>
    </div>
    </#if>
    <div class="form-actions">
      <button type="button" id="save" class="btn btn-primary">保存</button>
      <a class="btn" href="/doyen/task/">取消</a>
      <span id="warning" class="text-warning"></span>
    </div>
  </fieldset>
</form>

</div>

<script type="text/javascript">

  $(function(){

    $( "#deadline" ).datepicker({
      dateFormat:"yy-mm-dd",
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 2,
      onClose: function( selectedDate ) {
      }
    });

    $('#save').click(function(){

      var tid = $('input[name=tid]').val();
      var status = $('input[name=status]:checked').val();
      var name = $.trim($('#name').val());
      var url = $.trim($('#url').val());
      var actionurl = $.trim($('#actionurl').val());
      var summary= $.trim($('#summary').val());
      var deadline = $.trim($('#deadline').val());


      if(status == undefined){
        return warning('任务状态不正确');
      }
      if(name.length < 2 || name.length > 32){
        return warning('任务名称不正确');
      }
      if(url.length < 2){
        return warning('任务详情的链接地址不正确');
      }
      if(actionurl.length < 2){
        return warning('任务动作的链接地址不正确');
      }
      if(summary.length < 2 || summary.length>256){
        return warning('任务简介太少了');
      }
      if(deadline.length!=10){
        return warning('截止时间不正确');
      }
      var data = {tid:tid,status:status,name:name,url:url,actionurl:actionurl,
        summary:summary,_method:'PUT',deadline:deadline};
      $.post('/doyen/task/add/',data,function(data){
        if('true' == data){
          window.location.href='/doyen/task/';
        }else{
          alert('操作失败!');
        }
      });
    });
  });
</script>

<#include "commons/footer.ftl" />
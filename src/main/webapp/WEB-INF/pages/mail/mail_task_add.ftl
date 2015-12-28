<#assign _csss=['/static/css/bootstrap-datetimepicker.min.css']>
<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/plugin/bootstrap-typeahead.js?20130407','/static/js/plugin/jquery.tokeninput.js?20130520','/static/js/plugin/bootstrap-datetimepicker.min.js']>
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
<#include "/mail/mail_tab.ftl">
  <form class="form-horizontal">
    <fieldset>

      <div class="control-group">
        <label class="control-label" for="">
          名称: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="mtid" type="hidden" value="${(mailTask.id)!0}">
          <input id="name" type="text" class="input-xxlarge" placeholder="名称" value="${(mailTask.name)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label">
          发送目标类型: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <select id="targetType" name="targetType" class="input-small" onchange="changeTargetType(this.value)">
            <option<#if !(receivers??)> SELECTED</#if> value="0">sql方式</option>
            <option<#if receivers??> SELECTED</#if> value="1">指定uid</option>
          </select>
        </div>
      </div>

        <div class="control-group target-by-uids"<#if !receivers??> hidden="hidden"</#if>>
          <label class="control-label" for="">
            目标uid: <i class="icon-star icon-blue"></i>
          </label>
          <div class="controls form-inline">
            <textarea id="receivers" class="input-xxlarge" placeholder="逗号分隔">${(receivers)!""}</textarea>
          </div>
        </div>

        <div class="control-group target-by-sql"<#if receivers??> hidden="hidden"</#if>>
          <label class="control-label" for="">
            mtbid: <i class="icon-star icon-blue"></i>
          </label>
          <div class="controls form-inline">
            <input id="mtbid" type="text" class="input-xxlarge" placeholder="逗号分隔" value="${(mailTask.tbid)!0}">
          </div>
        </div>
        <div class="control-group target-by-sql"<#if receivers??> hidden="hidden"</#if>>
          <label class="control-label" for="">
            总数查询参数参数: <i class="icon-star icon-blue"></i>
          </label>
          <div class="controls form-inline">
            <input id="countParam" type="text" class="input-xxlarge" placeholder="逗号分隔" value="${(countParam)!""}">
          </div>
        </div>

        <div class="control-group target-by-sql"<#if receivers??> hidden="hidden"</#if>>
          <label class="control-label" for="">
            数据查询参数: <i class="icon-star icon-blue"></i>
          </label>
          <div class="controls form-inline">
            <input id="dataParam" type="text" class="input-xxlarge" placeholder="逗号分隔" value="${dataParam!""}">
          </div>
        </div>

      <div class="control-group">
        <label class="control-label">
          任务类型${mailTask.type}: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <select id="mailTaskType" name="mailTaskType" class="input-small">
            <option<#if ((mailTask.type)!"")=='Constant'> SELECTED</#if> value="0">固定内容</option>
            <option<#if ((mailTask.type)!"")=='Personal'> SELECTED</#if> value="1">个性化内容</option>
          </select>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          执行时间: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="sendAt" name="sendAt" type="text" class="input-medium" autocomplete="off" placeholder="发送时间" value="${(mailTask.sendAt?number_to_datetime)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          邮件标题: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="subject" type="text" class="input-xxlarge" placeholder="" value="${(mailTask.subject)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          邮件内容: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <textarea id="content" name="content" style="height: 200px" class="input-xxlarge">${(mailTask.content)!""}</textarea>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          任务状态: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <select id="status" name="status" class="input-medium">
            <option<#if ((mailTask.status)!"")=='Waiting'> SELECTED</#if> value="0">等待执行</option>
            <option<#if ((mailTask.status)!"")=='Succeed'> SELECTED</#if> value="1">执行成功</option>
            <option<#if ((mailTask.status)!"")=='Failed'> SELECTED</#if> value="2">执行失败</option>
            <option<#if ((mailTask.status)!"")=='HungUp'> SELECTED</#if> value="3">挂起</option>
          </select>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          执行状态元数据: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <textarea id="statusMeta" disabled>${(mailTask.statusMeta)!""}</textarea>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          描述: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="comments" type="text" class="input-xxlarge" placeholder="" value="${(mailTask.comments)!""}">
        </div>
      </div>

      <div class="control-group" id="position-submit">
        <div class="controls form-inline">
          <button id="save" type="button">保存</button>
        </div>
      </div>
    </fieldset>

  </form>
</div>

<script type="text/javascript">
  function changeTargetType(val) {
    if (val=="0") {
        $('.target-by-uids').hide();
        $('.target-by-sql').show();
    } else {
      $('.target-by-uids').show();
      $('.target-by-sql').hide();
    }
  }

  $('#save').click(function(){
    var mtid = $('#mtid').val();
    var mtbid = $('#mtbid').val();
    var name = $('#name').val();
    var mailTaskType = $('#mailTaskType').val();
    var countParam = $('#countParam').val();
    var dataParam = $('#dataParam').val();
    var sendAt = $('#sendAt').val();
    var subject = $('#subject').val();
    var content = $('#content').val();
    var status = $('#status').val();
    var statusMeta = $('#statusMeta').val();
    var comments = $('#comments').val();
    var receivers = $('#receivers').val();
    var targetType = $('#targetType').val();

    var data = {mtid: mtid, name: name, mailTaskType: mailTaskType, mtbid: mtbid, countParam: countParam, dataParam: dataParam, sendAt: sendAt,
        subject: subject, content: content, status: status, statusMeta: statusMeta, comments: comments, receivers: receivers, targetType: targetType};

    $('#save').attr('disabled', 'disabled');
    $.ajax({
      url: "/mail/task/add/",
      data: data,
      type: "POST",
      dataType: "json",
      success: function(ret) {
        if (ret.code == 0) {
          alert('保存成功');
        } else {
          alert('保存失败' + ret.msg);
        }
        $('#save').removeAttr('disabled');
      },
      error: function() {
        alert('操作失败');
        $('#save').removeAttr('disabled');
      }
    });
  });

  $("#sendAt").datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
  });

</script>
<#include "commons/footer.ftl" />
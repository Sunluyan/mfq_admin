<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/plugin/bootstrap-typeahead.js?20130407','/static/js/plugin/jquery.tokeninput.js?20130520']>
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
          <input id="mtbid" type="hidden" value="${(mailTaskBasic.id)!0}">
          <input id="name" type="text" class="input-xxlarge" placeholder="名称" value="${(mailTaskBasic.name)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          总数查询sql: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="count_sql" type="text" class="input-xxlarge" placeholder="查询总数据条数" value="${(mailTaskBasic.countSql)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          总数查询sql的参数: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="count_param_meta" name="count_param_meta" type="text" class="input-xxlarge" autocomplete="off" placeholder="总数查询sql参数的描述信息" value="${countParamMeta!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          数据sql: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="data_sql" type="text" class="input-xxlarge" placeholder="查询数据的sql" value="${(mailTaskBasic.dataSql)!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          数据查询sql的参数: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="data_param_meta" name="data_param_meta" type="text" class="input-xxlarge" autocomplete="off" placeholder="(string, in, long, date)以逗号分隔，不需要limit之后的内容" value="${dataParamMeta!""}">
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          任务描述: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="comments" name="comments" type="text" class="input-xxlarge" autocomplete="off" placeholder="描述信息" value="${(mailTaskBasic.comments)!""}">
        </div>
      </div>

      <div class="control-group">
        <div class="controls form-inline">
          <button id="save" type="button">保存</button>
        </div>
      </div>
    </fieldset>

  </form>
</div>

<script type="text/javascript">
  $('#save').click(function(){
    var mtbid = $('#mtbid').val();
    var name = $('#name').val();
    var count_sql = $('#count_sql').val();
    var count_param_meta = $('#count_param_meta').val();
    var data_sql = $('#data_sql').val();
    var data_param_meta = $('#data_param_meta').val();
    var comments = $('#comments').val();

    var data = {mtbid: mtbid, name: name, countSql: count_sql, countParamMeta: count_param_meta, dataSql: data_sql,
      dataParamMeta: data_param_meta, comments: comments};

    $('#save').attr('disabled', 'disabled');
    $.ajax({
      url: "/mail/task_basic/add/",
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
</script>
<#include "commons/footer.ftl" />
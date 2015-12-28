<#assign _csss=['/static/css/bootstrap-datetimepicker.min.css']>
<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/plugin/bootstrap-typeahead.js?20130407','/static/js/plugin/jquery.tokeninput.js?20130520','/static/js/plugin/bootstrap-datetimepicker.min.js']>
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
<#include "/mail/mail_tab.ftl">
  <form class="form-horizontal">
    <fieldset>

      <div class="control-group" id="message-div">
        <label class="control-label" for="">
          任务id: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          <input id="mtid" name="mtid" type="text" class="input-xxlarge" autocomplete="off" placeholder="数字id" value="">
        </div>
      </div>

      <div class="control-group" id="position-submit">
        <div class="controls form-inline">
          <button id="send" type="button">发送</button>
        </div>
      </div>
    </fieldset>

  </form>
</div>

<script type="text/javascript">
  $('#send').click(function(){
    var mtid = $('#mtid').val();

    var data = {mtid: mtid};
    $('#send').attr('disabled', 'disabled');
    $.ajax({
      url: "/mail/send/",
      data: data,
      type: "POST",
      dataType: "json",
      success: function(ret) {
        if (ret.code == 0) {
          alert('发送成功');
        } else {
          alert('发送失败' + ret.msg);
        }
        $('#send').removeAttr('disabled');
      },
      error: function() {
        alert('操作失败');
        $('#save').removeAttr('disabled');
      }
    });
  });

</script>
<#include "commons/footer.ftl" />
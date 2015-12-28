<div id="yunhu-alert-dialog" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
        <h4 class="modal-title" id="mySmallModalLabel">系统提醒<a class="anchorjs-link" href="#mySmallModalLabel"><span class="anchorjs-icon"></span></a></h4>
      </div>
      <div class="modal-body">  
        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        拨打成功
      </div>
    </div>
  </div>
</div>
<div class="modal fade yunhu-task-dialog" id="task_call_dialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button style="display:none" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">线索详细：</h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <span class="text-danger">您已抢到该任务，请尽快外呼并填写外呼结论。</span><a class="btn btn-primary btn-success call-400-label yunhu-dialog-close"         data-phone-number="" data-task-info-id="" target="_blank"><i class="icon icon-phone"></i>拨打外呼电话</a>
      </div>
    </div>
  </div>
</div>
<div id="yunhu_msg_sys" class="admin-message-box">
  <div style="display:none" class="js-info-alert alert alert-warning alert-dismissible fade in" role="alert">
    <a class="message-box-btn js-link btn btn-primary" target="_blank">立即处理</a>
    <h4 class="js-title"></h4>
    <p class="js-info"></p>
  </div>
  <div style="display:none" class="js-hint-alert alert alert-danger alert-dismissible fade in" role="alert">
    <a class="message-box-btn js-link btn btn-danger" target="_blank">立即处理</a>
    <h4 class="js-title"></h4>
    <p class="js-info"></p>
  </div>
  <div style="display:none;height:50px;" class="js-task-alert alert alert-info" role="alert">
     <button type="button" class="message-box-btn message-box-btn-withpic js-grab-button btn btn-primary">
     	<div style="padding-left:30px">抢</div>
     </button>
    <#--
    <h4 class="js-title"></h4>
    <p class="js-info"></p>
    -->
  </div>
</div>
<script type="text/javascript" src="/static/js/msgsys/msgSys.js?v20150604"></script>


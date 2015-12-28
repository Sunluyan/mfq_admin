<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2']>
<#include "commons/header.ftl" />
<#assign toolbar="auditor" />
<#include "commons/toolbar.ftl" />

<div class="container">
	<form class="form-horizontal">
    <fieldset>
      <div class="control-group">
        <label class="control-label" for="">
          报名时间: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          ${reservation.createdAt?number_to_datetime}
        </div>
      </div>

      <div class="control-group" id="message-div">
        <label class="control-label" for="">
          用户姓名: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          ${reservation.userName}
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="">
          联系方式: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
       	  ${reservation.mobile}
        </div>
      </div>

      <div class="control-group" id="message-div">
        <label class="control-label" for="">
          REF: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          ${refDisplay}
        </div>
      </div>

      <div class="control-group" id="message-div">
        <label class="control-label" for="">
          来源: <i class="icon-star icon-blue"></i>
        </label>
        <div class="controls form-inline">
          ${reservation.source}
        </div>
      </div>
    </fieldset>
    </form>
</div>

<#include "commons/footer.ftl" />
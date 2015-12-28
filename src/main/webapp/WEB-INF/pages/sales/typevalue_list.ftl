<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title=(SettingType.CLUE_CONCLUSION.value=type)?string('线索外呼结论', (SettingType.TRAVEL_PROBLEM.value=type)?string('旅行问题分类', '推广来源'))+'-${sysname}'>
<#assign toolbar="search">
<#assign tab1="setting">
<#assign tab2="valuetype">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_setting_nav.ftl">

    <ul class="nav nav-pills">
      <li role="presentation" ${(SettingType.CLUE_CONCLUSION.value==type)?string('class="active""','')}><a href="/sales/admin/setting/typevalue/list/">线索外呼结论</a></li>
      <li role="presentation" ${(SettingType.TRAVEL_PROBLEM.value==type)?string('class="active"','')}><a href="/sales/admin/setting/typevalue/list/?t=${SettingType.TRAVEL_PROBLEM.value}">旅行问题分类</a></li>
      <li role="presentation" ${(SettingType.PROMOTION.value==type)?string('class="active"','')}><a href="/sales/admin/setting/typevalue/list/?t=${SettingType.PROMOTION.value}">推广来源</a></li>
    </ul>

  <div class="panel panel-success panel-yunhu">
      <div class="panel-heading">增加分类</div>
      <div class="panel-body">
      <form class="form-inline pull-left">
        <div class="form-group">
          <label class="" for="val">一级分类</label>
          <input type="text" class="form-control" id="val" placeholder="编码" name="val" size="6">
          <input type="text" class="form-control" id="name" placeholder="名称" name="name" size="15">
          <button class="btn btn-info btn-sm" onclick="javascript: sales.addTypeValue(this, '${type}');return false;">增加</button>
        </div>
      </form>
      <form class="form-inline pull-right">
        <div class="form-group">
          <label class="" for="val">二级分类</label>
          <select class="form-control">
            <#list ptypes as pt>
            <#if 1=pt.status>
            <option value="${pt.val}">${pt.name?html}</option>
            </#if>
            </#list>
          </select>
          <input class="form-control" type="text" name="val" size="6" placeholder="编码">
          <input class="form-control" type="text" name="name" size="15" placeholder="名称">
          <#if SettingType.CLUE_CONCLUSION.value=type>
          <select class="form-control" name="proctype">
            <option value="0">终止</option>
            <option value="1">重新抢</option>
            <option value="2">商机受理</option>
          </select>
          </#if>
          <button class="btn btn-info btn-sm" onclick="javascript: sales.addTypeValue(this, '${type}');return false;">增加</button>
        </div>
      </form>
      </div>
  </div>


<#list vlists as vlist>
  <div class="panel panel-info panel-yunhu">
      <div class="panel-heading">${vlist[0].val} ${vlist[0].name?html}</div>
      <div class="panel-body">
      <#if vlist?size gt 1><#list vlist[1..] as ct>
        <form class="form-inline js_form" action="/sales/admin/setting/typevalue/save/" method="POST">
          <div class="form-group">
            <label for="">${ct.val}</label>
            <input type="text" class="form-control" name="name" placeholder="文本描述" value="${ct.name}">
            <input type="hidden" name="val" value="${ct.val}">
            <input type="hidden" name="t" value="${t}">
          </div>
          <#if ct.type == 'CLUE_CONCLUSION'>
          <div class="form-group radio-border">
            <label><input name="sign1" value="0" type="radio" ${(!ct.isSetSign(1)&&!ct.isSetSign(2))?string('checked','')}>终止</label>
            <label><input name="sign1" value="1" type="radio" ${ct.isSetSign(1)?string('checked','')}>重新抢</label>
            <label><input name="sign1" value="2" type="radio" ${ct.isSetSign(2)?string('checked','')}>商机受理</label>
          </div>
          <div class="form-group">
            <label><input name="sign3" value="3" type="checkbox" ${ct.isSetSign(3)?string('checked','')}>72小时后发送短信</label>
            <label><input name="sign4" value="4" type="checkbox" ${ct.isSetSign(4)?string('checked','')}>是否接通</label>
          	<label><input name="sign5" value="5" type="checkbox" ${ct.isSetSign(5)?string('checked','')} onclick="javascript:$('.js-smscontent-textarea-${ct.val}').toggle();">发送召回短信</label>
          </div>
          </#if>
          <div class="form-group">
            <label><input name="status" value="1" type="checkbox" ${(ct.status==1)?string('checked','')}>启用</label>
          </div>
          <#if ct.type == 'CLUE_CONCLUSION'>
          <div class="form-group js-smscontent-textarea-${ct.val}" <#if !ct.isSetSign(5)>style="display:none;"</#if>>
          	<label> 召回短信内容
          		<textarea name="smsContent" rows="1" cols="60" placeholder="65个字符">${ct.smsContent!''}</textarea>
          	</label>	
          </div>
          </#if>
          <button type="submit" class="btn btn-default js_save">保存</button>
        </form>
      </#list></#if>
      </div>
  </div>
</#list>

</div>

<script type="text/javascript">
  $('.js_form').submit(function(e) {
    e.preventDefault();
    var target =$(e.target);
    $.post('/sales/admin/setting/typevalue/save/', target.serialize(), function(data, textStatus, xhr) {
      alert(data);
    });
  });
</script>

<#include "commons/message_dialog.ftl">
<#include "commons/footer.ftl">

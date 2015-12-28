<#include "/sales/static_js_css.ftl">
<#include "/sales/yunhu_sys_name.ftl">
<#assign _title='电话来源-${sysname}'>
<#assign toolbar="search">
<#assign tab1="setting">
<#assign tab2="outcallsetting">
<#assign tab3="sourceteam">
<#include "commons/header.ftl">
<#include "sales/menubar.ftl">

<div class="container admin-sales">
	<#include "/sales/sales_setting_nav.ftl">
	<#include "/sales/sales_outcallsetting_nav.ftl">

<div class="page-header"><h4>修改来源分组/权重信息</h4></div>
<#if msg??><div class="well">${msg}</div></#if>
<form class="form-horizontal" method="POST" action="?sourceKey=${(sst.sourceKey)!}">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">来源信息</label>
    <div class="col-sm-4">
      <span class="label label-info">${(sst.sourceKey)!}</span>
      <span>${(sst.sourceName)!}</span>
      <input type="hidden" name="id" value="${(sst.id)!}">
      <input type="hidden" name="sourceKey" value="${(sst.sourceKey)!}">
      <input type="hidden" name="sourceName" value="${(sst.sourceName)!}">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">负责部门</label>
    <div class="col-sm-6">
      <label class="radio-inline"><input type="radio" name="team" value="1" ${(sst.team==1)?string('checked','')}>销售组</label>
      <label class="radio-inline"><input type="radio" name="team" value="2" ${(sst.team==2)?string('checked','')}>销售支持</label>
      <label class="radio-inline"><input type="radio" name="team" value="4" ${(sst.team==4)?string('checked','')}>不外呼</label>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">需求强弱</label>
    <div class="col-sm-6">
      <label class="radio-inline"><input type="radio" name="weak" value="0" ${(!sst.isWeak())?string('checked','')}>强</label>
      <label class="radio-inline"><input type="radio" name="weak" value="1" ${(sst.isWeak())?string('checked','')}>弱</label>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">权重配置</label>
    <div class="col-sm-3">
      <div class="input-group"><div class="input-group-addon">W</div><input name="w" type="number" step="0.01" class="form-control" placeholder="W" value="${(sst.w)?string("0.00")}"><div class="input-group-addon">初始权值&gt;1</div></div>
      <div class="input-group"><div class="input-group-addon">F</div><input name="f" type="number" step="0.01" class="form-control" placeholder="F" value="${(sst.f)?string("0.00")}"><div class="input-group-addon">初始倍数&gt;1</div></div>
      <div class="input-group"><div class="input-group-addon">P</div><input name="p" type="number" step="0.01" class="form-control" placeholder="P" value="${(sst.p)?string("0.00")}"><div class="input-group-addon">最大倍数&gt;1</div></div>
      <div class="input-group"><div class="input-group-addon">Q</div><input name="q" type="number" step="0.01" class="form-control" placeholder="Q" value="${(sst.q)?string("0.00")}"><div class="input-group-addon">最小倍数0&lt;q&lt;1</div></div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-3">
      <div class="input-group"><div class="input-group-addon">期望完成拨打时间(分钟)</div><input name="texp" type="number" class="form-control" placeholder="Texp" value="${sst.texp}"></div>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">销售话术</label>
    <div class="col-sm-6">
      <textarea name="salesScript" cols="50" rows="5">${(sst.salesScript!'')?html}</textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-warning">保存</button>
      <#if sst.id lt 1><span class="label label-warning">此来源还未配置过权重</span></#if>
    </div>
  </div>
</form>

  <ul>
    <li>初始权重 W*F &nbsp; 最大权重 W*F*P &nbsp; 最小权重 W*F*Q</li>
    <li>P&gt1;    0&lt;Q&lt;1</li>
    <li>电话来源地址：<a target="_blank" href="/dict/wordlist/?type=source">/dict/wordlist/?type=source</a></li>
  </ul>
</div>

<#include "commons/footer.ftl">

<#assign _title="Zookeeper节点数据">
<#include "/commons/header.ftl" />
<#assign toolbar="systemadmin" />
<#include "/commons/toolbar.ftl" />


<div class="container">
<h2>配置中心(Zookeeper)配置管理</h2>
<ul class="breadcrumb">
  <#list paths?keys as k>
    <li><a href="?path=${paths[k]}">${k}</a> <#if k_has_next><span class="divider">/</span></#if></li>
  </#list>
</ul>
<div class="row">
  <div class="span3">
    <#if nodes?has_content>
    <ul class="nav nav-tabs nav-stacked">
    <#list nodes as node>
      <li><a href="?path=${parentNode}${node}">${node}</a></li>
    </#list>
    </ul>
    <#else>
      <span>无子节点</span>
    </#if>
  </div>
  <div class="span9">
    <#if nodeStat??>
    <table class="table table-striped table-bordered ">
      <tr>
        <td>ctime: ${(nodeStat.ctime)?number_to_datetime}</td>
        <td>mtime: ${(nodeStat.mtime)?number_to_datetime}</td>
        <td>version: ${nodeStat.version}</td>
        <td>dataLength: ${nodeStat.dataLength}</td>
        <td>numChildren: ${nodeStat.numChildren}</td>
      </tr>
    </table>
    <div class="">
      <form action="/zookeeper/node/" method="POST">
        <input name="path" type="hidden" value="${path}">
        <textarea class="js_op" name="value" rows="10" style="width:100%" disabled>${value!"节点不存在或无数据？"}</textarea>
        <#if _userdetail.admin>
        <label class="checkbox inline" style="margin-right:30px;"><input id="cb-modify" type="checkbox">修改数据</label>
        <button id="btn-save" class="btn js_op" disabled>保存修改</button>
        </#if>
      </form>
    </div>
    <#else>
      <h3>节点不存在!</h3>
    </#if>
  </div>
  </div>
  <div class="row">
    <ul>
      <li>非专业人士，请不要操作，后果很严重！</li>
    </ul>
  </div>
</div>

<script type="text/javascript">

  $(function(){
    $('#cb-modify').change(function(event) {
      $('.js_op').attr('disabled',!$('#cb-modify').attr('checked'));
    });


  });
</script>
<#include "/commons/footer.ftl" />
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
<#include "/mail/mail_tab.ftl">

  <table class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
      <th width="50px">id</th>
      <th width="100px">tbid</th>
      <th width="120px">name</th>
      <th width="100px">status</th>
      <th width="100px">send_at</th>
    </tr>
    </thead>
    <tbody>
    <#list mailTasks as m>
    <tr>
      <td><a href="/mail/task/add/?mtid=${m.id}">${m.id}</a></td>
      <td><a href="/mail/task_basic/add/?mtbid=${m.tbid}">${m.tbid}</a></td>
      <td>${m.name}</td>
      <td>${m.status}</td>
      <td>${m.sendAt?number_to_datetime}</td>
    </tr>
    </#list>
    </tbody>
  </table>

</div>

<#include "commons/footer.ftl" />
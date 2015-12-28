<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />

<div class="container">
<#include "/mail/mail_tab.ftl">

  <table class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
      <th width="50px">id</th>
      <th width="150px">name</th>
      <th width="200px">comments</th>
      <th width="100px">created_at</th>
    </tr>
    </thead>
    <tbody>
    <#list mailTaskBasics as m>
    <tr>
      <td><a href="/mail/task_basic/add/?mtbid=${m.id}">${m.id}</a></td>
      <td>${m.name}</a></td>
      <td>${m.comments}</td>
      <td>${m.createdAt?number_to_datetime}</td>
    </tr>
    </#list>
    </tbody>
  </table>

</div>

<#include "commons/footer.ftl" />
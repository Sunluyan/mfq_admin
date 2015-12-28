<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container" >
    <div class="container-fluid">

    <form method="GET" action="/op/opfiles/">
      <input id="page" name="page" value="${page}" type="hidden">
    </form>

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />

            <div class="span10">
            <#if msg??>
                <div class="well">${msg}</div>
            </#if>

                <ul class="pager" style="float:left;">
                    <li>
                        <a class="btn-success" href="/op/opfiles/">文件列表</a>
                    </li>
                    <li>
                        <a class="btn" href="/op/opuploadform/?format=1">上传图像</a>
                    </li>
                      <li>
                        <a class="btn" href="/op/opuploadform/?format=2">上传文件</a>
                      </li>
                      <li>
                        <a class="btn" href="/op/opuploadform/?format=3">上传文件(文件名不变)</a>
                      </li>
                </ul>

                <#--
                <ul class="pager" style="float:right;">
                <#if num &gt; 1>
                    <li>
                        <a href="/op/opfiles/?page=${num-1}">前一页</a>
                    </li>
                </#if>
                    <li>
                        <a href="/op/opfiles/?page=${num+1}">后一页</a>
                    </li>
                    <li><span>文件总数:${total}</span></li>
                </ul>

                 -->

                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>作者</th>
                        <th>预览</th>
                        <th>多尺寸</th>
                        <th>信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list files as wfile>
                    <tr>
                        <td>
                            ${wfile.uploader}
                        </td>

                        <td>
                          <#if wfile.format == 1 >
                            <img src="${wfile.smallImageUrl}" title="${wfile.description}">
                            <br/>
                          </#if>
                        ${wfile.description!}
                          <br/>
                          ( ${wfile.uploadName!} )
                        </td>
                        <td>
                        <#if wfile.format == 1 >
                            <a href="${wfile.coverImageUrl}" target="_blank">封面尺寸fc</a><br/>
                            <a href="${wfile.headImageUrl}" target="_blank">头图尺寸fb</a><br/>
                            <a href="${wfile.smallImageUrl}" target="_blank">小尺寸fs</a><br/>
                            <a href="${wfile.mediumImageUrl}" target="_blank">中尺寸m</a><br/>
                            <a href="${wfile.bigImageUrl}" target="_blank">大尺寸b</a><br/>
                        </#if>

                            <a href="${wfile.srcUrl}" target="_blank">原文件</a><br/>
                        <#if wfile.format == 1 && wfile.size &gt; 1024*150 >
                          <span class="text-warning">网站用图一般不超过150KB! </span><br/>
                        </#if>
                        </td>

                        <td>
                        <button data-id="${wfile.id}" type="button" class="close js_close">&times;</button>
                        <#if wfile.format == 1 >
                        原图宽高: ${wfile.dimension!} <br/>
                        </#if>

                        原文件大小:
                            <#if wfile.size &gt; 1024*150 >
                                <span class="text-warning">${wfile.size/1024!} KB </span>
                            <#else >
                                ${wfile.size/1024!} KB
                            </#if>
                          <br/>
                        上传时间: ${wfile.createdAt?string}

                        </td>

                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

        <@pager page totalpage />

    </div>
</div>

<script type="text/javascript">
  $(function(){
    $('.js_close').on('click', function(event) {
      event.preventDefault();
      if(!confirm("确定删除么？")){
         return;
      }
      var target = $(event.target);
      $.post('/op/opfiles/',{id:target.data('id'),'_method':'DELETE'},function(){
        target.parent().parent().remove();
      });
    });
  });
</script>

<#include "commons/footer.ftl" />
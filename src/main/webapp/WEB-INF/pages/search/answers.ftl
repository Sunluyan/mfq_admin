<#include "/commons/header.ftl" />
<#assign toolbar="auditor" />
<#include "/commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container-fluid">

        <div class="row-fluid">

            <div class="span12">
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>uid</th>
                        <th>答案详情</th>
                        <th>更新时间</th>
                        <th>状态</th>
                        <th >操作<img style="float:right" id="enlarge" src="/static/img/enlarge.png"></th>
                         <th>继续问答</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#list answers as answer>
                    <tr>
                        <td>${answer.uid}</td>
                        <td>${answer.answer}</td>
                        <td>${answer.updatedAt?number_to_date}</td>
                        <td>${answer.status}</td>
                        <td>
                            <#if answer.status=='Normal'>
                            <a href="#" onclick="removeAnswer('${answer.qid}','${answer.aid}','${answer.uid}')" >删除答案</a>&nbsp;&nbsp;
                            </#if>
                            <br/>

                            <#if answer.marker &gt; 0>
                              <a href="#" onclick="unmarkerAnswer('${answer.qid}','${answer.aid}')" >移除标记</a>: ${answer.marker}
                            <#else>
                              <select id="marker_${answer.aid}" class="input-small">
                                <option value="">请选择</option>
                                <option value="1">答得好</option>
                              </select>
                              <a href="#" onclick="markerAnswer('${answer.qid}','${answer.aid}')" >标记</a>
                            </#if>

                        </td>
                        <td><a href="/search/answerreply/?aid=${answer.aid}">查看继续问答</a></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">

  function markerAnswer(qid,aid){

    var marker = $.trim($('#marker_'+ aid).val());

    if(marker >0) {
    $.post('/answer/marker/',
        { _method:'PUT',qid:qid, aid: aid, marker: marker },
        function(data){
          if('true' == data){
            alert('标记成功');
            window.location.reload();
          }else{
            alert('标记失败');
          }
        });
    }
    else{
      alert("请先选择");
    }
  }

  function unmarkerAnswer(qid,aid){

    $.post('/answer/marker/',
        { _method:'DELETE',qid:qid, aid: aid },
        function(data){
          if('true' == data){
            alert('移除标记成功');
            window.location.reload();
          }else{
            alert('移除失败');
          }
        });
  }


    function removeAnswer(qid,aid,uid){
        if(!confirm("确定删除吗？")){
            return;
        }
        var url= '/search/answers/delete/?qid='+qid+'&aid='+aid+'&uid='+uid ;

        window.location.href=url;
    }
</script
<#include "/commons/footer.ftl" />
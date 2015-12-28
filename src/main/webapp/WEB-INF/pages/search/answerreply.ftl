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
                        <th>答案详情</th>
                        <th>状态</th>
                        <th >操作<img style="float:right" id="enlarge" src="/static/img/enlarge.png"></th>

                    </tr>
                    </thead>
                    <tbody>
                    <#list answerReplyList as answerreply>
                    <tr>
                        <td>${answerreply.content}</td>
                        <td>${answerreply.status}</td>
                        <td>
                            <#if answerreply.status=='Normal'>
                                <a href="#" onclick="remove('${answerreply.arid}','${answerreply.aid}')" >删除</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">

    function remove(arid,aid){
        if(!confirm("确定删除吗？")){
            return;
        }
        var url= '/search/answerreply/delete/?aid='+aid+'&arid='+arid ;

        window.location.href=url;
    }
</script >
<#include "/commons/footer.ftl" />
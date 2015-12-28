<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container">
    <form class="well form-inline">
      <input id="author" type="text" class="input-small input-large" placeholder="作者">
      <input id="quotation" type="text" class="input-small input-xlarge" style="width:500px;" placeholder="语录">
      <button id="btn-create" type="button" class="btn btn-primary"><i class="icon-pencil icon-white"></i>来一条?</button>
    </form>

    <div class="page-header"><h1><small>水滴石穿</small></h1></div>

    <div class="container-fluid">
        <table class="table table-striped table-bordered table-condensed">
            <thead>
                <tr><th>作者</th><th>语录</th><th style="width:100px;">时间</th></tr>
            </thead>
            <tbody id="quotation-body">
                <#list quotations as q>
                <tr>
                    <td>${q.author}</td>
                    <td>${q.quotation}</td>
                    <td class="date">${q.createdAt?date}<i qid="${q.id}" class="icon-remove" style="float:right;display:none;"></i></td>
                </tr>
                </#list>               
            </tbody>
        </table>
        <div class="pagination pagination-centered">
          <ul>
            <#if (t>1&&p>1)><li><a id="pre" href="/quotation/?p=${p-1}">前一页</a></li></#if>
            <#list 1..t as x>
                <li <#if (x==p)>class="active"</#if>>
                    <a href="/quotation/?p=${x}">${x}</a>
                </li>
            </#list>
            <#if (t>1&&p<t)><li><a id="next" href="/quotation/?p=${p+1}">后一页</a></li></#if>
          </ul>
        </div>        
    </div>
</div>

<script type="text/javascript">
    function delete_one(){
        var qid = $(this).attr("qid");
        var tr = $(this).parent().parent();
        $.post("/quotation/",{
            _method:"DELETE",id:qid
        },function(data){
            if("true" == data){
                tr.remove();
            }else{
                alert("删除失败o(╯□╰)o");
            }
        });
    }

    function subscribe_click(target){
        target.mouseleave(function(){
            $(this).find("i.icon-remove").hide();
        }).mouseenter(function(){
            $(this).find("i.icon-remove").show();
        });
    }
    function create_one(){
        var author = $("#author").val();
        var quotation = $("#quotation").val();
        if(author.length>1 && quotation.length>6){
            $.post("/quotation/",
                {_method:"PUT",author:author,quotation:quotation},
                function(data){
                    if($.isNumeric(data) && parseInt(data) > 0){
                        var id = parseInt(data);
                        var line = '<tr><td>'+author+'</td><td>'+quotation+'</td><td>刚刚<i qid="'+id+'" class="icon-remove" style="float:right;display:none;"></i></td></tr>';
                        $("#quotation-body").append(line);
                        //$("#author").val("");
                        $("#quotation").val("");
                        $("i.icon-remove").last().click(delete_one);
                        subscribe_click($("i.icon-remove").last().parent());
                    }
                }
            );
        }
    }
    $("#btn-create").click(create_one);
    subscribe_click($("td.date"));

    $("i.icon-remove").click(delete_one);

    $("body").keydown(function(event){
        if(event.keyCode==37){
            <#if (t>1&&p>1)>window.location.href="/quotation/?p=${p-1}";</#if>
        }
        if(event.keyCode==39){
            <#if (t>1&&p<t)>window.location.href="/quotation/?p=${p+1}";</#if>
        }
    });
</script>
 
        
<#include "commons/footer.ftl" />
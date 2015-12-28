<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container" xmlns="http://www.w3.org/1999/html">
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />
            <div class="span10">
                <form class="well form-inline" action="/comment/" method="GET">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="cid">cid</option>

                    </select>
                    <input name="cid" type="text" class="input-medium search-query">
                  评论的发布人：<input name="uid" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询</button>
                </form>
                <legend>评论信息</legend>


                <fieldset>

                    <input id="cid" value="${comment.cid}" style="display: none"/>
                  <input id="uid" value="${comment.uid}" style="display: none"/>

                    <div class="control-group">
                        <label class="control-label">
                            <#if comment.cid &gt; 0 >
                            内容：${comment.comment}
                                <br/>
                            作者: ${user.nick}
                                <br/>
                                <br/>
                                <span id="btn-status" class="btn btn-danger" >删除</span>  (无法恢复!!)
                                <input id="status" value="Deleted" style="display: none"/>
                            <#else>
                                没有找到对应评论!
                            </#if>
                        </label>
                    </div>

                    <div class="controls">

                    </div>


                </fieldset>

            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
    $("#btn-status").click(set_status);

    function set_status() {

        var cid = $("#cid").val();
        var uid=$("#uid").val();
        var status = $("#status").val();

        do_execute_status(cid, uid,status);
    }

    function do_execute_status(cid,uid, status) {

        $.post(
                "/comment/" + cid + "/" + status + "/?uid="+uid,

                function () {
                    if(status=='Deleted') {
                        alert("已经删除");
                        location.href = "/search/comments/";
                    }
                }
        );
    }


</script>
<#include "commons/footer.ftl" />
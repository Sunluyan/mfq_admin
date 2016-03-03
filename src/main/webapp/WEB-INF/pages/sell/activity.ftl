<#include "commons/header.ftl" />
<#assign toolbar="items" />
<#include "commons/toolbar.ftl" />

<div class="container" id="enlarge-body">
    <div class="container">

        <form class="form-inline well" action="/sell/activity/" method="POST">
            <div class="form-group">
                <label for="proName">活动名称：</label>
                <input type="text" class="form-control" id="activityName" name="activityName" value="${proname}"
                       size="20">
                <label for="hosName">医院名称：</label>
                <input type="text" class="form-control" id="hosName" name="hosname" value="${hosname}" size="20">
                <br/><br/>
                <label for="hosName">排序方式：</label>
                <select id="orderby" name="orderby" class="orderby select-group" style="width:100px;">
                    <option value="id desc" <#if orderby == "id desc">selected=selected</#if>>无</option>
                    <option value="price desc"   <#if orderby == "price desc">selected=selected</#if>>价格</option>
                    <option value="created_at desc"  <#if orderby == "created_at desc">selected=selected</#if>>时间
                    </option>
                    <option value="tid desc"  <#if orderby == "tid desc">selected=selected</#if>>分类</option>
                </select>

                <label for="hosName">是否上线：</label>
                <select id="online" name="online" class="online select-group" style="width:100px;">
                    <option value="motherfucker" <#if online == "motherfucker">selected=selected</#if>>无</option>
                    <option value="true"   <#if online == "true">selected=selected</#if>>上线</option>
                    <option value="false"  <#if online == "false">selected=selected</#if>>下线</option>
                </select>


            </div>

            <div class="input-group">
                <td colspan="4" style="text-align: center;"><input type="submit" class="btn btn-info btn-sm" value="查询">
            </div>

            <input type="hidden" name="page" value="${page}" class="page">
        </form>
        <script>

            $(function () {
                var $page = $(".page")

                var maxPage = Math.ceil(${itemcount} / 50)
                )
                ;

                if (maxPage <= $page.val()) {
                    $(".next").hide()
                }
                $(".prev").click(function () {
                    $page.val(parseInt($page.val()) - 1);
                    $(".form-inline").submit();
                })
                $(".next").click(function () {
                    $page.val(parseInt($page.val()) + 1);
                    if (parseInt($page.val()) > maxPage) {
                        return false;
                    }
                    $(".form-inline").submit();
                })
            })
        </script>


        <div class="row-fluid">

            <div>

                <ul class="pager" style="float:right;">
                <#if page &gt; 1>
                    <li>
                        <a href="javascript:void(0)" class="prev">前一页</a>
                    </li>
                </#if>
                    <li>
                        <a href="javascript:void(0)" class="next">后一页</a>
                    </li>
                    <li><span>总数:${itemcount}</span></li>
                    <a href="/sell/activity/add/" target="_blank">增加活动</a>
                </ul>
            </div>
            <div>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>活动名称</th>
                        <th>类型</th>
                        <th>链接</th>
                        <th>有效日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list items as item>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<#include "commons/footer.ftl" />

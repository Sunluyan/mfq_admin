<#include "commons/header.ftl" />
<#assign toolbar="search" />
<#include "commons/toolbar.ftl" />


<div class="container" >
    <div class="container-fluid">

        <div class="row-fluid">
        <#assign tab="${item}" />
        <#include "/search/tabs.ftl" />

        <div class="span10">
            <#if msg??>
            <div class="well">${msg}</div>
            </#if>
                <form class="well form-inline" method="POST">
                    <select id="type-select" name="field" class="select input-medium">
                        <option value="uid">uid</option>
                        <option value="nick">用户名(nick)</option>
                        <option value="email">Email</option>
                        <option value="mobile">Mobile</option>
                    </select>
                    <input name="value" type="text" class="input-medium search-query">
                    <button type="submit" class="btn">查询</button>
                </form>

        </div>

        </div>
    </div>
</div>


<#include "commons/footer.ftl" />
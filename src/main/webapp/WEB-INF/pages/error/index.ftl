<#include "commons/header.ftl" />
<#include "commons/toolbar.ftl" />

<div class="container">
    <div class="well">
        <h2>${msg!"发生系统错误。"}</h2>
        <span>${Request['javax.servlet.error.message']}</span>
    </div>
</div>


<#include "commons/footer.ftl" />